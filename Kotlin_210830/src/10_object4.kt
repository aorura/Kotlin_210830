package ex10_4

interface Clickable {
    fun click() {
        println("Click")
    }
}

interface Focusable {
    fun focus() {}
}

fun click(clickable: Clickable) {
    clickable.click()
}

open class Base

class Button : Clickable

class Button2 {
    companion object: Base(), Clickable, Focusable
}

//--------
object Button3 : Clickable

fun main() {
    val button = Button()
    click(button)  // object

    click(Button2) // companion object
    click(Button3) // object 선언
}