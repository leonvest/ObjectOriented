package se.kth.iv1350.bikerepair.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

/**
 * Unit tests for {@link Amount}. Focuses on equality, addition and the
 * SEK string representation.
 */
public class AmountTest {

    @Test
    public void testNoArgConstructorCreatesZeroAmount() {
        Amount zero = new Amount();
        assertEquals(0, zero.getValue(),
                "The no-arg constructor must create an amount with value zero.");
    }

    @Test
    public void testIntConstructorStoresGivenValue() {
        Amount amount = new Amount(42);
        assertEquals(42, amount.getValue(),
                "The int constructor must store the given value.");
    }

    @Test
    public void testPlusReturnsSumOfTwoPositiveAmounts() {
        Amount sum = new Amount(800).plus(new Amount(2500));
        assertEquals(new Amount(3300), sum,
                "plus must return the sum of two amounts.");
    }

    @Test
    public void testPlusZeroReturnsEqualAmount() {
        Amount original = new Amount(100);
        Amount sum = original.plus(new Amount(0));
        assertEquals(original, sum,
                "Adding zero must return an equal amount.");
    }

    @Test
    public void testPlusDoesNotMutateOriginal() {
        Amount original = new Amount(100);
        original.plus(new Amount(50));
        assertEquals(100, original.getValue(),
                "plus must not mutate the original amount.");
    }

    @Test
    public void testEqualsReturnsTrueForAmountsWithSameValue() {
        assertEquals(new Amount(500), new Amount(500),
                "Two amounts with the same value must be equal.");
    }

    @Test
    public void testEqualsReturnsFalseForAmountsWithDifferentValue() {
        assertNotEquals(new Amount(500), new Amount(501),
                "Two amounts with different values must not be equal.");
    }

    @Test
    public void testEqualsReturnsFalseForNull() {
        assertFalse(new Amount(100).equals(null),
                "An amount must not be equal to null.");
    }

    @Test
    public void testEqualsReturnsFalseForDifferentType() {
        assertFalse(new Amount(100).equals("100"),
                "An amount must not be equal to an instance of another type.");
    }

    @Test
    public void testHashCodeIsEqualForEqualAmounts() {
        assertEquals(new Amount(500).hashCode(), new Amount(500).hashCode(),
                "Equal amounts must have equal hash codes.");
    }

    @Test
    public void testToStringIncludesValue() {
        assertTrue(new Amount(1234).toString().contains("1234"),
                "toString must include the numeric value.");
    }

    @Test
    public void testToStringIncludesSekSuffix() {
        assertTrue(new Amount(0).toString().contains("SEK"),
                "toString must include the SEK currency suffix.");
    }
}
