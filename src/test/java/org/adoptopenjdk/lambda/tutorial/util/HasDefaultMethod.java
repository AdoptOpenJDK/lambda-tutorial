package org.adoptopenjdk.lambda.tutorial.util;

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

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeDiagnosingMatcher;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

import java.io.IOException;

public class HasDefaultMethod extends TypeSafeDiagnosingMatcher<Class<?>> {
    private final String defaultMethodName;

    public HasDefaultMethod(String defaultMethodName) {
        this.defaultMethodName = defaultMethodName;
    }

    public static Matcher<Class<?>> called(String defaultMethodName) {
        return new HasDefaultMethod(defaultMethodName);
    }

    @Override
    protected boolean matchesSafely(Class<?> item, Description mismatchDescription) {
        if (!item.isInterface()) {
            mismatchDescription.appendText("class ").appendValue(item).appendText(" is not an interface");
            return false;
        }

        if (!interfaceHasDefaultMethod(defaultMethodName, item)) {
            mismatchDescription.appendText("did not have default method named ").appendText(defaultMethodName);
            return false;
        }

        return true;
    }

    @Override
    public void describeTo(Description description) {
        description.appendText("an interface with a default method called " + defaultMethodName);
    }

    private boolean interfaceHasDefaultMethod(String defaultMethodName, Class<?> clazz) {
        try {

            String resourceName = clazz.getName().replace(".", "/").concat(".class");
            ClassReader reader = new ClassReader(clazz.getClassLoader().getResourceAsStream(resourceName));
            HasDefaultMethodVisitor sourceFileNameVisitor = new HasDefaultMethodVisitor(defaultMethodName);
            reader.accept(sourceFileNameVisitor, 0);

            return sourceFileNameVisitor.defaultMethodExists();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    private static final class HasDefaultMethodVisitor extends ClassVisitor {

        private final String defaultMethodName;
        private boolean visitedYet = false;
        private boolean hasDefaultMethod = false;

        public HasDefaultMethodVisitor(String defaultMethodName) {
            super(Opcodes.ASM5);
            this.defaultMethodName = defaultMethodName;
        }

        @Override
        public MethodVisitor visitMethod(int access, String name, String desc, String signature, String[] exceptions) {
            visitedYet = true;
            hasDefaultMethod |= name.equals(defaultMethodName) && ((access & Opcodes.ACC_ABSTRACT) == 0);
            return super.visitMethod(access, name, desc, signature, exceptions);
        }

        public boolean defaultMethodExists() {
            if (!visitedYet) throw new IllegalStateException("Must visit a class before asking for result");
            return this.hasDefaultMethod;
        }
    }
}
