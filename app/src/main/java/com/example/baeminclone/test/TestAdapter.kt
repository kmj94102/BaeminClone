package com.example.baeminclone.test

import android.util.Log
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

class TestAdapter(fragmentActivity: FragmentActivity) : FragmentStateAdapter(fragmentActivity) {
    override fun getItemCount(): Int = 3

    override fun createFragment(position: Int): Fragment =
        when(position) {
            0 -> {
                Log.e("+++++", "TestA")
                TestAFragment()
            }
            1 -> {
                Log.e("+++++", "TestB")
                TestBFragment()
            }
            else -> {
                Log.e("+++++", "TestC")
                TestCFragment()
            }
        }
}