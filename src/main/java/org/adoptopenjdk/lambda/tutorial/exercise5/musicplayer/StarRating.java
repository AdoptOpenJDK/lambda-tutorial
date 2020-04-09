package org.adoptopenjdk.lambda.tutorial.exercise5.musicplayer;

/*
 * #%L
 * lambda-tutorial
 * %%
 * Copyright (C) 2013 - 2014 Adopt OpenJDK
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

public enum StarRating {

    FIVE_STARS(5),
    FOUR_STARS(4),
    THREE_STARS(3),
    TWO_STARS(2),
    ONE_STARS(1),
    ZERO_STARS(0);

    public final int numberOfStars;

    private StarRating(int stars) {
        this.numberOfStars = stars;
    }
}
