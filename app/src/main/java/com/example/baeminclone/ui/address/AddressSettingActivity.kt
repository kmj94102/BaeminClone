package com.example.baeminclone.ui.address

import android.os.Bundle
import android.view.View
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import com.example.baeminclone.BaseActivity
import com.example.baeminclone.R
import com.example.baeminclone.databinding.ActivityAddressSettingBinding
import com.example.baeminclone.ui.address.AddressSettingViewModel.AddressSettingEvent
import com.example.baeminclone.ui.address.adapter.AddressRegisteredAdapter
import com.example.baeminclone.util.hideKeyBoard
import com.example.baeminclone.util.intent
import com.example.baeminclone.util.repeatOnStarted
import com.example.baeminclone.util.toast
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect

@AndroidEntryPoint
class AddressSettingActivity : BaseActivity<ActivityAddressSettingBinding, AddressSettingViewModel>(R.layout.activity_address_setting) {

    override val viewModel : AddressSettingViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        overridePendingTransition(R.anim.slide_in_up, R.anim.trans_none)

        initViews()

        repeatOnStarted {
            viewModel.eventFlow.collect { event -> handleEvent(event) }
        }

    }

    /** 이벤트 핸들러 **/
    private fun handleEvent(event: AddressSettingEvent) {
        when(event) {
            // 등록된 주소 리스트 조회 및 리사이클러뷰 셋팅
            is AddressSettingEvent.AddressList -> {
                if (event.list.isEmpty()) {
                    binding.isListEmpty = true
                    return
                }

                binding.rvRegisteredAddress.adapter = AddressRegisteredAdapter {
                    viewModel.updateAddressStatus(it)
                }.apply {
                    submitList(event.list)
                }
            }
            // 등록된 주소가 없을 경우
            is AddressSettingEvent.Empty -> {
                binding.isListEmpty = true
            }
            // 오류 발생
            is AddressSettingEvent.Error -> {
                toast(R.string.error_guide_message)
            }
        }
    }

    /** 각종 뷰들 및 바인딩 초기화 **/
    private fun initViews() = with(binding) {
        activity = this@AddressSettingActivity
        vm = viewModel

        // [주소 검색] : 다음 화면으로 넘기는 용도로만 사용함
        etAddress.setFocusListener {
            etAddress.performClick()
        }
    }

    fun onClick(view: View?) = with(binding) {
        if (view == null) return@with

        when(view.id) {
            // [뒤로가기]
            viewBack.id -> {
                onBackPressed()
            }
            // [주소 검색]
            etAddress.id -> {
                searchLauncher.launch(intent(AddressSearchActivity::class.java))
            }
            // [다시 시도하기]
            cardAddressNull.id -> {
                viewModel.getAddressList()
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
    }

}