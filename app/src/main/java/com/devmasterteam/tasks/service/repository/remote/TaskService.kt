package com.devmasterteam.tasks.service.repository.remote

import com.devmasterteam.tasks.service.model.TaskModel
import retrofit2.Call
import retrofit2.http.*

interface TaskService {

    @GET("Task")
    fun listAll(): Call<List<TaskModel>>

    @GET("Task/Next7Days")
    fun listNext(): Call<List<TaskModel>>

    @GET("Task/Overdue")
    fun listOverdue(): Call<List<TaskModel>>

    @GET("Task/{id}")
    fun load(@Path(value = "id", encoded = true) id: Int): Call<TaskModel>

    @POST("Task")
    @FormUrlEncoded
    fun crete(
        @Field("PriorityId") priorityId: Int,
        @Field("Description") description: String,
        @Field("DueDate") dueDate: String,
        @Field("Complete") complete: Boolean
    ): Call<Boolean>

    @PUT("Task")
    @FormUrlEncoded
    fun update(
        @Field("Id") id: Int,
        @Field("PriorityId") priorityId: Int,
        @Field("Description") description: String,
        @Field("DueDate") dueDate: String,
        @Field("Complete") complete: Boolean
    ): Call<Boolean>

    @PUT("Task/Complete")
    @FormUrlEncoded
    fun complete(@Field("id") id: Int): Call<Boolean>


    @PUT("Task/Undo")
    @FormUrlEncoded
    fun undo(@Field("id") id: Int): Call<Boolean>


    // TODO Vai dar ruim aqui
    @HTTP(method = "DELETE", path = "Task", hasBody = true)
    @FormUrlEncoded
    fun delete(@Field("id") id: Int): Call<Boolean>
}