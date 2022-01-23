package com.example.baeminclone.test

import androidx.lifecycle.ViewModel
import com.example.baeminclone.MutableEventFlow

class TestViewModel : ViewModel() {

    private val _eventFlow = MutableEventFlow<Event>()

    sealed class Event {

    }

}