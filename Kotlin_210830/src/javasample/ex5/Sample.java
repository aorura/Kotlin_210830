package javasample.ex5;

class User {
    protected String name = "Bob";
}

public class Sample {
    public static void main(String[] args) {
        User user = new User();
        System.out.println(user.name);
    }
}
