package com.example.baeminclone.ui.dialog.data

import androidx.databinding.ObservableBoolean

data class SelectOne(
    val text : String,
    var isSelect : ObservableBoolean = ObservableBoolean(false)
)