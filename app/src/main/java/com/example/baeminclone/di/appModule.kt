package com.example.baeminclone.di

import com.example.baeminclone.test.TestFragmentViewModel
import com.example.baeminclone.test.TestViewModel
import kotlinx.coroutines.Dispatchers
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

internal val appModule = module {

    single { Dispatchers.Main }
    single { Dispatchers.IO }

    viewModel { TestViewModel() }
    viewModel { TestFragmentViewModel() }

}