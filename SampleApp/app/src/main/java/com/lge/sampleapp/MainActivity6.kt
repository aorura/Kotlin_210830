package com.lge.sampleapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.gson.FieldNamingPolicy
import com.google.gson.GsonBuilder
import com.lge.sampleapp.databinding.MainActivity5Binding
import retrofit2.Call
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

class SearchResponse

// Retrofit 사용 방법
// 1. 인터페이스를 정의합니다.
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

// 3. 요청을 처리하는 객체를 생성
val githubApi: GithubApi = retrofit.create(GithubApi::class.java)


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