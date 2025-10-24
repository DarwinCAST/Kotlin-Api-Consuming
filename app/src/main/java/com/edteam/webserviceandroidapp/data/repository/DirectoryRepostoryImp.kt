package com.edteam.webserviceandroidapp.data.repository

import android.util.Log
import com.edteam.webserviceandroidapp.data.Api
import com.edteam.webserviceandroidapp.data.Developer
import com.edteam.webserviceandroidapp.data.DeveloperRequest

class DirectoryRepostoryImp: DirectoryRepository {
    override suspend fun getDirectory(): List<Developer> {
        val response = Api.api.getDirectory()
        //Log.d("Api_call", response.body()?.data[0]?.names?: "")
        return response.body()?.data ?: emptyList()
    }

    override suspend fun deleteDeveloper(id: Int): String {
        val response = Api.api.deleteDeveloper(id)
        return response.body()?.message ?: "The developer not be deleted"
    }

    override suspend fun createDeveloper(request: DeveloperRequest): DeveloperRequest? {
        val response = Api.api.createDeveloper(request)
        return response.body()?.data
    }



}