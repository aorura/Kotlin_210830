package javasample.ex3;

class User {
    private String name;
    private int age;

    public User(String name, int age) {
        this.name = name;
        this.age = age;
    }

    @Override
    public boolean equals(Object obj) {
        // 1. 동일한 참조 인지 체크합니다.
        if (this == obj) {
            return true;
        }

        // 2. obj가 null이면 false를 반환합니다.
        if (obj == null) {
            return false;
        }

        // 3. obj가 User 타입인지 체크합니다.
        //  - class 비교: 정확한 User 타입인지 체크합니다.
        //  - instanceOf 비교: User 계층 구조에 포함되는지 체크합니다.
        if (!(obj instanceof User)) {
            return false;
        }

        // 4. obj를 User 객체로 캐스팅해야 합니다.
        User other = (User) obj;

        // 5. 비교 하고자하는 필드에 대한 비교해서, 같은 객체인지 판단합니다.
        return other.name.equals(name) &&
                other.age == age;
    }
}


public class Sample {
    public static void main(String[] args) {
        User user1 = new User("Tom", 42);
        User user2 = new User("Tom", 42);
        // User user2 = user1;

        if (user1 == user2) {
            System.out.println("동일한 참조 객체입니다.");
        }

        if (user1.equals(user2)) {
            System.out.println("동일한 내용의 객체입니다.");
        }

    }
}
