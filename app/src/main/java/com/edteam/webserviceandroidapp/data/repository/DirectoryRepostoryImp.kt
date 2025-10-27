package com.edteam.webserviceandroidapp.data.repository

import com.edteam.webserviceandroidapp.data.Api
import com.edteam.webserviceandroidapp.data.Developer
import com.edteam.webserviceandroidapp.data.DeveloperRequest
import com.edteam.webserviceandroidapp.core.Result
import okio.IOException
import retrofit2.HttpException

class DirectoryRepostoryImp : DirectoryRepository {
    override suspend fun getDirectory(): Result<List<Developer>> {
        return try {
            val response = Api.api.getDirectory()
            if (response.isSuccessful) {
                Result.Success(data = response.body()?.data)
            } else {
                Result.Error(message = response.message())
            }
        } catch (ex: Exception) {
            Result.Error(message = ex.message.toString())
        } catch (ex: HttpException) {
            Result.Error(message = "HTTP ERROR: ${ex.message.toString()}")
        } catch (ex: IOException) {
            Result.Error(message = "Internet error: ${ex.message.toString()}")
        }
    }

    override suspend fun deleteDeveloper(id: Int): Result<String> {
        return try {
            val response = Api.api.deleteDeveloper(id)
            if (response.isSuccessful) {
                Result.Success(data = response.body()?.message)
            } else {
                Result.Error(message = response.body()?.message ?: "The developer not be deleted")
            }
        } catch (ex: Exception) {
            Result.Error(message = ex.message.toString())
        } catch (ex: IOException) {
            Result.Error(message = "Internet Error: ${ex.message.toString()}")
        } catch (ex: HttpException) {
            Result.Error(message = "HTTP ERROR: ${ex.message.toString()}")
        }

    }

    override suspend fun createDeveloper(request: DeveloperRequest): Result<DeveloperRequest?> {

        return try {
            val response = Api.api.createDeveloper(request)
            if (response.isSuccessful) {
                Result.Success(data = response.body()?.data)
            } else {
                Result.Error(message = response.body()?.message ?: "Error creating developer")
            }
        } catch (ex: Exception) {
            Result.Error(message = ex.message.toString())
        } catch (ex: IOException) {
            Result.Error(message = "Internet Error: ${ex.message.toString()}")
        } catch (ex: HttpException) {
            Result.Error(message = "HTTP ERROR: ${ex.message.toString()}")
        }

    }

    override suspend fun getDeveloperById(developerId: Int): Result<Developer?> {
        return try {
            val response = Api.api.getDeveloperById(developerId)
            if (response.isSuccessful) {
                Result.Success(data = response.body()?.data)
            } else {
                Result.Error(message = response.message())
            }
        } catch (ex: Exception) {
            Result.Error(message = ex.message.toString())
        } catch (ex: IOException) {
            Result.Error(message = "Internet Error: ${ex.message.toString()}")
        } catch (ex: HttpException) {
            Result.Error(message = "HTTP ERROR: ${ex.message.toString()}")
        }
    }

    override suspend fun updateDeveloper(developer: DeveloperRequest): Result<DeveloperRequest?> {
        return try {
            val response = Api.api.updateDeveloper(developer)
            if(response.isSuccessful){
                Result.Success(data = response.body()?.data)
            }else{
                Result.Error(message = response.message())
            }
        }catch (ex: Exception) {
            Result.Error(message = ex.message.toString())
        } catch (ex: IOException) {
            Result.Error(message = "Internet Error: ${ex.message.toString()}")
        } catch (ex: HttpException) {
            Result.Error(message = "HTTP ERROR: ${ex.message.toString()}")
        }

    }


}