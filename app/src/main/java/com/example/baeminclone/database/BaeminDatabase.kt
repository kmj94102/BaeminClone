package com.example.baeminclone.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.baeminclone.database.dao.AddressDao
import com.example.baeminclone.database.entity.AddressEntity

@Database(
    entities = [AddressEntity::class],
    version = 1
)
abstract class BaeminDatabase : RoomDatabase() {

    abstract fun addressDao() : AddressDao

    companion object {
        const val DATABASE_NAME = "baemin_clone.db"
    }

}