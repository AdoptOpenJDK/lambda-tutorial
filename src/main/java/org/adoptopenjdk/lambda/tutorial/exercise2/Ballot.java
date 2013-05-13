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

    @SuppressWarnings("javadoc")
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

    /**
     * True if the Ballot is spoiled, else false
     * @return true if spoiled, else false
     */
    public boolean isSpoiled() {
        return isSpoiled;
    }

    /**
     * Get the party belonging to this Ballot
     * @return The party belonging to this Ballot
     */
    public Party getParty() {
        return party;
    }
}
