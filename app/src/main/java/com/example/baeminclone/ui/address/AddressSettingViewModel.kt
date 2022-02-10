package com.example.baeminclone.ui.address

import androidx.databinding.ObservableBoolean
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.baeminclone.MutableEventFlow
import com.example.baeminclone.asEventFlow
import com.example.baeminclone.database.entity.AddressEntity
import com.example.baeminclone.repository.AddressRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddressSettingViewModel @Inject constructor(
    private val addressRepository: AddressRepository
) : ViewModel(){

    private val _eventFlow = MutableEventFlow<AddressSettingEvent>()
    val eventFlow = _eventFlow.asEventFlow()
    var isLoading : ObservableBoolean = ObservableBoolean(false)

    init {
        observeAddress()
    }

    fun getAddressList() = viewModelScope.launch {
        observeAddress()
    }

    private fun observeAddress() {
        addressRepository
            .selectAllAddress()
            .onStart {
                isLoading.set(true)
            }
            .onEach {
                event(AddressSettingEvent.AddressList(it))
                isLoading.set(false)
            }
            .onEmpty {
                event(AddressSettingEvent.Empty)
            }
            .catch {
                isLoading.set(false)
            }
            .launchIn(viewModelScope)
    }

    fun updateAddressStatus(id : Long) = viewModelScope.launch {
        addressRepository.updateAddressSelect(id)
    }

    fun event(event: AddressSettingEvent) = viewModelScope.launch {
        _eventFlow.emit(event)
    }


    sealed class AddressSettingEvent {
        data class AddressList(val list : List<AddressEntity>) : AddressSettingEvent()
        object Empty : AddressSettingEvent()
        object Error : AddressSettingEvent()
    }

}