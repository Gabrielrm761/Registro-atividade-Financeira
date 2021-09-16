package com.example.mvp_registro.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.example.mvp_registro.App
import com.example.mvp_registro.databinding.ActivityMainBinding
import com.example.mvp_registro.datasource.RegisterDataSource

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val mainViewModel: MainViewModel by viewModels {
        MainViewModelFactory((application as App).repository)
    }
    private val adapter by lazy {RegisterListAdapter()}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.recycleRegister.adapter = adapter
        //updateList()
        getAllRegister()
        insertListeners()
    }

    private fun insertListeners() {
        binding.btnAdd.setOnClickListener {
            startActivityForResult(Intent(this, AddRegisterActivity::class.java), CREATE_NEW_REGISTER)
        }

        adapter.listenerEdit = {
            val intent = Intent(this, AddRegisterActivity::class.java)
            intent.putExtra(AddRegisterActivity.REGISTER_ID, it.id)
            startActivityForResult(intent, CREATE_NEW_REGISTER)
            updateList()
        }

        adapter.listenerDelete = {
            RegisterDataSource.deleteTask(it)
            updateList()
        }
    }

    private fun getAllRegister() {
        mainViewModel.getAll().observe(this, { register ->
            adapter.submitList(register)
        })
    }

    private fun updateList() {
        val list = RegisterDataSource.getList()

        adapter.submitList(list)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == CREATE_NEW_REGISTER) {
            binding.recycleRegister.adapter= adapter
            adapter.submitList(RegisterDataSource.getList())
        }
    }

    companion object {
        private const val CREATE_NEW_REGISTER = 1000
    }
}

