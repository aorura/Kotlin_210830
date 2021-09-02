package com.lge.sampleapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment

// => Single Activity Architecture
// : 액티비티가 화면을 보여주는 역활을 수행하는 것이 아니라,
//   프래그먼트를 호스팅하는 역활을 수행합니다.
//   => 호스트 액티비티
//    : 프래그먼트를 호스팅할 영역을 지정합니다.

// View Binding
// => 기존의 레이아웃의 이름 규칙이 변경되는 것이 좋습니다.
//  activity_main.xml => ActivityMainBinding
//  main_activity.xml => MainActivityBinding
class MainActivity2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
    }
}

class MainFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(
            R.layout.main_fragment,
            container,
            false
        )
        return view
    }
}












