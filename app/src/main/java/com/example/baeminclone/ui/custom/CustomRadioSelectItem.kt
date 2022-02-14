package com.example.baeminclone.ui.custom

import android.annotation.SuppressLint
import android.content.Context
import android.content.res.TypedArray
import android.graphics.PorterDuff
import android.graphics.PorterDuffColorFilter
import android.util.AttributeSet
import android.util.Log
import androidx.core.content.ContextCompat
import com.example.baeminclone.R

class CustomRadioSelectItem : com.google.android.material.textview.MaterialTextView {

    private var uncheckedBackgroundRes = resources.getIdentifier("bg_rounded_border_stroke_4", "drawable", context.packageName)
    private var checkedBackgroundRes = resources.getIdentifier("bg_rounded_border_brown_4", "drawable", context.packageName)
    private var uncheckedColorRes = ContextCompat.getColor(context, R.color.gray_card_stroke)
    private var checkedColorRes = ContextCompat.getColor(context, R.color.white)
    private var basicDrawableColorRes = ContextCompat.getColor(context, R.color.white)

    constructor(context : Context) : super(context)

    constructor(context: Context, attributeSet: AttributeSet) : super(context, attributeSet){
        getAttrs(attributeSet)
    }

    constructor(context: Context, attributeSet: AttributeSet, defStyle : Int) : super(context, attributeSet, defStyle){
        getAttrs(attributeSet)
    }

    private fun getAttrs(attributeSet: AttributeSet){
        val typeArray = context.obtainStyledAttributes(attributeSet, R.styleable.CustomRadioSelectItem)
        setTypeArray(typeArray)
    }

    init {
        setOnClickListener { isSelected = true }
    }

    private fun setTypeArray(typeArray: TypedArray) {
        val isSelected = typeArray.getBoolean(R.styleable.CustomRadioSelectItem_isSelectedItem, false)
        uncheckedBackgroundRes = typeArray.getResourceId(R.styleable.CustomRadioSelectItem_uncheckedBackground, R.drawable.bg_rounded_border_stroke_4)
        checkedBackgroundRes = typeArray.getResourceId(R.styleable.CustomRadioSelectItem_checkedBackground, R.drawable.bg_rounded_border_brown_4)
        uncheckedColorRes = typeArray.getResourceId(R.styleable.CustomRadioSelectItem_uncheckedColor, R.color.gray_card_stroke)
        checkedColorRes = typeArray.getResourceId(R.styleable.CustomRadioSelectItem_checkedColor, R.color.white)
        basicDrawableColorRes = typeArray.getResourceId(R.styleable.CustomRadioSelectItem_basicDrawableColor, R.color.white)

        setSelected(isSelected)
        if (isSelected) {
            postDelayed({
                (parent as? CustomRadioSelectGroup)?.helpSingleSelect(this.id, isSelected)
            }, 300)
        }
    }

    override fun setSelected(isSelected: Boolean) {
        when(isSelected){
            true -> {
                checkedView()
            }
            false -> {
                uncheckedView()
            }
        }

        (parent as? CustomRadioSelectGroup)?.helpSingleSelect(this.id, isSelected)
    }

    @SuppressLint("ResourceType")
    private fun checkedView(){
        setBackgroundResource(checkedBackgroundRes)
        setTextColor(ContextCompat.getColor(context, checkedColorRes))
        for (drawable in compoundDrawables){
            if(drawable != null){
                drawable.mutate()
                drawable.colorFilter = PorterDuffColorFilter(ContextCompat.getColor(context, checkedColorRes), PorterDuff.Mode.SRC_IN)
            }
        }
    }

    @SuppressLint("ResourceType")
    private fun uncheckedView(){
        setBackgroundResource(uncheckedBackgroundRes)
        setTextColor(ContextCompat.getColor(context, uncheckedColorRes))

        if (basicDrawableColorRes == -1 || basicDrawableColorRes == R.color.white) {
            basicDrawableColorRes = uncheckedColorRes
        }

        for (drawable in compoundDrawables){
            if(drawable != null){
                drawable.mutate()
                drawable.colorFilter = PorterDuffColorFilter(ContextCompat.getColor(context, basicDrawableColorRes), PorterDuff.Mode.SRC_IN)
            }
        }
    }


}