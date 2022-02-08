package com.example.baeminclone.ui.address

import com.example.baeminclone.R

enum class AddressType(val code : Int) {
    HOME(0), COMPANY(1), ETC(2);
}

fun getTypeString(code : Int) : String = when(code) {
    AddressType.HOME.code -> { "집" }
    AddressType.COMPANY.code -> { "회사" }
    else -> { "기타" }
}

fun getAddressTypeDrawable(code: Int) = when(code) {
    AddressType.HOME.code -> { R.drawable.ic_home }
    AddressType.COMPANY.code -> { R.drawable.ic_company }
    else -> { R.drawable.ic_etc }
}