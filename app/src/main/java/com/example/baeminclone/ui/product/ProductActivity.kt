package com.example.baeminclone.ui.product

import android.os.Bundle
import android.os.Handler
import android.view.View
import androidx.activity.viewModels
import com.example.baeminclone.BaseActivity
import com.example.baeminclone.R
import com.example.baeminclone.databinding.ActivityProductBinding
import com.example.baeminclone.ui.dialog.BottomSheetMinimumAmount
import com.example.baeminclone.ui.maim.MainActivity
import com.example.baeminclone.util.getProductTabList

class ProductActivity : BaseActivity<ActivityProductBinding ,ProductViewModel>(R.layout.activity_product) {

    override val viewModel: ProductViewModel by viewModels()
    private var initIdx = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        initViews()

    }

    private fun initViews() = with(binding) {
        activity = this@ProductActivity

        val category = intent?.getStringExtra(MainActivity.CATEGORY)
        txtTitle.text = category

        getProductTabList().forEachIndexed { index, tabItem ->
            if (category == tabItem) initIdx = index
            tabLayout.addTab(tabLayout.newTab().setText(tabItem))
        }

        Handler(mainLooper).postDelayed({
            tabLayout.setScrollPosition(initIdx, 0.0f, true, true)
        }, 100)

        radioGroup.setOnSelectedChangeListener {
            when(it) {
                radioMinimumAmount.id -> {
                    BottomSheetMinimumAmount(radioMinimumAmount.text.toString()).apply {
                        setClickListener { selectMinimum ->
                            radioMinimumAmount.text = selectMinimum
                        }
                        show(supportFragmentManager, "")
                    }
                }
            }
        }
    }

    fun onclick(view: View?) = with(binding) {
        view ?: return@with

        when(view.id) {
            viewBack.id -> {
                finish()
            }
        }
    }

}