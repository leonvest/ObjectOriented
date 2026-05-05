

package se.kth.iv1350.bikerepair.model;

/**
 * Diagnostic finding made by the technician while inspecting a bike.
 * (Instances are immutable).
 */
public final class DiagnosticResult {
    private final String text;

    /**
     * A new is crated with diagnostic result with the specified text.
     *
     * @param text the text describing the diagnostic finding
     */
    public DiagnosticResult(String text) {
        this.text = text;
    }

    /**
     * @return the text of this diagnostic result
     */
    public String getText() {
        return text;
    }

    /**
     * Two diagnostic results are equal if their texts are equal.
     *
     * @param other the object to compare with
     * @return <code>true</code> if <code>other</code> is a
     *         <code>DiagnosticResult</code> with the same text,
     *         <code>false</code> otherwise
     */
    @Override
    public boolean equals(Object other) {
        if (!(other instanceof DiagnosticResult)) {
            return false;
        }

        return this.text.equals(((DiagnosticResult) other).text);
    }

    /**
     * @return a hash code consistent with {@link #equals(Object)}
     */
    @Override
    public int hashCode() {
        return text.hashCode();
    }

    /**
     * @return the text of this diagnostic result
     */
    @Override
    public String toString() {
        return text;
    }
}
