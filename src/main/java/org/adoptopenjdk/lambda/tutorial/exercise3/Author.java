package org.adoptopenjdk.lambda.tutorial.exercise3;

/**
 * Class representing an author
 */
public final class Author {

    private final String firstName;
    private final String lastName;

    /**
     * Constructor
     * 
     * @param firstName - author's first name
     * @param lastName - author's last name
     */
    public Author(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    /**
     * Return the firstName and LastName
     * @return Full name
     */
    public String fullName() {
        return firstName + " " + lastName;
    }

    @SuppressWarnings("javadoc")
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Author author = (Author) o;

        if (!firstName.equals(author.getFirstName())) return false;
        if (!lastName.equals(author.getLastName())) return false;

        return true;
    }

    @SuppressWarnings("javadoc")
    @Override
    public int hashCode() {
        int result = firstName.hashCode();
        result = 31 * result + lastName.hashCode();
        return result;
    }

    /**
     * Get the first name
     * @return first name
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Get the last name 
     * @return last name
     */
    public String getLastName() {
        return lastName;
    }
}
