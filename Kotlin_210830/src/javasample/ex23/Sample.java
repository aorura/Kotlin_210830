package javasample.ex23;

// 단일 연결리스트 기반 컬렉션을 만들어봅시다.
// - 순회의 기능을 반복자 패턴을 통해 제공해야 합니다.
//  Iterator
//  Iterable

import java.util.Iterator;

class SListIterator<E> implements Iterator<E> {
    private SList.Node<E> current;

    public SListIterator(SList.Node<E> current) {
        this.current = current;
    }

    // 다음 위치가 존재하는지 여부를 확인하는 메소드
    @Override
    public boolean hasNext() {
        return current != null;
    }

    // 현재의 값을 반환하고, 다음위치로 이동합니다.
    @Override
    public E next() {
        E ret = current.getValue();
        current = current.getNext();

        return ret;
    }
}

class SList<E> {
    private Node<E> head = null;

    public void addFront(E value) {
        head = new Node<>(value, head);
    }

    public E front() {
        return head.value;
    }

    static class Node<E> {
        private E value;
        private Node<E> next;

        Node(E value, Node<E> next) {
            this.value = value;
            this.next = next;
        }

        public E getValue() {
            return value;
        }

        public Node<E> getNext() {
            return next;
        }
    }
}

public class Sample {
    public static void main(String[] args) {
        SList<Integer> list = new SList<>();
        list.addFront(10);
        list.addFront(20);
        list.addFront(30);

        System.out.println(list.front());
    }
}
