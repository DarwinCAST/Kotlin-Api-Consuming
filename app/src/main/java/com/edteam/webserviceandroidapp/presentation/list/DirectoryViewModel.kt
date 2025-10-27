package com.edteam.webserviceandroidapp.presentation.list

import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.snapshots.SnapshotApplyResult
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.edteam.webserviceandroidapp.core.Result
import com.edteam.webserviceandroidapp.data.Developer
import com.edteam.webserviceandroidapp.data.repository.DirectoryRepostoryImp
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class DirectoryViewModel: ViewModel() {
    var state = mutableStateOf(DirectoryState())
        private set
    val directory = DirectoryRepostoryImp()

    fun getDirectory(){
        state.value = state.value.copy(isLoading = true)
        viewModelScope.launch {
            try {
                val response = withContext(Dispatchers.IO) {
                    directory.getDirectory()
                }
               when(response){
                   is Result.Error<*> -> {
                       state.value = state.value.copy(error = response.message)
                   }
                   is Result.Success<*> -> {
                       state.value = state.value.copy(directory = response.data)
                   }
               }

            }catch (e: Error){
                state.value = state.value.copy(error = e.message)
            } finally{
                state.value = state.value.copy(isLoading = false)
            }
        }
    }

    fun deleteDirectory(id: Int){
        state.value = state.value.copy(isLoading = true)
        viewModelScope.launch {
            try {
                val response = withContext(Dispatchers.IO){
                    directory.deleteDeveloper(id)
                }

                when(response){
                    is Result.Error<*> -> {
                        state.value = state.value.copy(error = response.message)
                    }
                    is Result.Success<*> -> {
                        state.value = state.value.copy(deleteSuccess = response.data)
                    }
                }

            }catch (e: Exception){
                state.value = state.value.copy(error = e.message)
            } finally {
                state.value = state.value.copy(isLoading = false)
            }
        }
    }

    fun deleteClearState(){
        state.value = state.value.copy(deleteSuccess = null)
    }

}

data class DirectoryState(
    val isLoading: Boolean = false,
    val directory: List<Developer>? = null,
    val error: String? = null,
    val deleteSuccess: String? = null
)