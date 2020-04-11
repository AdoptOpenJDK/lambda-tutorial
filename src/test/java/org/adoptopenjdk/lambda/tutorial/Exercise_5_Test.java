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


import org.adoptopenjdk.lambda.tutorial.exercise5.musicplayer.StarRating;
import org.adoptopenjdk.lambda.tutorial.exercise5.musicplayer.UserRatedMusicLibrary;
import org.adoptopenjdk.lambda.tutorial.exercise5.thirdpartyplugin.CloudScrobblingMusicLibrary;
import org.adoptopenjdk.lambda.tutorial.exercise5.musicplayer.MusicLibrary;
import org.adoptopenjdk.lambda.tutorial.exercise5.musicplayer.Rating;
import org.adoptopenjdk.lambda.tutorial.exercise5.musicplayer.Song;
import org.adoptopenjdk.lambda.tutorial.exercise5.thirdpartyplugin.LocalFilesystemMusicLibrary;
import org.adoptopenjdk.lambda.tutorial.exercise5.thirdpartyplugin.UserRatedLocalFilesystemMusicLibrary;
import org.adoptopenjdk.lambda.tutorial.util.FeatureMatchers;
import org.adoptopenjdk.lambda.tutorial.util.HasConcreteMethod;
import org.hamcrest.Matcher;
import org.junit.Test;

import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;

