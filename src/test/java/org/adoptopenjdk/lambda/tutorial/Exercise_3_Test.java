package org.adoptopenjdk.lambda.tutorial;

/*
 * #%L
 * lambda-tutorial
 * %%
 * Copyright (C) 2013 Adopt OpenJDK
 * %%
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as
 * published by the Free Software Foundation, either version 2 of the 
 * License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public 
 * License along with this program.  If not, see
 * <http://www.gnu.org/licenses/gpl-2.0.html>.
 * #L%
 */

import org.adoptopenjdk.lambda.tutorial.exercise3.Author;
import org.adoptopenjdk.lambda.tutorial.exercise3.Book;
import org.adoptopenjdk.lambda.tutorial.exercise3.Books;
import org.adoptopenjdk.lambda.tutorial.exercise3.Publisher;
import org.adoptopenjdk.lambda.tutorial.util.FeatureMatchers;
import org.hamcrest.Matcher;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.equalTo;

/**
 * Exercise 3 - Mapping
 * <p>
 * Along with filter, map is one of the most common operations to perform.
 * </p>
 * <p>
 * Consider this idiomatic Java code used to convert a list of strings to uppercase:
 * <pre>
 * {@code
 *     List<String> upperCaseStrings = new ArrayList<>();
 *     for (String s: mixedCaseStrings) {
 *         upperCaseStrings.add(s.toUpperCase());
 *     }
 *     return upperCaseStrings;
 * }
 * </pre>
 * </p>
 * <p>
 * As with the filter operation, map removes a lot of the boilerplate of this example by pushing the common code into
 * the library, rather than having it repeated in your code. In this case, we are again:
 * <ul>
 *     <li>constructing a new, empty, destination collection</li>
 *     <li>iterating over the source collection</li>
 *     <li>"doing something" to each element</li>
 *     <li>adding the result to a new collection</li>
 * </ul>
 * </p>
 * <p>
 * However, unlike filter, we are not just adding elements that pass a test, we are taking the element and creating some
 * new value before adding it. The new value in the example above is taking the result of toUpperCase(). The result is a
 * new list, of the same length, where each element is the result of mapping the source element. In post-JDK 8, you
 * can express this operation like so:
 * <p>
 * <pre>
 * {@code
 *    return mixedCaseStrings.stream().map(s -> s.toUpperCase()).collect(Collectors.toList());
 * }
 * </pre>
 * </p>
 * <p>
 * As with the last exercise, we "open up" the Streams API by calling .stream() on a collection. This allows us to
 * access the map() method.
 * </p>
 * <p>
 * The map() method takes a function (java.util.function.Function) as an argument, and applies it to each element in a
 * list. Since Function is an interface with a single abstract method, JDK 8 allows us to express it as a lambda. The
 * single method on the Function type takes a single parameter of a certain type and returns a single result
 * of another type. In this example, the input type is String, and the return type also happens to be String. It
 * could be return any type, the resultant collection will be a collection of that type.
 * </p>
 * <p>
 * Again we're transferring into a destination list by means of the Collectors.toList() method.
 *
 * So what is the result of both of implementations:
 *
 * If the variable 'mixedCaseStrings' in the example above looked like this:
 *
 *     ["I", "am", "really", "enjoying", "lambda-tutorial"]
 *
 * The resultant variable 'upperCaseStrings', would like like this:
 *
 *     ["I", "AM", "REALLY", "ENJOYING", "LAMBDA-TUTORIAL"]
 * </p>
 * <p>
 * An important point to consider is that the map() method does not modify the original list, mixedCaseStrings still
 * exists, as it was before. This makes it much easier to prevent bugs where mixedCaseStrings could be used elsewhere,
 * perhaps later in the execution, or even concurrently in a different thread.
 * </p>
 * <p>
 * The map operation is also known in other languages/libraries as: transform; collect.
 * </p>
 * <p>
 * The below tests can be made to pass using Stream's map method. Try to make them pass without using a loop, or adding
 * to a new collection manually.
 * </p>
 * @see Collection#stream()
 * @see Stream#map(Function)
 * @see Function
 * @see Collector
 * @see Collectors
 * @see Collectors#toList()
 *
 */
@SuppressWarnings("unchecked")
public class Exercise_3_Test {

    private final Author joshuaBloch = new Author("Joshua", "Bloch");
    private final Author brianGoetz = new Author("Brian", "Goetz");
    private final Author barryBurd = new Author("Barry", "Burd");

    private final Publisher addisonWesley = new Publisher("Addison-Wesley");
    private final Publisher johnWileyAndSons = new Publisher("John Wiley & Sons");

    private final Book effectiveJava = new Book("Effective Java", joshuaBloch, addisonWesley);
    private final Book javaConcurrencyInPractice = new Book("Java Concurrency In Practice", brianGoetz, addisonWesley);
    private final Book javaForDummies = new Book("Java For Dummies", barryBurd, johnWileyAndSons);

    private final List<Book> books = Arrays.asList(effectiveJava, javaConcurrencyInPractice, javaForDummies);

    /**
     * Use Stream.map() to convert a collection of books into a collection of their titles.
     */
    @Test
    public void getAllBookTitles() {
        assertThat(Books.titlesOf(books),
                containsInAnyOrder("Effective Java", "Java Concurrency In Practice", "Java For Dummies"));
    }

    /**
     * Use Stream.map() to convert a collection of books into a collection of the author's full names.
     *
     * Note that it is possible to chain calls to map(). E.g. myCollection.stream().map(...).collect(). That may come
     * in useful when generating an author's full name.
     *
     * @see Author#fullName()
     */
    @Test
    public void getNamesOfAuthorsOfBooks() {
        assertThat(Books.namesOfAuthorsOf(books),
                containsInAnyOrder("Joshua Bloch", "Brian Goetz", "Barry Burd"));
    }

    /**
     * Use Stream.map() to convert a collection of books into a collection of the distinct publishers represented within
     * the given list of books. For example, given books A published by X, B published by Y, and C published by Y,
     * return a collection consisting of X and Y.
     *
     * This can be done with a single stream().map(...).collect(...). Remember you can collect into collections other
     * than a List.
     *
     * @see Publisher#hashCode()
     * @see Publisher#equals(Object)
     * @see Collectors#toSet()
     */
    @Test
    public void getPublishersRepresentedByBooks() {
        assertThat(Books.publishersRepresentedBy(books),
                containsInAnyOrder(publisherNamed("Addison-Wesley"), publisherNamed("John Wiley & Sons")));
    }

    // Test helpers

    private static Matcher<Publisher> publisherNamed(String name) {
        return FeatureMatchers.from(equalTo(name), "is named", "name", publisher -> publisher.getName());
    }

}
