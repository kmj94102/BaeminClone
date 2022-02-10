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
import com.example.baeminclone.util.hideKeyBoard
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

    /** 이벤트 핸들러 **/
    private fun handleEvent(event: AddressRegisterEvent) {
        when(event) {
            is AddressRegisterEvent.AddressInsert -> {
                toast(R.string.register_complete)
                setResult(Activity.RESULT_OK)
                onBackPressed()
            }
            is AddressRegisterEvent.Error -> {
                toast(R.string.error_guide_message)
            }
        }
    }

    /** 각종 뷰들 및 바인딩 초기화 **/
    private fun initViews() = with(binding) {
        activity = this@AddressRegisterActivity
        isEtc = false

        address = intent?.getStringExtra(AddressSearchActivity.ADDRESS)

        // 주소 타입 변경 리스너
        radioGroup.setOnSelectedChangeListener {
            isEtc = radioEtc.id == it
        }
    }

    fun onClick(view: View?) = with(binding) {
        if (view == null) return

        // 검색창 외 선택 시 포커스, 키보드 제거
        if(view.id != etAddress.id && view.id != etAddressAlias.id) {
            etAddress.clearFocus()
            etAddressAlias.clearFocus()
            hideKeyBoard(this@AddressRegisterActivity, view)
        }

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

    /** 주소 등록 **/
    private fun insertAddress() {
        val address = "${binding.address}, ${binding.etAddress.getText()}"
        val alias = binding.etAddressAlias.getText().ifEmpty { address }

        val addressEntity = AddressEntity(
            address = address,
            alias = alias,
            type = getAddressTypeCode(),
            status = true
        )

        viewModel.insertAddress(addressEntity)
    }

    /** 주소 타입에 해당하는 코드값 얻기 **/
    private fun getAddressTypeCode() : Int =
        when(binding.radioGroup.getSelectItemId()){
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

    override fun onBackPressed() {
        super.onBackPressed()
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right)
    }

}