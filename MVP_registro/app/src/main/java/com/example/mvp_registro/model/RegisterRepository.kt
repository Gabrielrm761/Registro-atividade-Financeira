package com.example.mvp_registro.model

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class RegisterRepository(private val dao: RegisterDao){

    fun insert(register: Register) = runBlocking {
        launch (Dispatchers.IO)  {
            dao.insert(register)
        }
    }

    fun getAll() = dao.getAll()

}