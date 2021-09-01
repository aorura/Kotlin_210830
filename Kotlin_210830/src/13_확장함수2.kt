// 13_확장함수2.kt
package ex13_2

open class View {
    // open fun click() = println("View click")     // 1
}

class Button : View() {
    // override fun click() = println("Button click") // 2
}

// Extension is shadowed by a member: public open fun click(): Unit
fun View.click() = println("View click")     // 1
fun Button.click() = println("Button click") // 2

// 1. 확장함수는 일반 함수이기 때문에, 정적 바인딩으로 수행됩니다.
//  => 참조되는 객체의 타입이 아니라, 참조 타입을 보고 컴파일러가 어떤 함수가 호출될지 결정합니다.
// 2. 확장함수는 이미 동일한 이름의 메소드가 기존 클래스에 제공된다면,
//    절대 호출되지 않습니다.

fun main() {
    val view: View = Button()
    view.click()
}