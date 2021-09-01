// 16_고차함수.kt
// - Higher Order Function
// > 함수를 인자로 전달받거나, 함수를 반환하는 함수
package ex16

// Kotlin
//    List<E>     : Immutable
//        |
// MutableList<E> : Mutable
// Set / MutableSet
// Map / MutableMap
// > 코틀린은 컬렉션의 인터페이스가 불변과 가변이 구분되어
//   있습니다.


fun filterOdd(data: List<Int>): List<Int> {
    // val result: List<Int> = emptyList()
    val result: MutableList<Int> = mutableListOf()

    for (e in data) {
        if (e % 2 == 1) {
            result.add(e)
        }
    }

    return result
}

fun main() {
    val list = listOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
    val result = filterOdd(list)

    println(result)
}