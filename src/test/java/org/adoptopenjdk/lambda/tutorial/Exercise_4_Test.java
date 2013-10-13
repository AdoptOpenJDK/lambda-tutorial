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

import org.adoptopenjdk.lambda.tutorial.exercise4.Document;
import org.adoptopenjdk.lambda.tutorial.exercise4.Document.Page;
import org.adoptopenjdk.lambda.tutorial.exercise4.Documents;
import org.adoptopenjdk.lambda.tutorial.exercise4.PagePrinter;
import org.adoptopenjdk.lambda.tutorial.exercise4.Translator;
import org.adoptopenjdk.lambda.tutorial.exercise4.Translator.Languages;
import org.adoptopenjdk.lambda.tutorial.util.FeatureMatchers;
import org.hamcrest.Matcher;
import org.junit.Test;

import java.util.Arrays;
import java.util.function.Consumer;

import static java.lang.String.format;
import static org.adoptopenjdk.lambda.tutorial.util.CodeUsesMethodReferencesMatcher.usesMethodReferences;
import static org.adoptopenjdk.lambda.tutorial.util.StringWithComparisonMatcher.isString;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.endsWith;
import static org.hamcrest.Matchers.everyItem;

/**
 * Exercise 4 - Method References
 * <p>
 * Method references are another syntactic addition to JDK 8. They are intended to be used in lambda expressions,
 * preventing unnecessary boilerplate. Consider the following lambda from an earlier test:
 * <code>books.stream().map(b -> b.getTitle())</code>. Here the lambda does nothing more than invoke a method on each
 * element of the stream. This can be written alternatively as: <code>books.stream().map(Book::getTitle)</code>. Both
 * forms are equivalent.
 * </p>
 * <p>
 * The parameter list and return type when using a method reference must match the signature of the method. A mismatch
 * between the two will result in a compile error, just as invoking a method with parameters or return type with an
 * incorrect type will cause a compile error.
 * For example, consider the following code:
 * <pre>
 * public class Printers {
 *     public static void print(String s) {...}
 * }
 * </pre>
 * Note that the <code>print</code> method takes a single <code>String</code> parameter, and has a void return
 * type. Although declared using different type names, this is the same signature as {@link Consumer}, the functional
 * interface that's passed to {@link Iterable#forEach(Consumer)}. Since they match, we can use a reference to the
 * <code>print</code> method when invoking forEach on a list of Strings, like so:
 * <pre>
 *     Arrays.asList("a", "b", "c").forEach(Printers::print)
 * </pre>
 * If we changed the signature of <code>print</code> to <code>public static void print(String s, String t)</code>
 * the use of the method reference would no longer compile, with an error message pointing to the mismatch in argument
 * lists.
 * </p>
 * <p>
 * There are four different kinds of methods that can be used with the method reference syntax:
 * <ol>
 *     <li>Static method belonging to a particular class</li>
 *     <li>Instance method bound to a particular object instance</li>
 *     <li>Instance method bound to a particular class</li>
 *     <li>Constructor belonging to a particular class</li>
 * </ol>
 * </p>
 * We'll discuss each in turn:
 * <p>
 * <em>Static method belonging to a particular class</em>
 * <br>
 * In the previous example, the <code>Printers::print</code> method reference is to a static method ('print') belonging
 * to the Printers class. Here the argument list of the lambda must match the argument list of the method, the first
 * argument to the lambda is the first argument passed into the method.
 * </p>
 *
 * <p>
 * <em>Instance method bound to a particular object instance</em>
 * <br>
 * It's possible to use a method invoked an a specific instance of a class. Consider the following code:
 * <pre>
 * public class Document {
 *     // field, constructor, etc
 *
 *     public String getPageContent(int pageNumber) {
 *         return this.pages.get(pageNumber).getContent();
 *     }
 * }
 *
 * public static void printPages(Document doc, int[] pageNumbers) {
 *     Arrays.stream(pageNumbers).map(doc::getPageContent).forEach(Printers::print);
 * }
 * </pre>
 *
 * In this case, when the <code>map</code> operation runs, the method <code>getPageContent</code> will
 * be invoked <i>on the <code>doc</code> instance</i>. Regardless of the current page number at that point
 * of the stream, it will always be transformed by calling the equivalent of <code>doc.getPageContent(i)</code>.
 * </p>
 * <p>
 * <em>Instance method belonging to a particular class</em>
 * <br>
 * When iterating over a stream of objects, you can invoke a method on each object by using an instance method
 * reference. As in this code:
 * <pre>
 * public static void printDocuments(List&lt;Page&gt; pages) {
 *     pages.stream().map(Page::getContent).forEach(Printers::print);
 * }
 * </pre>
 * In this case the method <code>getContent</code> will still be invoked on an instance of <code>Page</code>, however,
 * it will be invoked <em>on each <code>Page</code> instance</em> that is mapped over.
 * </p>
 * <p>
 * <em>Constructor belonging to a particular class</em>
 * <br>
 * By now, we know how to use method references for static methods and instance methods, that leaves an odd case:
 * constructors.
 * <p>
 * While we don't invoke a constructor like a static method, it is useful to think of it that way. Currently we write:
 * <code>Page p = new Page("content");</code> but imagine we changed the syntax of the Java language to allow this:
 * <code>Page p = Page.new("content");</code>. We can consider the <code>Page.new</code> method to have the exact
 * semantics of a constructor, that is, use a reference to the class object to invoke the constructor method and return
 * the newly created instance as the result of <code>new</code>.
 * </p>
 * With that in mind, consider the following code:
 * <pre>
 * public static Stream&lt;Page&gt; createPagesFrom(Stream&lt;String&gt; contents) {
 *     return contents.map(Page::new).
 * }
 * </pre>
 * The method will return a <code>Stream</code> of newly constructed <code>Page</code> objects. <code>new</code> is
 * still a special keyword in Java, but can now be used in the method reference construct. Note that just like other
 * method references, the method signature of the constructor must match the types fed by the <code>map</code>
 * operation.
 * </p>
 *
 */
