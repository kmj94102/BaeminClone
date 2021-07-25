package com.example.baeminclone

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.baeminclone.databinding.ActivityMenuBinding

class MenuActivity : AppCompatActivity() {

    val binding : ActivityMenuBinding by lazy { ActivityMenuBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
    }
}