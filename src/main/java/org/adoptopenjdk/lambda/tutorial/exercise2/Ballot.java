package org.adoptopenjdk.lambda.tutorial.exercise2;

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

    public static Ballot voteFor(Party party) {
        return new Ballot(party);
    }

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
