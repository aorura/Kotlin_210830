// 20_지역반환
package ex20

class Person(val name: String)

// 1. for-loop
fun lookForAlice1(people: List<Person>) {
    for (person in people) {
        if (person.name == "Alice") {
            println("Found!")
            // return
            break
        }
    }

    println("No Alice!")
}

// 2. forEach
inline fun <T> Iterable<T>.forEach2(action: (T) -> Unit): Unit {
    for (element in this) action(element)
}

// 비지역반환은 inline 함수에서만 가능합니다.
fun lookForAlice2(people: List<Person>) {
    people.forEach2 { person ->
        if (person.name == "Alice") {
            println("Found!")
            return             // 지역 반환이 아닌 '비지역반환'을 수행합니다.
            // return@forEach  // 지역 반환이 수행됩니다.
        }
    }

    println("No Alice!")
}

// 1) Found - O
// 2) Found
//    No Alice!

fun main() {
    val list = listOf(
        Person("Tom"),
        Person("Bob"),
        Person("Alice"),
    )

    lookForAlice2(list)
}