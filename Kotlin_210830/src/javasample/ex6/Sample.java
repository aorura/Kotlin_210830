package javasample.ex6;

import ex6.Person;


public class Sample {
    public static void main(String[] args) {
        Person person = new Person("Gildong", "Hong");

        person.setFullName("Soonshin Lee");
        System.out.println(person.getFullName());
    }
}
