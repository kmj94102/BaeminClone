package com.example.baeminclone.ui.address

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.baeminclone.MutableEventFlow
import com.example.baeminclone.asEventFlow
import com.example.baeminclone.di.DBModule
import com.example.baeminclone.di.RepositoryModule
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddressRegisterViewModel @Inject constructor(private val repository : RepositoryModule) : ViewModel(){

    private val _eventFlow = MutableEventFlow<AddressRegisterEvent>()
    val eventFlow = _eventFlow.asEventFlow()

    fun insertAddress() = viewModelScope.launch {
        repository
    }

    sealed class AddressRegisterEvent {
        object AddressInsert : AddressRegisterEvent()
        object Error : AddressRegisterEvent()
    }

}