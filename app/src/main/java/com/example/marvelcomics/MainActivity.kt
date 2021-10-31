package com.example.marvelcomics

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.marvelcomics.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        applicationBinding()
    }

    private fun applicationBinding() = ActivityMainBinding.inflate(layoutInflater).apply {
        binding = this
        setContentView(root)
    }


}