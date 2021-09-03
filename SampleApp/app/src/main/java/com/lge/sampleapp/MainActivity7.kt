package com.lge.sampleapp

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import coil.load
import coil.transform.CircleCropTransformation
import coil.transform.GrayscaleTransformation
import com.google.gson.FieldNamingPolicy
import com.google.gson.GsonBuilder
import com.jakewharton.rxbinding4.view.clicks
import com.lge.sampleapp.databinding.MainActivity5Binding
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.ObservableEmitter
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.kotlin.Observables
import io.reactivex.rxjava3.kotlin.addTo
import io.reactivex.rxjava3.kotlin.plusAssign
import io.reactivex.rxjava3.kotlin.subscribeBy
import io.reactivex.rxjava3.subjects.BehaviorSubject
import io.reactivex.rxjava3.subjects.PublishSubject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import java.util.concurrent.TimeUnit

// RxJava / RxKotlin / RxAndroid
// => 의존성 추가
// implementation 'io.reactivex.rxjava3:rxjava:3.1.1'
// implementation 'io.reactivex.rxjava3:rxkotlin:3.0.1'
// implementation 'io.reactivex.rxjava3:rxandroid:3.0.0'

// => Retrofit은 Call을 Observable로 변경해서 사용할 수 있는 기능을 제공합니다.
// => Call Adapter
// implementation 'com.squareup.retrofit2:adapter-rxjava3:2.9.0'

// UI의 이벤트에 대한 처리도 Rx를 통해 처리하고 싶다면,
//  => rxbinding
// implementation 'com.jakewharton.rxbinding4:rxbinding:4.0.0'
// implementation 'com.jakewharton.rxbinding4:rxbinding-core:4.0.0'
// implementation 'com.jakewharton.rxbinding4:rxbinding-appcompat:4.0.0'
// implementation 'com.jakewharton.rxbinding4:rxbinding-material:4.0.0'


// Reactive eXtension
// : 비동기의 연산을 컬렉션을 다루는 일반적인 연산처럼 처리할 수 있습니다.

//  Iterable     - pull  -    Iterator
//                           - hasNext(): Boolean
//                           - next(): E

//   forEach / map / flatMap / filter...
//------------------------------------------
//
// - 비동기의 연산은 어떤 시점에 수행되는지 알 수 없습니다.
// Observable    - push  -   Observer
//                           - onNext(E e)
//                           - onComplete()
//                           - onError(Throwable)
//
// Rx 구성 요소 5가지
// 1) Observable
//  : 이벤트가 발생하는 주체로, 이벤트 스트림을 통해 이벤트를 옵저버에게 전달합니다.
// 2) Observer
//  : Observable에서 발생한 이벤트에 반응하는 객체
//    이벤트가 발생하였을 때 수행할 작업을 정의합니다.
//
//   "Observer가 Observable을 구독(subscribe)합니다."
//    => 이벤트 스트림이 형성됩니다.
// 3) Operator
//  : 연산자는 이벤트 스트림을 통해 전달되는 이벤트에 연산을 수행할 수 있습니다.
//
// 4) Scheduler
//  : 작업이 수행되는 스레드 컨텍스트를 지정합니다.
//
// 5) Disposable
//  : Observer가 Observable을 구독할 때 형성되는 이벤트 스트림의 자원을 관리하는 객체입니다.
//   => 유효하지 않을 경우, 해지를 해주어야 합니다.

interface GithubApi {
    @GET("/users/{login}")
    fun fetchUser(@Path("login") login: String): Call<GithubUser>

    @GET("/users/{login}")
    fun fetchUserRx(@Path("login") login: String): Observable<GithubUser>

    @GET("/search/users")
    fun searchUsers(@Query("q") query: String): Call<SearchResponse>

    @GET("/search/users")
    fun searchUsersRx(@Query("q") query: String): Observable<SearchResponse>
}


