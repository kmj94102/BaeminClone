package com.example.baeminclone.ui.home

import android.os.Bundle
import androidx.activity.viewModels
import com.example.baeminclone.BaseActivity
import com.example.baeminclone.R
import com.example.baeminclone.databinding.ActivityHomeBinding
import com.example.baeminclone.ui.address.AddressSettingActivity
import com.example.baeminclone.ui.dialog.AddressBottomSheetDialog
import com.example.baeminclone.ui.maim.MainActivity
import com.example.baeminclone.ui.menu.MenuActivity
import com.example.baeminclone.util.repeatOnStarted
import com.example.baeminclone.util.startActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect

@AndroidEntryPoint
class HomeActivity : BaseActivity<ActivityHomeBinding, HomeViewModel>(R.layout.activity_home) {

    override val viewModel: HomeViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        repeatOnStarted {
            viewModel.eventFlow.collect { event -> handleEvent(event) }
        }

        initViews()

    }

    private fun initViews() = with(binding) {
        btnMenu.setOnClickListener {
            startActivity(MenuActivity::class.java)
        }

        cardDelivery.setOnClickListener {
            startActivity(MainActivity::class.java)
        }

        txtAddress.setOnClickListener {
//            val bottom = AddressBottomSheetDialog()
//            bottom.show(supportFragmentManager, bottom.tag)
            startActivity(AddressSettingActivity::class.java)

        }

        viewModel.getImageList()
    }

    private fun handleEvent(event: HomeViewModel.Event) {
        when (event) {
            is HomeViewModel.Event.ImageList -> {
                binding.cardAdvertisement.apply {
                    setAdvertisements(event.list)
                    setHandler(2000)
                }
            }
        }
    }
}