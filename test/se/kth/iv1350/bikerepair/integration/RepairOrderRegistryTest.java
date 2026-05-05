package se.kth.iv1350.bikerepair.integration;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;

import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import se.kth.iv1350.bikerepair.model.OrderId;
import se.kth.iv1350.bikerepair.model.PhoneNumber;
import se.kth.iv1350.bikerepair.model.ProblemDescription;
import se.kth.iv1350.bikerepair.model.RepairOrder;

/**
 * Unit tests for {@link RepairOrderRegistry}. Focus is on the assignment
 * of sequential ids to newly created orders and on the registry
 * actually storing them.
 */
public class RepairOrderRegistryTest {
    private RepairOrderRegistry registry;
    private CustomerDTO anna;
    private CustomerDTO bertil;
    private ProblemDescription problem;
    private ProblemDescription otherProblem;

    @BeforeEach
    public void setUp() {
        registry = new RepairOrderRegistry();
        anna = new CustomerDTO(
                "Anna Andersson",
                "anna@example.com",
                new PhoneNumber("0701234567"),
                new BikeDTO("VanMoof", "S5", "VM-S5-00123"));
        bertil = new CustomerDTO(
                "Bertil Bengtsson",
                "bertil@example.com",
                new PhoneNumber("0709876543"),
                new BikeDTO("Cake", "Makka", "CK-MK-00987"));
        problem = new ProblemDescription("Brakes broken");
        otherProblem = new ProblemDescription("Battery dead");
    }

    @AfterEach
    public void tearDown() {
        registry = null;
        anna = null;
        bertil = null;
        problem = null;
        otherProblem = null;
    }

    @Test
    public void testFirstCreatedOrderGetsIdOne() {
        RepairOrder order = registry.createRepairOrder(problem, anna);
        assertEquals(new OrderId(1), order.getId(),
                "The first order should have id 1.");
    }

    @Test
    public void testCreatedOrdersGetSequentialIds() {
        RepairOrder first = registry.createRepairOrder(problem, anna);
        RepairOrder second = registry.createRepairOrder(otherProblem, bertil);
        RepairOrder third = registry.createRepairOrder(problem, anna);

        assertEquals(new OrderId(1), first.getId(),
                "The first order should have id 1.");
        assertEquals(new OrderId(2), second.getId(),
                "The second order should have id 2.");
        assertEquals(new OrderId(3), third.getId(),
                "The third order should have id 3.");
    }

    @Test
    public void testCreatedOrderIsStoredInTheRegistry() {
        RepairOrder created = registry.createRepairOrder(problem, anna);
        List<RepairOrder> all = registry.findAll();
        assertSame(created, all.get(0),
                "The newly created order must be retrievable via findAll.");
    }

    @Test
    public void testFindAllOnEmptyRegistryReturnsEmptyList() {
        List<RepairOrder> all = registry.findAll();
        assertEquals(0, all.size(),
                "findAll must return an empty list when no orders are stored.");
    }

    @Test
    public void testFindAllReturnsEveryStoredOrder() {
        registry.createRepairOrder(problem, anna);
        registry.createRepairOrder(otherProblem, bertil);

        List<RepairOrder> all = registry.findAll();
        assertEquals(2, all.size(),
                "findAll must return every stored order.");
    }
}
