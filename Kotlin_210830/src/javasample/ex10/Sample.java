package javasample.ex10;

// Singleton Pattern
// : 오직 한개의 객체를 생성하고, 언제 어디서든 동일한 방법을 통해 객체를 참조할 수 있다.

class Cursor {
    // 1. 오직 한개의 객체
    private static final Cursor INSTANCE = new Cursor();

    // 2. private 생성자
    private Cursor() {
    }

    // 3. 언제 어디서든 동일한 방법을 통해 객체를 얻을 수 있는 정적 메소드
    public static Cursor getInstance() {
        return INSTANCE;
    }
}


public class Sample {
    public static void main(String[] args) {

    }
}
