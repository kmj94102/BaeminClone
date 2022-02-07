package com.example.baeminclone.ui.dialog

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.baeminclone.MutableEventFlow
import com.example.baeminclone.asEventFlow
import com.example.baeminclone.network.AddressApiUtil
import kotlinx.coroutines.launch
import javax.inject.Inject

class AddressViewModel @Inject constructor() : ViewModel() {

    private val _eventFlow = MutableEventFlow<Event>()
    val eventFlow = _eventFlow.asEventFlow()
    private var currentPage = 1
    private var currentKeyword = ""

    fun getAddressList(keyword : String, isSearch : Boolean){
        viewModelScope.launch {
            if(isSearch) {
                currentPage = 1
                currentKeyword = keyword
            }

            AddressApiUtil.ADDRESS_API.getAddress(keyword = currentKeyword, currentPage = currentPage).body()?.apply {
                val list = results.juso!!.mapNotNull { it.roadAddr }
                if (results.common.errorCode == "0") {
                    val isMoreData = results.common.totalCount?.toInt() ?: 0 > (results.common.countPerPage ?: 10) * currentPage
                    event(Event.AddressList(list, isMoreData))
                } else {
                    event(Event.Error)
                }

                currentPage++
            }

        }
    }

    private fun event(event: Event) {
        viewModelScope.launch {
            _eventFlow.emit(event)
        }
    }

    sealed class Event {
        data class AddressList(val list : List<String>, val isMoreData : Boolean) : Event()
        object Error : Event()
    }

}