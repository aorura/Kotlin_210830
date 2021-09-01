// 19_인라인.kt
package ex19

import java.lang.Exception
import java.util.concurrent.locks.Lock
import java.util.concurrent.locks.ReentrantLock
import kotlin.concurrent.withLock

// 코틀린은 자바에서 제공하는 synchronized 문법이 제공되지 않습니다.
/*
class IncThread : Thread() {
    companion object {
        var n = 0
    }

    override fun run() {
        // for (int i = 0 ; i < 1000000; ++i)
        for (i in 1..1_000_000) {
            n += 1
        }
    }
}
*/

// Expected performance impact from inlining is insignificant.
// Inlining works best for functions with parameters of functional types
// fun add(a: Int, b: Int) = a + b
// 위의 함수에 대한 인라인에 대한 판단은 이미 JVM 상에서 효과적으로 수행됩니다.


class IncThread(val lock: Lock) : Thread() {
    companion object {
        var n = 0
    }

    override fun run() {
        for (i in 1..1_000_000) {

//            withLock(lock) {
//                n += 1
//            }
            lock.withLock {
                n += 1
            }


        }
    }

    // lock.lock() / lock.unlock() 직접 호출할 경우
    // 임계 영역 안에서 예외가 발생하게 되면, unlock이 호출되지 않아서,
    // 데드락의 위험성이 있습니다.
    /*
    override fun run() {
        for (i in 1..1_000_000) {
            lock.lock()
            //---
            try {
                n += 1
            } catch (e: Exception) {
            } finally {
                lock.unlock()
            }
        }
    }
    */
    /*
    override fun run() {
        for (i in 1..1_000_000) {
            lock.lock()
            //---
            n += 1
            //---
            lock.unlock()
        }
    }
    */
}

// inline 문법
// => 함수를 호출하는 것이 아니라, 함수를 바이트코드로 치환하는 문법
//   inline 키워드
//    - 함수가 인자로 함수를 전달 받을 때에만 사용할 수 있습니다.
// inline fun withLock(lock: Lock, block: () -> Unit) {
inline fun <T> Lock.withLock2(block: () -> T): T {
    lock()
    try {
        return block()
    } finally {
        unlock()
    }
}

// Generic: 타입에 독립적인 구현을 제공할 때 사용한다
/*
fun print2(a: Int) {
    println(a)
}
fun print2(a: String) {
    println(a)
}
fun print2(a: Double) {
    println(a)
}
*/
fun <T> print2(a: T) {
    println(a)
}

fun main() {
    print2(42)           // T -> Int
    print2("hello")      // T -> String
    print2(3.14)         // T -> Double

    val lock = ReentrantLock()

    val result = lock.withLock2 {
        42
    }
    println(result)

    val t1 = IncThread(lock)
    val t2 = IncThread(lock)

    t1.start()
    t2.start()

    t1.join()
    t2.join()

    println(IncThread.n)
}