package se.kth.iv1350.bikerepair.view;

import se.kth.iv1350.bikerepair.controller.Controller;
import se.kth.iv1350.bikerepair.integration.CustomerDTO;
import se.kth.iv1350.bikerepair.integration.RepairOrderDTO;
import se.kth.iv1350.bikerepair.integration.RepairTaskDTO;
import se.kth.iv1350.bikerepair.model.Amount;
import se.kth.iv1350.bikerepair.model.DiagnosticResult;
import se.kth.iv1350.bikerepair.model.PhoneNumber;
import se.kth.iv1350.bikerepair.model.ProblemDescription;

/**
 * A placeholder for the real user interface. Instead of reading user
 * input, it makes a hard-coded sequence of calls to the controller that
 * exercises the basic flow, and prints everything the controller returns.
 */
public class View {
    private static final PhoneNumber CUSTOMER_PHONE = new PhoneNumber("0701234567");
    private static final ProblemDescription PROBLEM_DESCRIPTION =
            new ProblemDescription("Brakes don't work and battery drains quickly.");
    private static final DiagnosticResult DIAGNOSTIC_FINDING =
            new DiagnosticResult("Worn brake pads and a faulty battery cell.");
    private static final RepairTaskDTO BRAKE_REPAIR_TASK =
            new RepairTaskDTO("Replace brake pads", new Amount(800));
    private static final RepairTaskDTO BATTERY_REPLACEMENT_TASK =
            new RepairTaskDTO("Replace battery", new Amount(2500));

    private final Controller controller;

    /**
     * Creates a new view that talks to the given controller.
     *
     * @param controller the controller used for all system operations
     */
    public View(Controller controller) {
        this.controller = controller;
    }

    /**
     * Runs the hard-coded basic flow from start to acceptance, printing
     * every value returned by the controller.
     */
    public void runBasicFlow() {
        printHeader("1. Cashier looks up the customer");
        CustomerDTO customer = controller.findCustomer(CUSTOMER_PHONE);
        System.out.println(customer);

        printHeader("2. Cashier creates a repair order");
        RepairOrderDTO afterCreation = controller.createRepairOrder(
                PROBLEM_DESCRIPTION, customer);
        System.out.println(afterCreation);

        printHeader("3. Technician records the diagnostic finding");
        RepairOrderDTO afterDiagnosis = controller.addDiagnosticResult(
                DIAGNOSTIC_FINDING);
        System.out.println(afterDiagnosis);

        printHeader("4. Technician adds the brake repair task");
        RepairOrderDTO afterFirstTask = controller.addRepairTask(BRAKE_REPAIR_TASK);
        System.out.println(afterFirstTask);

        printHeader("5. Technician adds the battery replacement task");
        RepairOrderDTO afterSecondTask = controller.addRepairTask(BATTERY_REPLACEMENT_TASK);
        System.out.println(afterSecondTask);

        printHeader("6. Customer accepts the repair");
        RepairOrderDTO accepted = controller.acceptRepairOrder();
        System.out.println(accepted);
    }


    /**
     * Encapsulation - to avoid println lines 6 times
     */
    private void printHeader(String title) {
        System.out.println();
        System.out.println("--- " + title + " ---");
    }
}
