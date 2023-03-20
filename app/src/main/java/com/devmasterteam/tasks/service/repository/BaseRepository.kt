package com.devmasterteam.tasks.service.repository

import com.devmasterteam.tasks.service.constants.TaskConstants
import com.devmasterteam.tasks.service.listener.APIListener
import com.google.gson.Gson
import retrofit2.Response

open class BaseRepository {
    private fun failResponse(str: String): String {
        return Gson().fromJson(str, String::class.java)
    }

    fun <G> handleResponse(response: Response<G>, listner: APIListener<G>) {
        if (response.code() == TaskConstants.HTTP.SUCCESS) {
            response.body()?.let { listner.onSuccess(it) }
        } else {
            listner.onFail(failResponse(response.errorBody()!!.string()))
        }
    }
}