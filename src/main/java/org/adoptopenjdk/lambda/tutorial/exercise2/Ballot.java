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
public final class Ballot {

    private final boolean isSpoiled;
    private final Party party;

    private Ballot(Party party) {
        this.party = party;
        this.isSpoiled = party == null;
    }

    @Override
    public String toString() {
        return "Ballot{" +
                "isSpoiled=" + isSpoiled +
                ", party=" + party +
                '}';
    }

    /**
     * Vote for a particular party on a Ballot
     * 
     * @param party - party to vote for
     * @return The Ballot containing the party
     */
    public static Ballot voteFor(Party party) {
        return new Ballot(party);
    }

    /**
     * Indicate a spoiled Ballot (one that has no Party)
     * @return A spoiled Ballot
     */
    public static Ballot spoiled() {
        return new Ballot(null);
    }

    public boolean isSpoiled() {
        return isSpoiled;
    }

    public Party getParty() {
        return party;
    }
}
