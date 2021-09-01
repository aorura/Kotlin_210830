// 19_인라인2
package ex19

// inline
// 1) 함수가 인자를 함수로 받을 경우

// Android
// val intent = Intent(this, MainActivity::class.java)
// startActivity(intent)

open class Context

class Intent(context: Context, val clazz: Class<out Activity>)
open class Activity : Context() {
    open fun onCreate() {
    }

    fun startActivity(intent: Intent) {
        // Class: 클래스의 클래스 타입(Reflection)
        val clazz = intent.clazz
        val activity = clazz.newInstance()
        activity.onCreate()
    }
}
//---------

class MainActivity : Activity() {
    override fun onCreate() {
        super.onCreate()

        val intent = Intent(this, SecondActivity::class.java)
        startActivity(intent)
    }
}

class SecondActivity : Activity() {
    override fun onCreate() {
        super.onCreate()
        println("SecondActivity onCreate")
    }
}


fun main() {
    val activity = MainActivity()
    activity.onCreate()
}


