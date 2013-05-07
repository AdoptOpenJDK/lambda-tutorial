package org.adoptopenjdk.lambda.tutorial.exercise3;

import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class Books {

    public static List<String> titlesOf(List<Book> books) {
        return books.stream().map(b -> b.title).collect(Collectors.toList());
    }

    public static List<String> namesOfAuthorsOf(List<Book> books) {
        return books.stream().map(b -> b.author).map(Author::fullName).collect(Collectors.toList());
    }

    public static Set<Publisher> publishersRepresentedBy(List<Book> books) {
        return books.stream().map(b -> b.publisher).collect(Collectors.toSet());
    }
}
