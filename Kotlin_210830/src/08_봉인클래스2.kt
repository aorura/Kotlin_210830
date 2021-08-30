// 08_봉인클래스2.kt
package ex8_2

// Android - Java 8 / Java 11(2021) / Java 16
// Kotlin은 새로운 언어의 기능을 안드로이드에서 바로 사용할 수 있습니다.
// => 1.5

// open class Expr
/*
sealed class Expr
sealed class Expr2

class Num(val value: Int) : Expr()
class Sum(val left: Expr, val right: Expr) : Expr()
class Diff(val left: Expr, val right: Expr): Expr()
*/

// 1. Expr이 open이기 때문에, 누구나 새로운 클래스를 상속 받을 수 있습니다.
//  => else 구문이 필요합니다.
// 2. 새로운 클래스가 추가되어도, eval의 코드가 제대로 동작하기 위해서는
//    반드시 수정이 필요합니다.
// 봉인 클래스(sealed class)를 통해 위의 문제를 해결할 수 있습니다.
//  : 봉인 클래스에 대한 하위 클래스를 같은 파일에서만 작성할 수 있습니다.
//   > 컴파일러는 봉인 클래스에 대한 하위 클래스가 정확히 몇개 존재하는지, 어떤 타입인지 알 수 있습니다.

// 3. 코틀린 1.4에서 봉인된 인터페이스의 문법이 추가되었습니다.
sealed interface Expr
sealed interface Expr2

class Num(val value: Int) : Expr, Expr2
class Sum(val left: Expr, val right: Expr) : Expr
class Diff(val left: Expr, val right: Expr): Expr

fun eval(e: Expr): Int {
    return when (e) {
        is Num -> e.value
        is Sum -> eval(e.left) + eval(e.right)
        is Diff -> eval(e.left) - eval(e.right)
        // 봉인 클래스를 사용할 경우, else 구문을 사용하지 않는 것이 좋습니다.
        // else -> throw IllegalStateException("Unknown expression")
    }

    /*
    if (e is Num) {
        return e.value
    } else if (e is Sum) {
        return eval(e.left) + eval(e.right)
    } else {
        throw IllegalStateException("Unknown expression")
    }
    */
}

// 자바의 코드를 코틀린에서 100% 사용할 수 있습니다.
// 코틀린의 코드를 자바에서 100% 이용하기 어렵습니다.
//  => 고려해야 하는 사항이 있습니다.


fun main() {
    val left = Num(10)
    val right = Sum(Num(20), Num(10))
    val sum = Sum(left, right)

    println(eval(sum))
}