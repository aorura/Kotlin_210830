package com.lge.sampleapp

import android.content.Intent
import android.os.Bundle
import android.os.Parcel
import android.os.Parcelable
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.parcel.Parcelize
import kotlinx.android.synthetic.main.activity_main.*

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
        // val button: Button = findViewById(R.id.button)
        // val textView = findViewById<TextView>(R.id.textView)

        // SAM 지원: 람다 표현식을 사용할 수 있습니다.
        /*
        button.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                textView.text = "Hello, Kotlin"
            }
        })
        */


        // 2. kotlin-android-extension : plugin
        //  1) findViewById를 사용하지 않고,
        //     인플레이팅 된 뷰의 참조를 얻을 수 있습니다.
        //  2) Parcelable
        //    => Parcel 기반의 객체를 만들 때 사용되는 보일러플레이트를 제거합니다.
        //    => @Parcelize
        //  > Deprecated
        // import kotlinx.android.synthetic.main.activity_main.*
        button.setOnClickListener {
            // textView.text = "Hello, Kotlin"
            val intent = Intent(this, SecondActivity::class.java)
            // putExtra(String!, Parcelable?)
            // putExtra(String!, Serializable?)
            intent.putExtra("user", User("Tom", 42))
            startActivity(intent)
        }
    }
}

@Parcelize
data class User(val name: String, val age: Int) : Parcelable
/*
data class User(val name: String, val age: Int) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString()!!,
        parcel.readInt()
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(name)
        parcel.writeInt(age)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<User> {
        override fun createFromParcel(parcel: Parcel): User {
            return User(parcel)
        }

        override fun newArray(size: Int): Array<User?> {
            return arrayOfNulls(size)
        }
    }
}
*/

class SecondActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        val user = intent.getParcelableExtra<User>("user")
        if (user != null) {
            Toast.makeText(this, "User - $user", Toast.LENGTH_SHORT).show()
        }
    }
}






