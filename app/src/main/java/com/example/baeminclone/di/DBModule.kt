package com.example.baeminclone.di

import android.content.Context
import androidx.room.Room
import com.example.baeminclone.database.BaeminDatabase
import com.example.baeminclone.database.dao.AddressDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object DBModule {

    @Singleton
    @Provides
    fun provideBaeminDatabase(
        @ApplicationContext context: Context
    ) : BaeminDatabase = Room
        .databaseBuilder(context, BaeminDatabase::class.java, BaeminDatabase.DATABASE_NAME)
        .build()

    @Singleton
    @Provides
    fun provideAddressDao(baeminDatabase: BaeminDatabase) : AddressDao = baeminDatabase.addressDao()

}