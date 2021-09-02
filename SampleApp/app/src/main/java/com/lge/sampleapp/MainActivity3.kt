package com.lge.sampleapp

import android.app.Activity
import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding
import com.lge.sampleapp.databinding.ActivityMainBinding
import kotlin.reflect.KProperty

// 코틀린에서 뷰바인딩을 사용하면서 발생하는 보일러플레이트를
// 프로퍼티 위임 객체를 통해 제거하는 방법이 있습니다.
//  => 공식 라이브러리가 아닙니다.
class ActivityBindingDelegate<T : ViewBinding>(
    private val bindingClass: Class<T>,
    val activity: Activity
) {
    private var binding: T? = null

    operator fun getValue(thisRef: Activity, property: KProperty<*>): T {
        // if (binding != null)
        //    return binding!!
        binding?.let {
            return it
        }

        // 1. inflate 메소드 찾기
        // Class<T> -> ActivityMainBinding::class.java
        // => 이 클래스 안에서 메소드를 찾아서 호출합니다.
        //  Binding.inflate(LayoutInflator)
        val inflateMethod = bindingClass.getMethod(
            "inflate",
            LayoutInflater::class.java
        )

        // 2. inflate 메소드 호출을 통해 바인딩 객체 생성하기
        // Unchecked cast: Any! to T
        @Suppress("UNCHECKED_CAST")
        binding = inflateMethod.invoke(null, thisRef.layoutInflater) as T
        return binding!!
    }
}


class MainActivity3 : AppCompatActivity() {

    private val binding: ActivityMainBinding by ActivityBindingDelegate(
        ActivityMainBinding::class.java,
        this
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // setContentView(R.layout.activity_main)
        // binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.button.setOnClickListener {
            binding.textView.text = "Hello"
        }


//        if (savedInstanceState == null) {
//            val fragment = ListFragment()
//
//            supportFragmentManager.beginTransaction()
//                .add(R.id.fragmentContainer, fragment)
//                .commit()
//        }
    }
}