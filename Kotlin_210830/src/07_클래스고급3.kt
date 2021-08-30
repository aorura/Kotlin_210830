// 07_클래스고급3.kt
package ex7_3

import java.io.*

interface State : Serializable

interface View {
    var state: State
}

class Button : View {
    var x: Int = 0
    var y: Int = 0
    var width: Int = 0
    var height: Int = 0

    override var state: State
        get() {
            return ButtonState(x, y, width, height)
        }
        set(value) {
            value as ButtonState

            x = value.x
            y = value.y
            width = value.width
            height = value.height
        }

    class ButtonState(
        val x: Int,
        val y: Int,
        val width: Int,
        val height: Int,
    ) : State

    fun foo() {
        x += 10
        y += 10
        width += 10
        height += 10
    }

    override fun toString(): String {
        return "Button(x=$x, y=$y, width=$width, height=$height)"
    }
}

fun saveState(state: State) {
    val fos = FileOutputStream("button2.dat")
    val oos = ObjectOutputStream(fos)

    oos.writeObject(state)

    oos.close()
    fos.close()
}

fun loadState(): State {
    val fis = FileInputStream("button2.dat")
    val ois = ObjectInputStream(fis)

    val state: State = ois.readObject() as State

    ois.close()
    fis.close()

    return state
}

fun main() {
    val button = Button()
    // button.foo()
    // button.foo()
    // saveState(button.state)
    button.state = loadState()

    println(button)
}












