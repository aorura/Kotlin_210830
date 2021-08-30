package javasample.ex7;

import java.io.*;

// 파일에 위해 저장하기 위한 인터페이스 - Serializable
interface State extends Serializable {
}

interface View {
    State getState();               // 현재 상태를 반환합니다.

    void setState(State state);     // 현재 상태를 변경합니다.
}

class Button implements View {
    private int x;
    private int y;
    private int width;
    private int height;

    @Override
    public String toString() {
        return "Button{" +
                "x=" + x +
                ", y=" + y +
                ", width=" + width +
                ", height=" + height +
                '}';
    }

    // ...
    // 저장하고 복원해야 하는 상태는 별도의 클래스를 통해 캡슐화합니다.
    // => Memento Pattern
    // : 객체를 상태를 저장하고 복원하는 패턴

    // static class: Nested class
    //        class: Inner class
    // > 내부 클래스의 문제점
    //  : 내부 클래스는 자신을 감싸는 외부 클래스의 객체의 참조를 암묵적으로 소유합니다.
    //    - 메모리 누수 등의 다양한 문제의 원인이 됩니다.
    //    - 의도한 경우가 아니면, 내부 클래스가 아닌 중첩 클래스를 통해 설계를 해야 합니다.
    private class ButtonState implements State {
        private int x;
        private int y;
        private int width;
        private int height;
        // Button otherObj;

        private ButtonState(int x, int y, int width, int height) {
            this.x = x;
            this.y = y;
            this.width = width;
            this.height = height;
        }
    }

    @Override
    public State getState() {
        return new ButtonState(x, y, width, height);
    }

    @Override
    public void setState(State state) {
        ButtonState s = (ButtonState) state;
        x = s.x;
        y = s.y;
        width = s.width;
        height = s.height;
    }

    public void foo() {
        x += 10;
        y += 10;
        width += 100;
        height += 100;
    }
}


public class Sample {
    public static void saveStateToFile(State state) throws Exception {
        FileOutputStream fos = new FileOutputStream("button.dat");
        ObjectOutputStream oos = new ObjectOutputStream(fos);

        oos.writeObject(state);

        oos.close();
        fos.close();
    }

    public static State loadStateFromFile() throws Exception {
        FileInputStream fis = new FileInputStream("button.dat");
        ObjectInputStream ois = new ObjectInputStream(fis);

        State state = (State) ois.readObject();

        ois.close();
        fis.close();
        return state;
    }

    public static void main(String[] args) throws Exception {
        /*
        // 2. 복원
        State state = loadStateFromFile();
        Button button = new Button();
        button.setState(state);

        System.out.println(button);
        */


        // 1. 저장
        Button button = new Button();
        button.foo();

        State state = button.getState();
        saveStateToFile(state);

    }
}



