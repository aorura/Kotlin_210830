package com.lge.sampleapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import coil.load
import coil.transform.CircleCropTransformation
import coil.transform.GrayscaleTransformation
import com.lge.sampleapp.databinding.MainActivity5Binding

class MainActivity7 : AppCompatActivity() {
    private val binding: MainActivity5Binding by viewBinding()

    companion object {
        val TAG: String = MainActivity7::class.java.simpleName
    }

    private fun update(user: GithubUser) {
        with(binding) {
            nameTextView.text = user.name
            loginTextView.text = user.login
            profileImageView.load(user.avatarUrl) {
                crossfade(500)
                transformations(
                    CircleCropTransformation(),
                    GrayscaleTransformation(),
                )
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
    }
}