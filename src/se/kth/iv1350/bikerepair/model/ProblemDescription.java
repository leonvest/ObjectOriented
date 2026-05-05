

package se.kth.iv1350.bikerepair.model;

/**
 * The customer's description of the problem with their bike.
 * Instances are immutable.
 */
public final class ProblemDescription {
    private final String text;

    /**
     * Creates a new problem description with the specified text.
     *
     * @param text the text describing the problem
     */
    public ProblemDescription(String text) {
        this.text = text;
    }

    /**
     * @return the text of this problem description
     */
    public String getText() {
        return text;
    }

    /**
     * The two problem descriptions are equal if their texts are equal.
     *
     * @param other the object to compare with
     * @return <code>true</code> if <code>other</code> is a
     *         <code>ProblemDescription</code> with the same text,
     *         <code>false</code> otherwise
     */
    @Override
    public boolean equals(Object other) {
        if (!(other instanceof ProblemDescription)) {
            return false;
        }


        return this.text.equals(((ProblemDescription) other).text);
    }

    /**
     * @return a hash code consistent with {@link #equals(Object)}
     */
    @Override
    public int hashCode() {
        return text.hashCode();
    }

    /**
     * @return the text of this description
     */
    @Override
    public String toString() {
        return text;
    }
}
