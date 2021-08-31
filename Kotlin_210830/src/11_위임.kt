// 11_위임.kt
package ex11

// 위임: 하나 이상의 메소드의 호출을 다른 객체에 위임한다.
// => 기존 클래스의 기능을 재사용하는 방법

interface UIElement {
    fun getHeight(): Int
    fun getWidth(): Int
}

// open
class Rectangle(val x1: Int, val x2: Int, val y1: Int, val y2: Int) : UIElement {
    override fun getHeight(): Int {
        return y2 - y1
    }

    override fun getWidth(): Int {
        return x2 - x1
    }
}

// 1) 상속
//  : 부모 클래스의 모든 기능을 암묵적으로 이용할 수 있습니다.
//  => "다형성"을 목적으로 상속을 사용해야 합니다.
/*
class Panel(x1: Int, x2: Int, y1: Int, y2: Int) : Rectangle(x1, x2, y1, y2)

fun main() {
    val panel = Panel(10, 20, 30, 40)
    println(panel.getWidth())
    println(panel.getHeight())
}
*/

// 2) 포함
// - 인터페이스를 기반으로 의존하는 객체를 사용하기 때문에,
//   약한 결합입니다.
// - 교체 가능한 유연한 설계
// - 의존성 주입(Dependency Injection)

// 포함 문제점
//  : 위임하고자하는 모든 기능에 대한 명시적인 작성이 필요합니다.
//   => 위임

// class Panel(private val rectangle: UIElement) : UIElement by rectangle
class Panel(rectangle: UIElement) : UIElement by rectangle

/*
class Panel(private val rectangle: UIElement) : UIElement {
    override fun getHeight(): Int {
        return rectangle.getHeight()
    }

    override fun getWidth(): Int {
        return rectangle.getWidth()
    }
}
*/

fun main() {
    val rectangle = Rectangle(10, 20, 30, 40)
    val panel = Panel(rectangle)

    println(panel.getWidth())
    println(panel.getHeight())
}


