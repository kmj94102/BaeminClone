package com.example.baeminclone

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.baeminclone.databinding.FragmentDeliveryBinding

class DeliveryFragment : Fragment() {

    private lateinit var binding : FragmentDeliveryBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = FragmentDeliveryBinding.inflate(inflater, container, false).also { binding = it }.root

}