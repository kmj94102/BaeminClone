package com.example.baeminclone.ui.maim

import android.os.Bundle
import android.view.View
import com.example.baeminclone.BaseFragment
import com.example.baeminclone.R
import com.example.baeminclone.databinding.FragmentDeliveryBinding
import com.example.baeminclone.util.getRandomImageList
import org.koin.androidx.viewmodel.ext.android.viewModel

class DeliveryFragment : BaseFragment<FragmentDeliveryBinding, DeliveryViewModel>(R.layout.fragment_delivery) {

    override val viewModel: DeliveryViewModel by viewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.viewAdvertisement.apply {
            val list = getRandomImageList(7)
            setAdvertisements(list)
            setHandler(2000)
        }

    }

}