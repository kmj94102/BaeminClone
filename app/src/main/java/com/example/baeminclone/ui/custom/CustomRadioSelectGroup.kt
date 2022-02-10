package com.example.baeminclone.ui.custom

import android.content.Context
import android.util.AttributeSet
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.forEachIndexed
import com.example.baeminclone.ui.custom.CustomRadioSelectItem

class CustomRadioSelectGroup : ConstraintLayout {

    private var oldSelectItem : Int = -1
    private var onSelectedChangeListener : ((Int) -> Unit)? = null

    constructor(context : Context) : super(context)

    constructor(context: Context, attributeSet: AttributeSet) : super(context, attributeSet)

    constructor(context: Context, attributeSet: AttributeSet, defStyle : Int) : super(context, attributeSet, defStyle)

    fun getSelectedItemIndex() : Int {
        var selectItemIndex = -1
        forEachIndexed { index, view ->
            if (view.id == oldSelectItem){
                selectItemIndex = index
                return@forEachIndexed
            }
        }

        return selectItemIndex
    }

    fun getSelectItemId() : Int = oldSelectItem

    fun helpSingleSelect(newSelectItem: Int, isSelected : Boolean){
        if (oldSelectItem == newSelectItem) return
        if(oldSelectItem != -1){
            findViewById<CustomRadioSelectItem>(oldSelectItem).isSelected = false
        }
        if(isSelected) {
            oldSelectItem = newSelectItem
            onSelectedChangeListener?.invoke(newSelectItem)
        }
    }

    fun setOnSelectedChangeListener(listener : (Int) -> Unit){
        onSelectedChangeListener = listener
    }

}