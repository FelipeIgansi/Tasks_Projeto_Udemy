package com.devmasterteam.tasks.service.listener

interface APIListener<T> {
    fun onSuccess(result: T)
    fun onFail(message: String)
}