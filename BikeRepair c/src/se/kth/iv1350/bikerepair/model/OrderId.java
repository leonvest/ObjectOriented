package se.kth.iv1350.bikerepair.model;

/**
 * The unique identifier of a repair order. Instances are immutable.
 */
public final class OrderId {
    private final int value;

    /**
     * Creates a new identifier with the specified value.
     *
     * @param value the numeric value of the identifier
     */
    public OrderId(int value) {
        this.value = value;
    }

    /**
     * @return the numeric value of this identifier
     */
    public int getValue() {
        return value;
    }

    /**
     * Two order ids are equal if their values are equal.
     *
     * @param other the object to compare with
     * @return <code>true</code> if <code>other</code> is an <code>OrderId</code>
     *         with the same value, <code>false</code> otherwise
     */
    @Override
    public boolean equals(Object other) {
        if (!(other instanceof OrderId)) {
            return false;
        }
        return this.value == ((OrderId) other).value;
    }

    /**
     * @return a hash code consistent with {@link #equals(Object)}
     */
    @Override
    public int hashCode() {
        return Integer.hashCode(value);
    }

    /**
     * @return this identifier as a string, prefixed with <code>#</code>
     */
    @Override
    public String toString() {
        return "#" + value;
    }
}
