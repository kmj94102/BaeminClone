package com.example.baeminclone.ui.product.detail

import android.os.Bundle
import androidx.activity.viewModels
import com.example.baeminclone.BaseActivity
import com.example.baeminclone.R
import com.example.baeminclone.databinding.ActivityProductDetailBinding

class ProductDetailActivity : BaseActivity<ActivityProductDetailBinding, ProductDetailViewModel>(
    R.layout.activity_product_detail
) {

    override val viewModel: ProductDetailViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
    }
}