/**
 * Exercise 5 - Default Methods
 * <p>
 * The introduction of Default Methods is intended to allow for libraries to evolve more easily - with the
 * JDK Collections library as their first main user. They permit adding concrete methods, with an implementation,
 * to Java interfaces. Prior to JDK 8, every method on an interface had to be abstract, with implementations provided
 * by classes. With JDK 8, it's possible to declare the following interface:
 * <p/>
 * <code>
 * <pre>
 * // (stripped down version of the real Iterable.java)
 * public interface Iterable<T> {
 *
 *     Iterator<T> iterator(); // As in prior versions
 *
 *     // example of a new JDK 8 default method
 *     default void forEach(Consumer<? super T> action) {
 *         Objects.requireNonNull(action);
 *         for (T t : this) {
 *             action.accept(t);
 *         }
 *     }
 * }
 * </pre>
 * </code>
 * In this case the method <code>forEach</code> is a <em>default method</em> with a concrete implementation. With this
 * declaration <code>forEach()</code> can be invoked on any implementation of <code>Iterable</code>. This is very
 * similar to adding a concrete method to an abstract class -- if no implementation overrides it, the code in the method
 * on the interface is executed. Crucially, the new method can be added without causing compiler errors in the client
 * code. Consider if a normal <code>forEach</code> method was added to <code>Iterable</code>: every class that
 * implemented the <code>Iterable</code> interface would now fail to compile. Using default methods, the interface can
 * be evolved without breaking client code.
 * <p>
 * Allowing evolution of a library is the primary use case of default methods.
 * </p>
 * <p>
 * <h3>Rules of Method Lookup</h3>
 * <br>
 * Inheritance of method implementations is not new to Java. When a subclass method is invoked, the runtime finds the
 * nearest implementation available. This could be declared on the subclass itself, or on any of it's superclasses, or
 * even all the way up in <code>java.lang.Object</code>. This last case is what occurs if your class does not override
 * <code>toString</code>. However, if anywhere in the inheritance hierarchy from your class to Object, toString is
 * implemented, that would be executed instead of Object's toString. This behaviour is still in
 * place in JDK 8, but has been augmented to cope with default methods.
 * </p>
 * <p>
 * The main difference between superclass methods and default methods in JDK 8 is that there is only <em>single
 * inheritance</em> of classes, while there is <em>multiple inheritance</em> of interfaces. As such, there needs to be
 * some extra rules around method lookup.
 * <br>
 * Method lookup has all the following characteristics:
 * <br>
 * <h4>For a default method to be invoked, there must be no declaration anywhere in the class hierarchy.</h4>
 * <br>
 * This can
 * be thought of like <a href="http://en.wikipedia.org/wiki/Miranda_warning">Miranda rights</a>, as in, "You have
 * the right to a method implementation. If your class hierarchy cannot afford an implementation, one will be
 * provided for you". A method implementation anywhere in the class hierarchy will take priority over a default
 * method. This also applies to default methods which match the signature from java.lang.Object; the default method
 * can never be invoked. As such, declaring a default method which matches a method from Object is a compiler error.
 * <br>
 * <h4>The closest default method wins.</h4>
 * <br>
 * As with superclasses, when searching for a concrete method the nearest
 * superclass wins. For example, given "<code>class A</code>", "<code>class B extends A</code>", and "<code>class C
 * extends B</code>", if a method is invoked on C, the Java runtime will first look for the implementation in C,
 * then B, then A, then finally java.lang.Object, invoking the first method it finds. This is also the case with
 * super-interfaces. Indeed, given "<code>interface X</code>", "<code>interface Y extends X</code>" and "<code>
 * interface Z extends Y</code>", and declaring "<code>class C extends B, implements Z</code>", the lookup for a
 * default method would traverse the hierarchy in the following order: C -> B -> A -> Object -> Z -> Y -> X.
 * <br>
 * <h4>Ambiguous inheritance of a default method must be resolved in the implementing class, at compile time.</h4>
 * <br>
 * If a class inherits the same default method from more than one source, a compiler error will be emitted. This happens
 * when unrelated types declare the same method signature, and a class becomes a subtype of more than one of them.
 * Consider the following example:
 * <code><pre>
 * interface A {
 *     default void speak() { System.out.println("A says hi!"; }
 * }
 * interface B {
 *     default void speak() { System.out.println("Regards from B!"; }
 * }
 * class C implements A, B { } // compiler error: inherits unrelated default methods from A and B
 * </pre></code>
 * This principle applies regardless of how deep the interface inheritance hierarchy is, and also applies to
 * sub-interfaces as well as classes, i.e. "<code>interface Y extends A, B</code>" results in the same compile error.
 * This error can be resolved by removing ambiguity in the subtype, by overriding the default method. This can be any
 * compliant implementation, including directly invoking a specific inherited default method. A new syntax in JDK 8 is
 * available to allow choosing an inherited default method, like so:
 * <code><pre>
 * class C implements A, B {
 *     public void speak() { A.super.speak(); } // prints "A says hi!"
 * }
 * </pre></code>
 * In this case, when <code>instanceOfC.speak();</code> is executed, the default <code>speak</code> from <code>
 * interface A</code> is invoked. This syntax is also available within default method bodies, allowing an interface
 * to choose an implementation from one of its super-interfaces. Note that this syntax is not entirely unfamiliar: it
 * can be considered just like invoking <code>super.someMethod();</code>, except that with the single inheritance of
 * classes, the name of the super class is can be nothing other than the single superclass, so it can remain implicit.
 * <br>
 * It should be noted that ambiguities are <em>never</em> resolved by the order in which interface implementations are
 * declared (such as with Scala's traits). They must always be resolved explicitly in the subtype.
 * <p/>
 * <h3>Do default methods mean Java supports multiple inheritance?</h3>
 * <p>
 * In a way. Java has always supported multiple inheritance of interfaces, previously this did not include any of the
 * implementation, just the contract. Inheritance can be subdivided again, into inheritance of <em>state</em>, and
 * inheritance of <em>behaviour</em>. Default methods introduce the latter, multiple inheritance of behaviour.
 * <h4>What about the diamond problem?</h4>
 * <p>There are two aspects to the diamond problem: a) disambiguating implementations and b) handling state. We have seen
 * how ambiguities are resolved in JDK 8, requiring that users specify the implementation at compile time. Default
 * methods do not introduce a problem in that aspect of the diamond problem. The second problem, state, is where trickier
 * issues of the diamond problem live. It can be too easy to accidentally introduce bugs into an implementing class
 * because it must adhere to the contract defined in the interface and/or superclass, which can include maintaining
 * state. Any methods which manipulate that state must also be invoked in the implementing class, and accidentally
 * "losing" that invocation is an easy way to introduce a subtle bug. Also, there is the problem between the ordering of
 * conflicting methods, that cannot be resolved by disambiguating. Default methods in Java avoid this issue, due to an
 * existing virtue of interfaces, that <em>they do not contain state</em>. Because an interface cannot be constructed
 * with fields, there is no state available to encounter this issue. Java still has single inheritance of state,
 * through superclasses. If the contract of an interface requires state (like, e.g. <code>Iterator</code>) it will be
 * provided through the single inheritance chain.
 * </p>
 * <p>
 * Note that there are still ways to "simulate" state in interfaces with default methods, since there is no restriction
 * on accessing static fields defined in other classes. However, that should be avoided for all the reasons that both
 * multiple inheritance of state, and global mutable state should be avoided.
 * </p>
 *
 * @see Iterable#forEach(Consumer)
 */
