// 21_메모이제이션
package ex21
// 함수의 결과를 저장해서 재활용하는 최적화 기법을 "메모이제이션" 이라고 합니다.
// => 동적 계획법
// => 결과를 저장해서 재활용하기 위해서는 함수가 순수 함수 이어야 한다.
//   순수함수(pure function): 함수의 인자가 동일하면 결과가 동일하다.

// n = 0 or 1 -> 1
// n > 1      -> fib(k - 1) + fib(k - 2)

/*
fun fib(k: Int): Long = when (k) {
    0, 1 -> 1
    else -> fib(k - 1) + fib(k - 2)
}
*/

// Map에서 메모이제이션의 기능을 선언적으로 사용할 수 있는 기능을 제공합니다.
val cache = mutableMapOf<Int, Long>()
fun fib(k: Int): Long = cache.getOrPut(k) {
    when (k) {
        0, 1 -> 1
        else -> fib(k - 1) + fib(k - 2)
    }
}


/*
fun fib(k: Int): Long = when (k) {
    0, 1 -> 1
    else -> {
        val result = cache[k]
        if (result != null)
            result
        else {
            val ret = fib(k - 1) + fib(k - 2)
            cache[k] = ret
            ret
        }
    }
}
*/

fun main() {
    val result = fib(100)
    println(result)
}