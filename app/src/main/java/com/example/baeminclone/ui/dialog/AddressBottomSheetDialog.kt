package com.example.baeminclone.ui.dialog

import android.app.Dialog
import android.content.Context
import android.os.Bundle
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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        with(binding) {
            lifecycleOwner = this@AddressBottomSheetDialog
            setVariable(BR.vm, viewModel)
        }

        setStyle(
            STYLE_NORMAL,
            R.style.MyBottomSheetDialog
        )

        repeatOnStarted {
            viewModel.eventFlow.collect { event -> handleEvent(event) }
        }

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

    override fun onAttach(context: Context) {
        super.onAttach(context)



    }

    private fun initViews() = with(binding) {
        layoutRoot.setOnClickListener {
            hideKeyBoard(requireContext(), it)

            etAddress.clearFocus()
        }

        etAddress.apply {
            setFocusListener {
                if((rvAddress.adapter as? AddressAdapter)?.currentList?.isEmpty() == true) {
                    showSearchView()
                    imgSearchGuide.isVisible = true
                }
            }
            setSearchListener { searchText ->
                imgSearchGuide.isVisible = false
                rvAddress.isVisible = true
                rvAddress.adapter = AddressAdapter()
                viewModel.getAddressList(searchText, true)
                layoutRoot.performClick()
            }
        }

        rvAddress.adapter = AddressAdapter()

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
        isSettingView = true
        group.isVisible = false
        rvRegisteredAddress.isVisible = false
    }

    private fun showSettingView() = with(binding) {
        isSettingView = true
        group.isVisible = false
        rvRegisteredAddress.isVisible = false
        imgSearchGuide.isVisible = false
        rvAddress.isVisible = false
    }

    private fun handleEvent(event : AddressViewModel.Event) {
        when(event) {
            is AddressViewModel.Event.AddressList -> {
                isMoreData = event.isMoreData
                (binding.rvAddress.adapter as? AddressAdapter)?.apply {
                    submitList(currentList + event.list)
                }
            }

            is AddressViewModel.Event.Error -> {
                Toast.makeText(requireActivity(), "오류가 발생하였습니다.", Toast.LENGTH_SHORT).show()
            }
        }
    }

}