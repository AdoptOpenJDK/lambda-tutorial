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


    public static void main(String... args) {
        Page p1 = new Page("this is the first page");
        Page p2 = new Page("this is the second page");

        Document myDocument = new Document(Arrays.asList(p1, p2));

        Printers.printPages(myDocument, 0, 1);

        List<Page> pages = Arrays.asList(p1, p2);
        pages.stream().map(Page::getContent).forEach(Printers::print);

        Stream<Page> pagesFromContent = createPagesFrom(Arrays.asList("a", "b").stream());
        System.out.println(pagesFromContent.collect(toList()));
    }
}
