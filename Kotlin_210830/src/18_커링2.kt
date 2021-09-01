// 18_커링2.kt
// 기존 함수에 대한 커링 버전의 함수를 실행시간에 생성하는 함수
package ex18_2

fun sum2(a: Int, b: Int): Int = a + b

// 인자가 2개인 함수에 대한 커링 버전의 함수를 생성하는 함수
// : (P1, P2) -> R

fun <P1, P2, R> ((P1, P2) -> R).curried(): (P1) -> (P2) -> R = { p1 ->
    { p2 ->
        this(p1, p2)
    }
}


fun main() {
    println(sum2(10, 20))

    val fn = ::sum2.curried()
    println(fn(10)(20))
}