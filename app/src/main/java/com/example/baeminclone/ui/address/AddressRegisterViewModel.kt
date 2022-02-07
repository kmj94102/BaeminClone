package com.example.baeminclone.ui.address

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.baeminclone.MutableEventFlow
import com.example.baeminclone.asEventFlow
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddressRegisterViewModel @Inject constructor() : ViewModel(){

    private val _eventFlow = MutableEventFlow<AddressRegisterEvent>()
    val eventFlow = _eventFlow.asEventFlow()

    fun insertAddress() = viewModelScope.launch {
    }

    sealed class AddressRegisterEvent {
        object AddressInsert : AddressRegisterEvent()
        object Error : AddressRegisterEvent()
    }

}