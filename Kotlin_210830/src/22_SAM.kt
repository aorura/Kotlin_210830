// 22_SAM.kt
package ex22

import java.util.function.Predicate

// Kotlin 1.4 부터는 코틀린의 SAM 인터페이스에 대해서도 람다 표현식을
// 사용할 수 있습니다.
fun interface Predicate2<E> {
    fun test(e: E): Boolean
}

fun goo(predicate: Predicate2<Int>) {
    predicate.test(42)
}

fun foo(predicate: Predicate<Int>) {
    predicate.test(42)
}

fun main() {
    goo(object : Predicate2<Int> {
        override fun test(e: Int): Boolean = e > 40
    })
    goo { e -> e > 40 }

    foo(object : Predicate<Int> {
        override fun test(e: Int): Boolean = e > 40
    })
    foo { e -> e > 40 }
    // 자바의 인터페이스 중에서 한개의 추상 메소드를 제공하는 인터페이스일 경우
    // 람다 표현식을 통해 인자를 전달할 수 있습니다.
    // => SAM(Single Abstract Method) 지원 문법
    //  : 자바에서는 한개의 추상 메소드를 가지고 있는 인터페이스를 함수형 인터페이스 라고 합니다.

}

