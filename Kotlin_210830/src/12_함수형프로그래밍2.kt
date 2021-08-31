// 12_함수형프로그래밍2.kt
// - 메소드
class Car {
    // fun go(this: Car)
    fun go() {
        println("Car go")
    }

    // fun move(this: Car, destination: String)
    fun move(destination: String) {
        println("move - $destination")
    }
}

// 메소드의 시그니처는 첫번째 인자로 객체에 대한 전달이 있습니다. - this
fun main() {
    val fn1: (Car) -> Unit = Car::go
    val fn2: (Car, String) -> Unit = Car::move

    val car1 = Car()
    val car2 = Car()

    car1.go()   // go(car1)
    car2.go()   // go(car2)

    fn1(car1)
    fn1(car2)
}
