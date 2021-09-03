package com.lge.sampleapp.javasample

import android.app.NotificationManager
import android.content.SharedPreferences
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.edit
import androidx.core.content.getSystemService

// KTX - 코틀린 확장 함수 라이브러리 입니다.
// core-ktx

class MainActivity4 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

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

    }
}








