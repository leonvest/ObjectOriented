

package se.kth.iv1350.bikerepair.integration;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import se.kth.iv1350.bikerepair.model.OrderId;
import se.kth.iv1350.bikerepair.model.ProblemDescription;
import se.kth.iv1350.bikerepair.model.RepairOrder;

/**
 * Stands in for the external repai order database. Holds repair orders
 * in memory and assigns them sequential ids.
 */
public class RepairOrderRegistry {
    private static final int FIRST_ORDER_ID = 1;

    private final List<RepairOrder> orders = new ArrayList<>();
    private int nextOrderId = FIRST_ORDER_ID;

    /**
     * Creates a new repair order, stores it, and returns it.
     *
     * @param problemDescription the customer's description of the problem
     * @param customer the customer who owns the bike
     * @return the newly created order
     */
    public RepairOrder createRepairOrder(ProblemDescription problemDescription, CustomerDTO customer) {
        RepairOrder order = new RepairOrder(new OrderId(nextOrderId), problemDescription, customer);
        nextOrderId++;
        orders.add(order);
        return order;
    }

    /**
     * @return an unmodifiable view of all stored repair orders
     */
    public List<RepairOrder> findAll() {
        return Collections.unmodifiableList(orders);
    }
}
