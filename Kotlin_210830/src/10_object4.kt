package ex10_4

interface Clickable {
    fun click() {
        println("Click")
    }
}


fun click(clickable: Clickable) {
    clickable.click()
}

class Button : Clickable

class Button2 {
    companion object: Clickable
}

object Button3 : Clickable

fun main() {
    val button = Button()
    click(button)  // object

    click(Button2) // companion object
    click(Button3) // object 선언
}