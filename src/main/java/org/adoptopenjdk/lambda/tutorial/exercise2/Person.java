package org.adoptopenjdk.lambda.tutorial.exercise2;

/**
 * Basic class representing a human being
 */
public final class Person {

    private final String name;
    private final int age;

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public int getAge() {
        return age;
    }
    
    public String getName() {
        return name;
    }
}
