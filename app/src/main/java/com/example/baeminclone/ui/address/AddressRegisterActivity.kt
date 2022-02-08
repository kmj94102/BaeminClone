package com.example.baeminclone.ui.address

import android.app.Activity
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import com.example.baeminclone.BaseActivity
import com.example.baeminclone.R
import com.example.baeminclone.database.entity.AddressEntity
import com.example.baeminclone.databinding.ActivityAddressRegisterBinding
import com.example.baeminclone.util.repeatOnStarted
import com.example.baeminclone.util.toast
import com.example.baeminclone.ui.address.AddressRegisterViewModel.AddressRegisterEvent
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect

@AndroidEntryPoint
class AddressRegisterActivity : BaseActivity<ActivityAddressRegisterBinding, AddressRegisterViewModel>(R.layout.activity_address_register) {

    override val viewModel : AddressRegisterViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)

        repeatOnStarted {
            viewModel.eventFlow.collect { event -> handleEvent(event) }
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
                insertAddress()
            }
        }
    }

    private fun insertAddress() {
        val address = "${binding.address}, ${binding.etAddress.getText()}"
        val alias = if (binding.etAddressAlias.getText().isNotEmpty()) binding.etAddressAlias.getText() else address

        val addressEntity = AddressEntity(
            address = address,
            alias = address,
            type = getAddressType(),
            status = true
        )

        // todo 기존 status true 수정 필요

        viewModel.insertAddress(addressEntity)
    }

    private fun getAddressType() : Int =
        when(binding.radioGroup.getSelectedItemIndex()){
            binding.radioHome.id -> {
                AddressType.HOME.code
            }
            binding.radioCompany.id -> {
                AddressType.COMPANY.code

            }
            else -> {
                AddressType.ETC.code
            }
        }

    private fun handleEvent(event: AddressRegisterEvent) {
        when(event) {
            is AddressRegisterEvent.AddressInsert -> {
                toast("등록이 완료되었습니다.")
                setResult(Activity.RESULT_OK)
                onBackPressed()
            }
            is AddressRegisterEvent.Error -> {
                toast("오류가 발생하였습니다.")
            }
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right)
    }

}