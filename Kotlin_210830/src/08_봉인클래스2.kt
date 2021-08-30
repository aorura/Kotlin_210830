// 08_봉인클래스2.kt
package ex8_2

open class Expr
class Num(val value: Int): Expr()
class Sum(val left: Expr, val right: Expr): Expr()

fun eval(e: Expr): Int {
    return when(e) {
        is Num -> e.value
        is Sum -> eval(e.left) + eval(e.right)
        else -> throw IllegalStateException("Unknown expression")
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

fun main() {
    val left = Num(10)
    val right = Sum(Num(20), Num(10))
    val sum = Sum(left, right)

    println(eval(sum))
}