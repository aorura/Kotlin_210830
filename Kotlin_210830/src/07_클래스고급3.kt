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

    // 코틀린은 중첩 클래스가 기본 입니다.
    // > 내부 클래스로 만들고 싶다면, inner 라는 키워드를 통해
    //   만들 수 있습니다.
    // inner class ButtonState(
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

// Java
//  특정한 메소드가 예외를 던질 경우,
//  - 예외를 try-catch를 통해서 처리하거나,
//  - 예외를 외부로 다시 던져야 합니다.
// > 코틀린은 예외 처리가 강제되지 않습니다.
//   : 코틀린은 예외 처리에 대한 부분에 대해서 누락될 가능성이 높습니다.

fun saveState(state: State) {
    val fos = FileOutputStream("button2.dat")
    val oos = ObjectOutputStream(fos)

    oos.writeObject(state)

    oos.close()
    fos.close()
}

fun loadState(): State {
    val fis = FileInputStream("button3.dat")
    val ois = ObjectInputStream(fis)

    val state: State = ois.readObject() as State

    ois.close()
    fis.close()

    return state
}

fun main() {
    val button = Button()
    button.foo()
    button.foo()
    saveState(button.state)

    try {
        button.state = loadState()
    } catch (e: IOException) {
        e.printStackTrace()
    }

    println(button)
}












