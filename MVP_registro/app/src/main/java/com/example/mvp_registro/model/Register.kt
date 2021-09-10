package com.example.mvp_registro.model

data class Register(
    val description : String,
    val date : String,
    val value : Double,
    val id : Int = 0
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

