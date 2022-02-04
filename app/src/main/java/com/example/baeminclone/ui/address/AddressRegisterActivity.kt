package com.example.baeminclone.ui.address

import android.app.Activity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.viewModels
import com.example.baeminclone.BaseActivity
import com.example.baeminclone.R
import com.example.baeminclone.databinding.ActivityAddressRegisterBinding
import com.example.baeminclone.util.repeatOnStarted
import com.example.baeminclone.util.toast
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AddressRegisterActivity : BaseActivity<ActivityAddressRegisterBinding, AddressRegisterViewModel>(R.layout.activity_address_register) {

    override val viewModel : AddressRegisterViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)

        repeatOnStarted {
//            viewModel.eventFlow.collect { event -> handleEvent(event) }
        }

        initViews()

    }

    private fun initViews() = with(binding) {
        activity = this@AddressRegisterActivity
        isEtc = false

        address = intent?.getStringExtra("ADDRESS")

        radioGroup.setOnSelectedChangeListener {
            isEtc = radioEtc.id == it
        }
    }

    fun onClick(view: View?) = with(binding) {
        if (view == null) return

        when(view.id) {
            // [뒤로가기]
            viewBack.id -> {
                onBackPressed()
            }
            // [지도에서 위치 확인]
            viewMap.id, txtMap.id, viewMapRight.id -> {
                toast("준비 중입니다~")
            }
            // [완료]
            cardComplete.id -> {
                // 로컬 db 추가하기
                setResult(Activity.RESULT_OK)
                onBackPressed()
            }
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right)
    }

}