val retrofit: Retrofit = Retrofit.Builder().apply {
    baseUrl("https://api.github.com")

    val gson = GsonBuilder().apply {
        setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
    }.create()
    addConverterFactory(GsonConverterFactory.create(gson))
    addCallAdapterFactory(RxJava3CallAdapterFactory.create())
}.build()

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

    // var disposable: Disposable? = null
    private val compositeDisposable = CompositeDisposable()

    override fun onDestroy() {
        super.onDestroy()
        // disposable?.dispose()
        compositeDisposable.dispose()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        // binding.loadButton.setOnClickListener(this::onLoadButtonClicked1)
        // binding.loadButton.setOnClickListener(this::onLoadButtonClicked2)
        // binding.loadButton.setOnClickListener(this::onLoadButtonClicked3)
        // binding.loadButton.setOnClickListener(this::onLoadButtonClicked4)

        // 화면이 종료되는 시점에, 이벤트 스트림에 대한 종료 처리가 필요합니다.
        compositeDisposable += binding.loadButton.clicks() // Observable<Unit>
            .throttleFirst(1, TimeUnit.SECONDS)
            // Observable<Unit> -> flatMap -> Observable<GithubUser>
            // .flatMap {
            //    githubApi.searchUsersRx("hello")
            // }
            .switchMap {
                githubApi.searchUsersRx("hello")
            }
            .filter { response ->
                response.items.isNotEmpty()
            }
            .map { response ->
                response.items.shuffled().first().login
            }
            .doOnDispose {
                Log.i(TAG, "onDispose")
            }
            .flatMap(githubApi::fetchUserRx)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeBy(
                onNext = { user ->
                    this.update(user)
                    Log.i(TAG, "onNext: ${user.login}")
                },
                onError = { t ->
                    Log.e(TAG, "onError: ${t.localizedMessage}", t)
                },
                onComplete = {
                    Log.i(TAG, "onComplete")
                })
            // .addTo(compositeDisposable)

        //compositeDisposable.add(disposable)
    }

    fun fetchUserRx(login: String): Observable<GithubUser> {
        return Observable.create { emitter: ObservableEmitter<GithubUser> ->

            githubApi.fetchUser(login)
                .enqueue(object : Callback<GithubUser> {
                    override fun onResponse(
                        call: Call<GithubUser>,
                        response: Response<GithubUser>
                    ) {
                        val user = response.body() ?: return
                        emitter.onNext(user)
                        emitter.onComplete()
                    }

                    override fun onFailure(call: Call<GithubUser>, t: Throwable) {
                        emitter.onError(t)
                    }
                })
        }
    }

    private fun onLoadButtonClicked1(view: View) {
        // val observable: Observable<GithubUser> = githubApi.fetchUserRx("JakeWharton")
        val observable = fetchUserRx("JakeWharton")

        // Observable에 구독이 일어나면, 이벤트 스트림이 형성되고, 데이터를 전달 받을 수 있습니다.
        //                    subscribeBy - RxKotlin
        // AndroidSchedulers.mainThread() - RxAndroid
        observable
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeBy(
                onNext = { user ->
                    Log.i(TAG, "onNext: $user")
                    update(user)
                },
                onError = {
                    Log.i(TAG, "onError")
                },
                onComplete = {
                    Log.i(TAG, "onComplete")
                }
            )
    }

    private fun onLoadButtonClicked2(view: View) {
        // List<T> -> map -> List<U>
        githubApi.searchUsersRx("hello")  // Observable<SearchResponse>
            .filter {
                it.items.isNotEmpty()
            }
            // Observable<SearchResponse> -> map -> Observable<String>
            .map {
                it.items.shuffled().first().login
            }
            // Observable<String> -> map -> Observable< Observable<GithubUser> >
            // Observable<String> -> flatMap -> Observable<GithubUser>
            // .flatMap { login ->
            //    githubApi.fetchUserRx(login)
            // }
            .flatMap(githubApi::fetchUserRx)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeBy(
                onNext = this::update,
                onError = {

                }
            )

    }


    // 동기
    //  aUser = fetchUser(a)
    //  bUser = fetchUser(b)
    //   - id가 더 작은 사람의 정보를 화면에 출력하고 싶다.
    //  if (aUser.id > bUser.id)
    //    update(bUser)
    //  else
    //    update(aUser)

    private fun onLoadButtonClicked3(view: View) {
        val aUserObservable = githubApi.fetchUserRx("Google")
        val bUserObservable = githubApi.fetchUserRx("Apple")
        // zip
        /*
        Observable
            .zip(aUserObservable, bUserObservable, { aUser: GithubUser, bUser: GithubUser ->
                if (aUser.id > bUser.id)
                    bUser
                else
                    aUser
            })
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeBy(onNext = this::update)
        */

        // merge - 두 개이상의 Observable을 하나의 이벤트 스트림을 통해 처리할 수 있습니다.
        Observable.merge(aUserObservable, bUserObservable)
            .doOnNext {
                Log.i(TAG, "doOnNext")
            }
            .doOnComplete {
                Log.i(TAG, "doOnComplete")
            }
            .doOnSubscribe {
                Log.i(TAG, "doOnSubscribe")
            }
            .subscribeBy(
                onNext = { user ->
                    Log.i(TAG, "onNext: $user")
                },
                onComplete = {
                    Log.i(TAG, "onComplete")
                }
            )
    }

    private fun onLoadButtonClicked4(view: View) {
        Log.i(TAG, "onLoadButtonClicked4")
    }

}










