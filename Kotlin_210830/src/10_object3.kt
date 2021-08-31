// 10_object3.kt
package ex10_3

// companion object는 객체이기 때문에,
// 인터페이스와 상속을 할 수 있습니다.

// Map<String, Any>을 실제 객체로 변환하는 인터페이스를 정의합니다.
//  - JSONObject

interface MapFactory<T> {
    fun fromMap(map: Map<String, Any>): T
}

fun <T> fromJSON(json: Map<String, Any>, factory: MapFactory<T>): T {
    return factory.fromMap(json)
}

class Person(val name: String, val age: Int) {
    companion object : MapFactory<Person> {
        fun foo() {}

        override fun fromMap(map: Map<String, Any>): Person {
            val name = map["name"] as String
            val age = map["age"] as Int
            return Person(name, age)
        }
    }
}


fun main() {
    // companion object는 클래스 이름을 통해 참조할 수 있습니다.
    val p = Person
    p.foo()

    val json = mapOf(
        "name" to "Tom",
        "age" to 42
    )

    // 타입이 인터페이스를 만족하는 것처럼 사용할 수 있습니다.
    val person = fromJSON(json, Person)
    println(person)
}







