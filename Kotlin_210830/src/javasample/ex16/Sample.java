package javasample.ex16;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Sample {
    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();
        list.add(10);
        list.add(20);
        list.add(30);

        // 자바에서 불변 리스트를 만드는 방법
        list = Collections.unmodifiableList(list);
        // Immutable object is modified
        // list.add(10);
        // list.add(20);

    }
}
