package org.adoptopenjdk.lambda.tutorial.exercise4;

import java.util.Arrays;
import java.util.List;
import static java.util.stream.Collectors.toList;
import java.util.stream.Stream;

import org.adoptopenjdk.lambda.tutorial.exercise4.Document.Page;

public class Printers {
    public static void print(String s) {
        System.out.println(s);
    }

    public static void printPages(Document doc, Integer... pageNumbers) {
        Arrays.stream(pageNumbers).map(doc::getPageContent).forEach(Printers::print);
    }

    public static Stream<Page> createPagesFrom(Stream<String> contents) {
        return contents.map(Page::new);
    }

}
