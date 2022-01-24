package com.example.baeminclone.ui.maim

import com.example.baeminclone.BaseFragment
import com.example.baeminclone.R
import com.example.baeminclone.databinding.FragmentDeliveryBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class DeliveryFragment : BaseFragment<FragmentDeliveryBinding, DeliveryViewModel>(R.layout.fragment_delivery) {

    override val viewModel: DeliveryViewModel by viewModel()

}