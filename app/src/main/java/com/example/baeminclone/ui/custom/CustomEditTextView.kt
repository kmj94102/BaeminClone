package com.example.baeminclone.ui.custom

import android.annotation.SuppressLint
import android.content.Context
import android.content.res.TypedArray
import android.util.AttributeSet
import android.view.KeyEvent
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import com.example.baeminclone.R
import com.example.baeminclone.databinding.CustomEditTextBinding
import kotlin.math.absoluteValue

class CustomEditTextView : ConstraintLayout {

    private val binding = CustomEditTextBinding.inflate(LayoutInflater.from(context))
    private var focusListener : (() -> Unit)? = null
    private var searchListener : ((String) -> Unit)?= null

    constructor(context: Context) : super(context) {
        initView()
    }

    constructor(context: Context, attributeSet: AttributeSet) : super(context, attributeSet) {
        initView()
        getAttrs(attributeSet)
    }

    constructor(context: Context, attributeSet: AttributeSet, defStyle : Int) : super(context, attributeSet, defStyle) {
        initView()
        getAttrs(attributeSet)
    }

    private fun initView() {
        addView(binding.root)
    }

    @SuppressLint("Recycle")
    private fun getAttrs(attributeSet: AttributeSet) {
        setInitSetting(context.obtainStyledAttributes(attributeSet, R.styleable.CustomEditTextView))
    }

    private fun setInitSetting(typeArray: TypedArray) = with(binding) {
        val bgColor = typeArray.getResourceId(R.styleable.CustomEditTextView_bgColor, -1)
        val startIcon = typeArray.getResourceId(R.styleable.CustomEditTextView_startIcon, -1)
        val hint = typeArray.getString(R.styleable.CustomEditTextView_hint)
        val initStrokeColor = typeArray.getResourceId(R.styleable.CustomEditTextView_initStrokeColor, -1)
        val selectStrokeColor = typeArray.getResourceId(R.styleable.CustomEditTextView_selectStrokeColor, -1)

        if (bgColor != -1) {
            cardView.setCardBackgroundColor(ContextCompat.getColor(context, bgColor))
        }

        editText.setCompoundDrawablesWithIntrinsicBounds(startIcon, 0, 0, 0)
        editText.hint = hint

        if (initStrokeColor == -1) {
            return@with
        }

        cardView.strokeColor = ContextCompat.getColor(context, initStrokeColor)

        editText.setOnFocusChangeListener { _, b ->
            if (b) {
                cardView.strokeColor = ContextCompat.getColor(context, selectStrokeColor)
                focusListener?.invoke()
            } else {
                cardView.strokeColor = ContextCompat.getColor(context, initStrokeColor)
            }
        }

        editText.setOnKeyListener { _, keyCode, keyEvent ->
            when(keyCode) {
                KeyEvent.KEYCODE_ENTER -> {
                    if (keyEvent.action == KeyEvent.ACTION_UP) {
                        searchListener?.invoke(editText.text.toString())
                    }
                    return@setOnKeyListener true
                }
            }
            return@setOnKeyListener false
        }

    }

    fun setFocusListener(listener : () -> Unit) {
        focusListener = listener
    }

    fun setSearchListener(listener: (String) -> Unit) {
        searchListener = listener
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)

        binding.cardView.minimumWidth = widthMeasureSpec.absoluteValue
        binding.editText.minimumWidth = widthMeasureSpec.absoluteValue
    }

}