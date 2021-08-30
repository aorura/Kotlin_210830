// 07_클래스고급.kt
package ex7

// 상속
// Java:
// class Car {
//    Car(String name) {}
// }
// class Truck extends Car {
//    Truck(String name) { super(name); }
// }
// 1. 코틀린의 클래스는 기본적으로 상속이 금지되어 있습니다.
//   Java: public final class Car
// Kotlin: class Car

// 2. 상속 문제점
//   - 기본 클래스와 자식 클래스의 강한 결합이 형성됩니다.
//    => 기본 클래스의 변경 사항이 하위 클래스에 동작을 예기치 않게 변경될 수 있습니다.
//       : 상속을 위한 설계와 문서를 갖추거나, 그럴수 없다면 상속을 금지해야 한다.
//        => Effective Java

// 2. 코틀린에서 상속을 허용하기 위해서는 open 클래스로 만들어야 합니다.
//   => open: Soft Keyword
//      단독으로 사용할 때는 키워드가 아니지만, class랑 사용될 때 키워드가 됩니다.

// 3. 코틀린의 메소드는 기본적으로 오버라이딩이 금지되어 있는 final method 입니다.
//   => open 키워드를 통해 메소드에 대한 오버라이딩을 허용할 수 있습니다.

/*
open class Car(val name: String) {
    open fun display() {
        println("Car($name) display")
    }
    fun open() {

    }
}

open class Truck(name: String) : Car(name) {
    override fun display() {
        println("Truck($name) display")
    }
}
*/

// class DumpTruck(name: String) : Truck(name)

// Car의 객체를 만들 수 없습니다.
// > 추상 클래스는 인스턴스화가 불가능합니다.
//  abstract: 상속 허용
abstract class Car(val name: String) {
    // 구현을 제공할 필요가 없고, 자식 클래스가 만드시 오버라이딩 하도록 해야 한다.
    // => abstract method
    // => 오버라이딩 허용
    abstract fun display()
    fun open() {
    }
}

class Truck(name: String) : Car(name) {
    override fun display() {
        println("Truck($name) display")
    }
}


fun main() {
    val car: Car = Truck("AAA")
    car.display()
}





