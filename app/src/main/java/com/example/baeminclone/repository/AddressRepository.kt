package com.example.baeminclone.repository

import com.example.baeminclone.network.AddressClient
import com.example.baeminclone.util.AddressResult
import kotlinx.coroutines.flow.flow
import javax.inject.Inject


class  AddressRepository @Inject constructor(
    private val addressClient: AddressClient,
) {

    suspend fun getAddressList(
        keyword : String,
        currentPage : Int?
    ) : AddressResult? {
        val response = addressClient.getAddress(keyword, currentPage)

        return if (response.isSuccessful) {
            response.body()
        } else {
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

}