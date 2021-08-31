// 09_데이터클래스.kt
package ex9

import java.util.*

// VO(Value Object) / DTO(Data Transfer Object) / Entity(Database Entity)
/*
class User(val name: String, val age: Int) {
    override fun toString(): String {
        return "User(name='$name', age=$age)"
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is User) return false

        if (name != other.name) return false
        if (age != other.age) return false
        return true
    }

    override fun hashCode(): Int {
        return Objects.hash(name, age)
    }

    // 코틀린은 clone / finalize 을 제공하지 않습니다.
    // copy()
    fun copy(
        name: String = this.name,
        age: Int = this.age
    ): User {
        return User(name, age)
    }

    // 비구조화 선언 - 연산자 오버로딩 메소드
    operator fun component1(): String {
        return name
    }

    operator fun component2(): Int {
        return age
    }
}
*/

// 1. 객체를 문자열로 표현할 수 있어야 합니다. - toString()
// 2. 객체의 동등성 판단
//     equals / hashCode
// 3. 객체의 복사본을 만드는 방법
//     copy
// 4. 비구조화 선언 문법
data class User(val name: String, val age: Int)

// 데이터 클래스
// : Data class must have a t least oneprimary constructor parameter
// data class Person

// : Data class primary constructor
//   must have only property (val / var) parameters
// data class Person(name: String)

fun foo(a: Int = 10, b: Int = 20) {

}

fun main() {
    foo(10, 20)
    foo(10)
    foo()

    val user1 = User("Tom", 42)
    val user2 = User("Tom", 42)

    println(user1)
    println(user1 == user2)

    val user3 = user1.copy(age = 30)
    println(user3)

    val user4 = user1.copy(name = "Bob")
    println(user4)

    val list: List<User> = listOf(user1, user2, user3, user4)
    for (e in list) {
        println("name = ${e.name} / age = ${e.age}")
    }

    // 비구조화 선언
    // => 약속된 메소드가 수행됩니다.
    // => "연산자 오버로딩"
    //  (name, age): Tuple
    for ((name, age) in list) {
        println("name = $name / age = $age")
    }

    val (name, _) = user1
    println(name)
}