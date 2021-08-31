// 08_봉인클래스
package ex8

// 1. enum class
//  : 코틀린의 enum은 자바와 동일하게 프로퍼티와 메소드를 가질 수 있는 객체입니다.
/*
enum class Color {
    RED, ORANGE, YELLOW, GREEN
}
*/

enum class Color(val red: Int, val green: Int, val blue: Int) {
    RED(255, 0, 0),
    BLUE(0, 0, 255),
    ORANGE(255, 165, 0),
    YELLOW(255, 255, 0),
    GREEN(0, 255, 0)

    ;

    // 메소드를 정의하기 위해서는, 반드시 토큰 분리가 필요합니다.
    fun rgb(): Int {
        return ((red * 255) + green) * 255 + blue
    }

    val rgb: Int
        get() {
            return ((red * 255) + green) * 255 + blue
        }
}

// 2. Kotlin은 switch-case statement(문) 이 제공되지 않습니다.
//   => when expression(식) 을 제공합니다.

//   Expression vs Statement
//   => Expression은 결과값이 존재하지만, Statement는 결과가 존재하지 않습니다.
//   => void -> Unit

//   코틀린에서는 if도 Expression입니다.
//   => 각 조건에 따른 블록의 마지막 표현식을 통해 결과값이 결정됩니다.

fun printColor(color: Color) {
    when (color) {
        Color.RED -> {
            println("RED")
            println("RED")
        }
        Color.GREEN -> println("GREEN")
        Color.BLUE -> println("BLUE")
        else -> println("Other")
    }
}

fun getWarmth(color: Color): String {
    return when (color) {
        Color.RED, Color.ORANGE, Color.YELLOW -> "Warm"
        else -> "Cold"
    }
}

// 코틀린에서는 when을 통해 객체 동등성 판단도 지원합니다.
//  ==
fun mix(c1: Color, c2: Color): Color {
    val set: Set<Color> = setOf(c1, c2)

    return when (set) {
        setOf(Color.RED, Color.YELLOW) -> Color.ORANGE
        setOf(Color.BLUE, Color.YELLOW) -> Color.GREEN
        else -> throw Exception("Unknown")
    }

    /*
    return if (set == setOf(Color.RED, Color.YELLOW)) Color.ORANGE
    else if (set == setOf(Color.BLUE, Color.YELLOW)) Color.GREEN
    else throw Exception("Unknown")
    */
}

fun main() {
    println(mix(Color.RED, Color.YELLOW))
    println(mix(Color.YELLOW, Color.RED))

    printColor(Color.GREEN)
    printColor(Color.RED)

    println(getWarmth(Color.ORANGE))
    println(getWarmth(Color.BLUE))


    val age = 30
    /*
    var grade = ""
    if (age >= 10 && age < 20)  {
        grade = "통과"
    } else {
        grade = "탈락"
    }
    */
    val grade = if (age in 10..19) // (age >= 10 && age < 20)
        "통과"
    else {
        "탈락"
    }

    val grade2 = when {
        age in 10..19 -> "통과"
        age in 20..29 -> "보류"
        else -> "탈락"
    }
    val grade3 = when (age) {
        in 10..19 -> "통과"
        in 20..29 -> "보류"
        else -> "탈락"
    }



    println(grade)

    val color = Color.RED
    println(color.rgb())
    println(color.rgb)
}
