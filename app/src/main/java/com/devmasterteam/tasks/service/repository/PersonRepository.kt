package com.devmasterteam.tasks.service.repository

import android.content.Context
import com.devmasterteam.tasks.R
import com.devmasterteam.tasks.service.listener.APIListener
import com.devmasterteam.tasks.service.model.PersonModel
import com.devmasterteam.tasks.service.repository.remote.PersonService
import com.devmasterteam.tasks.service.repository.remote.RetrofitClient

class PersonRepository(context: Context) : BaseRepository(context) {
    private val remote = RetrofitClient.getService(PersonService::class.java)
    fun login(email: String, password: String, listner: APIListener<PersonModel>) {

        if (!isConnectionAvaliable()){
            listner.onFail(context.getString(R.string.ERROR_INTERNET_CONNECTION))
            return
        }
        val call = remote.login(email, password)
        executeCall(call, listner)
    }
}