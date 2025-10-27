package com.edteam.webserviceandroidapp.presentation.register

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.edteam.webserviceandroidapp.core.Result
import com.edteam.webserviceandroidapp.data.Developer
import com.edteam.webserviceandroidapp.data.DeveloperRequest
import com.edteam.webserviceandroidapp.data.repository.DirectoryRepostoryImp
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class RegisterDeveloperViewModel : ViewModel() {

    var state = mutableStateOf(RegisterDeveloperState())
        private set

    val repository = DirectoryRepostoryImp()

    fun createDeveloper(request: DeveloperRequest) {
        viewModelScope.launch {
            state.value = state.value.copy(isLoading = true)
            try {
                val response = withContext(Dispatchers.IO) {
                    repository.createDeveloper(request)
                }

                when(response){
                    is Result.Error<*> -> {
                        state.value = state.value.copy(error = response.message)
                    }
                    is Result.Success<*> -> {
                        state.value = state.value.copy(successful = response.data)
                    }
                }
            } catch (e: Exception) {
                state.value = state.value.copy(error = e.message, isLoading = false)
            } finally {
                state.value = state.value.copy(isLoading = false, error = null)
            }
        }
    }

    fun getDeveloperById(developerId: Int) {
        viewModelScope.launch {
            state.value = state.value.copy(isLoading = true)
            try {
                val response = withContext(Dispatchers.IO) {
                    repository.getDeveloperById(developerId)
                }

                when(response){
                    is Result.Error<*> -> {
                        state.value = state.value.copy(error = response.message)

                    }
                    is Result.Success<*> -> {
                        state.value = state.value.copy(data = response.data)

                    }
                }

            } catch (e: Exception) {
                state.value = state.value.copy(error = e.message, isLoading = false)
            } finally {
                state.value = state.value.copy(isLoading = false, error = null)
            }
        }
    }

    fun updateDeveloper(developer: DeveloperRequest) {
        viewModelScope.launch {
            state.value = state.value.copy(isLoading = true)

            try {
                val response = withContext(Dispatchers.IO) {
                    repository.updateDeveloper(developer)
                }
                when (response) {
                    is Result.Error<*> -> {
                        state.value = state.value.copy(error = response.message)

                    }
                    is Result.Success<*> -> {
                        state.value = state.value.copy(successful = response.data)

                    }
                }
            } catch (e: Exception) {
                state.value = state.value.copy(error = e.message, isLoading = false)
            } finally {
                state.value = state.value.copy(isLoading = false)

            }
        }
    }

}


data class RegisterDeveloperState(
    val isLoading: Boolean = false,
    val error: String? = null,
    val successful: DeveloperRequest? = null,
    val data: Developer? = null

)