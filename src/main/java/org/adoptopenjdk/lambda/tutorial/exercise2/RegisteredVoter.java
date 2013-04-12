package org.adoptopenjdk.lambda.tutorial.exercise2;

/**
 * Lambda Tutorial -- Adopt Open JDK
 *
 * @author Graham Allan grundlefleck at gmail dot com
 */
public final class RegisteredVoter {
    public final String electorId;

    public RegisteredVoter(String electorId) {
        this.electorId = electorId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RegisteredVoter that = (RegisteredVoter) o;

        if (!electorId.equals(that.electorId)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return electorId.hashCode();
    }
}
