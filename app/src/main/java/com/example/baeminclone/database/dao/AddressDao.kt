package com.example.baeminclone.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.baeminclone.database.entity.AddressEntity

@Dao
interface AddressDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertAddress(addressEntity: AddressEntity)

    @Query("SELECT * FROM AddressEntity")
    suspend fun selectAllAddress() : List<AddressEntity>

}