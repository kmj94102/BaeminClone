package com.example.baeminclone.util

import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager

fun Int.pxToDp(context: Context) : Float {
    val density = context.resources.displayMetrics.density
    return this / density
}

/**
 * 키보드 숨김 처리
 * */
fun hideKeyBoard(context: Context, view: View){
    val imm = (context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager)
    if (imm.isActive){
        imm.hideSoftInputFromWindow(view.windowToken, 0)
        view.isFocusableInTouchMode = true
        view.requestFocus()
        // focus 효과 주고 터치는 제거
        view.isFocusableInTouchMode = false
    }
}