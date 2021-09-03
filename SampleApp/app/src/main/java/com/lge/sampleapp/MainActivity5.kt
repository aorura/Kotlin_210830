package com.lge.sampleapp

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.gson.FieldNamingPolicy
import com.google.gson.GsonBuilder
import com.google.gson.annotations.SerializedName
import com.lge.sampleapp.databinding.MainActivity5Binding
import okhttp3.*
import okhttp3.logging.HttpLoggingInterceptor
import java.io.IOException

// REST API(HTTP API)
// -> 서버로부터 데이터를 로드해서 화면에 보여주는 작업
// Github API
// - https://api.github.com/users/JakeWharton

// Request
//    URL: 리소스 위치
// METHOD: GET / POST / PUT / PATCH / DELETE

// Response
//   status code:
//    - 200-299: 성공
//    - 400-499: 클라이언트 오류
//    - 500-599: 서버 오류

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


// android.os.NetworkOnMainThreadException
//  => UI 업데이트를 하는 메인 스레드 상에서 네트워크 등의 시간이 걸리는 작업을 수행하는 것이
//     금지되어 있습니다.
// java.lang.SecurityException: Permission denied (missing INTERNET permission?)
//  => AndroidManifest.xml
//    INTERNET 사용 권한이 필요합니다.
//    <uses-permission android:name="android.permission.INTERNET"/>
// Can't toast on a thread that has not called Looper.prepare()
//  => UI 업데이트는 UI 스레드에서만 가능합니다.


// Gson을 이용해서 응답 받은 JSON을 객체로 변환해서 사용해야 합니다.
// => 의존성 추가

// Gson은 리플레션을 이용해서, 클래스 안에서 동일한 이름을 가지는 프로퍼티의 값을 변경하는 형태로 동작합니다.
// => 이름의 형식이 다를 경우 해결하는 방법.
// 1) @field:SerializedName("avatar_url")
// 2) GsonBuilder에서 이름 규칙에 대한 설정이 가능합니다.
//    setFieldNamingPolicy
// > 코틀린의 공식 라이브러리로 JSON Serialization / Deserialization을 지원합니다.
//   Experimental
data class GithubUser(
    val login: String,
    val id: Int,
    // @field:SerializedName("avatar_url")
    val avatarUrl: String,
    val name: String,
    val email: String?
)

class MainActivity5 : AppCompatActivity() {
    private val binding: MainActivity5Binding by viewBinding()

    companion object {
        // const val TAG = "MainActivity5"
        val TAG: String = MainActivity5::class.java.simpleName
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val httpClient = OkHttpClient.Builder().apply {
            addInterceptor(HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            })
        }.build()
        val gson = GsonBuilder().apply {
            setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
        }.create()

        binding.loadButton.setOnClickListener {
            val request = Request.Builder().apply {
                url("https://api.github.com/users/JakeWharton")
                get()
            }.build()

            val call: Call = httpClient.newCall(request)

            // 두개 이상의 람다 표현식을 전달할 때는,
            // 파라미터 라벨을 지정하는 것이 좋습니다.
            /*
            call.enqueue({
            }, {
            })

            call.enqueue({}) {}
            */
            call.enqueue(
                onResponse = { response ->
                    if (!response.isSuccessful)
                        return@enqueue

                    val json = response.body?.string()
                    val user = gson.fromJson(json, GithubUser::class.java)

                    Log.i(TAG, "user: $user")

                    runOnUiThread {
                        Toast.makeText(
                            this,
                            "Hello, ${user.name}",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                },
                onFailure = { e ->
                    Log.e(TAG, e.localizedMessage, e)
                }
            )

            // call.execute() : 동기
            // call.enqueue() : 비동기 - 별도의 스레드에서 수행된다.
            //    - 별도의 스레드에서 수행되는 결과를 콜백을 통해 처리합니다.
            /*
            call.enqueue(object : Callback {
                override fun onFailure(call: Call, e: IOException) {
                    Log.e(TAG, e.localizedMessage, e)
                }

                override fun onResponse(call: Call, response: Response) {
                    if (!response.isSuccessful)
                        return

                    val json = response.body?.string()
                    val user = gson.fromJson(json, GithubUser::class.java)

                    Log.i(TAG, "user: $user")

                    runOnUiThread {
                        Toast.makeText(
                            this@MainActivity5,
                            "Hello, ${user.name}",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
            })
             */
        }


        /*
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

            /*
            Thread(object: Runnable {
                override fun run() {
                    TODO("Not yet implemented")
                }
            }).start()
            */

            Thread {
                // 4. 동기 수행
                try {
                    val response: Response = call.execute()

                    // val code = response.code
                    // if (code !in 200..299) {
                    //    return@setOnClickListener
                    // }

                    if (!response.isSuccessful) {
                        // return@setOnClickListener
                        return@Thread
                    }

                    // val json = response.body?.string() ?: return@setOnClickListener
                    val json = response.body?.string() ?: return@Thread
                    Log.i(TAG, "json: $json")

                    val gson = GsonBuilder().apply {
                        setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                    }.create()

                    val user: GithubUser = gson.fromJson(json, GithubUser::class.java)
                    Log.i(TAG, "user: $user")

                    runOnUiThread {
                        Toast.makeText(this, "Hello, ${user.name}", Toast.LENGTH_SHORT).show()
                    }

                } catch (e: IOException) {
                    // 서버에 접속할 수 없다.
                    // 1) 핸드폰이 인터넷이 되지 않습니다.
                    // 2) 서버가 접속할 수 없는 상태입니다.
                    Log.e(TAG, e.localizedMessage, e)
                }
            }.start()
        }
        */

    }
}

// OKHttp - Call의 Callback을 사용할 때
//          람다표현식을 이용하고 싶다.
//  - 인라인 함수 안에서 다른 함수에서 호출되는 함수가 인라인이 필요합니다.
inline fun Call.enqueue(
    crossinline onResponse: (response: Response) -> Unit,
    crossinline onFailure: (e: IOException) -> Unit
) {
    enqueue(object: Callback {
        override fun onFailure(call: Call, e: IOException) = onFailure(e)
        override fun onResponse(call: Call, response: Response)
         = onResponse(response)
    })
}









