package se.kth.iv1350.bikerepair.model;

/**
 * Represents an immutable amount of money. Operations return a new
 * <code>Amount</code> instead of changing this instance.
 */
public final class Amount {
    private final int value;

    /**
     * Creates a new amount with the value zero.
     */
    public Amount() {
        this(0);
    }

    /**
     * A new amount is created with the specified value.
     *
     * @param value the value of the new amount
     */
    public Amount(int value) {
        this.value = value;
    }

    /**
     * Adds the specified amount to this amount.
     *
     * @param other the amount to add
     * @return a new amount that is the sum of this amount and the other
     */
    public Amount plus(Amount other) {
        return new Amount(this.value + other.value);
    }

    /**
     * Returns the numeric value of this amount.
     *
     * @return the value
     */
    public int getValue() {
        return value;
    }

    /**
     * Two amounts are equal if their values are equal.
     *
     * @param other the object to compare with
     * @return <code>true</code> if <code>other</code> is an
     *         <code>Amount</code> with the same value,
     *         <code>false</code> otherwise
     */
    @Override
    public boolean equals(Object other) {
        if (!(other instanceof Amount)) {
            return false;

        }
        return this.value == ((Amount) other).value;
    }

    /**
     * @return a hash code consistent with {@link #equals(Object)}
     */
    @Override
    public int hashCode() {
        return Integer.hashCode(value);
    }

    /**
     * Returns this amount formatted as a string in Swedish kronor.
     */
    @Override
    public String toString() {
        return value + " SEK";
    }
}
