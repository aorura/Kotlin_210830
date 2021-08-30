package javasample.ex6;

class User {
    private String name;
    private int age;

    public User(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        // ...
        return name;
    }

    public void setName(String name) {
        // ...
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    private void setAge(int age) {
        this.age = age;
    }
}


public class Sample {
    public static void main(String[] args) {

    }
}
