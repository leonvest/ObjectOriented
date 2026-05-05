package se.kth.iv1350.bikerepair.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.Test;

/**
 * Unit tests for {@link PhoneNumber}. Verifies value-equality semantics.
 */
public class PhoneNumberTest {

    @Test
    public void testEqualsReturnsTrueForNumbersWithSameDigits() {
        assertEquals(new PhoneNumber("0701234567"), new PhoneNumber("0701234567"),
                "Two phone numbers with the same digits must be equal.");
    }

    @Test
    public void testEqualsReturnsFalseForNumbersWithDifferentDigits() {
        assertNotEquals(new PhoneNumber("0701234567"), new PhoneNumber("0709876543"),
                "Two phone numbers with different digits must not be equal.");
    }

    @Test
    public void testEqualsReturnsFalseForNull() {
        assertFalse(new PhoneNumber("0701234567").equals(null),
                "A phone number must not be equal to null.");
    }

    @Test
    public void testEqualsReturnsFalseForDifferentType() {
        assertFalse(new PhoneNumber("0701234567").equals("0701234567"),
                "A phone number must not be equal to a plain String.");
    }

    @Test
    public void testHashCodeIsEqualForEqualNumbers() {
        assertEquals(new PhoneNumber("0701234567").hashCode(),
                new PhoneNumber("0701234567").hashCode(),
                "Equal phone numbers must have equal hash codes.");
    }

    @Test
    public void testToStringReturnsTheNumberAsAString() {
        assertEquals("0701234567", new PhoneNumber("0701234567").toString(),
                "toString must return the phone number as a string.");
    }
}
