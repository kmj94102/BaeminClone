package com.example.baeminclone.ui.custom

import android.annotation.SuppressLint
import android.content.Context
import android.os.Handler
import android.util.AttributeSet
import android.util.Log
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.viewpager2.widget.ViewPager2
import com.example.baeminclone.R
import com.example.baeminclone.databinding.CustomAdvertisementViewBinding

class CustomAdvertisementView : ConstraintLayout {

    private val binding = CustomAdvertisementViewBinding.inflate(LayoutInflater.from(context))
    private var delayMillis = 1000L
    private var index = 0
    private val myHandler = Handler(context.mainLooper)

    private val runnable = Runnable {
        val size = (binding.viewPager.adapter as? AdvertisementAdapter)?.itemCount ?: return@Runnable

        binding.viewPager.currentItem = index++ % size
    }

    private val timerInitRunnable = Runnable {
        myHandler.run {
            myHandler.removeCallbacks(runnable)
            myHandler.postDelayed(runnable, delayMillis)
        }
    }

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
    private fun getAttrs(attributeSet: AttributeSet){
        val typeArray = context.obtainStyledAttributes(attributeSet, R.styleable.CustomAdvertisementView)
        val radius = typeArray.getDimensionPixelSize(R.styleable.CustomAdvertisementView_AdvertisementRadius, -1)

        if (radius != -1){
            binding.layoutRoot.radius = radius.toFloat()
        }

    }

    fun setAdvertisements(list: List<String>) {
        val adapter = AdvertisementAdapter()
        binding.viewPager.adapter = adapter
        adapter.submitList(list)

        binding.txtAdCount.text = context.getString(R.string.advertisement_state, 1, adapter.currentList.size)
        myHandler.postDelayed(timerInitRunnable, delayMillis)

        binding.viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                index = position + 1
                binding.txtAdCount.text = context.getString(R.string.advertisement_state, position + 1, adapter.currentList.size)
            }

            override fun onPageScrollStateChanged(state: Int) {
                super.onPageScrollStateChanged(state)

                if(state == ViewPager2.SCROLL_STATE_DRAGGING) {
                    handlerAllStop()
                } else if (state == ViewPager2.SCROLL_STATE_IDLE) {
                    handlerStart()
                }
            }
        })
    }

    fun setHandler(delayMillis: Long) {
        this.delayMillis = delayMillis
    }

    fun handlerAllStop() {
        myHandler.removeCallbacks(runnable)
        myHandler.removeCallbacks(timerInitRunnable)
    }

    fun handlerStart() {
        myHandler.postDelayed(timerInitRunnable, delayMillis)
    }

    override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()
        handlerAllStop()
    }

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        handlerStart()
    }

}