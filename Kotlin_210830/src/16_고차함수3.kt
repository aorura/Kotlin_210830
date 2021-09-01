// 16_고차함수3.kt

// 함수를 기반으로 정책을 분리하는 방법
fun filter(data: List<Int>, test: (Int) -> Boolean): List<Int> {
    // val result: List<Int> = emptyList()
    val result: MutableList<Int> = mutableListOf()

    for (e in data) {
        if (test(e)) {
            result.add(e)
        }
    }

    return result
}


// 정책으로 함수를 제공합니다.
//  : (Int) -> Boolean
fun isOdd(e: Int): Boolean = e % 2 == 1
fun isEven(e: Int): Boolean = e % 2 == 0


fun main() {
    val list = listOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
    val result = filterEven(list)

    println(result)
}