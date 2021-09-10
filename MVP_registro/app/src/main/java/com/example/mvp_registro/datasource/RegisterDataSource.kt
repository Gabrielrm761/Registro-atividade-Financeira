package com.example.mvp_registro.datasource

import com.example.mvp_registro.model.Register

object RegisterDataSource {
    private val list = arrayListOf<Register>()

    fun getList() = list.toList()

    fun insertRegister(register: Register) {
        if (register.id == 0){
            list.add(register.copy(id = list.size + 1))
        }
        else {
            list.remove(register)
            list.add(register)
        }
    }

    fun findById(registerId: Int) =  list.find { it.id == registerId
    }

    fun deleteTask(register: Register) {
        list.remove(register)
    }
}