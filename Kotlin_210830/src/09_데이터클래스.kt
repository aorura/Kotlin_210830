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
}
*/

// 1. 객체를 문자열로 표현할 수 있어야 합니다. - toString()
// 2. 객체의 동등성 판단
//     equals / hashCode
// 3. 객체의 복사본을 만드는 방법
//     copy

data class User(val name: String, val age: Int)

fun main() {
    val user1 = User("Tom", 42)
    val user2 = User("Tom", 42)

    println(user1)
    println(user1 == user2)

    val user3 = user1.copy(age = 30)
    println(user3)

    val user4 = user1.copy(name = "Bob")
    println(user4)
}