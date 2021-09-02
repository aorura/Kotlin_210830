package com.lge.sampleapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView

// 1. build.gradle
//   - project-level build.gradle
//    : classpath "org.jetbrains.kotlin:kotlin-gradle-plugin:1.5.30"
//   - module-level  build.gradle
//    plugins {
//        id 'kotlin-android'
//    }
//    android {
//        kotlinOptions {
//            jvmTarget = '1.8'
//        }
//    }
//    dependencies {
//        implementation 'androidx.core:core-ktx:1.6.0'
//    }

//   ktx: Kotlin Extensions
//   => 안드로이드의 기능을 코틀린의 확장 함수 라이브러리를 통해 사용할 수 있도록 하는
//      라이브러리입니다.
//   => fragment-ktx
//      viewmodel-ktx

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // 1. findViewById
        val button: Button = findViewById(R.id.button)
        val textView = findViewById<TextView>(R.id.textView)

        // SAM 지원: 람다 표현식을 사용할 수 있습니다.
        /*
        button.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                textView.text = "Hello, Kotlin"
            }
        })
        */
        button.setOnClickListener {
            textView.text = "Hello, Kotlin"
        }

        // 2. kotlin-android-extension : plugin
        //  > Deprecated

    }
}








