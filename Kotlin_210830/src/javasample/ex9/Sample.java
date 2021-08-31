package javasample.ex9;

import java.util.Objects;

// Lombok 라이브러리를 통해, 보일러플레이트를 제거할 수 있습니다.
//  - Java 최신버전에서 Record 라는 개념으로 비슷한 기능을 제공합니다.

class User implements Cloneable {


    private String name;
    private int age;

    public User(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public User(User other) {
        // this.name = new String(other.name);
        this.name = other.name;
        this.age = other.age;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;

        User user = (User) o;
        return age == user.age &&
                Objects.equals(name, user.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, age);
    }

    // 자바의 객체를 복제하는 방법
    // 1. Object.clone 오버라이딩
    // 2. protected -> public
    //    부모의 protected 메소드를 public 메소드로 변경하는 것이 허용됩니다.
    // 3. CloneNotSupportedException 메소드 내부에서 처리합니다.
    // 4. clone의 반환 타입을 User객체로 변경합니다.
    //    캐스팅을 메소드 내부에서 처리합니다.
    //    "공변 반환의 룰"
    // 5. Object.clone() 메소드를 이용해서 복제를 수행합니다.
    //    => 해당 객체가 복제의 기능을 제공할 경우 반드시 Cloneable 인터페이스를
    //       구현해야 합니다.
    //       if (!(obj instanceOf Cloneable))
    //          throw new CloneNotSupportedException();
    // 6. class User implements Cloneable
    // 7. 내부의 참조 타입에 대한 깊은 복사는 별도로 수행해야 합니다.
    @Override
    public User clone() {
        try {
            // return (User) super.clone();
            User copy = (User) super.clone();
            // copy.name = name.clone();
            return copy;
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return null;
    }

    // 객체가 GC에 의해서 해지되는 시점에 수행되는 메소드 입니다.
    // => 사용하지 않는 것이 좋습니다.
    //  1) 호출 시점이 명확하지 않습니다.
    //  2) 모든 계층 구조의 메소드가 finalize를 온전하게 구현해야 합니다.
    //  3) 호출이 보장되지 않습니다.
    //  : 안드로이드 프레임워크 내부 코드에서 finalize는 누수가 발생할 경우,
    //    로그를 통해 알려주는 역활로만 구현되어 있습니다.
    @Override
    protected void finalize() throws Throwable {
        super.finalize();
    }
}

// Object
//   |
//  User
//   |
//  Admin  - clone()


// * clone을 통해 객체를 복제하는 방법은 사용하지 않는 것이 좋습니다.
// => 내부의 참조 타입이 복제라는 방법을 동일하게 제공해주지 않는다면,
//    복제를 구현하는 것이 불가능하다.
// => 계층 구조의 모든 클래스가 clone을 만족해야 사용할 수 있습니다.
//  : 새로운 객체를 동일한 필드로 생성할 수 있는 방법을 제공하는 것이 좋습니다.
//    "복사 생성자"
//     - 자신의 동일한 타입을 인자로 받는 생성자

public class Sample {
    public static void main(String[] args) {
        User user1 = new User("Tom", 42);
        // User user2 = user1.clone(); // 깊은 복사
        User user2 = new User(user1);

        System.out.println(user2);
    }
}










