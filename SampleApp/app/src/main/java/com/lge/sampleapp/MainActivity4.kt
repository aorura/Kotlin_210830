package com.lge.sampleapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.commit

// KTX - 코틀린 확장 함수 라이브러리 입니다.
// : https://developer.android.com/kotlin/ktx/extensions-list?hl=ko
// core-ktx
// fragment-ktx
//  :  implementation 'androidx.fragment:fragment-ktx:1.3.6'
class MainActivity4 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)

        if (savedInstanceState == null) {
            val fragment = ListFragment2()

            // supportFragmentManager.beginTransaction()
            //    .add(R.id.fragmentContainer, fragment)
            //    .commit()
            supportFragmentManager.commit {
                add(R.id.fragmentContainer, fragment)
            }
        }


        /*
        // NotificationManager
        // - getSystemService
        // val notificationManager: NotificationManager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager

        // Java
        // val notificationManager: NotificationManager = getSystemService(NotificationManager::class.java)

        // core-ktx
        val notificationManager = getSystemService<NotificationManager>()
        //------------------------------------

        // SharedPreferences
        val sharedPref = getSharedPreferences("com.lge.sampleapp.javasample", MODE_PRIVATE)

        // 읽기
        val isEnable = sharedPref.getBoolean("isEnable", false)

        // 쓰기
        val editor = sharedPref.edit()
        editor.putBoolean("isEnable", isEnable)
        editor.apply()

        // core-ktx
        sharedPref.edit {
            putBoolean("isEnable", isEnable)
        }
        */

    }
}








