package com.example.mvp_registro.ui

import android.app.Activity
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.mvp_registro.R
import com.example.mvp_registro.databinding.ActivityAddRegisterBinding
import com.example.mvp_registro.datasource.RegisterDataSource
import com.example.mvp_registro.extensions.format
import com.example.mvp_registro.extensions.text
import com.example.mvp_registro.model.Register
import com.google.android.material.datepicker.MaterialDatePicker
import java.util.*

class AddRegisterActivity : AppCompatActivity(){

    private lateinit var binding: ActivityAddRegisterBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_register)
        binding = ActivityAddRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (intent.hasExtra(REGISTER_ID)){
            val registerId = intent.getIntExtra(REGISTER_ID, 0)
            RegisterDataSource.findById(registerId)?.let {
                binding.tilDetail.text = it.description
                binding.tilDate.text = it.date
                binding.tilValue.text = it.value.toString()
            }
        }

        insertListeners()
    }

    private fun insertListeners() {
        binding.tilDate.editText?.setOnClickListener {
            val datePicker = MaterialDatePicker.Builder.datePicker().build()
            datePicker.addOnPositiveButtonClickListener {
                val timeZone = TimeZone.getDefault()
                val offset = timeZone.getOffset(Date().time) * -1
                binding.tilDate.text = Date(it + offset).format()
            }
            datePicker.show(supportFragmentManager, "DATE_PICKER_TAG")
        }

        binding.btnDone.setOnClickListener{
            val register = Register(
                description = binding.tilDetail.text,
                date = binding.tilDate.text,
                value = binding.tilValue.text.toDouble(),
                id = intent.getIntExtra(REGISTER_ID, 0)
            )
            RegisterDataSource.insertRegister(register)

            setResult(Activity.RESULT_OK)
            finish()
        }
    }
    companion object {
        const val  REGISTER_ID = "register_id"
    }
}