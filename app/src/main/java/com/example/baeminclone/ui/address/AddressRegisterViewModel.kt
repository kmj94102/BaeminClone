package com.example.baeminclone.ui.address

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.baeminclone.MutableEventFlow
import com.example.baeminclone.asEventFlow
import com.example.baeminclone.database.entity.AddressEntity
import com.example.baeminclone.repository.AddressRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddressRegisterViewModel @Inject constructor(
    private val addressRepository: AddressRepository
) : ViewModel(){

    private val _eventFlow = MutableEventFlow<AddressRegisterEvent>()
    val eventFlow = _eventFlow.asEventFlow()

    fun insertAddress(addressEntity : AddressEntity) = viewModelScope.launch {
        val result = addressRepository.insertAddress(addressEntity)

        if (result > 0) {
            event(AddressRegisterEvent.AddressInsert)
        } else {
            event(AddressRegisterEvent.Error)
        }
    }

    fun event(event : AddressRegisterEvent) = viewModelScope.launch {
        _eventFlow.emit(event)
    }

    sealed class AddressRegisterEvent {
        object AddressInsert : AddressRegisterEvent()
        object Error : AddressRegisterEvent()
    }

}