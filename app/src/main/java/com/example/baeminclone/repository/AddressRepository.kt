package com.example.baeminclone.repository

import com.example.baeminclone.database.dao.AddressDao
import com.example.baeminclone.database.entity.AddressEntity
import com.example.baeminclone.network.AddressClient
import com.example.baeminclone.util.AddressResult
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject


class  AddressRepository @Inject constructor(
    private val addressClient: AddressClient,
    private val addressDao: AddressDao
) {

    /** 주소 조회 **/
    suspend fun getAddressList(
        keyword : String,
        currentPage : Int?
    ) : AddressResult? {
        val response = addressClient.getAddress(keyword, currentPage)

        return try {
            if (response.isSuccessful) {
                response.body()
            } else {
                null
            }
        } catch (e : Exception) {
            e.printStackTrace()
            null
        }
    }

    suspend fun getAddressListFlow(
        keyword: String,
        currentPage: Int?
    ) = flow<AddressResult?> {
        val response = addressClient.getAddress(keyword, currentPage)
        emit(response.body())
    }

    /** 주소 등록 **/
    suspend fun insertAddress(addressEntity: AddressEntity) : Long {
        val insertResult = addressDao.insertAddress(addressEntity)

        return if (insertResult > 0) {
            addressDao.updateAllStatusFromInsert().toLong()
        } else {
            0
        }
    }

    /** 등록된 주소 조회 **/
    fun selectAllAddress() : Flow<List<AddressEntity>> =
        addressDao.selectAllAddress()

    /** 등록된 주소 업데이트 **/
    suspend fun updateAddressSelect(id : Long) {
        addressDao.updateStatusFromSelect(id)
    }

}