// 17_함수합성
package ex17

// f(x) -> y
// g(y) -> z
// x -> f(x) -> y -> g(y) -> z
// x  -> f(x) * g(y) -> z

fun compose(f: (x: String) -> Int, g: (y: Any) -> Int): (x: String) -> Int {
    return { x: String ->
        val y = f(x)
        val z = g(y)
        z
    }
}

fun main() {
    val f: (String) -> Int = String::length
    val g: (Any) -> Int = Any::hashCode
    // 문자열의 길이를 기반으로 해시코드 값을 생성하고 싶다.

    val x = "hello"
    val y = f(x)
    val z = g(y)

    println(z)
}