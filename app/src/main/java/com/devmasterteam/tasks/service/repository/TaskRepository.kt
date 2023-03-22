package com.devmasterteam.tasks.service.repository

import android.content.Context
import com.devmasterteam.tasks.R
import com.devmasterteam.tasks.service.listener.APIListener
import com.devmasterteam.tasks.service.model.TaskModel
import com.devmasterteam.tasks.service.repository.remote.RetrofitClient
import com.devmasterteam.tasks.service.repository.remote.TaskService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class TaskRepository(val context: Context) : BaseRepository() {

    private val remote = RetrofitClient.getService(TaskService::class.java)

    private fun list(call: Call<List<TaskModel>>, listner: APIListener<List<TaskModel>>) {
        call.enqueue(object : Callback<List<TaskModel>> {
            override fun onResponse(
                call: Call<List<TaskModel>>,
                response: Response<List<TaskModel>>
            ) {
                handleResponse(response, listner)
            }

            override fun onFailure(call: Call<List<TaskModel>>, t: Throwable) {
                listner.onFail(context.getString(R.string.ERROR_UNEXPECTED))
            }

        })
    }

    //    listAll
    fun listAll(listner: APIListener<List<TaskModel>>) {
        val call = remote.listAll()
        list(call, listner)
    }

    //    listNext
    fun listNext(listner: APIListener<List<TaskModel>>) {
        val call = remote.listNext()
        list(call, listner)
    }

    //    ListOverdue
    fun listOverdue(listner: APIListener<List<TaskModel>>) {
        val call = remote.listOverdue()
        list(call, listner)
    }

    //load

    fun create(task: TaskModel, listner: APIListener<Boolean>) {
        val call = remote.crete(task.priorityId, task.description, task.dueDate, task.complete)
        call.enqueue(object : Callback<Boolean> {
            override fun onResponse(call: Call<Boolean>, response: Response<Boolean>) {
                handleResponse(response, listner)
            }

            override fun onFailure(call: Call<Boolean>, t: Throwable) {
                listner.onFail(context.getString(R.string.ERROR_UNEXPECTED))
            }
        })
    }

    //    update

    //    complete

    //    undo

    //    delete
    fun delete (id:Int, listner: APIListener<Boolean>){
        val call = remote.delete(id)
        call.enqueue(object : Callback<Boolean>{
            override fun onResponse(call: Call<Boolean>, response: Response<Boolean>) {
                handleResponse(response, listner)
            }

            override fun onFailure(call: Call<Boolean>, t: Throwable) {
                listner.onFail(context.getString(R.string.ERROR_UNEXPECTED))
            }
        })
    }


}