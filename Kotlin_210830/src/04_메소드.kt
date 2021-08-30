// 04_메소드.kt
package ex4

class Car {
    /*
    fun go() {
        println("go0")
    }

    fun go(speed: Int) {
        // println("go1 " + speed)
        println("go1: $speed")
        // Template String / String Interpolation
    }

    fun go(speed: Int, color: Int) {
        println("go2: $speed / $color")
    }
    */

    // 파라미터 기본값을 지원합니다.
    // => 불필요한 오버로딩의 코드를 제거할 수 있습니다.
    fun go(speed: Int = 100, color: Int = 255, destination: String = "Seoul") {
        println("go3: $speed / $color / $destination")
    }
}

fun main() {
    val car = Car()

    car.go()
    car.go(100)
    car.go(100, 255)
    // car.go(100, 255, "Seoul")

    val a = 100
    val b = 200
    val c = "Seoul"
    car.go(a, b, c)
    // 각각의 인자가 어떤 파라미터로 전달되는지 지정하는 기능이 있습니다.
    car.go(speed = a, color = b, destination = c)
    car.go(color = b, destination = c, speed = a)

    // 순서가 일치할 경우, 부분 파라미터 지정도 가능합니다. - Kotlin 1.4
    car.go(a, b, destination = c)
    car.go(speed = a, b, destination = c)

    car.go(
        speed = a,
        color = b,
        destination = c,  // Trailing Comma - Kotlin 1.4
    )

    car.go(color = 20)
}