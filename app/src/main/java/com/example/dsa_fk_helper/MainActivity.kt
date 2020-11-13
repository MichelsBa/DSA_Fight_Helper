package com.example.dsa_fk_helper

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.dsa_fk_helper.challenge_tp_modificator.calculateFkMods
import com.example.dsa_fk_helper.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.calculateButton.setOnClickListener { calculateFkMods(binding) }
    }
}


