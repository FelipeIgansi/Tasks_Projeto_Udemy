package com.devmasterteam.tasks.service.repository

import android.content.Context
import com.devmasterteam.tasks.R
import com.devmasterteam.tasks.service.constants.TaskConstants
import com.devmasterteam.tasks.service.listener.APIListener
import com.devmasterteam.tasks.service.model.PriorityModel
import com.devmasterteam.tasks.service.repository.remote.PriorityService
import com.devmasterteam.tasks.service.repository.remote.RetrofitClient
import com.google.gson.Gson
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PriorityRepository(val context : Context) {

    private val remote = RetrofitClient.getService(PriorityService::class.java)
    fun list(listner: APIListener<List<PriorityModel>>){
        val call = remote.list()
        call.enqueue(object  : Callback<List<PriorityModel>>{
            override fun onResponse(call: Call<List<PriorityModel>>, response: Response<List<PriorityModel>>) {
                if (response.code() == TaskConstants.HTTP.SUCCESS) {
                    response.body()?.let { listner.onSuccess(it) }
                } else {
                    listner.onFail(failResponse(response.errorBody()!!.string()))
                }
            }

            override fun onFailure(call: Call<List<PriorityModel>>, t: Throwable) {
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