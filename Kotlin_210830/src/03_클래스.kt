// 03_클래스.kt
package ex3

import java.util.*

// User.java
/*
public class User {
    private String name;
    private int age;

    public User(String name, int age) {
        this.name = name;
        this.age = age;
    }
}
*/
// 1. Kotlin의 기본 접근지정자는 public 입니다.
//    Java에서 접근지정자를 지정하지 않은 경우, package-level 입니다.
// public class User {}

// 2. Kotlin 에서는 반드시 필드에 대한 초기화가 수행되어야 합니다.
// 3. 생성자를 만들때, 클래스의 이름과 동일한 것이 아니라,
//    constructor 키워드를 통해 만들 수 있습니다.

/*
class User {
    private var name: String
    private var age: Int

    constructor(name: String, age: Int) {
        this.name = name
        this.age = age
    }
}
*/

// 주 생성자(Primary Constructor)
/*
class User constructor(private var name: String,
                       private var age: Int)
*/


// 4. 객체 동등성 판단
// 1) 참조 동등성
//   Java: obj1 == obj2
// Kotlin: obj1 === obj2
// 2) 객체 동등성
//   Java: obj1.equals(obj2)
// Kotlin: obj1 == obj2
//      - null에 대한 처리가 포함되어 있습니다.

// 5. 객체를 생성하는 방법
//  : new 연산자를 사용하지 않습니다.
//   Java: User user = new User(...);
// Kotlin: var user: User = User(...);

// constructor 라는 키워드도 생략 가능합니다.

// 6. @Override -> override
//    Object    -> Any
//  instanceOf  -> is / !is
//  User other = (User)obj   ->  other as User

class User(
    private var name: String,
    private var age: Int
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        if (other === null) {
            return false
        }
        // other: Any? -> Any

        /*
        if (other !is User) {
            return false
        }
        */
        // other: Any -> User
        if (other.javaClass != User::class.java) {
            return false
        }

        // 위의 코드는 스마트캐스트가 동작하지 않습니다.
        other as User

        // : 코틀린은 스마트 캐스트를 지원합니다.
        //  => 컴파일러가 코드를 분석해서, 자동으로 타입을
        //     추론해줍니다.
        return other.name == name &&
                other.age == age
    }

    override fun hashCode(): Int {
        return Objects.hash(name, age)
    }
}

fun main() {
    val user1 = User("Tom", 42)
    // val user1: User? = null;
    val user2 = User("Tom", 42)
    // val user2 = user1

    if (user1 === user2) {
        println("동일한 참조 객체입니다.")
    }

    if (user1 == user2) {
        println("동일한 내용의 객체입니다.")
    }
}








