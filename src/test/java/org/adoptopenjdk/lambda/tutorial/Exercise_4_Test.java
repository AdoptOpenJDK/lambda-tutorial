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

import java.util.function.Consumer;

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
 * Note that the <code>printNumber</code> method takes a single <code>String</code> parameter, and has a void return
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


}