@SuppressWarnings("unchecked")
public class Exercise_4_Test {

    /**
     * The <code>Documents</code> class has a method which transforms a list of <code>Document</code> into a list of
     * their titles. The implementation has already been filled out, but it uses a lambda, as in:
     * <code>.map(document -> document.getTitle())</code>
     * <br>
     * Instead of using a lambda, use a method reference instead.
     *
     * @see Documents#titlesOf(Document...)
     * @see Document#getTitle()
     *
     */
    @Test
    public void getListOfDocumentTitlesUsingReferenceOfInstanceMethodBelongingToAClass() {
        Document expenses = new Document("My Expenses",
                Arrays.asList(new Page("LJC Open Conference ticket: £25"), new Page("Beer stipend: £100")));
        Document toDoList = new Document("My ToDo List",
                Arrays.asList(new Page("Build a todo app"), new Page("Pick up dry cleaning")));
        Document certificates = new Document("My Certificates",
                Arrays.asList(new Page("Oracle Certified Professional"), new Page("Swimming 10m")));

        assertThat(Documents.titlesOf(expenses, toDoList, certificates),
                contains("My Expenses", "My ToDo List", "My Certificates"));
        assertThat(Documents.class, usesMethodReferences("getTitle"));

    }

    /**
     * The <code>Documents</code> class has a method which calculates a list of the character counts of Pages in a
     * Document. The method <code>characterCount</code> can be applied to each Page to calculate the number of
     * characters in that page. Currently it is invoked using a lambda.
     * <br>
     * Change to use a method reference which uses the static <code>characterCount</code> method.
     *
     * @see Documents#pageCharacterCounts(Document)
     * @see Documents#characterCount(Page) 
     */
    @Test
    public void getListOfPageCharacterCountsFromDocumentUsingReferenceOfStaticMethodBelongingToAClass() {
        Document diary = new Document("My Diary", Arrays.asList(
                new Page("Today I went shopping"),
                new Page("Today I did maths"),
                new Page("Today I wrote in my diary")));

        assertThat(Documents.pageCharacterCounts(diary), contains(21, 17, 25));
        assertThat(Documents.class, usesMethodReferences("characterCount"));
    }

