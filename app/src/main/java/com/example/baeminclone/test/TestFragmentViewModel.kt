package com.example.baeminclone.test

import androidx.lifecycle.ViewModel
import com.example.baeminclone.MutableEventFlow

class TestFragmentViewModel : ViewModel() {

    private val _eventFlow = MutableEventFlow<Event>()

    sealed class Event {
        
    }

}