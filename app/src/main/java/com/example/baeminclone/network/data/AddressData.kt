package com.example.baeminclone.util

import com.example.baeminclone.BuildConfig

data class AddressParam(
    val confmKey : String? = BuildConfig.JUSO_API_KEY,
    val currentPage : Int? = 1,
    val countPerPage : Int? = 10,
    val resultType : String? = "json",
    val firstSort : String? = "road", // 정확도순 정렬(none), 우선정렬(road: 도로명 포함, location: 지번 포함)
    val keyword : String
)

data class AddressResult(
    val results : AddressData
)

data class AddressData(
    val common : AddressCommon,
    val juso : List<Juso>
)

data class AddressCommon(
    val totalCount : String?,
    val currentPage : String?,
    val countPerPage : Int?,
    val errorCode : String?,
    val errorMessage : String?
)

data class Juso(
    val roadAddr : String?
)

