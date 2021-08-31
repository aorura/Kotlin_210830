// 06_프로퍼티.kt
package ex6

import javasample.ex6.User as JUser
// 새로운 이름을 부여해서, 이름 충돌을 해결할 수 있습니다.

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
        private set

    constructor(name: String, age: Int) {
        this.name = name
        this.age = age
    }
}

class Person(var firstName: String, var lastName: String) {
    /*
    fun getFullName(): String {
        return "$firstName $lastName"
    }
    */
    // Backing Field가 없는 프로퍼티
    // => 별도의 메모리 공간을 차지 않는다.
    // var: getter + setter
    // val: getter
    // > 별도의 초기화를 제공할 필요가 없습니다.
    var fullName: String
        // getFullName
        get() {
            // field 키워드도 사용하면 안됩니다.
            //  > field 키워드는 Backing Field가 있는 프로퍼티로 만듭니다.
            return "$firstName $lastName"
        }
        // setFullName
        set(value) {
            val arr = value.split(" ")
            firstName = arr[0]
            lastName = arr[1]
        }
}


fun main() {
    // val user1 = javasample.ex6.User("Tom", 42)
    val user1 = JUser("Tom", 42)
    println(user1.age)
    user1.name = "Bob"
    println(user1.name)

    val user = User("Tom", 42)
    user.name = "Bob"    // Setter
    println(user.name)   // Getter

    val person = Person("Gildong", "Hong")
    person.lastName = "Kim"
    person.fullName = "Soonshin Lee"

    println("${person.firstName} ${person.lastName}")
    // println(person.getFullName())
    println(person.fullName)


}







