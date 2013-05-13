package org.adoptopenjdk.lambda.tutorial.exercise2;

/**
 * Lambda Tutorial -- Adopt Open JDK
 *
 * @author Graham Allan grundlefleck at gmail dot com
 */
public final class RegisteredVoter {
    private final String electorId;

    /**
     * Constructor
     * @param electorId - elector Id
     */
    public RegisteredVoter(String electorId) {
        this.electorId = electorId;
    }

    @SuppressWarnings("javadoc")
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RegisteredVoter that = (RegisteredVoter) o;

        if (!electorId.equals(that.getElectorId())) return false;

        return true;
    }

    @SuppressWarnings("javadoc")
    @Override
    public int hashCode() {
        return electorId.hashCode();
    }

    @SuppressWarnings("javadoc")
    @Override
    public String toString() {
        return "RegisteredVoter{" +
                "electorId='" + electorId + '\'' +
                '}';
    }

    /**
     * Get the electorId for this voter
     * @return elector ID
     */
    public String getElectorId() {
        return electorId;
    }
}
