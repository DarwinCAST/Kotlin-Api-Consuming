package com.edteam.webserviceandroidapp.data

data class DirectoryResponse(
    val success: Boolean,
    val message: String,
    val data: List<Developer>,
)

data class Developer(val id: Int, val lastName: String, val names: String, val specialty: String)

data class CreateDeveloperResponse(
    val success: String,
    val message: String,
    val data: DeveloperRequest
)


data class DeleteDeveloperResponse(val success: Boolean, val message: String, val data: String)