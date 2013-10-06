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

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static java.lang.String.format;
import static java.util.stream.Collectors.collectingAndThen;

public final class Document {
    private final String title;
    private final List<Page> pages;

    public Document(String title, List<Page> pages) {
        this.title = title;
        this.pages = Collections.unmodifiableList(new ArrayList<>(pages));
    }

    public List<Page> getPages() {
        return this.pages;
    }

    public String getTitle() {
        return this.title;
    }

    private Page appendFooter(Page original) {
        String footer = "Document: " + getTitle();
        return new Page(format("%s%n%s", original.getContent(), footer));
    }

    private Document copyWithPages(List<Page> newPages) {
        return new Document(title, newPages);
    }

    public Document copyWithFooter() {
        return getPages().stream()
            .map(this::appendFooter)
            .collect(collectingAndThen(Collectors.<Page>toList(), this::copyWithPages));
    }

    public static final class Page {
        private final String content;

        public Page(String content) {
            this.content = content;
        }

        public String getContent() {
            return this.content;
        }
    }
}
