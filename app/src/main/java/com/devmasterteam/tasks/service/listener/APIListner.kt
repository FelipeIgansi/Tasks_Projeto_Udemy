package com.devmasterteam.tasks.service.listener

import com.devmasterteam.tasks.service.model.PersonModel

interface APIListner<T> {
    fun onSuccess(result: PersonModel)
    fun onFail(message : String)
}