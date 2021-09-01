// 16_고차함수2.kt
package ex16_2

// 변해야 하는 정책을 인터페이스 기반으로 분리합니다.
fun interface Predicate<E> {
    fun test(e: E): Boolean
}

// 동작 파라미터화 설계 - 정책을 인터페이스 기반의 인자를 통해 전달합니다.
fun filter(data: List<Int>, predicate: Predicate<Int>): List<Int> {
    val result: MutableList<Int> = mutableListOf()

    for (e in data) {
        if (predicate.test(e)) {
            result.add(e)
        }
    }

    return result
}

// Predicate 인터페이스를 구현하는 다양한 필터의 정책 클래스를 제공합니다.
class EvenPredicate : Predicate<Int> {
    override fun test(e: Int): Boolean = e % 2 == 0
}

class OddPredicate : Predicate<Int> {
    override fun test(e: Int): Boolean = e % 2 == 1
}

fun main() {
    val list = listOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
    var result = filter(list, EvenPredicate())
    result = filter(list, OddPredicate())

    // 익명의 객체
    result = filter(list, object : Predicate<Int> {
        override fun test(e: Int): Boolean = e % 2 == 0
    })
    result = filter(list) { e -> e % 2 == 0 }

    println(result)
}