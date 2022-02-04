package com.example.baeminclone.di

import com.example.baeminclone.repository.AddressRepository
import com.example.baeminclone.repository.AddressRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Singleton
    @Binds
    abstract fun bindAddressRepository(repository : AddressRepositoryImpl) : AddressRepository
}