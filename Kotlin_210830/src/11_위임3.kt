// 11_위임3.kt
package ex11_3

import com.sun.org.apache.xpath.internal.operations.Bool
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
class SampleDelegate<T>(var value: T, private val predicate: Predicate<T>? = null) {
    operator fun getValue(thisRef: User, property: KProperty<*>): T {
        return value
    }

    operator fun setValue(thisRef: User, property: KProperty<*>, value: T) {
        // ?: - Elvis Operator
        //    - null일때 기본식(Expression)을 지정할 수 있습니다.
        val test: Boolean = predicate?.test(value) ?: true
        if (test) {
            this.value = value
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
    var name: String by SampleDelegate("", predicate = IsValidName())
    var age: Int by SampleDelegate(0, predicate = object : Predicate<Int> {
        override fun test(t: Int): Boolean {
            return t > 0
        }
    })
}

fun main() {
    val user = User()
    user.name = "Bob"
    println(user.name)
}