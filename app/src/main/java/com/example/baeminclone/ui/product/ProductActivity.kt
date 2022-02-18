package com.example.baeminclone.ui.product

import android.os.Bundle
import androidx.activity.viewModels
import com.example.baeminclone.BaseActivity
import com.example.baeminclone.R
import com.example.baeminclone.databinding.ActivityProductBinding
import com.example.baeminclone.ui.dialog.BottomSheetMinimumAmount
import com.example.baeminclone.ui.maim.MainActivity

class ProductActivity : BaseActivity<ActivityProductBinding ,ProductViewModel>(R.layout.activity_product) {

    override val viewModel: ProductViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val category = intent?.getStringExtra(MainActivity.CATEGORY)
        binding.txtTitle.text = category

        binding.radioGroup.setOnSelectedChangeListener {
            when(it) {
                binding.radioMinimumAmount.id -> {
                    BottomSheetMinimumAmount(binding.radioMinimumAmount.text.toString()).apply {
                        setClickListener { selectMinimum ->
                            binding.radioMinimumAmount.text = selectMinimum
                        }
                        show(supportFragmentManager, "")
                    }
                }
            }
        }

    }
}