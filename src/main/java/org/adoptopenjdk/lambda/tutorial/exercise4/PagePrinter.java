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

import static java.lang.String.format;

public final class PagePrinter {

    private final String pageBreak;

    public PagePrinter(String pageBreak) {
        this.pageBreak = pageBreak;
    }

    public String printTitlePage(Document document) {
        return format(
                "%s%n" +
                "%s%n", document.getTitle(), pageBreak);
    }

    public String printPage(Page page) {
        return format(
                "%s%n" +
                "%s%n", page.getContent(), pageBreak);
    }
}
