package com.edteam.webserviceandroidapp.presentation.register

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.edteam.webserviceandroidapp.data.DeveloperRequest
import com.edteam.webserviceandroidapp.data.repository.DirectoryRepostoryImp
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class RegisterDeveloperViewModel: ViewModel() {

    val state = mutableStateOf(RegisterDeveloperState())
        private set

    val repository = DirectoryRepostoryImp()

    fun createDeveloper(request: DeveloperRequest){
        viewModelScope.launch {
            state.value = state.value.copy(isLoading = true)
            try {
                val response = withContext(Dispatchers.IO){
                    repository.createDeveloper(request)
                }

                state.value = state.value.copy(successful = response)
            } catch (e: Exception){
                state.value = state.value.copy(error = e.message)
            } finally {
                state.value = state.value.copy(isLoading = false)
            }
        }
    }
}

data class RegisterDeveloperState(
    val isLoading: Boolean = false,
    val error: String? = null,
    val successful: DeveloperRequest? = null
)