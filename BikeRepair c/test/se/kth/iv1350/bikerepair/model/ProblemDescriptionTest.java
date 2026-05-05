package se.kth.iv1350.bikerepair.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.Test;

/**
 * Unit tests for {@link ProblemDescription}. Verifies value-equality
 * semantics.
 */
public class ProblemDescriptionTest {

    @Test
    public void testEqualsReturnsTrueForDescriptionsWithSameText() {
        assertEquals(new ProblemDescription("Brakes broken"),
                new ProblemDescription("Brakes broken"),
                "Two problem descriptions with the same text must be equal.");
    }

    @Test
    public void testEqualsReturnsFalseForDescriptionsWithDifferentText() {
        assertNotEquals(new ProblemDescription("Brakes broken"),
                new ProblemDescription("Battery dead"),
                "Two problem descriptions with different texts must not be equal.");
    }

    @Test
    public void testEqualsReturnsFalseForNull() {
        assertFalse(new ProblemDescription("Brakes broken").equals(null),
                "A problem description must not be equal to null.");
    }

    @Test
    public void testHashCodeIsEqualForEqualDescriptions() {
        assertEquals(new ProblemDescription("Brakes broken").hashCode(),
                new ProblemDescription("Brakes broken").hashCode(),
                "Equal problem descriptions must have equal hash codes.");
    }

    @Test
    public void testToStringReturnsTheText() {
        assertEquals("Brakes broken",
                new ProblemDescription("Brakes broken").toString(),
                "toString must return the description text.");
    }
}
