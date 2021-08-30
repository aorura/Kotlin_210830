// 07_클래스고급2.kt
package ex7_2

// 인터페이스
interface Clickable {
    fun click()

    fun showOff() {
        println("Clickable")
    }

    // 코틀린에서는 프로퍼티도 인터페이스를 통해 약속할 수 있습니다.
    var name: String
    // Clickable의 인터페이스를 구현하는 모든 클래스는 반드시 name의 프로퍼티를 getter/setter 모두 제공해야 한다.

    // fun showOff()
    // 깨지기 쉬운 기반 클래스 문제
}

// 1. 인터페이스는 기본 구현을 가질 수 있습니다.
//   "인터페이스"
//   => 장점: 약한 결합(느슨한 결합)
//           교체 가능한 유연한 설계를 구현할 수 있습니다.
//   => 단점: 변화에 대응하기 어렵습니다.
//       > 인터페이스에 새로운 기능을 추가하는 것이 어렵다.
//        Java 8에서는 default method / defender method 라는 개념으로
//        인터페이스에 새로운 기능을 기본 구현을 통해 제공하고 있습니다.
// 2. 인터페이스와 추상 클래스의 차이점
//   > 추상 클래스는 필드와 메소드를 동시에 제공할 수 있습니다.
//   > 인터페이스는 메소드에 대한 기본 구현만 제공할 수 있습니다.


// class Button implements Button {}
/*
class Person {
    private String name;
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getFullName() { return name; }
    public void setFullName(String fullName) { name = fullName; }
}
*/

class Person {
    var name: String        // 메모리에 저장공간이 존재하는 프로퍼티(접근자 메소드)

    var fullName: String    // 메모리에 저장공간이 필요없는 프로퍼티
        get() {
            return name
        }
        set(value) {
            name = value
        }

    constructor(name: String) {
        this.name = name
    }
}

class Button : Clickable {
    // override var name: String = "Tom"  // Backing Field가 있는 프로퍼티
    override var name: String             // Backing Field가 없는 프로퍼티
        get() {
            return "Tom"
        }
        set(value) {

        }



    override fun click() {
        println("Button click")
    }
}

fun main() {
    val clickable: Clickable = Button()
    clickable.click()

    // clickable.showOff()
}