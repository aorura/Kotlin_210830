// 03_클래스.kt
package ex3

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

// constructor 라는 키워드도 생략 가능합니다.
class User(
    private var name: String,
    private var age: Int
)

// 4. 객체 동등성 판단
// 1) 참조 동등성
// 2) 객체 동등성










