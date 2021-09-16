package com.example.mvp_registro.model

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface RegisterDao {

    @Query("SELECT * FROM Register") // Mapeia a entidade como uma tabela
    fun getAll() : LiveData<List<Register>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(register: Register)
}