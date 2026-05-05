package se.kth.iv1350.bikerepair.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

/**
 * Unit tests for {@link OrderId}. Verifies value-equality semantics and
 * the textual representation.
 */
public class OrderIdTest {

    @Test
    public void testEqualsReturnsTrueForIdsWithSameValue() {
        assertEquals(new OrderId(1), new OrderId(1),
                "Two order ids with the same value must be equal.");
    }

    @Test
    public void testEqualsReturnsFalseForIdsWithDifferentValue() {
        assertNotEquals(new OrderId(1), new OrderId(2),
                "Two order ids with different values must not be equal.");
    }

    @Test
    public void testEqualsReturnsFalseForNull() {
        assertFalse(new OrderId(1).equals(null),
                "An order id must not be equal to null.");
    }

    @Test
    public void testEqualsReturnsFalseForDifferentType() {
        assertFalse(new OrderId(1).equals(Integer.valueOf(1)),
                "An order id must not be equal to a plain Integer.");
    }

    @Test
    public void testHashCodeIsEqualForEqualIds() {
        assertEquals(new OrderId(7).hashCode(), new OrderId(7).hashCode(),
                "Equal order ids must have equal hash codes.");
    }

    @Test
    public void testToStringIncludesValue() {
        assertTrue(new OrderId(42).toString().contains("42"),
                "toString must include the numeric value.");
    }
}
