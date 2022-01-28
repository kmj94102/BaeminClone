package com.example.baeminclone.ui.dialog

import android.annotation.SuppressLint
import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.FrameLayout
import androidx.core.view.isVisible
import com.example.baeminclone.BuildConfig
import com.example.baeminclone.R
import com.example.baeminclone.databinding.BottomSheetDialogAddressBinding
import com.example.baeminclone.util.hideKeyBoard
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class AddressBottomSheetDialog : BottomSheetDialogFragment() {

    private val binding : BottomSheetDialogAddressBinding by lazy { BottomSheetDialogAddressBinding.inflate(layoutInflater) }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = binding.root

    @SuppressLint("ClickableViewAccessibility")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setStyle(
            STYLE_NORMAL,
            R.style.MyBottomSheetDialog
        )

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
                group.isVisible = false
                rvRegisteredAddress.isVisible = false
                imgSearchGuide.isVisible = true
            }
            setSearchListener { searchText ->
                imgSearchGuide.isVisible = false
                rvAddress.isVisible = true
                layoutRoot.performClick()
            }
        }

    }

}