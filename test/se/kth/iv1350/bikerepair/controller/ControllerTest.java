package se.kth.iv1350.bikerepair.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import se.kth.iv1350.bikerepair.integration.CustomerDTO;
import se.kth.iv1350.bikerepair.integration.Printer;
import se.kth.iv1350.bikerepair.integration.RegistryCreator;
import se.kth.iv1350.bikerepair.integration.RepairOrderDTO;
import se.kth.iv1350.bikerepair.integration.RepairTaskDTO;
import se.kth.iv1350.bikerepair.model.Amount;
import se.kth.iv1350.bikerepair.model.DiagnosticResult;
import se.kth.iv1350.bikerepair.model.PhoneNumber;
import se.kth.iv1350.bikerepair.model.ProblemDescription;
import se.kth.iv1350.bikerepair.model.RepairOrderState;

/**
 * Unit tests for {@link Controller}. Verifies that each system operation
 * correctly delegates to the integration and model layers, and that the
 * state of the active order is reflected in the returned DTO.
 */
public class ControllerTest {
    private static final PhoneNumber ANNA_PHONE = new PhoneNumber("0701234567");
    private static final ProblemDescription PROBLEM =
            new ProblemDescription("Brakes don't work.");
    private static final DiagnosticResult DIAGNOSTIC =
            new DiagnosticResult("Worn brake pads.");
    private static final RepairTaskDTO TASK =
            new RepairTaskDTO("Replace brake pads", new Amount(800));

    private Controller controller;
    private CustomerDTO anna;
    private ByteArrayOutputStream printerOutput;
    private PrintStream originalSysOut;

    @BeforeEach
    public void setUp() {
        originalSysOut = System.out;
        printerOutput = new ByteArrayOutputStream();
        System.setOut(new PrintStream(printerOutput));

        RegistryCreator registryCreator = new RegistryCreator();
        Printer printer = new Printer();
        controller = new Controller(registryCreator, printer);
        anna = controller.findCustomer(ANNA_PHONE);
    }

    @AfterEach
    public void tearDown() {
        System.setOut(originalSysOut);
        printerOutput = null;
        controller = null;
        anna = null;
    }

    @Test
    public void testFindCustomerReturnsMatchingCustomer() {
        CustomerDTO customer = controller.findCustomer(ANNA_PHONE);
        assertNotNull(customer,
                "findCustomer must return a customer for a registered number.");
    }

    @Test
    public void testFindCustomerReturnsNullForUnknownPhone() {
        CustomerDTO customer = controller.findCustomer(new PhoneNumber("0000000000"));
        assertNull(customer,
                "findCustomer must return null when no customer matches.");
    }

    @Test
    public void testCreateRepairOrderReturnsDtoWithProblemDescription() {
        RepairOrderDTO order = controller.createRepairOrder(PROBLEM, anna);
        assertEquals(PROBLEM, order.getProblemDescription(),
                "The created order DTO must carry the problem description.");
    }

    @Test
    public void testCreateRepairOrderReturnsDtoWithCreatedState() {
        RepairOrderDTO order = controller.createRepairOrder(PROBLEM, anna);
        assertEquals(RepairOrderState.CREATED, order.getState(),
                "A newly created order must be in the CREATED state.");
    }

    @Test
    public void testCreateRepairOrderReturnsDtoWithMatchingCustomer() {
        RepairOrderDTO order = controller.createRepairOrder(PROBLEM, anna);
        assertEquals(ANNA_PHONE, order.getCustomer().getPhoneNumber(),
                "The created order must reference the customer with the given phone number.");
    }

    @Test
    public void testAddDiagnosticResultMovesOrderToAwaitingApproval() {
        controller.createRepairOrder(PROBLEM, anna);
        RepairOrderDTO updated = controller.addDiagnosticResult(DIAGNOSTIC);
        assertEquals(RepairOrderState.AWAITING_APPROVAL, updated.getState(),
                "Recording a diagnostic finding must move the order to AWAITING_APPROVAL.");
    }

    @Test
    public void testAddDiagnosticResultDtoContainsTheFinding() {
        controller.createRepairOrder(PROBLEM, anna);
        RepairOrderDTO updated = controller.addDiagnosticResult(DIAGNOSTIC);
        assertTrue(updated.getDiagnosticResults().contains(DIAGNOSTIC),
                "The returned DTO must include the recorded diagnostic finding.");
    }

    @Test
    public void testAddRepairTaskUpdatesTotalCost() {
        controller.createRepairOrder(PROBLEM, anna);
        RepairOrderDTO updated = controller.addRepairTask(TASK);
        assertEquals(new Amount(800), updated.getTotalCost(),
                "The returned DTO must reflect the cost of the added task.");
    }

    @Test
    public void testAddRepairTaskAccumulatesCostAcrossMultipleTasks() {
        controller.createRepairOrder(PROBLEM, anna);
        controller.addRepairTask(TASK);
        RepairOrderDTO updated = controller.addRepairTask(
                new RepairTaskDTO("Replace battery", new Amount(2500)));
        assertEquals(new Amount(3300), updated.getTotalCost(),
                "Total cost must accumulate across multiple repair tasks.");
    }

    @Test
    public void testAcceptRepairOrderMovesItToAcceptedState() {
        controller.createRepairOrder(PROBLEM, anna);
        controller.addRepairTask(TASK);
        RepairOrderDTO accepted = controller.acceptRepairOrder();
        assertEquals(RepairOrderState.ACCEPTED, accepted.getState(),
                "acceptRepairOrder must move the order to the ACCEPTED state.");
    }

    @Test
    public void testAcceptRepairOrderTriggersAPrintout() {
        controller.createRepairOrder(PROBLEM, anna);
        controller.addRepairTask(TASK);
        printerOutput.reset();
        controller.acceptRepairOrder();
        assertTrue(printerOutput.toString().contains("REPAIR ORDER"),
                "acceptRepairOrder must trigger a printout to System.out.");
    }
}
