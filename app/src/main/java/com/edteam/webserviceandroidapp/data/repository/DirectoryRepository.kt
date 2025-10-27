package com.edteam.webserviceandroidapp.data.repository

import com.edteam.webserviceandroidapp.data.Developer
import com.edteam.webserviceandroidapp.data.DeveloperRequest
import com.edteam.webserviceandroidapp.data.DeveloperResponse
import com.edteam.webserviceandroidapp.core.Result

interface DirectoryRepository {
    suspend fun getDirectory(): Result<List<Developer>>
    suspend fun deleteDeveloper(id: Int) : Result<String>

    suspend fun createDeveloper(request: DeveloperRequest) : Result<DeveloperRequest?>

    suspend fun getDeveloperById(developerId: Int): Result<Developer?>

    suspend fun updateDeveloper(developer: DeveloperRequest): Result<DeveloperRequest?>
}