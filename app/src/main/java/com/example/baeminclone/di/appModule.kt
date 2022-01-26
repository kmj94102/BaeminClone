package com.example.baeminclone.di

import com.example.baeminclone.ui.MainViewModel
import com.example.baeminclone.ui.home.HomeViewModel
import com.example.baeminclone.ui.maim.DeliveryViewModel
import kotlinx.coroutines.Dispatchers
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

internal val appModule = module {

    single { Dispatchers.Main }
    single { Dispatchers.IO }

//    viewModel { HomeViewModel() }
    viewModel { MainViewModel() }
    viewModel { DeliveryViewModel() }

}