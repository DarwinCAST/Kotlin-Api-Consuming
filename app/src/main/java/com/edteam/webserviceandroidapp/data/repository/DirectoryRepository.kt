package com.edteam.webserviceandroidapp.data.repository

import com.edteam.webserviceandroidapp.data.Developer
import com.edteam.webserviceandroidapp.data.DeveloperRequest

interface DirectoryRepository {
    suspend fun getDirectory(): List<Developer>
    suspend fun deleteDeveloper(id: Int) : String

    suspend fun createDeveloper(request: DeveloperRequest) : DeveloperRequest?
}