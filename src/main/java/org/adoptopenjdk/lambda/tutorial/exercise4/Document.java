package org.adoptopenjdk.lambda.tutorial.exercise4;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public final class Document {
    private final List<Page> pages;

    public Document(List<Page> pages) {
        this.pages = Collections.unmodifiableList(new ArrayList<>(pages));
    }

    public String getPageContent(Integer pageNumber) {
        return this.pages.get(pageNumber).getContent();
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
