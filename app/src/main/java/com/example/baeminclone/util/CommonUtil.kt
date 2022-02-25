package com.example.baeminclone.util

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.ColorRes
import androidx.core.content.ContextCompat
import com.example.baeminclone.R

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

fun TextView.setTextColorRes(colorRes: Int) {
    setTextColor(ContextCompat.getColor(context, colorRes))
}

fun <T>Activity.startActivity(clazz: Class<T>) {
    startActivity(intent(clazz))
}

fun <T>Activity.intent(clazz: Class<T>) : Intent = Intent(this, clazz)

fun Activity.toast(message : String) {
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
}

fun Activity.toast(res : Int){
    Toast.makeText(this, getString(res), Toast.LENGTH_SHORT).show()
}

fun Context.getProductTabList() = listOf(
    getString(R.string.one_person),
    getString(R.string.korean_food),
    getString(R.string.snack_bar),
    getString(R.string.caffe_dessert),
    getString(R.string.japanese_food),
    getString(R.string.chicken),
    getString(R.string.pizza),
    getString(R.string.western_food),
    getString(R.string.chinese_food),
    getString(R.string.pork_feet),
    getString(R.string.midnight_snack),
    getString(R.string.steamed),
    getString(R.string.lunch),
    getString(R.string.fast_food),
    getString(R.string.brand),
    getString(R.string.ranking)
)