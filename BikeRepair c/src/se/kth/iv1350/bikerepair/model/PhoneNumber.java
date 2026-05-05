package se.kth.iv1350.bikerepair.model;

/**
 * A customer's phone number. Instances are immutable.
 */
public final class PhoneNumber {
    private final String number;

    /**
     * Creates a new phone number with the specified text.
     *
     * @param number the digits making up the phone number
     */
    public PhoneNumber(String number) {
        this.number = number;
    }

    /**
     * @return the text representation of this phone number
     */
    public String getNumber() {
        return number;
    }

    /**
     * Two phone numbers are equal if their text representations are equal.
     *
     * @param other the object to compare with
     * @return <code>true</code> if <code>other</code> is a
     *         <code>PhoneNumber</code> with the same digits,
     *         <code>false</code> otherwise
     */
    @Override
    public boolean equals(Object other) {
        if (!(other instanceof PhoneNumber)) {
            return false;
        }
        return this.number.equals(((PhoneNumber) other).number);
    }

    /**
     * @return a hash code consistent with {@link #equals(Object)}
     */
    @Override
    public int hashCode() {
        return number.hashCode();
    }

    /**
     * @return this phone number as a string
     */
    @Override
    public String toString() {
        return number;
    }
}
