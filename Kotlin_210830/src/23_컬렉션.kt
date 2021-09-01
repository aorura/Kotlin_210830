// 23_컬렉션.kt
package ex23

import ex19_2.Intent

// 컬렉션을 이해하기 위해서는
// 반드시 반복자 패턴에 대해서 이해해야 합니다.
// => Iterator Pattern
//  : 컬렉션의 내부 구조에 상관없이 요소를 열거할 수 있는 객체
//  > Iterator / Iterable
class SListIterator<E>(var current: SList.Node<E>?) : Iterator<E> {
    override fun hasNext(): Boolean =
        current == null

    override fun next(): E {
        val ret = current?.value
        current = current?.next

        return ret!!
    }
}

class SList<E> : Iterable<E> {
    class Node<E>(val value: E, val next: Node<E>?)

    var head: Node<E>? = null
    val front: E?
        get() = head?.value

    fun addFront(value: E) {
        head = Node(value, head)
    }

    override fun iterator(): Iterator<E> = SListIterator(head)
}

fun main() {
    val list = SList<Int>()
    list.addFront(10)
    list.addFront(20)
    list.addFront(30)

    println(list.front)
}
