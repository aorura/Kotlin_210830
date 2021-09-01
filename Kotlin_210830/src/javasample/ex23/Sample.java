package javasample.ex23;

// 단일 연결리스트 기반 컬렉션을 만들어봅시다.
// - 순회의 기능을 반복자 패턴을 통해 제공해야 합니다.
//  Iterator
//  Iterable

import java.util.Iterator;
import java.util.function.Consumer;

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

class SList<E> implements Iterable<E> {
    private Node<E> head = null;

    public void addFront(E value) {
        head = new Node<>(value, head);
    }

    public E front() {
        return head.value;
    }

    // 처음 위치를 가르키는 반복자 객체를 얻어오는 방법을 제공합니다.
    // => Iterable
    @Override
    public Iterator<E> iterator() {
        return new SListIterator<>(head);
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
        // List<Integer> list = Arrays.asList(10, 20, 30);

        // System.out.println(list.front());
        Iterator<Integer> iterator = list.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }

        // Collection이 반복자의 요구사항(Iterator / Iterable) 만족한다면,
        // 향상된 for 구문을 사용할 수 있습니다.
        for (Integer e : list) {
            System.out.println(e);
        }

        // Java 8부터
        // 많은 새로운 기능이 인터페이스의 기본 메소드를 통해
        // 제공됩니다.
        list.forEach(new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) {
                System.out.println(integer);
            }
        });

        // Java 8에서는 함수형 인터페이스에 대해서
        // 람다표현식을 사용할 수 있습니다.
        list.forEach(integer -> System.out.println(integer));

        // 메소드 레퍼런스도 지원합니다.
        list.forEach(System.out::println);



    }
}
