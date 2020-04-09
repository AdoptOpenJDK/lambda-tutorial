package org.adoptopenjdk.lambda.tutorial.exercise5.thirdpartyplugin;

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

import org.adoptopenjdk.lambda.tutorial.exercise5.musicplayer.Song;
import org.adoptopenjdk.lambda.tutorial.exercise5.musicplayer.StarRating;
import org.adoptopenjdk.lambda.tutorial.exercise5.musicplayer.UserRatedMusicLibrary;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class UserRatedLocalFilesystemMusicLibrary implements UserRatedMusicLibrary {

    private final Set<Song> allSongs = Collections.unmodifiableSet(new HashSet<>(Arrays.asList(
            new Song("A Change Is Gonna Come", "Sam Cooke"),
            new Song("Bad Moon Rising", "Creedence Clearwater Revival"),
            new Song("Candy", "Paulo Nutini"),
            new Song("Desolation Row", "Bob Dylan"),
            new Song("Eleanor Rigby", "The Beatles"))));

    @Override
    public StarRating userRatingOf(Song song) {
        // Simulate real user ratings
        switch (song.getTitle()) {
            case "Candy":
                return StarRating.FIVE_STARS;
            case "A Change Is Gonna Come":
                return StarRating.FOUR_STARS;
            case "Desolation Row":
                return StarRating.THREE_STARS;
            case "Bad Moon Rising":
                return StarRating.TWO_STARS;
            case "Eleanor Rigby":
                return StarRating.ONE_STARS;
            default:
                return StarRating.ZERO_STARS;
        }
    }

    @Override
    public Collection<Song> allSongs() {
        return allSongs;
    }

    @Override
    public int timesPlayed(Song song) {
        // Could read a local database file to find the number of times played
        return 5;
    }
}
