// 17_함수합성
package ex17

// f(x) -> y
// g(y) -> z
// x -> f(x) -> y -> g(y) -> z
// x  -> f(x) * g(y) -> z

/*
fun compose(f: (x: String) -> Int, g: (y: Any) -> Int): (x: String) -> Int {
    return { x: String ->
        // val y = f(x)
        // val z = g(y)
        // z
        g(f(x))
    }
}
*/
// 1. 일반화 적용 - Generic
/*
fun <X, Y, Z> compose(f: (x: X) -> Y, g: (y: Y) -> Z): (x: X) -> Z {
    return { x: X ->
        g(f(x))
    }
}
*/
// f: ((x: X) -> Y),
// 2. 확장 함수 - Extension Function
//  => 수신 객체 타입 지정을 통해 첫번째 인자를 this를 통해 암묵적으로 전달합니다.
// 3. 중위 함수
infix fun <X, Y, Z> ((x: X) -> Y).compose(g: (y: Y) -> Z): (x: X) -> Z {
    return { x: X ->
        g(this(x))
    }
}

// 4. 연산자 오버로딩을 통해 제공하는 경우도 있습니다.
operator fun <X, Y, Z> ((x: X) -> Y).plus(g: (y: Y) -> Z): (x: X) -> Z {
    return { x: X ->
        g(this(x))
    }
}
operator fun <X, Y, Z> ((x: X) -> Y).times(g: (y: Y) -> Z): (x: X) -> Z {
    return { x: X ->
        g(this(x))
    }
}

fun main() {
    var a: Any = "he"
    a = 10

    val f: (String) -> Int = String::length
    val g: (Any) -> Int = Any::hashCode
    // 문자열의 길이를 기반으로 해시코드 값을 생성하고 싶다.

    val x = "hello"
    val y = f(x)
    val z = g(y)
    println(z)

    // val h = compose(f, g)
    // val h = f.compose(g)
    // val h = f compose g
    // val h = f + g
    val h = f * g

    println(h("hello"))
}