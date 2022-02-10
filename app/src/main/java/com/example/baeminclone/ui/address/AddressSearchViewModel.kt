package com.example.baeminclone.ui.address

import androidx.databinding.ObservableBoolean
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.baeminclone.MutableEventFlow
import com.example.baeminclone.asEventFlow
import com.example.baeminclone.network.AddressApiUtil
import com.example.baeminclone.repository.AddressRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddressSearchViewModel @Inject constructor(
    private val addressRepository: AddressRepository
) : ViewModel() {
    private val _eventFlow = MutableEventFlow<AddressSearchEvent>()
    val eventFlow = _eventFlow.asEventFlow()

    private var currentPage = 1
    private var currentKeyword = ""
    var isLoading : ObservableBoolean = ObservableBoolean(false)

    fun getAddressList(keyword: String, isSearch: Boolean) = viewModelScope.launch {
        isLoading.set(true)

        if (isSearch) {
            currentPage = 1
            currentKeyword = keyword
        }

        addressRepository.getAddressList(keyword = currentKeyword, currentPage = currentPage)?.let {
            if (it.results.common.errorCode == "0" && it.results.juso != null) {
                val list = it.results.juso.mapNotNull { addressList ->  addressList.roadAddr }
                val isMoreData = it.results.common.totalCount?.toInt() ?: 0 > (it.results.common.countPerPage ?: 10) * currentPage
                event(AddressSearchEvent.AddressList(list, isMoreData))
            } else {
                event(AddressSearchEvent.Error)
            }

            isLoading.set(false)
            currentPage++
        }

    }

    fun event(event: AddressSearchEvent) {
        viewModelScope.launch {
            _eventFlow.emit(event)
        }
    }

    sealed class AddressSearchEvent {
        data class AddressList(val list : List<String>, val isMoreData : Boolean) : AddressSearchEvent()
        object Error : AddressSearchEvent()
    }
}