package com.example.mvp_registro.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Register(
    @PrimaryKey(autoGenerate = true) val id : Int = 0,
    val description : String,
    val date : String,
    val value : Double
)
{
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Register

        if (id != other.id) return false

        return true
    }

    override fun hashCode(): Int {
        return id
    }
}

