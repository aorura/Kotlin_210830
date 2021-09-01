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


fun main() {
    val list = listOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
    // val result = filter(list, ::isOdd)


    // 1. 지역 함수
    fun isEven(e: Int): Boolean = e % 2 == 0
    // val result = filter(list, isEven)

    // 2. 익명 함수
    var result = filter(list,
        fun(e: Int): Boolean = e % 2 == 0)
    println(result)
    // 3. 람다 표현식
    //  : 실행 가능한 코드 블록
    // => 블록의 마지막 표현식의 블록의 최종 결과가 됩니다.
    result = filter(list, { e: Int ->
        e % 2 == 0
    })
    println(result)

    // 4. 인자 타입의 추론도 가능합니다.
    result = filter(list, { e ->
        e % 2 == 0
    })

    // 5. 호출하는 함수의 마지막 인자가, 함수일 경우
    //    람다 표현식의 블록을 함수 호출 괄호의 밖에 둘 수 있습니다.
    //  => Trailing Lambda
    result = filter(list) { e ->
        e % 2 == 0
    }

    // 6. 람다 표현식의 인자가 1개인 경우,
    //    인자의 이름도 생략 가능합니다.
    //    생략된 경우, 인자의 이름은 it으로 참조할 수 있습니다.
    result = filter(list) {
        it % 2 == 0
    }
    

    println(result)
}
















