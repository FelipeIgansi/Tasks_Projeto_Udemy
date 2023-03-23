package com.devmasterteam.tasks.service.repository

import android.content.Context
import com.devmasterteam.tasks.R
import com.devmasterteam.tasks.service.listener.APIListener
import com.devmasterteam.tasks.service.model.TaskModel
import com.devmasterteam.tasks.service.repository.remote.RetrofitClient
import com.devmasterteam.tasks.service.repository.remote.TaskService

class TaskRepository(context: Context) : BaseRepository(context) {

    private val remote = RetrofitClient.getService(TaskService::class.java)

    //    listAll
    fun listAll(listner: APIListener<List<TaskModel>>) {
        if (!isConnectionAvaliable()){
            listner.onFail(context.getString(R.string.ERROR_INTERNET_CONNECTION))
            return
        }
        executeCall(remote.listAll(), listner)
    }

    //    listNext
    fun listNext(listner: APIListener<List<TaskModel>>) {
        if (!isConnectionAvaliable()){
            listner.onFail(context.getString(R.string.ERROR_INTERNET_CONNECTION))
            return
        }
        executeCall(remote.listNext(), listner)
    }

    //    ListOverdue
    fun listOverdue(listner: APIListener<List<TaskModel>>) {
        if (!isConnectionAvaliable()){
            listner.onFail(context.getString(R.string.ERROR_INTERNET_CONNECTION))
            return
        }
        executeCall(remote.listOverdue(), listner)
    }

    //load
    fun load(id: Int, listner: APIListener<TaskModel>) {
        if (!isConnectionAvaliable()){
            listner.onFail(context.getString(R.string.ERROR_INTERNET_CONNECTION))
            return
        }
        executeCall(remote.load(id), listner)
    }

    //Create
    fun create(task: TaskModel, listner: APIListener<Boolean>) {
        if (!isConnectionAvaliable()){
            listner.onFail(context.getString(R.string.ERROR_INTERNET_CONNECTION))
            return
        }
        val call = remote.crete(task.priorityId, task.description, task.dueDate, task.complete)
        executeCall(call, listner)
    }

    //    update
    fun update(task: TaskModel, listner: APIListener<Boolean>) {
        if (!isConnectionAvaliable()){
            listner.onFail(context.getString(R.string.ERROR_INTERNET_CONNECTION))
            return
        }
        val call =
            remote.update(task.id, task.priorityId, task.description, task.dueDate, task.complete)
        executeCall(call, listner)
    }

    //    complete
    fun complete(id: Int, listner: APIListener<Boolean>) {
        if (!isConnectionAvaliable()){
            listner.onFail(context.getString(R.string.ERROR_INTERNET_CONNECTION))
            return
        }
        val call = remote.complete(id)
        executeCall(call, listner)
    }

    //    undo
    fun undo(id: Int, listner: APIListener<Boolean>) {
        if (!isConnectionAvaliable()){
            listner.onFail(context.getString(R.string.ERROR_INTERNET_CONNECTION))
            return
        }
        val call = remote.undo(id)
        executeCall(call, listner)
    }

    //    delete
    fun delete(id: Int, listner: APIListener<Boolean>) {
        if (!isConnectionAvaliable()){
            listner.onFail(context.getString(R.string.ERROR_INTERNET_CONNECTION))
            return
        }
        val call = remote.delete(id)
        executeCall(call, listner)
    }
}