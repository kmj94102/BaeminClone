package com.example.baeminclone

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.baeminclone.databinding.ActivityHomeBinding

class HomeActivity : AppCompatActivity() {

    private val binding : ActivityHomeBinding by lazy { ActivityHomeBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.btnMenu.setOnClickListener {
            startActivity(Intent(this, MenuActivity::class.java))
        }

    }
}