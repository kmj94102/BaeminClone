package com.example.baeminclone.network

import com.example.baeminclone.util.AddressResult
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface AddressService {

    // https://www.juso.go.kr/addrlink/devAddrLinkRequestGuide.do?menu=roadApi
    @GET("addrlink/addrLinkApi.do")
    suspend fun getAddress(
        @Query("confmKey") confmKey : String? = "U01TX0FVVEgyMDIyMDEyODA4NDEwOTExMjE5MTM=",
        @Query("currentPage") currentPage : Int? = 1,
        @Query("countPerPage") countPerPage : Int? = 10,
        @Query("keyword") keyword : String,
        @Query("resultType") resultType : String? = "json",
        @Query("firstSort") firstSort : String? = "road"
    ): Response<AddressResult>

}