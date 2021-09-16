package com.example.mvp_registro.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.mvp_registro.model.Register
import com.example.mvp_registro.model.RegisterRepository
import java.lang.IllegalArgumentException

class MainViewModel(private val registerRepository: RegisterRepository): ViewModel() {

    fun insert(register: Register) {
        registerRepository.insert(register)
    }

    fun getAll() : LiveData<List<Register>> {
        return registerRepository.getAll()
    }

}

//Teoricamente é utilizado dessa forma por não ter injeção de dependência com algum framework
class MainViewModelFactory(private val repository: RegisterRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)){
            @Suppress("UNCHECKED_CAST")
            return MainViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}