
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

    // 동반 객체에 이름을 부여하는 것이 가능합니다.
    //  > 자바 쪽에서 참조할 때 사용되는 이름입니다.
    companion object Companion {
        // (Int, Int) -> Int
        fun add(a: Int, b: Int): Int = a + b
    }
}

// 메소드의 시그니처는 첫번째 인자로 객체에 대한 전달이 있습니다. - this
fun main() {
    // 동반 객체의 메소드를 참조할 때는, Car.Companion::add / (Car)::add
    val fn5 = Car.Companion::add
    Car.add(10, 20) // this를 전달할 필요가 없습니다.

    val fn1: (Car) -> Unit = Car::go
    val fn2: (Car, String) -> Unit = Car::move

    val car1 = Car()
    val car2 = Car()

    // Bound Reference
    val fn3: () -> Unit = car1::go
    fn3()        // go(car1)
    val fn4: (String) -> Unit = car1::move
    fn4("Seoul") // move(car1, "Seoul")

    car1.go()   // go(car1)
    car2.go()   // go(car2)

    fn1(car1)
    fn1(car2)
    fn2(car1, "Seoul")

    // "hello".lastChar()
}

