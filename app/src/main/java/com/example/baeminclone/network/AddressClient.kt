package com.example.baeminclone.network

import com.example.baeminclone.util.AddressResult
import retrofit2.Response
import javax.inject.Inject

class AddressClient @Inject constructor(
    private val addressService: AddressService
) {

    suspend fun getAddress(
        keyword : String,
        currentPage : Int?
    ) : Response<AddressResult> =
        addressService.getAddress(
            keyword = keyword,
            currentPage = currentPage
        )

}