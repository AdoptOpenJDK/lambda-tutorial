package org.adoptopenjdk.lambda.tutorial.exercise2;

/**
 * Basic class representing a human being
 */
public final class Person {

    private final String name;
    private final int age;

    /**
     * Constructor
     * 
     * @param name - person's name
     * @param age - person's age
     */
    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    /**
     * Get the persons age
     * @return age
     */
    public int getAge() {
        return age;
    }
    
    /**
     * Get the persons name
     * @return name
     */
    public String getName() {
        return name;
    }
}
