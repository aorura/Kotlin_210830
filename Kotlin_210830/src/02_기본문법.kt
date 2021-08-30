// 02_기본문법.kt
package ex2

// 자바는 디렉토리를 통해 패키지가 구성되어야 하지만,
// 코틀린은 패키지를 그냥 사용할 수 있습니다.
// => 제품 코드에서는 기존의 자바의 형식을 따르는 것이 권장됩니다.

// 1. main 함수를 만드는 방법
// fun main(args: Array<String>)
// => Kotlin 1.3 이후, 사용하지 않는 args를 생략 가능합니다.
// => 코틀린에서는 전역 함수를 만들 수 있습니다.
//   : 자바에서는 전역 함수라는 문법이 제공되지 않았기 때문에,
//     별도의 유틸리티 함수를 클래스를 통해 캡슐화를 하였습니다.
//     : Arrays, Objects, Collections

// 2. 함수를 만드는 방법
//   Java: int add(int a, int b)
// Kotlin: fun 함수이름(인자이름: 인자타입, 인자이름: 인자타입): 반환타입 {}
fun add(a: Int, b: Int) : Int {
    return a + b
}

fun main() {
    println(add(10, 20))
}