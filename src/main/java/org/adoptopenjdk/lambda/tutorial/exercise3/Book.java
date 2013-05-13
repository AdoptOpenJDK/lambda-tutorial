package org.adoptopenjdk.lambda.tutorial.exercise3;

/**
 * Class representing a book
 */
public final class Book {
    
    private final String title;
    private final Author author;
    private final Publisher publisher;

    public Book(String title, Author author, Publisher publisher) {
        this.title = title;
        this.author = author;
        this.publisher = publisher;
    }

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

    @Override
    public int hashCode() {
        int result = title.hashCode();
        result = 31 * result + author.hashCode();
        result = 31 * result + publisher.hashCode();
        return result;
    }

    public String getTitle() {
        return title;
    }

    public Author getAuthor() {
        return author;
    }

    public Publisher getPublisher() {
        return publisher;
    }
}
