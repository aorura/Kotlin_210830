// 14_확장프로퍼티.kt
package ex14

 //   확장 함수: 기존 클래스의 메소드처럼 보이게 하는 문법
// 확장 프로퍼티: 기존 클래스의 프로퍼티처럼 보이게 하는 문법
//   프로퍼티 종류
//    1) Backing Field가 있는 프로퍼티 - 확장이 불가능합니다.
//    2) Backing Field가 없는 프로퍼티 - 확장 프로퍼티
//      : val(getter)
//        var(getter + setter)

fun String.lastChar(): Char = this[length - 1]

val String.lastChar: Char
    get() = this[length - 1]

fun main() {
    val result = "hello".lastChar()
    println(result)

    val result2 = "hello".lastChar
}