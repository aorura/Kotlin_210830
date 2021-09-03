package com.lge.sampleapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.lge.sampleapp.databinding.MainActivity5Binding

// REST API(HTTP API)
// -> 서버로부터 데이터를 로드해서 화면에 보여주는 작업

// Github API
// - https://api.github.com/users/JakeWharton

// OKHttpClient
// 의존성 추가
// - BOM이 없는 경우
// def okhttpVersion = "4.9.0"
// implementation "com.squareup.okhttp3:okhttp:$okhttpVersion"
// implementation "com.squareup.okhttp3:logging-interceptor:$okhttpVersion"
//
// implementation(platform("com.squareup.okhttp3:okhttp-bom:4.9.0"))
// implementation("com.squareup.okhttp3:okhttp")
// implementation("com.squareup.okhttp3:logging-interceptor")


class MainActivity5 : AppCompatActivity() {
    private val binding: MainActivity5Binding by viewBinding()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

    }
}