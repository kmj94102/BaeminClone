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
import com.example.baeminclone.ui.dialog.AddressSearchAdapter
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
        binding.activity = this

        repeatOnStarted {
            viewModel.eventFlow.collect { event -> handleEvent(event) }
        }

        initViews()

    }

    private fun handleEvent(event : AddressSearchViewModel.AddressSearchEvent) {
        when(event) {
            is AddressSearchViewModel.AddressSearchEvent.AddressList -> {
                binding.imgSearchGuide.isVisible = false
                isMoreData = event.isMoreData

                (binding.rvAddress.adapter as? AddressSearchAdapter)?.apply {
                    submitList(currentList + event.list)
                }
            }
            is AddressSearchViewModel.AddressSearchEvent.Error -> {
                toast("오류가 발생하였습니다.")
            }
        }
    }

    private fun initViews() = with(binding) {
        etAddress.apply {
            setFocusListener {
                if((rvAddress.adapter as? AddressSearchAdapter)?.currentList?.isEmpty() == true) {
                    imgSearchGuide.isVisible = true
                }
            }
            setSearchListener { searchText ->
                rvAddress.isVisible = true
                rvAddress.adapter = AddressSearchAdapter(this@AddressSearchActivity::rvAddressClickListener)
                viewModel.getAddressList(searchText, true)
                layoutRoot.performClick()
            }
            requestFocus()
        }

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

            }
        }

    }

    private fun rvAddressClickListener(address: String) {
        registerLauncher.launch(intent(AddressRegisterActivity::class.java).also { it.putExtra("ADDRESS", address) })
    }

    val registerLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            finish()
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right)
    }

}