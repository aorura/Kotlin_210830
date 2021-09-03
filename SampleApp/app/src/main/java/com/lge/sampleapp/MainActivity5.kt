package com.lge.sampleapp

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.lge.sampleapp.databinding.MainActivity5Binding
import okhttp3.Call
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import java.io.IOException

// REST API(HTTP API)
// -> 서버로부터 데이터를 로드해서 화면에 보여주는 작업
// Github API
// - https://api.github.com/users/JakeWharton

//    URL: 리소스 위치
// METHOD: GET / POST / PUT / PATCH / DELETE


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

    companion object {
        // const val TAG = "MainActivity5"
        val TAG = MainActivity5::class.java.simpleName
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.loadButton.setOnClickListener {
            // 1. OKHttpClient 객체 생성
            /*
            val loggingInterceptor = HttpLoggingInterceptor()
            loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY

            val httpClient = OkHttpClient.Builder()
                .addInterceptor(loggingInterceptor)
                .build()
            */
            val httpClient = OkHttpClient.Builder().apply {
                addInterceptor(HttpLoggingInterceptor().apply {
                    level = HttpLoggingInterceptor.Level.BODY
                })
            }.build()

            // 2. Request 객체 생성
            val request = Request.Builder().apply {
                url("https://api.github.com/users/JakeWharton")
                get()
            }.build()

            // 3. Call 객체
            val call: Call = httpClient.newCall(request)
            // => 동기로 수행할지 비동기로 수행할지를 결정할 수 있습니다.

            // 4. 동기 수행
            try {
                val response: Response = call.execute()

            } catch (e: IOException) {
                // 서버에 접속할 수 없다.
                // 1) 핸드폰이 인터넷이 되지 않습니다.
                // 2) 서버가 접속할 수 없는 상태입니다.
                Log.e(TAG, e.localizedMessage, e)
            }


        }

    }
}