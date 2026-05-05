package se.kth.iv1350.bikerepair.controller;

import se.kth.iv1350.bikerepair.integration.CustomerDTO;
import se.kth.iv1350.bikerepair.integration.CustomerRegistry;
import se.kth.iv1350.bikerepair.integration.Printer;
import se.kth.iv1350.bikerepair.integration.RegistryCreator;
import se.kth.iv1350.bikerepair.integration.RepairOrderDTO;
import se.kth.iv1350.bikerepair.integration.RepairOrderRegistry;
import se.kth.iv1350.bikerepair.integration.RepairTaskDTO;
import se.kth.iv1350.bikerepair.model.DiagnosticResult;
import se.kth.iv1350.bikerepair.model.PhoneNumber;
import se.kth.iv1350.bikerepair.model.ProblemDescription;
import se.kth.iv1350.bikerepair.model.RepairOrder;

/**
 * The single entry point for the view into the model and integration layers.
 * Each public method corresponds to one system operation in the basic flow.
 */
public class Controller {
    private final CustomerRegistry customerRegistry;
    private final RepairOrderRegistry repairOrderRegistry;
    private final Printer printer;
    private RepairOrder currentOrder;

    /**
     * Creates a new controller wired to the given external services.
     *
     * @param registryCreator provides access to all integration registries
     * @param printer the printer used when an order is accepted
     */
    public Controller(RegistryCreator registryCreator, Printer printer) {
        this.customerRegistry = registryCreator.getCustomerRegistry();
        this.repairOrderRegistry = registryCreator.getRepairOrderRegistry();
        this.printer = printer;
    }

    /**
     * Looks up a customer by phone number.
     *
     * @param phoneNumber the customer's phone number
     * @return the matching customer, or <code>null</code> if none was found
     */
    public CustomerDTO findCustomer(PhoneNumber phoneNumber) {
        return customerRegistry.findCustomer(phoneNumber);
    }

    /**
     * Creates a new repair order for the given customer's bike. The newly
     * created order becomes the active order, on which subsequent system
     * operations will operate.
     *
     * @param problemDescription the customer's description of the problem
     * @param customer the customer who owns the bike
     * @return a snapshot of the newly created order
     */
    public RepairOrderDTO createRepairOrder(ProblemDescription problemDescription, CustomerDTO customer) {
        currentOrder = repairOrderRegistry.createRepairOrder(problemDescription, customer);
        return currentOrder.toDTO();
    }

    /**
     * Records a diagnostic finding on the active repair order.
     *
     * @param diagnosticResult the diagnostic finding
     * @return an updated snapshot of the active order
     */
    public RepairOrderDTO addDiagnosticResult(DiagnosticResult diagnosticResult) {
        currentOrder.addDiagnosticResult(diagnosticResult);
        return currentOrder.toDTO();
    }

    /**
     * Adds a planned repair task to the active repair order.
     *
     * @param task a description and cost of the planned repair task
     * @return an updated snapshot of the active order
     */
    public RepairOrderDTO addRepairTask(RepairTaskDTO task) {
        currentOrder.addRepairTask(task);
        return currentOrder.toDTO();
    }

    /**
     * Marks the active repair order as accepted by the customer and
     * prints its receipt.
     *
     * @return an updated snapshot of the active order
     */
    public RepairOrderDTO acceptRepairOrder() {
        currentOrder.accept();
        currentOrder.printReceipt(printer);
        return currentOrder.toDTO();
    }
}
