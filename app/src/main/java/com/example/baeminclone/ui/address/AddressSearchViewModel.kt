package com.example.baeminclone.ui.address

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.baeminclone.MutableEventFlow
import com.example.baeminclone.asEventFlow
import com.example.baeminclone.network.AddressApiUtil
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddressSearchViewModel @Inject constructor() : ViewModel() {
    private val _eventFlow = MutableEventFlow<AddressSearchEvent>()
    val eventFlow = _eventFlow.asEventFlow()
    private var currentPage = 1
    private var currentKeyword = ""

    fun getAddressList(keyword: String, isSearch : Boolean) {
        viewModelScope.launch {
            if (isSearch) {
                currentPage = 1
                currentKeyword = keyword
            }

            AddressApiUtil.ADDRESS_API.getAddress(keyword = currentKeyword, currentPage = currentPage).body()?.apply {
                val list = results.juso.mapNotNull { it.roadAddr }
                if (results.common.errorCode == "0") {
                    val isMoreData = results.common.totalCount?.toInt() ?: 0 > (results.common.countPerPage ?: 10) * currentPage
                    event(AddressSearchEvent.AddressList(list, isMoreData))
                } else {
                    event(AddressSearchEvent.Error)
                }

                currentPage++
            }
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