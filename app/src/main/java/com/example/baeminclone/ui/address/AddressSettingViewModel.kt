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
class AddressSettingViewModel @Inject constructor(
    private val addressRepository: AddressRepository
) : ViewModel(){

    private val _eventFlow = MutableEventFlow<AddressSettingEvent>()
    val eventFlow = _eventFlow.asEventFlow()

    fun getAddressList() = viewModelScope.launch {
        val list = addressRepository.selectAllAddress()

        event(AddressSettingEvent.AddressList(list))
    }

    fun event(event: AddressSettingEvent) = viewModelScope.launch {
        _eventFlow.emit(event)
    }


    sealed class AddressSettingEvent {
        data class AddressList(val list : List<AddressEntity>) : AddressSettingEvent()
        object Error : AddressSettingEvent()
    }

}