package com.example.baeminclone.ui.maim

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.example.baeminclone.BaseFragment
import com.example.baeminclone.R
import com.example.baeminclone.databinding.FragmentDeliveryBinding
import com.example.baeminclone.util.getRandomImageList
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DeliveryFragment : BaseFragment<FragmentDeliveryBinding, DeliveryViewModel>(R.layout.fragment_delivery) {

    override val viewModel: DeliveryViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)



        initViews()

    }

    private fun initViews() = with(binding) {
        binding.fm = this@DeliveryFragment

        // 상단 광고 뷰
        viewAdvertisement.apply {
            val list = getRandomImageList(7)
            setAdvertisements(list)
            setHandler(2000)
        }

    }

    fun onClick(view : View?) = with(binding) {
        view ?: return@with

        when(view.id) {
            // 카테고리 선택
            layoutOnePerson.id, layoutKoreanFood.id, layoutSnackBar.id, layoutCaffeDessert.id, layoutJapaneseFood.id,
            layoutChicken.id, layoutPizza.id, layoutWesternFood.id, layoutChineseFood.id, layoutPorkFeet.id,
            layoutMidnightSnack.id, layoutSteamed.id, layoutLunch.id,layoutFastFood.id, layoutBrand.id, layoutRanking.id -> {
                (requireActivity() as MainActivity).startProductActivity(view.tag as String)
            }
            else -> {}
        }

    }

}