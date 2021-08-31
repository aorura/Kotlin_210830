// 10_object.kt
package ex10


// object 용법 3가지
// 1. object 선언 - Singleton Pattern
//    : constructor를 제공할 수 없습니다.
object Cursor {
    // 초기화 블록을 이용해서 초기화를 수행해야 합니다.
    init {
        println("Cursor 객체 생성")
    }

    fun move() {
        println("Move")
    }
}

fun main() {
    println("Main start")
    Cursor.move()
}