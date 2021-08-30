// 06_프로퍼티.kt
package ex6

// 프로퍼티
// => 접근자 메소드를 자동으로 생성하는 문법
// : 코틀린은 필드를 생성하는 문법을 제공하지 않습니다.
// var: getter + setter
// val: getter
class User {
    // 아래 코드는 필드가 아닌, 프로퍼티의 문법입니다.
    var name: String
        get() {
            return "Hello, $field"
        }
        set(newName) {
            field = newName.uppercase()
        }
    // field라는 키워드를 통해 실제 필드에 접근이 가능합니다.

    var age: Int

    constructor(name: String, age: Int) {
        this.name = name
        this.age = age
    }
}

fun main() {
    val user = User("Tom", 42)

    user.name = "Bob"    // Setter
    println(user.name)   // Getter
}







