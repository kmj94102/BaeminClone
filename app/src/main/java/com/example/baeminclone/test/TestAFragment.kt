package com.example.baeminclone.test

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.baeminclone.BaseFragment
import com.example.baeminclone.R
import com.example.baeminclone.databinding.FragmentTestBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class TestAFragment : BaseFragment<FragmentTestBinding, TestFragmentViewModel>(R.layout.fragment_test) {

    override val viewModel: TestFragmentViewModel by viewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

}