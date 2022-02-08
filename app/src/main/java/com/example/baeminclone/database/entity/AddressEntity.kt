package com.example.baeminclone.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class AddressEntity(
    @PrimaryKey(autoGenerate = true) val id : Long = 0,
    val address : String,
    val alias : String,
    val type : Int,
    val status : Boolean = false
)