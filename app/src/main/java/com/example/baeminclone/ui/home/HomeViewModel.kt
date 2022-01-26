package com.example.baeminclone.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.baeminclone.MutableEventFlow
import com.example.baeminclone.asEventFlow
import com.example.baeminclone.util.getRandomImageList
import kotlinx.coroutines.launch

class HomeViewModel : ViewModel() {

    private val _eventFlow = MutableEventFlow<Event>()
    val eventFlow = _eventFlow.asEventFlow()

    fun getImageList() {
        val list = getRandomImageList(5)
        event(Event.ImageList(list))
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