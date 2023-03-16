package com.devmasterteam.tasks.service.model

class ValidationModel (val message: String= "") {
    fun status() = message == ""
    fun message() = message
}