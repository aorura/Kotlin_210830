// 11_위임5.kt
package ex11_5

import kotlin.reflect.KProperty

class SampleDelegate<T>(
    var value: T,
    val onValueChanged: (old: T, new: T) -> Unit
    ) {
    operator fun getValue(thisRef: User, property: KProperty<*>): T {
        return value
    }

    operator fun setValue(thisRef: User, property: KProperty<*>, value: T) {
        val old = this.value
        this.value = value

        onValueChanged(old, value)
    }
}

class User {
    var name: String by SampleDelegate("Tom") { old, new ->
        println("$old -> $new")
    }
}

fun main() {
    val user = User()
    user.name = "Bob"
}