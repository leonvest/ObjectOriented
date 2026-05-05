package se.kth.iv1350.bikerepair.integration;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertSame;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Unit tests for {@link RegistryCreator}. Verifies that the creator
 * exposes its registries and returns the same instance on repeated
 * calls.
 */
public class RegistryCreatorTest {
    private RegistryCreator creator;

    @BeforeEach
    public void setUp() {
        creator = new RegistryCreator();
    }

    @AfterEach
    public void tearDown() {
        creator = null;
    }

    @Test
    public void testGetCustomerRegistryReturnsNonNull() {
        assertNotNull(creator.getCustomerRegistry(),
                "getCustomerRegistry must return a non-null registry.");
    }

    @Test
    public void testGetRepairOrderRegistryReturnsNonNull() {
        assertNotNull(creator.getRepairOrderRegistry(),
                "getRepairOrderRegistry must return a non-null registry.");
    }

    @Test
    public void testGetCustomerRegistryReturnsSameInstanceOnRepeatedCalls() {
        assertSame(creator.getCustomerRegistry(), creator.getCustomerRegistry(),
                "getCustomerRegistry must always return the same instance.");
    }

    @Test
    public void testGetRepairOrderRegistryReturnsSameInstanceOnRepeatedCalls() {
        assertSame(creator.getRepairOrderRegistry(), creator.getRepairOrderRegistry(),
                "getRepairOrderRegistry must always return the same instance.");
    }
}
