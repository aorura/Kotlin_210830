package com.lge.sampleapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.lge.sampleapp.databinding.MainActivity5Binding

class MainActivity6 : AppCompatActivity() {
    private val binding: MainActivity5Binding by viewBinding()

    companion object {
        val TAG: String = MainActivity6::class.java.simpleName
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
    }
}