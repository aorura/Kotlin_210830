// 11_위임3.kt
package ex11_3

import java.util.function.Predicate
import kotlin.reflect.KProperty

/*
class SampleDelegate(var value: String) {
    operator fun getValue(thisRef: User, property: KProperty<*>): String {
        return value
    }

    operator fun setValue(thisRef: User, property: KProperty<*>, value: String) {
        this.value = value
    }
}
*/


// Generic
// 1) 값이 조건에 부합되어야만 변경되도록 하고 싶습니다.

// 2) 프로퍼티의 값의 변경을 알 수 있는 위임 객체
interface ValueChanged<T> {
    fun onValueChanged(old: T, new: T)
}

class SampleDelegate<T>(
    var value: T,
    private val predicate: Predicate<T>? = null,
    private val onValueChanged: ValueChanged<T>? = null
) {
    operator fun getValue(thisRef: User, property: KProperty<*>): T {
        return value
    }

    // 언어적으로 어떤 문법을 사용했을 때, 약속된 메소드가 호출됩니다.
    // => 연산자 오버로딩
    operator fun setValue(thisRef: User, property: KProperty<*>, value: T) {
        // ?: - Elvis Operator
        //    - null일때 기본식(Expression)을 지정할 수 있습니다.
        val test: Boolean = predicate?.test(value) ?: true
        if (test) {
            val old = this.value
            val new = value

            this.value = value
            onValueChanged?.onValueChanged(old, new)
        }
    }
}


/*
interface Predicate<T> {
    fun test(e: T): Boolean
}
*/

class IsValidName : Predicate<String> {
    override fun test(t: String): Boolean {
        return t.length >= 3
    }
}

class User {
    operator fun plusAssign(i: Int) {
        println("User에 ${i}를 더했습니다.")
    }

    var name: String by SampleDelegate(
        "",
        predicate = IsValidName(),
        onValueChanged = object: ValueChanged<String> {
            override fun onValueChanged(old: String, new: String) {
                println("name: $old -> $new")
            }
        }
    )

    var age: Int by SampleDelegate(0, predicate = object : Predicate<Int> {
        override fun test(t: Int): Boolean {
            return t > 0
        }
    })
}

fun main() {
    val user = User()
    user += 10

    user.name = "Bob"
    user.name = "Tom"
    println(user.name)
}