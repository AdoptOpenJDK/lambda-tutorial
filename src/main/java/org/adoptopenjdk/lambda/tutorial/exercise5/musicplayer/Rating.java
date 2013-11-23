package org.adoptopenjdk.lambda.tutorial.exercise5.musicplayer;

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
public final class Rating {
    public final int score;

    public Rating(int score) {
        if (score < 0 || score > 100) {
            throw new IllegalArgumentException("Rating must be between 0 and 100, inclusive");
        }
        this.score = score;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Rating rating = (Rating) o;

        if (score != rating.score) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return score;
    }

    @Override
    public String toString() {
        return "Rating{score=" + score + '}';
    }
}
