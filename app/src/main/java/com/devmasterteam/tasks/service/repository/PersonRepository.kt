package com.devmasterteam.tasks.service.repository

import android.content.Context
import com.devmasterteam.tasks.R
import com.devmasterteam.tasks.service.constants.TaskConstants
import com.devmasterteam.tasks.service.listener.APIListner
import com.devmasterteam.tasks.service.model.PersonModel
import com.devmasterteam.tasks.service.model.PriorityModel
import com.devmasterteam.tasks.service.repository.remote.PersonService
import com.devmasterteam.tasks.service.repository.remote.RetrofitClient
import com.google.gson.Gson
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PersonRepository(val context: Context) {
    private val remote = RetrofitClient.getService(PersonService::class.java)
    fun login(email: String, password: String, listner: APIListner<PriorityModel>) {
        val call = remote.login(email, password)
        call.enqueue(object : Callback<PersonModel> {
            override fun onResponse(call: Call<PersonModel>, response: Response<PersonModel>) {
                if (response.code() == TaskConstants.HTTP.SUCCESS) {
                    val s = ""
                    response.body()?.let { listner.onSuccess(it) }
                } else {
                    listner.onFail(failResponse(response.errorBody()!!.string()))
                }
            }

            override fun onFailure(call: Call<PersonModel>, t: Throwable) {
                val s = ""
                listner.onFail(context.getString(R.string.ERROR_UNEXPECTED))
            }
        })
    }

    private fun failResponse(str: String): String {
        return Gson().fromJson(str, String::class.java)
        // O que quer converter e para o que
    }
}