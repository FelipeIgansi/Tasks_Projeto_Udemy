package com.devmasterteam.tasks.service.repository

import android.content.Context
import com.devmasterteam.tasks.service.listener.APIListener
import com.devmasterteam.tasks.service.model.PriorityModel
import com.devmasterteam.tasks.service.repository.local.TaskDatabase
import com.devmasterteam.tasks.service.repository.remote.PriorityService
import com.devmasterteam.tasks.service.repository.remote.RetrofitClient

class PriorityRepository(context: Context) : BaseRepository(context) {

    private val remote = RetrofitClient.getService(PriorityService::class.java)
    private val database = TaskDatabase.getDatabase(context).priorityDAO()

    // Para usar com API
    fun list(listner: APIListener<List<PriorityModel>>) {
        executeCall(remote.list(), listner)
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

    companion object {
        private val cache = mutableMapOf<Int, String>()
        fun getDescription(id: Int): String {
            return cache[id] ?: ""
        }

        fun setDescription(id: Int, str: String) {
            cache[id] = str
        }
    }

    fun getDescription(id: Int): String {
        val cached = PriorityRepository.getDescription(id)
        return if (cached == "") {
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