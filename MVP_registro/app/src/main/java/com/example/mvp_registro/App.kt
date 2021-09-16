package com.example.mvp_registro

import android.app.Application
import com.example.mvp_registro.model.AppDatabase
import com.example.mvp_registro.model.RegisterRepository

class App : Application() {
    val database by lazy { AppDatabase.getDatabase(this) }
    val repository by lazy { RegisterRepository(database.registerDao()) }
}