// 10_object5.kt
// object
// 1. object 선언
// 2. companion object
// 3. Anonymous object - 익명 객체

interface MouseAdapter {
    fun mouseClicked()
    fun mouseEntered()
}

class Window {
    var mouseAdapter: MouseAdapter? = null

    fun click() {
        // mouseAdapter.mouseClicked()
        // Nullable 타입은 반드시 null check가 필요합니다.

        // if (mouseAdapter != null) {
        // mouseAdapter.mouseClicked()
        // 스마트 캐스트가 동작하지 않습니다.
        // : Smart cast to 'MouseAdapter' is impossible,
        // because 'mouseAdapter' is a mutable property
        //   that could have been changed by this time
        // }

        // 1) 강제 접근 - !!
        // mouseAdapter!!.mouseClicked()
        // : Null일 경우 예외가 발생합니다.

        // 2) 지역 변수
        // val mouseAdapter = mouseAdapter
        // if (mouseAdapter != null) {
        //    mouseAdapter.mouseClicked()
        // }

        // 3) Safe-Call
        // 위의 코드를 간결하게 작성할 수 있는 문법이 있습니다.
        mouseAdapter?.mouseClicked()
    }

    fun enter() {
        mouseAdapter?.mouseEntered()
    }
}

fun main() {
    val window = Window()

    // 익명의 객체를 생성할 때 object 키워드를 통해 생성할 수 있습니다.
    // => 차이점이 한개 있습니다.
    //    "클로저"
    var n = 0
    window.mouseAdapter = object : MouseAdapter {
        override fun mouseClicked() {
            println("mouseClicked - ${++n}")
        }

        override fun mouseEntered() {
            println("mouseEntered")
        }
    }

    window.click()
    window.click()
    window.enter()
    window.enter()
}









