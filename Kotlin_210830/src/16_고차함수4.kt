// 16_고차함수4.kt
package ex16_4

// 고차 함수
// 1) 함수를 인자로 전달하는 함수
// => 다양한 시나리오에서 동작하는 함수의 코드 중복을 없앨 수 있다.
//   "재사용"

// 2) 함수를 반환하는 함수
// - (String) -> String 함수를 반환합니다.
fun foo1(): (String) -> String {
    return { text ->
        text.reversed()
    }
}

fun foo2(): (String) -> String = { text ->
    text.reversed()
}

// fun isOdd(e: Int) = e % 2 == 1
// fun isEven(e: Int) = e % 2 == 0

// isOdd / isEven 의 함수를 실행시간에 생성하는 함수를 만들어 봅시다.
// isOdd = module(2, 1)
// isEven = module(2, 0)
fun modulo(k: Int, r: Int): (e: Int) -> Boolean {
    return { e: Int ->
        e % k == r
    }
}

fun main() {
    val isOdd = modulo(2, 1)
    val list = listOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)

    val result2 = list.filter(isOdd)
    println(result2)


    val fn: (String) -> String = foo1()

    val result = fn("hello")
    println(result)
}