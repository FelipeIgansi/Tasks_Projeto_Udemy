package com.devmasterteam.tasks.service.repository

import android.content.Context
import com.devmasterteam.tasks.R
import com.devmasterteam.tasks.service.listener.APIListener
import com.devmasterteam.tasks.service.model.PriorityModel
import com.devmasterteam.tasks.service.repository.local.TaskDatabase
import com.devmasterteam.tasks.service.repository.remote.PriorityService
import com.devmasterteam.tasks.service.repository.remote.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PriorityRepository(val context: Context) : BaseRepository() {

    private val remote = RetrofitClient.getService(PriorityService::class.java)
    private val database = TaskDatabase.getDatabase(context).priorityDAO()

    // Para usar com API
    fun list(listner: APIListener<List<PriorityModel>>) {
        val call = remote.list()
        call.enqueue(object : Callback<List<PriorityModel>> {
            override fun onResponse(
                call: Call<List<PriorityModel>>,
                response: Response<List<PriorityModel>>
            ) {
                handleResponse(response, listner)
            }

            override fun onFailure(call: Call<List<PriorityModel>>, t: Throwable) {
                val s = ""
                listner.onFail(context.getString(R.string.ERROR_UNEXPECTED))
            }

        })
    }

    // Para usar com o Banco
    fun list(): List<PriorityModel> {
        return database.list()
    }

    /** Cache:
     * Basicamente ele vai guardar a informação de algo que ele já tenha "visto"
     * Assim se o código já vai saber que o significado de um valor e não precisará
     * passar por ele mais vezes
     * */

    companion object{
        private val cache = mutableMapOf<Int, String >()
        fun getDescription(id: Int):String{
            return cache[id] ?: ""
        }
        fun setDescription(id:Int, str:String){
            cache[id] = str
        }
    }
    fun getDescription(id:Int):String{
        val cached = PriorityRepository.getDescription(id)
        return if (cached == ""){
            val description = database.getDescription(id)
            setDescription(id, description)
            description
        } else cached

    }

    fun save(list: List<PriorityModel>) {
        database.clear()
        database.save(list)

    }
}