@SuppressWarnings("unchecked")
public class Exercise_5_Test {

    /**
     * Add a default method to {@link MusicLibrary} that returns every song in the library, sorted by artist. You should
     * NOT need to add a method to any implementation of MusicLibrary.
     * <br/>
     * There is a helper class within MusicLibrary that can help perform sorting.
     * <br/>
     * Uncomment the line below that causes a compiler error until the default method is included.
     *
     *
     * @see MusicLibrary#allSongs()
     * @see MusicLibrary.SongByArtistSorter#sort(java.util.Collection)
     *
     */
    @Test
    public void useDefaultMethodToReturnPlaylistOrderedByArtist() {
        MusicLibrary library = new LocalFilesystemMusicLibrary(
            new Song("A Change Is Gonna Come", "Sam Cooke"),
            new Song("Bad Moon Rising", "Creedence Clearwater Revival"),
            new Song("Candy", "Paulo Nutini"),
            new Song("Desolation Row", "Bob Dylan"),
            new Song("Eleanor Rigby", "The Beatles")
        );

//        UNCOMMENT THE LINES BELOW
//        Until the sortedByArtist method is added to MusicLibrary, there will be a compiler error.
//        assertThat(library.sortedByArtist(), containsSongsBy("Bob Dylan", "Creedence Clearwater Revival",
//                                                             "Paulo Nutini", "Sam Cooke", "The Beatles"));
        assertThat(MusicLibrary.class, HasConcreteMethod.called("sortedByArtist"));
        assertThat(LocalFilesystemMusicLibrary.class, not(HasConcreteMethod.called("sortedByArtist")));
    }

    /**
     * Override the default method {@link MusicLibrary#ratingOf(Song)} in {@link CloudScrobblingMusicLibrary} to return
     * a {@link Rating} based on the rating given by a cloud scrobbling service.
     * <br/>
     * There is a helper method within {@link CloudScrobblingMusicLibrary.CloudScrobblingService} that can be called
     * to retrieve the rating from the cloud.
     *
     * @see - Definition of Scrobbling - http://www.last.fm/help/faq?category=99
     * @see MusicLibrary#ratingOf(Song)
     * @see CloudScrobblingMusicLibrary.CloudScrobblingService#retrieveScrobbledRatingOf(Song)
     */
    @Test
    public void overridesDefaultMethodInClassToProvideCustomSongRatingAlgorithm() {
        MusicLibrary library = new CloudScrobblingMusicLibrary();

        assertThat(library.ratingOf(new Song("Candy", "Paulo Nutini")), is(new Rating(78)));
        assertThat(CloudScrobblingMusicLibrary.class, HasConcreteMethod.called("ratingOf"));
    }

    /**
     * Override the default method {@link MusicLibrary#ratingOf(Song)} in {@link UserRatedMusicLibrary} to return
     * a {@link Rating} based on the {@link StarRating} entered by the user.
     * <br/>
     * The method {@link UserRatedMusicLibrary#userRatingOf(Song)} provides a user-entered rating that can be converted
     * to a Rating type with the {@link UserRatedMusicLibrary.StarRatingConverter#convert(StarRating)} method.
     *
     */
    @Test
    public void overrideDefaultMethodInInterfaceToProvideUserEnteredSongRatings() {
        MusicLibrary library = new UserRatedLocalFilesystemMusicLibrary();

        assertThat(library.ratingOf(new Song("Desolation Row", "Bob Dylan")), is(new Rating(60)));
        assertThat(UserRatedMusicLibrary.class, HasConcreteMethod.called("ratingOf"));
    }

    private Matcher<Song> songBy(String artist) {
        return FeatureMatchers.from(equalTo(artist), "a song by", "artist", Song::getArtist);
    }

    private Matcher<? super List<Song>> containsSongsBy(String... artists) {
        List<Matcher<? super Song>> songMatchers = Stream.of(artists).map(this::songBy).collect(Collectors.toList());
        return contains(songMatchers);
    }
}
