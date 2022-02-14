package com.example.baeminclone.ui.maim

import android.os.Build
import android.os.Bundle
import android.view.View
import android.view.WindowInsetsController
import androidx.activity.viewModels
import androidx.core.content.ContextCompat
import com.example.baeminclone.BaseActivity
import com.example.baeminclone.BaseViewPagerAdapter
import com.example.baeminclone.R
import com.example.baeminclone.databinding.ActivityMainBinding
import com.example.baeminclone.ui.MainViewModel
import com.example.baeminclone.ui.address.AddressSettingActivity
import com.example.baeminclone.ui.product.ProductActivity
import com.example.baeminclone.util.intent
import com.example.baeminclone.util.startActivity
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding, MainViewModel>(R.layout.activity_main) {

    override val viewModel : MainViewModel by viewModels()

    val list = listOf("배달", "배민1", "포장", "쇼핑라이브", "선물하기", "전국별미")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initViews()

    }

    private fun initViews() = with(binding) {
        //스테이스 바 색상 변경
        setStatusBar()
        // 뷰페이저 설정
        initViewPager()
        // 텝레이아웃 설정
        initTabLayout()

        btnBack.setOnClickListener {
            finish()
        }

        txtAddress.setOnClickListener {
            startActivity(AddressSettingActivity::class.java)
        }

    }

    fun onClick(view : View?) {
        view ?: return



    }

    fun startProductActivity(category : String) {
        startActivity(intent(ProductActivity::class.java).also {
            it.putExtra(CATEGORY, category)
        })
    }

    /** 뷰페이저 설정 **/
    private fun initViewPager() {
        val adapter = BaseViewPagerAdapter(this)
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

    /** 스테이스 바 색상 변경 **/
    private fun setStatusBar() {
        window.statusBarColor = ContextCompat.getColor(this, R.color.white)
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.R){
            window.insetsController?.setSystemBarsAppearance(WindowInsetsController.APPEARANCE_LIGHT_STATUS_BARS, WindowInsetsController.APPEARANCE_LIGHT_STATUS_BARS)
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
        }
    }

    companion object {
        const val CATEGORY = "CATEGORY"
    }

}