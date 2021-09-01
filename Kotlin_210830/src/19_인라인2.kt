// 19_인라인2
package ex19_2

// inline
// 1) 함수가 인자를 함수로 받을 경우
// 2) reified Generic

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
// 액티비티 전환의 코드를 확장 함수를 통해서 제공해보자.
// - 아래 코드를 자바에서는 절대 구현이 불가능합니다.
//  Generic
// : 타입에 독립적인 알고리즘을 가지는 함수와 클래스를 만들 수 있는 문법입니다.

//  Generic 문법을 언어가 구현하는 방법
// 1) 코드 생성 방식
/*
fun print2(a: Int) {
}
fun print2(a: String) {
}
*/
// 컴파일러가 자동으로 코드를 생성합니다.
// 단점: 코드 메모리의 사용량이 증가합니다.
// 장점: 코드를 생성하는 기술을 이용해서 다양한 설계 기법을 사용할 수 있습니다.
//   => Meta Programming
//  - C++, C#, Swift

// 2) 타입 소거 방식
fun print2(a: Any) {
}
// 컴파일러는 T를 Any로 결정한 한개의 구현만 제공합니다.
// 모든 호출은 Any 버전을 이용합니다.
//  => 컴파일러가 컴파일 타임에 타입 체크를 하기 위한 목적으로만 사용되고,
//     실제 바이트코드 상에서는 제네릭에 대한 정보가 존재하지 않습니다.
// - 장점: 코드 메모리 사용량의 증가가 없습니다.
//        오직 한개의 구현만 제공됩니다.
// - 단점: 활용이 제한적입니다.
// print2(42)       // T -> Int
// print2("Hello")  // T -> String

inline fun<reified T: Activity> Activity.startActivity() {
    val intent = Intent(this, T::class.java)
    startActivity(intent)
}

class MainActivity : Activity() {
    override fun onCreate() {
        super.onCreate()

        val intent = Intent(this, SecondActivity::class.java)
        startActivity(intent)

        startActivity<SecondActivity>()
        //    val intent = Intent(this, SecondActivity::class.java)
        //    startActivity(intent)
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


