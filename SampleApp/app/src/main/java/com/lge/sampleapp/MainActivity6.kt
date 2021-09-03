package com.lge.sampleapp

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import coil.load
import coil.transform.CircleCropTransformation
import coil.transform.GrayscaleTransformation
import com.bumptech.glide.Glide
import com.google.gson.FieldNamingPolicy
import com.google.gson.GsonBuilder
import com.lge.sampleapp.databinding.MainActivity5Binding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

// OkHttp 보일러플레이트
// 1. 각 요청마다 Request를 만들어야 합니다.
// => Retrofit
//  implementation 'com.squareup.retrofit2:retrofit:2.9.0'

// 2. 응답을 Gson을 이용해서 객체로 역직렬화 해주어야 합니다.
// => CONVERTERS
//  implementation 'com.squareup.retrofit2:converter-gson:2.9.0'

// 3. 콜백이 UI 스레드에서 수행됩니다.
// => runOnUiThread가 필요하지 않습니다.

data class SearchResponse(
    val totalCount: Int,
    val incompleteResults: Boolean,
    val items: List<GithubUser>
)

// Retrofit 사용 방법
// 1. 인터페이스를 정의합니다.
/*
interface GithubApi {
    @GET("/users/{login}")
    fun fetchUser(@Path("login") login: String): Call<GithubUser>

    @GET("/search/users")
    fun searchUsers(@Query("q") query: String): Call<SearchResponse>
}


// 2. Retrofit 객체 생성
val retrofit: Retrofit = Retrofit.Builder().apply {
    baseUrl("https://api.github.com")

    val gson = GsonBuilder().apply {
        setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
    }.create()
    addConverterFactory(GsonConverterFactory.create(gson))

}.build()
 */
// 3. 요청을 처리하는 객체를 생성
val githubApi: GithubApi = retrofit.create(GithubApi::class.java)

// 이미지 처리를 위한 라이브러리에 대한 의존성 추가
// => Glide
//  implementation 'com.github.bumptech.glide:glide:4.12.0'
//  annotationProcessor 'com.github.bumptech.glide:compiler:4.12.0'
//   Java: annotationProcessor
// Kotlin: kapt
//   kotlin-kapt 플러그인 추가가 필요합니다.
//   kapt 'com.github.bumptech.glide:compiler:4.12.0'
//  => 컴파일 타임에 어노테이션을 기반으로 코드를 생성하는 기술

// => coil-kt
//  implementation 'io.coil-kt:coil:1.3.2'
// '콜백' 문제점
//  => 실행 흐름 제어가 어렵다.
//   동기:
//    val ra = a()
//    val rb = b(ra)
//    val rc = c(rb)
//    // ...
//  비동기:
//    a({ ra ->
//       b(ra) { rb ->
//         c(rb) {
//            // ...
//         }
//       }
//    })
//
// 콜백 문제점 해결 방법
// 1) LiveData - Android
//   : 간단하고 직관적으로 사용할 수 있습니다.
//   => 제공하는 기능이 거의 없습니다.
// 2) RxJava / RxKotlin
//   : 비동기에 대한 처리를 효과적으로 수행할 수 있는 다양한 방법을 제공합니다.
//  => 러닝 커브가 있습니다.
//---------------------
// 3) Coroutine


class MainActivity6 : AppCompatActivity() {
    private val binding: MainActivity5Binding by viewBinding()

    companion object {
        val TAG: String = MainActivity6::class.java.simpleName
    }

    private fun update(user: GithubUser) {
        with(binding) {
            nameTextView.text = user.name
            loginTextView.text = user.login

            /*
            Glide.with(this@MainActivity6)
                .load(user.avatarUrl)
                .circleCrop()
                .into(profileImageView)
            */

            // coil
            // - Extension Function
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

        binding.loadButton.setOnClickListener {
            githubApi.searchUsers("hello")
                .enqueue(
                    onResponse = { response ->
                        if (!response.isSuccessful) {
                            return@enqueue
                        }

                        val result = response.body() ?: return@enqueue

                        val firstUserLogin = result.items
                            .shuffled()
                            .firstOrNull()?.login ?: return@enqueue


                        githubApi.fetchUser(firstUserLogin)
                            .enqueue(
                                onResponse = inner@{ responseUser ->
                                    if (!responseUser.isSuccessful)
                                        return@inner

                                    val user = responseUser.body() ?: return@inner
                                    update(user)
                                },
                                onFailure = { t ->
                                    Log.e(MainActivity5.TAG, t.localizedMessage, t)
                                }
                            )
                    },
                    onFailure = { t ->
                        Log.e(MainActivity5.TAG, t.localizedMessage, t)
                    }
                )


        }

    }

    /*
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.loadButton.setOnClickListener {
            githubApi.fetchUser("JakeWharton")
                .enqueue(
                    onResponse = { response ->
                        if (!response.isSuccessful)
                            return@enqueue

                        val user = response.body() ?: return@enqueue
                        Toast.makeText(
                            this,
                            "Hello, ${user.name}",
                            Toast.LENGTH_SHORT
                        ).show()
                    },
                    onFailure = { t ->
                        Log.e(MainActivity5.TAG, t.localizedMessage, t)
                    }
                )
            /*
            val call: Call<GithubUser> = githubApi.fetchUser("JakeWharton")
            call.enqueue(object : Callback<GithubUser> {
                override fun onResponse(
                    call: Call<GithubUser>,
                    response: Response<GithubUser>
                ) {
                    if (!response.isSuccessful)
                        return

                    val user = response.body() ?: return
                    Toast.makeText(
                        this@MainActivity6,
                        "Hello, ${user.name}",
                        Toast.LENGTH_SHORT
                    ).show()
                }

                override fun onFailure(
                    call: Call<GithubUser>,
                    t: Throwable
                ) {
                    Log.e(MainActivity5.TAG, t.localizedMessage, t)
                }
            })
            */
        }
    }
    */
}

inline fun <T> Call<T>.enqueue(
    crossinline onResponse: (response: Response<T>) -> Unit,
    crossinline onFailure: (t: Throwable) -> Unit
) {
    enqueue(object : Callback<T> {
        override fun onResponse(
            call: Call<T>,
            response: Response<T>
        ) = onResponse(response)

        override fun onFailure(
            call: Call<T>,
            t: Throwable
        ) = onFailure(t)
    })
}











