package com.lge.sampleapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.lge.sampleapp.databinding.MainActivity5Binding

class MainActivity5 : AppCompatActivity() {
    private val binding: MainActivity5Binding by viewBinding()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
    }
}