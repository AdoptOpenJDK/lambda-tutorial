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
 *     <li>Instance method bound to an particular class</li>
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
 * <em>Instance method bound to a particular object instance</em>
 * TODO: Carry on from here.
 *
 */
@SuppressWarnings("unchecked")
public class Exercise_4_Test {


}
