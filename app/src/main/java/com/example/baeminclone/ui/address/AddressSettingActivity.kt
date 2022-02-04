package com.example.baeminclone.ui.address

import android.app.Activity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import com.example.baeminclone.BaseActivity
import com.example.baeminclone.R
import com.example.baeminclone.databinding.ActivityAddressSettingBinding
import com.example.baeminclone.util.hideKeyBoard
import com.example.baeminclone.util.intent
import com.example.baeminclone.util.repeatOnStarted
import com.example.baeminclone.util.startActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AddressSettingActivity : BaseActivity<ActivityAddressSettingBinding, AddressSettingViewModel>(R.layout.activity_address_setting) {

    override val viewModel : AddressSettingViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        overridePendingTransition(R.anim.slide_in_up, R.anim.trans_none)
        binding.activity = this

        repeatOnStarted {
            // todo 등록되어 있는 주소 불러오는 로직 필요
//            viewModel.eventFlow.collect { event -> handleEvent(event) }
        }

        initViews()

    }

    private fun initViews() = with(binding) {
        // recyclerView 설정 예정
        etAddress.setFocusListener {
            etAddress.performClick()
        }
    }

    fun onClick(view: View?) = with(binding) {
        if (view == null) return@with

        when(view.id) {
            viewBack.id -> {
                onBackPressed()
            }
            etAddress.id -> {
                searchLauncher.launch(intent(AddressSearchActivity::class.java))
            }
        }

    }


    override fun onBackPressed() {
        super.onBackPressed()
        overridePendingTransition(R.anim.trans_none, R.anim.slide_out_down)
    }

    private val searchLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        binding.etAddress.apply {
            hideKeyBoard(this@AddressSettingActivity, this)
            clearFocus()
        }

        if (result.resultCode == Activity.RESULT_OK) {

        }

    }

}