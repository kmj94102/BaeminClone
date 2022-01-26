package com.example.baeminclone.ui.maim

import android.os.Bundle
import com.example.baeminclone.BaseActivity
import com.example.baeminclone.R
import com.example.baeminclone.databinding.ActivityMainBinding
import com.example.baeminclone.ui.MainViewModel
import com.google.android.material.tabs.TabLayoutMediator
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : BaseActivity<ActivityMainBinding, MainViewModel>(R.layout.activity_main) {

    override val viewModel : MainViewModel by viewModel()

    val list = listOf("배달", "배민1", "포장", "쇼핑라이브", "선물하기", "전국별미")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initViewPager()
        initTabLayout()

    }

    /** 뷰페이저 설정 **/
    private fun initViewPager() {
        val adapter = MainViewPagerAdapter(this)
        adapter.addFragment(DeliveryFragment())
        adapter.addFragment(DeliveryFragment())
        adapter.addFragment(DeliveryFragment())
        adapter.addFragment(DeliveryFragment())
        adapter.addFragment(DeliveryFragment())
        adapter.addFragment(DeliveryFragment())

        binding.viewPager.adapter = adapter
    }

    /** 텝레이아웃 설정 **/
    private fun initTabLayout(){
        TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, position ->
            tab.text = list[position]
        }.attach()
    }

}