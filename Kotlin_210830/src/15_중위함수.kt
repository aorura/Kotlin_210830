// 15_중위함수.kt
package ex15

// 1. Generic Function
//  : 타입에 독립적인 코드를 작성할 수 있습니다.
//   - 코틀린은 생성자의 인자를 통해, 제네릭 타입에 대한 추론이 가능합니다.
//    : 자바에서는 클래스 타입에 대한 추론이 불가능하기 때문에, 반드시 명시를 해줘야했습니다.
// fun<K, V> pair(key: K, value: V) = Pair(key, value)

// 2. Extension Function
// 3. 중위 함수 - infix function
//   : 인자가 한개인 메소드 또는 확장 함수를 중위 함수로 만들 수 있습니다.
infix fun<K, V> K.pair(value: V) = Pair(this, value)

fun main() {
    // Pair<String, Any>를 만드는 함수를 만들어봅시다.
    // => Map을 구성하는 요소로서 사용됩니다.
    // val pair1 = Pair<String, Any>("name", "Tom")
    // val pair1 = pair("name", "Tom")

    val pair4 = 42 pair 100

    val pair2 = "name".pair("Tom")
    val pair3 = "name" pair "Tom"       // infix

    val map = mapOf(
        "name" to "Tom",
        "age" to 42
    )

    // Infix
    //  Java: <<, >>(산술 쉬프트), >>>(논리 쉬프트), &, |, ^, ~
    // val x = 0xfafa
    // 코틀린은 2진수 표현 방법을 문법적으로 제공합니다.
    val x = 0b1111101011111010
    println(x)

    println(x shl 2)        // x << 2
    println(x shr 2)        // x >> 2
    println(x ushr 2)       // x >>> 3

    println(x and 0b1111) // x & 0xf
    println(x or 0b1111)  // x | 0xf
    println(x xor 0b1111) // x ^ 0xf

    println(x.inv()) // ~x
}