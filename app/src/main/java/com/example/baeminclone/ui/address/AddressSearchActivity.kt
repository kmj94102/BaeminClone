package com.example.baeminclone.ui.address

import android.app.Activity
import android.os.Bundle
import android.view.View
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.example.baeminclone.BaseActivity
import com.example.baeminclone.R
import com.example.baeminclone.databinding.ActivityAddressSearchBinding
import com.example.baeminclone.ui.address.adapter.AddressSearchAdapter
import com.example.baeminclone.util.hideKeyBoard
import com.example.baeminclone.util.intent
import com.example.baeminclone.util.repeatOnStarted
import com.example.baeminclone.util.toast
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect

@AndroidEntryPoint
class AddressSearchActivity : BaseActivity<ActivityAddressSearchBinding, AddressSearchViewModel>(R.layout.activity_address_search) {

    override val viewModel : AddressSearchViewModel by viewModels()
    private var isMoreData = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)

        initViews()

        repeatOnStarted {
            viewModel.eventFlow.collect { event -> handleEvent(event) }
        }

    }

    /** 이벤트 핸들러 **/
    private fun handleEvent(event : AddressSearchViewModel.AddressSearchEvent) {
        when(event) {
            is AddressSearchViewModel.AddressSearchEvent.AddressList -> {
                isMoreData = event.isMoreData
                rvAddressSetting(event.list)
            }
            is AddressSearchViewModel.AddressSearchEvent.Error -> {
                toast(getString(R.string.error_guide_message))
            }
        }
    }

    /** 주소 검색 리사이클러뷰 셋팅 **/
    private fun rvAddressSetting(list: List<String>) {
        (binding.rvAddress.adapter as? AddressSearchAdapter)?.apply {
            submitList(currentList + list)

            if (currentList.isNotEmpty()) {
                binding.rvAddress.isVisible = true
                binding.imgSearchGuide.isVisible = false
            } else {
                binding.rvAddress.isVisible = false
                binding.imgSearchGuide.isVisible = true
                toast(getString(R.string.search_result_empty))
            }

        }
    }

    /** 각종 뷰들 및 바인딩 초기화 **/
    private fun initViews() = with(binding) {
        activity = this@AddressSearchActivity

        // [주소 검색]
        etAddress.apply {
            setFocusListener {
                if((rvAddress.adapter as? AddressSearchAdapter)?.currentList?.isEmpty() == true) {
                    imgSearchGuide.isVisible = true
                }
            }
            setSearchListener { searchText ->
                rvAddress.adapter = AddressSearchAdapter(this@AddressSearchActivity::rvAddressClickListener)
                viewModel.getAddressList(searchText, true)
                layoutRoot.performClick()
            }
            // 최초 포커스 설정
            requestFocus()
        }

        // [주소 리스트]
        rvAddress.adapter = AddressSearchAdapter(this@AddressSearchActivity::rvAddressClickListener)

        rvAddress.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)

                if( !recyclerView.canScrollVertically(1) ) {
                    if (isMoreData) {
                        viewModel.getAddressList("", false)
                    }
                }
            }
        })
    }

    fun onClick(view: View?) = with(binding) {
        if (view == null) return

        // 검색 창 외 클릭 시 포커스 및 키보드 제거
        if (view.id != etAddress.id) {
            hideKeyBoard(this@AddressSearchActivity, view)
            etAddress.clearFocus()
        }

        when(view.id) {
            // [뒤로가기]
            viewBack.id -> {
                onBackPressed()
            }
            // [현재 위치로 설정]
            viewLocation.id, txtLocation.id, viewLocationRight.id -> {
                toast(R.string.preparing)
            }
        }

    }

    private fun rvAddressClickListener(address: String) {
        registerLauncher.launch(intent(AddressRegisterActivity::class.java).also { it.putExtra(ADDRESS, address) })
    }

    private val registerLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        setResult(result.resultCode)
        if (result.resultCode == Activity.RESULT_OK ) {
            finish()
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right)
    }

    companion object {
        const val ADDRESS = "ADDRESS"
    }

}