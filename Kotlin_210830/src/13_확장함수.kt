// 13_확장함수
// : Extension Function
// => 코틀린 라이브러리의 핵심 문법
package ex13

// 아래 코드를 주석해제 하였을 때, 오류가 나는 이유에 대해서
// 정확하게 이해해야 합니다.
/*
fun lastChar(text: String): Char {
    return text[text.length - 1]  // get(index: Int)
}
*/

// Extension Function을 만드는 방법
// - 수신 객체 타입 지정: this로 암묵적으로 전달됩니다.
// lastChar(/* this: String */)
fun String.lastChar(): Char {
    return this[this.length - 1]  // get(index: Int)
}

// 기존 라이브러리를 확장하는 방법
// 1) 상속(수직 확장)
//  : 기반 클래스를 상속 받아서 자식 클래스를 통해 새로운 기능을 제공합니다.
//  한계) 기반 클래스가 반드시 상속을 목적으로 설계되어 있어야 한다.
//       경직된 설계이기 때문에, 수정이 어렵다.
//
// 2) 확장 함수(수평 확장)
//  : 사용자가 정의한 함수가 기존 클래스의 메소드처럼 보이게 하는 기술
//  => 기존 클래스의 구조에 상관없이 다양한 기능을 유연하게 추가할 수 있습니다.
//    C#: Extension Function
// Swift: Extension
//  Android - KTX(Kotlin Extension)
//  => 주의해야 할 점
//   : 함부로 추가하면 안됩니다.

/*
fun <E> xjoinToString(
    collection: Collection<E>,
    separator: String,
    prefix: String,
    postfix: String
): String {
    val result = StringBuilder(prefix)

    for ((index, element) in collection.withIndex()) {
        if (index > 0)
            result.append(separator)
        result.append(element)
    }

    result.append(postfix)
    return result.toString()
}
*/

fun <E> Collection<E>.xjoinToString(
    // collection: Collection<E>,
    separator: String,
    prefix: String,
    postfix: String
): String {
    val result = StringBuilder(prefix)

    for ((index, element) in this.withIndex()) {
        if (index > 0)
            result.append(separator)
        result.append(element)
    }

    result.append(postfix)
    return result.toString()
}

fun main() {
    val list = listOf(1, 2, 3, 4, 5)
    /*
    val str = xjoinToString(
        list,
        separator = ", ",
        prefix = "[",
        postfix = "]"
    )
    */
    val str = list.xjoinToString(
        separator = ", ",
        prefix = "[",
        postfix = "]"
    )

    println(str)


    // val result = lastChar("hello")
    val result = "hello".lastChar()

    println(result)
}












