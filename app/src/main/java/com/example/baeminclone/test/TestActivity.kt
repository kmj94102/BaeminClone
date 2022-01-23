package com.example.baeminclone.test

import android.os.Bundle
import com.example.baeminclone.BaseActivity
import com.example.baeminclone.R
import com.example.baeminclone.databinding.ActivityTestBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class TestActivity : BaseActivity<ActivityTestBinding, TestViewModel>(R.layout.activity_test) {

    override val viewModel: TestViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.viewPager.apply {
            adapter = TestAdapter(this@TestActivity)
        }

    }

}