package javasample.ex10;

// Singleton Pattern
// : 오직 한개의 객체를 생성하고, 언제 어디서든 동일한 방법을 통해 객체를 참조할 수 있다.

/*
class Cursor {
    // 1. 오직 한개의 객체
    private static final Cursor INSTANCE = new Cursor();
    // static final은 JLS에서 스레드 안전성을 보장합니다.

    // 2. private 생성자
    private Cursor() {
    }

    // 3. 언제 어디서든 동일한 방법을 통해 객체를 얻을 수 있는 정적 메소드
    public static Cursor getInstance() {
        return INSTANCE;
    }
}
*/
// 문제점: 싱글톤 객체가 프로그램이 시작되는 시점에 초기화가 수행됩니다.
//     => 지연 초기화 싱글톤
//     => 처음 객체가 참조되는 시점에 객체를 생성해야 합니다.
/*
class Cursor {
    private static Cursor sInstance;

    private Cursor() {
        System.out.println("Cursor()");
    }

    // public static Cursor getInstance() {
    //    if (sInstance == null)
    //        sInstance = new Cursor();
    //
    //    return sInstance;
    // }
    // 위의 getInstance 메소드는, 여러개의 스레드에서 동시에 수행될 경우,
    // 문제가 발생합니다.
    // => 지연 초기화에서는 반드시 스레드 안전성에 대한 고려가 필요합니다.

    // public static Cursor getInstance() {
    //    synchronized (Cursor.class) {
    //        if (sInstance == null)
    //            sInstance = new Cursor();
    //    }
    //    return sInstance;
    // }
    // 동기화가 필요한 시점은 처음 객체가 생성되는 시점입니다.
    // 싱글톤은 객체가 한번만 생성됩니다.
    // => 불필요한 동기화로 인해, getInstance에 대한 성능 저하가 발생할 수 있습니다.
    // => DCLP(Double Checked Locking Pattern)

    public static Cursor getInstance() {
        if (sInstance == null) {
            synchronized (Cursor.class) {
                if (sInstance == null)
                    sInstance = new Cursor();
            }
        }
        return sInstance;
    }
}
*/

// 지연 초기화 싱글톤
//  => IODH(Initialization on demand holder)
//   1. static final 필드는 동기화가 필요하지 않습니다.
//   2. 중첩 클래스의 정적 필드는 처음 접근되는 시점에 초기화가 수행됩니다.
class Cursor {
    static class Singleton {
        private static final Cursor INSTANCE = new Cursor();
    }

    private Cursor() {
        System.out.println("Cursor()");
    }

    public static Cursor getInstance() {
        return Singleton.INSTANCE;
    }

    public void move() {
    }
}

public class Sample {
    public static void main(String[] args) {
        System.out.println("Main start..");
        Cursor c1 = Cursor.getInstance();
        System.out.println(c1);

        Cursor c2 = Cursor.getInstance();
        System.out.println(c2);
    }
}
