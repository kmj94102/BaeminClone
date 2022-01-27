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
        // 상단 광고 뷰
        viewAdvertisement.apply {
            val list = getRandomImageList(7)
            setAdvertisements(list)
            setHandler(2000)
        }

    }

}