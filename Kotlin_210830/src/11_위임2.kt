// 11_위임2.kt
package ex11_2

import kotlin.reflect.KProperty

// 위임
// - 클래스 위임
// - 프로퍼티 위임
//  : 접근자 메소드에 대한 동작을 다른 객체에게 위임한다.

class SampleDelegate {
    // Type 'SampleDelegate' has no method
    // 'getValue(User, KProperty<*>)' and thus it cannot serve as a delegate
    operator fun getValue(thisRef: User, property: KProperty<*>): String {
        return "Tom"
    }

    // Type 'SampleDelegate' has no method
    // 'setValue(User, KProperty<*>, String)'
    operator fun setValue(thisRef: User, property: KProperty<*>, value: String) {
        println("value: $value")
    }
}

class User {
    var name: String by SampleDelegate()
    // name에 대한 getter 호출을 SampleDelegate 객체가 처리한다.
    // => 약속된 메소드를 호출하는 연산자 오버로딩으로 구현됩니다.
}

fun main() {
    val user = User()
    user.name = "Bob"
    println(user.name)
}
