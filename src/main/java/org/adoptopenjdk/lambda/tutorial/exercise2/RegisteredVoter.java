package org.adoptopenjdk.lambda.tutorial.exercise2;

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
 * Lambda Tutorial -- Adopt Open JDK
 *
 * @author Graham Allan grundlefleck at gmail dot com
 */
public final class RegisteredVoter {

    private final String electorId;

    public RegisteredVoter(String electorId) {
        this.electorId = electorId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RegisteredVoter that = (RegisteredVoter) o;

        if (!electorId.equals(that.getElectorId())) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return electorId.hashCode();
    }

    @Override
    public String toString() {
        return "RegisteredVoter{" +
                "electorId='" + electorId + '\'' +
                '}';
    }

    public String getElectorId() {
        return electorId;
    }
}
