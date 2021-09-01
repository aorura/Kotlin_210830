// 18_커링
package ex18

// 커링(Currying)
// : 다중 인수를 갖는 함수를 단일 인수를 갖는 함수들의 함수열로 바꾸는 작업을 의미합니다.
// => 인자 고정(bind)으로 사용됩니다.

// sum(10, 20, 30) => 커링 => sum(10)(20)(30)

fun sum2(a: Int, b: Int): Int = a + b

fun sum2(a: Int): (b: Int) -> Int {
    return { b ->
        a + b
    }
}

fun sum3(a: Int, b: Int, c: Int): Int = a + b + c

// (b: Int) -> ((c: Int) -> Int)
// : 코틀린에서 -> 문법은 오른쪽에서 왼쪽으로 결합합니다.
fun sum3(a: Int): (b: Int) -> (c: Int) -> Int = { b: Int ->
    { c: Int ->
        a + b + c
    }
}
fun sum4(a: Int): (b: Int) -> (c: Int) -> (d: Int) -> Int = { b ->
    { c ->
        { d ->
            a + b + c + d
        }
    }
}

/*
fun sum3(a: Int): (b: Int) -> ((c: Int) -> Int) {
    return { b: Int ->
        { c: Int ->
            a + b + c
        }
    }
}

*/

// 커링을 고려한다면, 함수 인자의 순서에서
// 변동 가능성이 낮은 순서로 인자를 설계해야 합니다.
fun main() {
    val sum10 = sum2(10)
    println(sum10(20)) // 30
    println(sum10(30)) // 40

    var result2 = sum3(10, 20, 30)
    println(result2)

    result2 = sum3(10)(20)(30)
    println(result2)

    var result = sum2(10, 20)
    println(result)

    result = sum2(10)(20)
    // val s = sum2(10)
    // result = s(20)

    println(result)
}
