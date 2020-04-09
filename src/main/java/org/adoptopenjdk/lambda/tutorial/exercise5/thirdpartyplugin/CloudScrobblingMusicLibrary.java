package org.adoptopenjdk.lambda.tutorial.exercise5.thirdpartyplugin;

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

import org.adoptopenjdk.lambda.tutorial.exercise5.musicplayer.MusicLibrary;
import org.adoptopenjdk.lambda.tutorial.exercise5.musicplayer.Rating;
import org.adoptopenjdk.lambda.tutorial.exercise5.musicplayer.Song;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Random;

public class CloudScrobblingMusicLibrary implements MusicLibrary {
    private final CloudScrobblingService cloudScrobblingService;

    public CloudScrobblingMusicLibrary() {
        this.cloudScrobblingService = new CloudScrobblingService();
    }

    @Override
    public Collection<Song> allSongs() {
        return cloudScrobblingService.retrieveAllSongs();
    }

    @Override
    public int timesPlayed(Song song) {
        return cloudScrobblingService.retrieveTimesPlayedFromCloud(song);
    }

    public static final class CloudScrobblingService {

        public int retrieveTimesPlayedFromCloud(Song song) {
            // Simulate real cloud service by returning a random number
            return (int) (1 + Math.round(Math.random() % 500));
        }

        public int retrieveScrobbledRatingOf(Song song) {
            // Simulate real cloud service by returning a stable but meaningless number
            switch (song.getTitle()) {
                case "Candy":
                    return 78;
                case "A Change Is Gonna Come":
                    return 65;
                case "Desolation Row":
                    return 34;
                case "Bad Moon Rising":
                    return 28;
                case "Eleanor Rigby":
                    return 42;
                default:
                    return 0;
            }
        }

        public Collection<Song> retrieveAllSongs() {
            return Arrays.asList(
                    new Song("A Change Is Gonna Come", "Sam Cooke"),
                    new Song("Bad Moon Rising", "Creedence Clearwater Revival"),
                    new Song("Candy", "Paulo Nutini"),
                    new Song("Desolation Row", "Bob Dylan"),
                    new Song("Eleanor Rigby", "The Beatles"));
        }
    }
}
