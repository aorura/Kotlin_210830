package javasample.ex23;

// 단일 연결리스트 기반 컬렉션을 만들어봅시다.
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
