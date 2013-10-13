package org.adoptopenjdk.lambda.tutorial.exercise4;

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

import org.adoptopenjdk.lambda.tutorial.exercise4.Document.Page;

import java.util.Arrays;
import java.util.List;

import static java.util.stream.Collectors.collectingAndThen;
import static java.util.stream.Collectors.toList;

public class Documents {

    /**
     * Return the titles from a list of documents.
     */
    public static List<String> titlesOf(Document... documents) {
        return Arrays.stream(documents)
                .map(d -> d.getTitle())
                .collect(toList());
    }

    public static Integer characterCount(Page page) {
        return page.getContent().length();
    }

    public static List<Integer> pageCharacterCounts(Document document) {
        return document.getPages().stream()
                .map(doc -> Documents.characterCount(doc))
                .collect(toList());
    }

    public static String print(Document document, PagePrinter pagePrinter) {
        StringBuilder output = new StringBuilder();

        output.append(pagePrinter.printTitlePage(document));
        document.getPages().stream()
                .map(p -> pagePrinter.printPage(p))
                .forEach(s -> output.append(s));

        return output.toString();
    }

    public static Document translate(Document document, Translator translator) {
        return document.getPages().stream()
                .map(page -> page.getContent())
                .map(content -> translator.translate(content))
                .map(translated -> new Page(translated))
                .collect(collectingAndThen(toList(),
                                           pages -> new Document(translator.translate(document.getTitle()), pages)));
    }
}
