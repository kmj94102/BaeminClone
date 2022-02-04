package com.example.baeminclone.repository

import com.example.baeminclone.database.dao.AddressDao
import com.example.baeminclone.database.entity.AddressEntity
import com.example.baeminclone.di.IoDispatcher
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class AddressRepositoryImpl @Inject constructor(
    private val addressDao: AddressDao,
    @IoDispatcher private val dispatcher: CoroutineDispatcher
) : AddressRepository {

    override suspend fun insertAddress(addressEntity: AddressEntity) = withContext(dispatcher) {
        addressDao.insertAddress(addressEntity)
    }

    override suspend fun selectAllAddress(): List<AddressEntity> = withContext(dispatcher) {
        addressDao.selectAllAddress()
    }
}