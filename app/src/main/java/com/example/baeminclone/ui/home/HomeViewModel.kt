package com.example.baeminclone.ui.home

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.baeminclone.MutableEventFlow
import com.example.baeminclone.asEventFlow
import com.example.baeminclone.network.AddressApiUtil
import com.example.baeminclone.util.AddressParam
import com.example.baeminclone.util.getRandomImageList
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor() : ViewModel() {

    private val _eventFlow = MutableEventFlow<Event>()
    val eventFlow = _eventFlow.asEventFlow()

    fun getImageList() {
        val list = getRandomImageList(5)
        event(Event.ImageList(list))
    }

    fun getAddressList(){
        viewModelScope.launch {
            AddressApiUtil.ADDRESS_API.getAddress(keyword = "가능로 109번길").body()?.apply {
                Log.e("++++++", "body : $this")
                Log.e("++++", "result : ${results.common.errorCode} / ${results.common.errorMessage} / ${results.common.totalCount}")
                results.juso.forEach {
                    Log.e("+++++", "${it.roadAddr}")
                }
            }
        }
    }

    private fun event(event: Event) {
        viewModelScope.launch {
            _eventFlow.emit(event)
        }
    }

    sealed class Event {
        data class ImageList(val list : List<String>) : Event()
    }

}