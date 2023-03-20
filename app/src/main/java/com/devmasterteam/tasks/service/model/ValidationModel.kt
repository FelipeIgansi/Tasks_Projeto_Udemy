package com.devmasterteam.tasks.service.model

class ValidationModel(private val message: String = "") {
    fun status() = message == ""
    fun message() = message
}