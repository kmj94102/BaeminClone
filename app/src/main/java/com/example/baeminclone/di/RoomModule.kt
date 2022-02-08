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

@Module
@InstallIn(SingletonComponent::class)
object RoomModule {

    @Provides
    @Singleton
    fun provideRoomDatabase(
        @ApplicationContext context: Context
    ) : BaeminDatabase =
        Room.databaseBuilder(context, BaeminDatabase::class.java, BaeminDatabase.DATABASE_NAME).build()

    @Provides
    @Singleton
    fun provideAddressDao(
        database: BaeminDatabase
    ) : AddressDao =
        database.addressDao()

}