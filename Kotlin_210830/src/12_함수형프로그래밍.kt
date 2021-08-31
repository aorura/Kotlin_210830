// 12_함수형프로그래밍.kt
package ex12

// 함수형 언어
// - Haskell, Lisp, Erlang, Scala
// => Kotlin
// 1) 함수를 변수에 담을 수 있어야 합니다.
// 2) 함수를 인자로 전달할 수 있어야 합니다.
// 3) 함수를 반환할 수 있어야 합니다.
// 4) 실행 시간에 함수를 생성할 수 있어야 합니다.
// 5) 함수를 익명으로 생성할 수 있어야 합니다.
//  : 함수를 일급 시민(First class citizen)으로 취급합니다.

// 1. 코틀린 함수는 단일 표현식 함수를 지원합니다.
fun add1(a: Int, b: Int): Int {
    return a + b
}

fun add2(a: Int, b: Int): Int = a + b
fun add3(a: Int, b: Int) = a + b
// 단일 표현식은 타입 추론이 가능합니다.
// - 반환 타입은 단일 표현식에 생략 가능합니다.

// val a: Int = 42   // 명시적인 타입 지정
// val a = 42        // 타입 추론

// 2. 함수의 타입
//  : 코틀린의 함수의 타입은 함수의 인자의 정보, 반환 타입에 의해서 결정됩니다.
//   => 함수의 타입은 함수의 시그니처에 의해 결정됩니다.
//   전역 함수는 ::으로 참조할 수 있습니다.

// 3. 함수의 타입을 표현하는 방법
fun add4(a: Int, b: Int): Int {
    return a + b
}
// (Int, Int) -> Int

// (Int, Char, Double) -> Unit
fun foo(a: Int, b: Char, c: Double) {
}

fun main() {
    val fn3: (Int, Char, Double) -> Unit = ::foo

    val fn1: (Int, Int) -> Int = ::add1
    var fn2: (Int, Int) -> Int = ::add2
    fn2 = ::add3

    println(fn1(10, 20)) // add1(10, 20)
}





