package com.edteam.webserviceandroidapp.presentation.list

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
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
                state.value = state.value.copy(directory = response)
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

                state.value = state.value.copy(deleteSuccess = response)
            }catch (e: Exception){
                state.value = state.value.copy(error = e.message)
            } finally {
                state.value = state.value.copy(isLoading = false)
            }
        }
    }

    fun clearDeleteState(){
        state.value = state.value.copy(deleteSuccess = null)
    }
}

data class DirectoryState(
    val isLoading: Boolean = false,
    val directory: List<Developer>? = null,
    val error: String? = null,
    val deleteSuccess: String? = null
)