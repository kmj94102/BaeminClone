package com.example.baeminclone.ui.dialog

import android.app.Dialog
import android.os.Bundle
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.RecyclerView
import com.example.baeminclone.BR
import com.example.baeminclone.R
import com.example.baeminclone.databinding.BottomSheetDialogAddressBinding
import com.example.baeminclone.ui.address.adapter.AddressSearchAdapter
import com.example.baeminclone.util.hideKeyBoard
import com.example.baeminclone.util.repeatOnStarted
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import kotlinx.coroutines.flow.collect

class AddressBottomSheetDialog : BottomSheetDialogFragment() {

    private val binding : BottomSheetDialogAddressBinding by lazy { BottomSheetDialogAddressBinding.inflate(layoutInflater) }
    private val viewModel : AddressViewModel by viewModels()

    private var isMoreData = false
    private var isSettingView = true

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = binding.root

    override fun getTheme(): Int = R.style.MyBottomSheetDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        with(binding) {
            lifecycleOwner = this@AddressBottomSheetDialog
            setVariable(BR.vm, viewModel)
        }

        repeatOnStarted {
            viewModel.eventFlow.collect { event -> handleEvent(event) }
        }

        setBackPressed()
        initViews()

    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val bottomSheetDialog = BottomSheetDialog(requireContext(), theme)
        bottomSheetDialog.setOnShowListener { dialog ->
            val bottomSheet = (dialog as BottomSheetDialog).findViewById<FrameLayout>(com.google.android.material.R.id.design_bottom_sheet)
            BottomSheetBehavior.from(bottomSheet!!).apply {
                state = BottomSheetBehavior.STATE_EXPANDED
                skipCollapsed = true
                isHideable = true
            }
        }

        return bottomSheetDialog
    }

    private fun initViews() = with(binding) {
        layoutRoot.setOnClickListener {
            hideKeyBoard(requireContext(), it)

            etAddress.clearFocus()
        }

        etAddress.apply {
            setFocusListener {
                if((rvAddress.adapter as? AddressSearchAdapter)?.currentList?.isEmpty() == true) {
                    showSearchView()
                    imgSearchGuide.isVisible = true
                }
            }
            setSearchListener { searchText ->
                imgSearchGuide.isVisible = false
                rvAddress.isVisible = true
//                rvAddress.adapter = AddressSearchAdapter()
                viewModel.getAddressList(searchText, true)
                layoutRoot.performClick()
            }
        }

//        rvAddress.adapter = AddressSearchAdapter()

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

    private fun showSearchView() = with(binding) {
        isSettingView = false
        viewBack.isVisible = false
        group.isVisible = false
        rvRegisteredAddress.isVisible = false
    }

    private fun showSettingView() = with(binding) {
        isSettingView = true
        viewBack.isVisible = true
        group.isVisible = true
        rvRegisteredAddress.isVisible = true
        imgSearchGuide.isVisible = false
        rvAddress.isVisible = false
    }

    private fun handleEvent(event : AddressViewModel.Event) {
        when(event) {
            is AddressViewModel.Event.AddressList -> {
                isMoreData = event.isMoreData
                (binding.rvAddress.adapter as? AddressSearchAdapter)?.apply {
                    submitList(currentList + event.list)
                }
            }

            is AddressViewModel.Event.Error -> {
                Toast.makeText(requireActivity(), "오류가 발생하였습니다.", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun setBackPressed() {
        dialog?.setOnKeyListener { _, _, keyEvent ->
            when(keyEvent.keyCode) {
                KeyEvent.KEYCODE_BACK -> {
                    if (keyEvent.action == KeyEvent.ACTION_UP){
                        if (isSettingView) {
                            dialog?.dismiss()
                            return@setOnKeyListener false
                        } else {
                            showSettingView()
                        }
                    }
                }
                else -> {
                    return@setOnKeyListener false
                }
            }
            return@setOnKeyListener true
        }
    }

}