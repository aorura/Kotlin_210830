package com.lge.sampleapp

import android.os.Bundle
import android.os.Parcelable
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.lge.sampleapp.databinding.ActivityMainBinding
import kotlinx.parcelize.Parcelize

// import kotlinx.android.parcel.Parcelize
// : kotlin-android-extensions

// import kotlinx.parcelize.Parcelize
// : id 'kotlin-parcelize'

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

// Kotlin에서 자바의 코드를 인식할 때
//  -  String: @NonNull String
//  - String?: @Nullable String
//  - String!: String


class MainActivity : AppCompatActivity() {

    fun foo(): Int? {
        return 42
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        /*
        Integer n = null;
        n.toLong()       // NullPointerException

        val n: Int? = null
        if (n != null) {
            // ...
        }

        n!!.toLong()    // NullPointerException
        */

        // val n: Int? = 42
        // n!!.toLong()

        /*
        val result2: Long? = if (n != null) {
            n.toLong()
        } else {
            null
        }
        */

        // val result: Long? = n?.let { it.toLong() }
        // val result: Long? = n?.toLong()
        // n이 null이면 결과도 null 입니다.

        // setContentView(R.layout.activity_main)
        // activity_main.xml -> ActivityMainBinding
        val binding: ActivityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        /*
        binding.button.setOnClickListener {
            binding.textView.text = "Hello. Kotlin"
        }
        */

        with(binding) {
            button.setOnClickListener {
                textView.text = getString(R.string.hello_kotlin)
            }
        }

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
        //   => Deprecated
        // import kotlinx.android.synthetic.main.activity_main.*
        //   문제점
        //   1) import를 통해서 처리될 때, 동일한 아이디를 갖는 잘못된 참조가 전달될
        //      가능성이 있습니다.
        //   2) 캐싱 - findViewById가 비효율적으로 발생하는 문제를 해결하기 위해 사용되었지만,
        //      리사이클러뷰에서 제대로 수행되지 않는다.
        //   => view-binding 으로 대체해야 합니다.
        //    viewBinding {
        //        enabled = true
        //    }

        //  2) Parcelable
        //    => Parcel 기반의 객체를 만들 때 사용되는 보일러플레이트를 제거합니다.
        //    => @Parcelize
        //    => kotlin-parcelize 플러그인으로 분리되었습니다.
        /*
        button.setOnClickListener {
            textView.text = "Hello, Kotlin"
            val intent = Intent(this, SecondActivity::class.java)
            // putExtra(String!, Parcelable?)
            // putExtra(String!, Serializable?)

            val date = Date()

            // Parcelable
            intent.putExtra("user", User("Tom", 42))
            // Serializable
            intent.putExtra("date", date)

            startActivity(intent)
        }
        */
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
        // val date: Date = intent.getSerializableExtra("date") as Date
        if (user != null) {
            Toast.makeText(this, "User - $user", Toast.LENGTH_SHORT).show()
        }
    }
}






