// 11_위임5.kt
package ex11_5

import kotlin.reflect.KProperty

class SampleDelegate(var value: String) {
    operator fun getValue(thisRef: User, property: KProperty<*>): String {
        return value
    }

    operator fun setValue(thisRef: User, property: KProperty<*>, value: String) {
        this.value = value
    }
}

class User {

}