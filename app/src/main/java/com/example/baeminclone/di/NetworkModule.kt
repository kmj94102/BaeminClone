package com.example.baeminclone.di

import com.example.baeminclone.BuildConfig
import com.example.baeminclone.network.AddressClient
import com.example.baeminclone.network.AddressService
import com.example.baeminclone.util.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideOkHttpClient() : OkHttpClient =
        OkHttpClient.Builder()
            .readTimeout(10, TimeUnit.SECONDS)
            .connectTimeout(10, TimeUnit.SECONDS)
            .writeTimeout(15, TimeUnit.SECONDS)
            .addInterceptor(getLoggingInterceptor())
            .build()

    @Provides
    @Singleton
    fun provideGsonConvertFactory() : GsonConverterFactory = GsonConverterFactory.create()

    @Provides
    @Singleton
    fun provideRetrofit(
        okHttpClient: OkHttpClient,
        gsonConverterFactory: GsonConverterFactory
    ) : Retrofit =
        Retrofit.Builder()
            .baseUrl(Constants.JUSO_API_BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(gsonConverterFactory)
            .build()

    private fun getLoggingInterceptor() : HttpLoggingInterceptor =
        HttpLoggingInterceptor().apply {
            level = if(BuildConfig.DEBUG) {
                HttpLoggingInterceptor.Level.BODY
            } else {
                HttpLoggingInterceptor.Level.NONE
            }
        }

    @Provides
    @Singleton
    fun provideAddressService(retrofit: Retrofit) : AddressService =
        retrofit.create(AddressService::class.java)

    @Provides
    @Singleton
    fun provideAddressClient(addressService: AddressService) : AddressClient =
        AddressClient(addressService)

}


// https://asuhdevstory.tistory.com/entry/Clean-Architecture-MVVM-Coroutine-Hilt-SafeApiCall-%EB%A5%BC-%EC%9D%B4%EC%9A%A9%ED%95%98%EC%97%AC-Github-API%EB%A5%BC-%ED%98%B8%EC%B6%9C%ED%95%B4-%EB%B3%B4%EC%9E%90
