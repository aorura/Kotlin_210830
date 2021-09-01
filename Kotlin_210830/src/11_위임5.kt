// 11_위임5.kt
package ex11_5

import kotlin.reflect.KProperty

class SampleDelegate<T>(
    var value: T,
    val onValueChanged: ((old: T, new: T) -> Unit)? = null
) {
    operator fun getValue(thisRef: User, property: KProperty<*>): T {
        return value
    }

    operator fun setValue(thisRef: User, property: KProperty<*>, value: T) {
        val old = this.value
        this.value = value

        onValueChanged?.invoke(old, value)
    }
}

// SampleDelegate를 생성하는 함수를 제공합니다.
fun <T> observable(
    value: T,
    onValueChanged: ((old: T, new: T) -> Unit)? = null
) = SampleDelegate(value, onValueChanged)

class User {
    var name: String by observable("Tom") { old, new ->
        println("$old -> $new")
    }

    var age: Int by observable(0)
}

fun main() {
    val user = User()
    user.name = "Bob"
}