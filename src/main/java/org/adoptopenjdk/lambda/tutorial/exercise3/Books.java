package org.adoptopenjdk.lambda.tutorial.exercise3;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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

/**
 * Domain object representing a collection of books
 */
public class Books {

    /**
     * Apply a mapping of Books to titles (Strings)
     * 
     * @param books - books to transform
     * @return list of book titles
     */
    public static List<String> titlesOf(List<Book> books) {
        List<String> titles = new ArrayList<>();
        for (Book b: books) {
            titles.add(b.getTitle());
        }

        return titles;
    }

    /**
     * Apply a mapping of Books to their author's full names
     *  
     * @param books - books to transform
     * @return list of author full names
     */
    public static List<String> namesOfAuthorsOf(List<Book> books) {
        List<String> fullNames = new ArrayList<>();
        for (Book b: books) {
            fullNames.add(b.getAuthor().fullName());
        }

        return fullNames;
    }

    /**
     * Apply a mapping of Books to a unique set of their publishers
     * 
     * @param books - books to transform
     * @return set of publishers
     */
    public static Set<Publisher> publishersRepresentedBy(List<Book> books) {
        Set<Publisher> publishers = new HashSet<>();
        for (Book b: books) {
            publishers.add(b.getPublisher());
        }

        return publishers;
    }
}
