package se.kth.iv1350.bikerepair.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.Test;

/**
 * Unit tests for {@link DiagnosticResult}. Verifies value-equality
 * semantics.
 */
public class DiagnosticResultTest {

    @Test
    public void testEqualsReturnsTrueForResultsWithSameText() {
        assertEquals(new DiagnosticResult("Worn brake pads."),
                new DiagnosticResult("Worn brake pads."),
                "Two diagnostic results with the same text must be equal.");
    }

    @Test
    public void testEqualsReturnsFalseForResultsWithDifferentText() {
        assertNotEquals(new DiagnosticResult("Worn brake pads."),
                new DiagnosticResult("Faulty battery cell."),
                "Two diagnostic results with different texts must not be equal.");
    }

    @Test
    public void testEqualsReturnsFalseForNull() {
        assertFalse(new DiagnosticResult("Worn brake pads.").equals(null),
                "A diagnostic result must not be equal to null.");
    }

    @Test
    public void testHashCodeIsEqualForEqualResults() {
        assertEquals(new DiagnosticResult("Worn brake pads.").hashCode(),
                new DiagnosticResult("Worn brake pads.").hashCode(),
                "Equal diagnostic results must have equal hash codes.");
    }

    @Test
    public void testToStringReturnsTheText() {
        assertEquals("Worn brake pads.",
                new DiagnosticResult("Worn brake pads.").toString(),
                "toString must return the diagnostic text.");
    }
}
