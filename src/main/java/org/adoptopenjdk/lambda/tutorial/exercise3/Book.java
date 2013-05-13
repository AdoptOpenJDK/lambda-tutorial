package org.adoptopenjdk.lambda.tutorial.exercise3;

/**
 * Class representing a book
 */
public final class Book {
    
    private final String title;
    private final Author author;
    private final Publisher publisher;

    /**
     * Constructor
     * 
     * @param title - book title
     * @param author - book author
     * @param publisher - book publisher
     */
    public Book(String title, Author author, Publisher publisher) {
        this.title = title;
        this.author = author;
        this.publisher = publisher;
    }

    @SuppressWarnings("javadoc")
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Book book = (Book) o;

        if (!author.equals(book.getAuthor())) return false;
        if (!publisher.equals(book.getPublisher())) return false;
        if (!title.equals(book.getTitle())) return false;

        return true;
    }

    @SuppressWarnings("javadoc")
    @Override
    public int hashCode() {
        int result = title.hashCode();
        result = 31 * result + author.hashCode();
        result = 31 * result + publisher.hashCode();
        return result;
    }

    /**
     * Get the book title
     * @return title
     */
    public String getTitle() {
        return title;
    }

    /**
     * Get the author of the book
     * @return author
     */
    public Author getAuthor() {
        return author;
    }

    /**
     * Get the publisher of the book
     * @return publisher
     */
    public Publisher getPublisher() {
        return publisher;
    }
}
