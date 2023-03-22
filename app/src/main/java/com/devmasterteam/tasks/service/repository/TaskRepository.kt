package com.devmasterteam.tasks.service.repository

import android.content.Context
import com.devmasterteam.tasks.service.listener.APIListener
import com.devmasterteam.tasks.service.model.TaskModel
import com.devmasterteam.tasks.service.repository.remote.RetrofitClient
import com.devmasterteam.tasks.service.repository.remote.TaskService

class TaskRepository(context: Context) : BaseRepository(context) {

    private val remote = RetrofitClient.getService(TaskService::class.java)

    //    listAll
    fun listAll(listner: APIListener<List<TaskModel>>) {
        executeCall(remote.listAll(), listner)
    }

    //    listNext
    fun listNext(listner: APIListener<List<TaskModel>>) {
        executeCall(remote.listNext(), listner)
    }

    //    ListOverdue
    fun listOverdue(listner: APIListener<List<TaskModel>>) {
        executeCall(remote.listOverdue(), listner)
    }

    //load

    fun create(task: TaskModel, listner: APIListener<Boolean>) {
        val call = remote.crete(task.priorityId, task.description, task.dueDate, task.complete)
        executeCall(call, listner)
    }

    //    update

    //    complete

    //    undo

    //    delete
    fun delete(id: Int, listner: APIListener<Boolean>) {
        val call = remote.delete(id)
        executeCall(call, listner)
    }
    fun complete(id: Int, listner: APIListener<Boolean>) {
        val call = remote.complete(id)
        executeCall(call, listner)
    }
    fun undo(id: Int, listner: APIListener<Boolean>) {
        val call = remote.undo(id)
        executeCall(call, listner)
    }
}