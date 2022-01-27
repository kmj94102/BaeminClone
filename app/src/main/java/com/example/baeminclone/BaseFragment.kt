package com.example.baeminclone

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel

abstract class BaseFragment<B: ViewDataBinding, VM : ViewModel>(
    @LayoutRes
    private val layoutId: Int
) : Fragment() {

    abstract val viewModel : VM
    protected lateinit var binding: B

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate(layoutInflater, layoutId, container, false)

        with(binding) {
            lifecycleOwner = this@BaseFragment
            setVariable(BR.vm, viewModel)
        }

        return binding.root
    }

}