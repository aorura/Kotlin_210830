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

open class Car(val name: String) {
    fun display() {
        println("Car($name) display")
    }
    fun open() {

    }
}

open class Truck(name: String) : Car(name)
class DumpTruck(name: String) : Truck(name)

fun main() {

}





