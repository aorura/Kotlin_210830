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
// fun lastChar(text: String): Char {}

val String.lastChar: Char
    get() = this[length - 1]
// fun String.getLastChar(): Char {}
// fun getLastChar(text: String): Char {}

var StringBuilder.lastChar: Char
    get() = this[length - 1]
    set(value) = setCharAt(length - 1, value)

// 확장 메소드나 확장 함수도 외부에 있는 함수이기 때문에,
// 해당 클래스가 외부로 공개한 메소드나 프로퍼티만 접근이 가능합니다.
fun main() {
    val sb = StringBuilder("hello")
    sb.lastChar = 'O'
    println(sb.lastChar)

    val result = "hello".lastChar()
    println(result)

    val result2 = "hello".lastChar
}