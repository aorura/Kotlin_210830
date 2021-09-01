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


// 변하지 않는 전체 알고리즘에서 변해야 하는 정책을 분리해야 합니다.
//  "공통성과 가변성의 분리"
//  - 함수에서 정책을 분리하는 방법
//    1) 인터페이스 기반 정책 분리 방법
//    2) 함수 기반 정책 분리 방법
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

fun filterEven(data: List<Int>): List<Int> {
    val result: MutableList<Int> = mutableListOf()

    for (e in data) {
        if (e % 2 == 0) {
            result.add(e)
        }
    }

    return result
}

fun main() {
    val list = listOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
    val result = filterEven(list)

    println(result)
}