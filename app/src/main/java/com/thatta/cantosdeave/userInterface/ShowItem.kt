package com.thatta.cantosdeave.userInterface

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import com.thatta.cantosdeave.R
import com.thatta.cantosdeave.databinding.ActivityShowItemBinding

class ShowItem : AppCompatActivity() {

    private lateinit var binding: ActivityShowItemBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityShowItemBinding.inflate(layoutInflater)
        setContentView(binding.root)

    }
}