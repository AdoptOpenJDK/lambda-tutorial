package org.adoptopenjdk.lambda.tutorial.exercise3;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class Books {

    public static List<String> titlesOf(List<Book> books) {
        List<String> titles = new ArrayList<>();
        for (Book b: books) {
            titles.add(b.title);
        }

        return titles;
    }

    public static List<String> namesOfAuthorsOf(List<Book> books) {
        List<String> fullNames = new ArrayList<>();
        for (Book b: books) {
            fullNames.add(b.author.fullName());
        }

        return fullNames;
    }

    public static Set<Publisher> publishersRepresentedBy(List<Book> books) {
        Set<Publisher> publishers = new HashSet<>();
        for (Book b: books) {
            publishers.add(b.publisher);
        }

        return publishers;
    }
}
