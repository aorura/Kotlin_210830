// 10_object.kt
package ex10

// object 용법 3가지
// 1. object 선언 - Singleton Pattern
//    : constructor를 제공할 수 없습니다.
object Cursor {
    // 초기화 블록을 이용해서 초기화를 수행해야 합니다.
    init {
        println("Cursor 객체 생성")
    }

    fun move() {
        println("Move")
    }
}

/*
fun main() {
    // Cursor <- 객체
    val c = Cursor

    println("Main start")
    Cursor.move()
}
*/

// 활용

// Comparator 인터페이스를 구현하는 객체를 제공해야 합니다.
// class NameComparator : Comparator<Person> {
object NameComparator : Comparator<Person> {
    override fun compare(o1: Person, o2: Person): Int {
        return o1.name.compareTo(o2.name)
    }
}

data class Person(val name: String) {
    // 클래스 내부에 object 선언 객체를 둘 수 있습니다.
    object NameComparator : Comparator<Person> {
        override fun compare(o1: Person, o2: Person): Int {
            return o1.name.compareTo(o2.name)
        }
    }
}


fun main() {
    val people = listOf(
        Person("Tom"),
        Person("Bob"),
        Person("Alice")
    )

    // 정책을 매번 새롭게 생성해야 합니다.
    // val result1 = people.sortedWith(NameComparator())
    // println(result1)

    // 정책을 단일 객체를 통해 관리할 수 있습니다.
    val result1 = people.sortedWith(NameComparator)
    println(result1)
    val result2 = people.sortedWith(Person.NameComparator)
    println(result1)

}








