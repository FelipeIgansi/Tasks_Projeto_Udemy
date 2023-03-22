package com.devmasterteam.tasks.service.repository

import android.content.Context
import com.devmasterteam.tasks.R
import com.devmasterteam.tasks.service.constants.TaskConstants
import com.devmasterteam.tasks.service.listener.APIListener
import com.google.gson.Gson
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

open class BaseRepository(val context: Context) {
    private fun failResponse(str: String): String {
        return Gson().fromJson(str, String::class.java)
    }

    fun <G> executeCall(call: Call<G>, listner: APIListener<G>) {
        call.enqueue(object : Callback<G> {
            override fun onResponse(call: Call<G>, response: Response<G>) {
                if (response.code() == TaskConstants.HTTP.SUCCESS) {
                    response.body()?.let { listner.onSuccess(it) }
                } else {
                    listner.onFail(failResponse(response.errorBody()!!.string()))
                }
            }

            override fun onFailure(call: Call<G>, t: Throwable) {
                listner.onFail(context.getString(R.string.ERROR_UNEXPECTED))
            }
        })
    }
}