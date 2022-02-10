package com.example.baeminclone.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(indices = [Index(value = ["address", "alias", "type"], unique = true)])
data class AddressEntity(
    @PrimaryKey(autoGenerate = true) val id : Long = 0,
    @ColumnInfo(name = "address") val address : String,
    @ColumnInfo(name = "alias") val alias : String,
    @ColumnInfo(name = "type") val type : Int,
    @ColumnInfo(name = "status") val status : Boolean = false
)