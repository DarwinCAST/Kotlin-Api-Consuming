package com.edteam.webserviceandroidapp.presentation.register

enum class Specialty(value: String) {
    MOBILE("MOBILE"),
    FRONTEND("FRONTEND"),
    BACKEND("BACKEND");

    companion object {
        fun getAllSpecialty(): List<String> {
            return values().map {
                it.name

            }
        }
    }
}