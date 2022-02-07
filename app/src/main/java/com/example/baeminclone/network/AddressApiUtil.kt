package com.example.baeminclone.network

import com.example.baeminclone.BuildConfig
import com.example.baeminclone.util.Constants
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object AddressApiUtil {

    val ADDRESS_API : AddressService by lazy { getAddressRetrofit().create(AddressService::class.java) }

    fun getAddressRetrofit() : Retrofit =
        Retrofit.Builder()
            .baseUrl(Constants.JUSO_API_BASE_URL)
            .addConverterFactory(
                GsonConverterFactory.create()
            )
            .client(buildOkHttpClient())
            .build()


    private fun buildOkHttpClient() : OkHttpClient {
        val interceptor = HttpLoggingInterceptor()

        if (BuildConfig.DEBUG) {
            interceptor.level = HttpLoggingInterceptor.Level.BODY
        } else {
            interceptor.level = HttpLoggingInterceptor.Level.NONE
        }

        return OkHttpClient.Builder()
            .connectTimeout(5, TimeUnit.SECONDS)
            .addInterceptor(interceptor)
            .build()
    }

}