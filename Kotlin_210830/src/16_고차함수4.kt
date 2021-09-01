// 16_고차함수4.kt
package ex16_4

// 고차 함수
// 1) 함수를 인자로 전달하는 함수
// => 다양한 시나리오에서 동작하는 함수의 코드 중복을 없앨 수 있다.
//   "재사용"

// 2) 함수를 반환하는 함수
// - (String) -> String 함수를 반환합니다.
// 1) 실행 시간에 함수를 생성할 수 있다.
//   => 함수의 코드 중복을 제거한다.

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
    // k, r - 클로저 캡쳐가 수행된 변수는
    //        람다 표현식이 사라지는 시점까지 유효합니다.
    return { e: Int ->
        e % k == r
    }
}

fun goo(a: Int) {
    val b = 10
    // a, b는 goo 함수가 시작되는 시점에 생성되었다가,
    // goo 함수가 끝나는 시점에 사라집니다.
}

// 함수형 프로그래밍 - 핵심 문법
// 1) 람다 표현식
// 2) 클로저
fun main() {
    val isEven = modulo(2, 0)

    val isOdd = modulo(2, 1)
    val list = listOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)

    val result2 = list.filter(isOdd)
    println(result2)

    val fn: (String) -> String = foo1()
    val result = fn("hello")
    println(result)
}