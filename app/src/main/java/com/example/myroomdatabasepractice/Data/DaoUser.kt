package com.example.myroomdatabasepractice.Data

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface DaoUser {
    @Insert
    suspend fun insertuser(todo : User)
    @Update
    suspend fun updateuser(todo : User)
    @Delete
    suspend fun deleteuser(todo : User)

    @Query("SELECT * FROM todo_table")
    fun getUser() : LiveData<List<User>>

}