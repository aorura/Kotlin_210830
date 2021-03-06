// 18_커링2.kt
// 기존 함수에 대한 커링 버전의 함수를 실행시간에 생성하는 함수
package ex18_2

import java.lang.Appendable
import java.time.LocalDateTime

fun sum2(a: Int, b: Int): Int = a + b

// 인자가 2개인 함수에 대한 커링 버전의 함수를 생성하는 함수
// : (P1, P2) -> R
fun <P1, R> ((P1) -> R).curried(): (P1) -> () -> R = { p1 ->
    {
        this(p1)
    }
}

fun <P1, P2, R> ((P1, P2) -> R).curried(): (P1) -> (P2) -> R = { p1 ->
    { p2 ->
        this(p1, p2)
    }
}

fun <P1, P2, P3, R> ((P1, P2, P3) -> R).curried(): (P1) -> (P2) -> (P3) -> R = { p1 ->
    { p2 ->
        { p3 ->
            this(p1, p2, p3)
        }
    }
}

// 3rd party 라이브러리
// 1. 로깅 라이브러리
enum class Level {
    INFO, WARN
}

fun log(level: Level, appendable: Appendable, message: String) {
    appendable.appendLine(
        "[${level.name}][${LocalDateTime.now()}]: $message"
    )
}

//----
// 2. 이미지 프로세스 라이브러리
fun processImage(logger: (message: String) -> Unit) {
    logger("이미지 로드 시작")
    logger("이미지 로드 성공")
    logger("이미지 프로세싱 완료")
}

class User {
    fun move(position: Int) {
        println("User - move($position)")
    }
}

fun foo(a: Int) {
    println("foo: $a")
}

fun main() {
    val fn3 = ::foo.curried()(30)
    fn3()
    fn3()
    fn3()

    val fn: (User, Int) -> Unit = User::move

    // Bound Reference 원리
    // => C++ bind
    val user = User()
    val fn2: (Int) -> Unit = User::move.curried()(user)
    fn2(42)

    log(Level.INFO, System.out, "Main 시작")

    // 1. 람다 표현식
    processImage { message ->
        log(Level.INFO, System.out, message)
    }

    // 2. 커링을 통해 인자를 고정할 수 있습니다.
    val logger = ::log.curried()(Level.INFO)(System.out)
    processImage(logger)

    // println(sum2(10, 20))
    // val fn = ::sum2.curried()
    // println(fn(10)(20))
}