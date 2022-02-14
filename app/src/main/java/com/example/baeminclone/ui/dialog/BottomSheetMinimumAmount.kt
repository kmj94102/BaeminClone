package com.example.baeminclone.ui.dialog

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.baeminclone.databinding.BottomSheetMinimumAmountBinding
import com.example.baeminclone.ui.dialog.adapter.SelectOneAdapter
import com.example.baeminclone.ui.dialog.data.SelectOne
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class BottomSheetMinimumAmount(private val currentItem : String?) : BottomSheetDialogFragment() {

    private val binding : BottomSheetMinimumAmountBinding by lazy { BottomSheetMinimumAmountBinding.inflate(layoutInflater) }
    private var beforeSelectIdx = 0

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = binding.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        currentItem?.let {
            list = list.map { selectOne ->
                selectOne.isSelect = selectOne.text == currentItem
                selectOne
            }
            beforeSelectIdx = list.indexOfFirst { it.isSelect }
        }

        binding.recyclerView.adapter = SelectOneAdapter {
            (binding.recyclerView.adapter as? SelectOneAdapter)?.apply {
                Log.e("++++++", "before : $beforeSelectIdx, new $it")
                if (beforeSelectIdx == it) return@SelectOneAdapter
                currentList[beforeSelectIdx].isSelect = false
                notifyItemChanged(beforeSelectIdx)
                currentList[it].isSelect = true
                notifyItemChanged(it)
                Log.e("++++++", "before : ${currentList[beforeSelectIdx].isSelect}, new ${currentList[it].isSelect}")
                beforeSelectIdx = it
            }
        }.apply {
            submitList(list)
        }

    }

    companion object {
        private var list = listOf(
            SelectOne("전체", true), SelectOne("5,000원 이하", false),
            SelectOne("10,000원 이하", false), SelectOne("12,000원 이하", false),
            SelectOne("15,000원 이하", false), SelectOne("20,000원 이하", false)
        )
    }

}