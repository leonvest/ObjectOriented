package se.kth.iv1350.bikerepair.integration;

/**
 * Creates and provides access to all integration-layer registries used
 * by the controller. Centralizing instantiation here keeps the
 * controller decoupled from the concrete registry classes.
 */
public class RegistryCreator {
    private final CustomerRegistry customerRegistry = new CustomerRegistry();
    private final RepairOrderRegistry repairOrderRegistry = new RepairOrderRegistry();

    /**
     * @return the registry of customers
     */
    public CustomerRegistry getCustomerRegistry() {
        return customerRegistry;
    }

    /**
     * @return the registry of repair orders
     */
    public RepairOrderRegistry getRepairOrderRegistry() {
        return repairOrderRegistry;
    }
}
