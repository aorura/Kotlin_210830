package javasample.ex4;

// 생성자의 인자가 많은 클래스를 초기화하는 방법
// 1. Telescoping Constructor Pattern
//  => 생성자의 오버로딩을 통해 다양한 객체 생성 방법을 제공합니다.
/*
class User {
    private String name;    // 필수
    private String address; // 선택
    private int age;        // 선택
    private int level;      // 선택

    public User(String name, String address, int age, int level) {
        this.name = name;
        this.address = address;
        this.age = age;
        this.level = level;
    }

    public User(String name, String address, int age) {
        this(name, address, age, 0);
    }

    public User(String name, String address) {
        this(name, address, 0, 0);
    }

    public User(String name) {
        this(name, null, 0, 0);
    }
}
*/
// 2. Builder Pattern
// => 생성자의 인자가 많을 경우, 빌더를 사용하면 좋습니다.
class User {
    private String name;    // 필수
    private String address; // 선택
    private int age;        // 선택
    private int level;      // 선택

    private User(Builder b) {
        name = b.name;
        address = b.address;
        age = b.age;
        level = b.level;
    }

    // User 객체를 생성하기 위한 별도의 클래스를 제공합니다.
    public static class Builder {
        private final String name;    // 필수
        private String address; // 선택
        private int age;        // 선택
        private int level;      // 선택

        public Builder(String name) {
            this.name = name;
        }

        public User build() {
            return new User(this);
        }

        // 연쇄 호출을 가능하게 만드는 방법
        public Builder setAddress(String address) {
            this.address = address;
            return this;
        }

        public Builder setAge(int age) {
            this.age = age;
            return this;
        }

        public Builder setLevel(int level) {
            this.level = level;
            return this;
        }
    }
}

public class Sample {
    public static void main(String[] args) {
        String a = "Tom";
        String b = "Seoul";
        int c = 10;
        int d = 100;

        // User user = new User(a, b, c, d);
        // 각각의 인자가 어떤 값을 초기화하는지 알기 어렵다.
        User user = new User.Builder(a)
                .setAddress(b)
                .setAge(c)
                .setLevel(d)
                .build();


    }
}


