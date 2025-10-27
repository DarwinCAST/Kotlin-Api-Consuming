package com.edteam.webserviceandroidapp.data

import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

object Api {

    //URL Completa = "https://pokeapi.co/api/v2/pokemon?limit=100&offset=0"
    //URL base = ""https://pokeapi.co/"
    //URL Metodo = "api/v2/pokemon?limit=100&offset=0"

    //1. Configurar retrofit
    val baseUrl = "http://10.0.2.2:3000/"

    val retrofit = Retrofit.Builder().baseUrl(baseUrl).addConverterFactory(
        GsonConverterFactory.create()
    ).build()

    //2. Definir los metodos
    interface MethodApi {
        @GET("developer")
        suspend fun getDirectory(): Response<DirectoryResponse>

        @DELETE("developer/{developerId}")
        suspend fun deleteDeveloper(@Path("developerId") developerId: Int): Response<DeleteDeveloperResponse>

        @POST("developer")
        suspend fun createDeveloper(@Body request: DeveloperRequest): Response<CreateDeveloperResponse>

        @GET("developer/{developerId}")
        suspend fun getDeveloperById(@Path("developerId") developerId: Int): Response<DeveloperResponse>

        @PUT("developer")
        suspend fun updateDeveloper(@Body request: DeveloperRequest): Response<CreateDeveloperResponse>

    }

    //3. Exponer la clase para que se consuma
    val api = retrofit.create(MethodApi::class.java)
}