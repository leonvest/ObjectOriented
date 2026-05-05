package se.kth.iv1350.bikerepair.integration;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import se.kth.iv1350.bikerepair.model.PhoneNumber;

/**
 * Unit tests for {@link CustomerRegistry}. Focus is on the lookup logic
 * for the pre-populated set of customers.
 */
public class CustomerRegistryTest {
    private CustomerRegistry registry;

    @BeforeEach
    public void setUp() {
        registry = new CustomerRegistry();
    }

    @AfterEach
    public void tearDown() {
        registry = null;
    }

    @Test
    public void testFindCustomerReturnsMatchingCustomer() {
        CustomerDTO found = registry.findCustomer(new PhoneNumber("0701234567"));
        assertNotNull(found,
                "findCustomer must return a customer when one matches the number.");
    }

    @Test
    public void testFindCustomerReturnsCustomerWithMatchingPhone() {
        PhoneNumber phone = new PhoneNumber("0701234567");
        CustomerDTO found = registry.findCustomer(phone);
        assertEquals(phone, found.getPhoneNumber(),
                "The returned customer's phone number must match the searched number.");
    }

    @Test
    public void testFindCustomerReturnsNullForUnknownPhone() {
        CustomerDTO found = registry.findCustomer(new PhoneNumber("0000000000"));
        assertNull(found,
                "findCustomer must return null when no customer has the given phone number.");
    }

    @Test
    public void testFindCustomerCanLookUpDifferentCustomers() {
        CustomerDTO anna = registry.findCustomer(new PhoneNumber("0701234567"));
        CustomerDTO bertil = registry.findCustomer(new PhoneNumber("0709876543"));
        assertNotNull(anna, "First registered customer must be findable.");
        assertNotNull(bertil, "Second registered customer must be findable.");
        assertNotEquals(anna.getName(), bertil.getName(),
                "Different phone numbers must resolve to different customers.");
    }
}
