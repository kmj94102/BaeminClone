package com.example.baeminclone.repository

import com.example.baeminclone.database.entity.AddressEntity

interface  AddressRepository {

    suspend fun insertAddress(addressEntity: AddressEntity)

    suspend fun selectAllAddress() : List<AddressEntity>

}