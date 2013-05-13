package org.adoptopenjdk.lambda.tutorial.exercise3;

/**
 * Domain object representing a Publisher
 */
public final class Publisher {
    
    private final String name;

    /**
     * Constructor
     * 
     * @param name - publisher's name
     */
    public Publisher(String name) {
        this.name = name;
    }

    @SuppressWarnings("javadoc")
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Publisher publisher = (Publisher) o;

        if (!name.equals(publisher.getName())) return false;

        return true;
    }

    @SuppressWarnings("javadoc")
    @Override
    public int hashCode() {
        return name.hashCode();
    }

    /**
     * Get the publisher name
     * @return name
     */
    public String getName() {
        return name;
    }
}
