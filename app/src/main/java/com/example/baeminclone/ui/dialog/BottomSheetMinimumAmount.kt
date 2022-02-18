package com.example.baeminclone.ui.dialog

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.ObservableBoolean
import com.example.baeminclone.R
import com.example.baeminclone.databinding.BottomSheetMinimumAmountBinding
import com.example.baeminclone.ui.dialog.adapter.SelectOneAdapter
import com.example.baeminclone.ui.dialog.data.SelectOne
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

/**
 * 최소 주문 금액 선택 다이얼로그
 * */
class BottomSheetMinimumAmount(private val initItem : String) : BottomSheetDialogFragment() {

    private val binding : BottomSheetMinimumAmountBinding by lazy { BottomSheetMinimumAmountBinding.inflate(layoutInflater) }
    private var beforeSelectIdx = 0
    private var listener : ((String) -> Unit)? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = binding.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        list = list.mapIndexed { index, selectOne ->
            selectOne.isSelect.set(selectOne.text == initItem)
            if (selectOne.text == initItem){
                beforeSelectIdx = index
            }

            selectOne
        }
        // 최초 문구의 경우 선택 리스트와 문구가 다르므로 0으로 고정
        if (beforeSelectIdx == 0) {
            list[0].isSelect.set(true)
        }

        binding.recyclerView.adapter = SelectOneAdapter {
            selectMinimumAmount(it)

            listener?.invoke(list[it].text)
            dismiss()
        }.apply {
            submitList(list)
        }

        binding.txtClose.setOnClickListener { dismiss() }

    }

    private fun selectMinimumAmount(index : Int) {
        val currentList = (binding.recyclerView.adapter as? SelectOneAdapter)?.currentList ?:return
        if (beforeSelectIdx == index) return

        currentList[beforeSelectIdx].isSelect.set(false)
        currentList[index].isSelect.set(true)

        beforeSelectIdx = index
    }

    fun setClickListener(listener : (String) -> Unit) {
        this.listener = listener
    }

    private var list = listOf(
        SelectOne(getString(R.string.all), ObservableBoolean(true)),
        SelectOne(getString(R.string.less_then_5000), ObservableBoolean(false)),
        SelectOne(getString(R.string.less_then_10000), ObservableBoolean(false)),
        SelectOne(getString(R.string.less_then_12000), ObservableBoolean(false)),
        SelectOne(getString(R.string.less_then_15000), ObservableBoolean(false)),
        SelectOne(getString(R.string.less_then_20000), ObservableBoolean(false)),
    )

}