    /**
     * The <code>Documents</code> class has a method which takes a <code>PagePrinter</code> and renders a
     * <code>Document</code> to text. The method uses two lambda expressions where method references can be used. In
     * this case the method references are to methods belonging to a particular instance object.
     * <br>
     * Change {@link Documents#print(Document, PagePrinter)} to use method references to invoke instance methods of
     * particular objects.
     *
     * @see Documents#print(Document, PagePrinter)
     * @see StringBuilder#append
     * @see PagePrinter#printPage(Page)
     *
     */
    @Test
    public void printContentsOfDocumentUsingReferenceOfInstanceMethodBeloningToAnObject() {
        Document diary = new Document("My Diary", Arrays.asList(
                new Page("Today I went shopping"),
                new Page("Today I did maths"),
                new Page("Today I wrote in my diary")));

        assertThat(Documents.print(diary, new PagePrinter("----")),
                isString(format("My Diary%n" +
                                "----%n" +
                                "Today I went shopping%n" +
                                "----%n" +
                                "Today I did maths%n" +
                                "----%n" +
                                "Today I wrote in my diary%n" +
                                "----%n")));
        assertThat(Documents.class, allOf(usesMethodReferences("printPage"), usesMethodReferences("append")));
    }


    /**
     * The <code>Document</code> class has a method which can create a new Document where all the pages have had a
     * footer appended to it of the format "Document: {title}". The method uses two lambda expressions where method
     * references can be used. In this case the method references are to methods belonging to <code>this</code> object
     * instance. That is, the methods to be invoked should be invoked on <code>this</code>.
     * <br>
     * Change {@link Document#copyWithFooter()} to use method references to invoke instance methods on <code>this</code>
     * instance.
     */
    @Test
    public void transformPagesToHaveFooterUsingReferenceOfInstanceMethodBelonginToThisObject() {
        Document diary = new Document("My Diary", Arrays.asList(
                new Page("Today I went shopping"),
                new Page("Today I did maths"),
                new Page("Today I wrote in my diary")));

        Document diaryWithFooters = diary.copyWithFooter();

        assertThat(diaryWithFooters.getPages(), everyItem(pageEndingWith("Document: My Diary")));
        assertThat(Document.class, allOf(usesMethodReferences("appendFooter"), usesMethodReferences("copyWithPages")));
    }


    /**
     * The <code>Documents</code> class has a method which can translate a document into another language. The method
     * uses a lambda expression to construct each translated <code>Page</code>, where it could use a method reference
     * to Page's constructor.
     * <br>
     * Change {@link Documents#translate} to use a method reference to construct each translated <code>Page</code>.
     *
     * @see Documents#translate(Document, Translator)
     * @see Translator.Languages
     * @see Page
     */
    @Test
    public void createNewDocumentWithTranslatedPagesUsingReferenceOfConstructorMethod() {
        Document diary = new Document("My Diary", Arrays.asList(
                new Page("Today I went shopping"),
                new Page("Today I did maths"),
                new Page("Today I wrote in my diary")));

        Document translated = Documents.translate(diary, Languages.REVERSISH);

        assertThat(translated.getPages(),
                contains(pageContaining("gnippohs tnew I yadoT"),
                        pageContaining("shtam did I yadoT"),
                        pageContaining("yraid ym ni etorw I yadoT")));
        assertThat(Documents.class, usesMethodReferences("new"));
    }

    private static Matcher<Page> pageEndingWith(String ending) {
        return FeatureMatchers.from(endsWith(ending), "page containing", "contents", Page::getContent);
    }

    private static Matcher<Page> pageContaining(String content) {
        return FeatureMatchers.from(isString(content), "page containing", "contents", Page::getContent);
    }
}
