// 02_기본문법.kt
package ex2

import java.io.Serializable

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

//   Java: void foo() {}
// Kotlin: void -> Unit
//    Unit - 결과가 존재하지 않음을 나타내는 값
//  "순수 함수: 함수의 인자가 동일하면, 결과가 동일하다."
fun foo() {
    println("foo")
}

/*
fun main() {
    println(add(10, 20))
    // println(foo())
    println(Sample.foo())
}
*/
// 3. 타입 시스템
//  Java
//   1) Primitive Type
//     int, double, char, byte, long ...
//    => value type으로 동작합니다.
//    => ArrayList 같은 컬렉션에 저장할 수 없습니다.
//      : int    -> Integer(Wrapper class)
//        double -> Double
//    => Auto boxing / unboxing을 제공합니다.
//    => 객체 타입이 아니기 때문에, 필드와 메소드를 가질수 없습니다.

//   2) Reference Type(객체)
//     class, interface, enum. array
//   => 참조 계수 기반으로 객체의 수명의 관리됩니다.
//   => 필드와 메소드를 가질 수 있습니다.
//------------------------------------------------------
//  Kotlin
//   => 모든 타입은 객체 타입입니다.
//    : 모든 타입은 필드와 메소드를 가지고 있습니다.
//   => 강타입 언어입니다.
//    : 암묵적인 타입 변환을 거의 허용하지 않습니다.

// 4. 변수 정의하는 방법
//   Java
//    int n = 42;
//   Kotlin
//    val: final int n = 42;
//    var: int n = 42;
//   1) 타입 추론
//     val n = 42   // n: Int
//   2) 명시적 타입 지정
//     val n: Long = 42

fun main() {
    println(42.toDouble())

    val n: Int = 42
    val a: Long = n.toLong()
}

/*
internal interface State : Serializable
internal interface View {
    var state: State?
}
*/








