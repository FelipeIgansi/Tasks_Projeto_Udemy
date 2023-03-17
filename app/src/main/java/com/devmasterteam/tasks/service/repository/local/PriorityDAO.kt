package com.devmasterteam.tasks.service.repository.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.devmasterteam.tasks.service.model.PriorityModel

@Dao
interface PriorityDAO {
    @Insert
    fun save(list: List<PriorityModel>)

    @Query("Select * from Priority")
    fun list(): List<PriorityModel>

    @Query("delete from Priority ")
    fun clear()
}