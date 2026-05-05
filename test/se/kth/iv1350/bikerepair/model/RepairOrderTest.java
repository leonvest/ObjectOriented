package se.kth.iv1350.bikerepair.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import se.kth.iv1350.bikerepair.integration.BikeDTO;
import se.kth.iv1350.bikerepair.integration.CustomerDTO;
import se.kth.iv1350.bikerepair.integration.Printer;
import se.kth.iv1350.bikerepair.integration.RepairOrderDTO;
import se.kth.iv1350.bikerepair.integration.RepairTaskDTO;

/**
 * Unit tests for {@link RepairOrder}. Focus is on state transitions and
 * cost aggregation, since simple getters/constructors are not interesting
 * to test.
 */
public class RepairOrderTest {
    private static final OrderId ORDER_ID = new OrderId(1);
    private static final ProblemDescription PROBLEM =
            new ProblemDescription("Front brake fails to engage.");

    private CustomerDTO customer;
    private RepairOrder order;

    @BeforeEach
    public void setUp() {
        customer = new CustomerDTO(
                "Anna Andersson",
                "anna@example.com",
                new PhoneNumber("0701234567"),
                new BikeDTO("VanMoof", "S5", "VM-S5-00123"));
        order = new RepairOrder(ORDER_ID, PROBLEM, customer);
    }

    @AfterEach
    public void tearDown() {
        customer = null;
        order = null;
    }

    @Test
    public void testNewOrderStartsInCreatedState() {
        assertEquals(RepairOrderState.CREATED, order.getState(),
                "A freshly created order must be in the CREATED state.");
    }

    @Test
    public void testNewOrderHasZeroCost() {
        assertEquals(new Amount(0), order.getTotalCost(),
                "An order with no repair tasks should have a total cost of zero.");
    }

    @Test
    public void testAddingDiagnosticResultMovesOrderToAwaitingApproval() {
        order.addDiagnosticResult(new DiagnosticResult("Worn brake pads."));
        assertEquals(RepairOrderState.AWAITING_APPROVAL, order.getState(),
                "Recording a diagnostic finding must move the order to AWAITING_APPROVAL.");
    }

    @Test
    public void testAddingRepairTaskMovesOrderToAwaitingApproval() {
        order.addRepairTask(new RepairTaskDTO("Replace brake pads", new Amount(800)));
        assertEquals(RepairOrderState.AWAITING_APPROVAL, order.getState(),
                "Adding a repair task must move the order to AWAITING_APPROVAL.");
    }

    @Test
    public void testTotalCostIsSumOfSingleRepairTask() {
        order.addRepairTask(new RepairTaskDTO("Replace brake pads", new Amount(800)));
        assertEquals(new Amount(800), order.getTotalCost(),
                "Total cost must equal the cost of the single added task.");
    }

    @Test
    public void testTotalCostIsSumOfAllRepairTasks() {
        order.addRepairTask(new RepairTaskDTO("Replace brake pads", new Amount(800)));
        order.addRepairTask(new RepairTaskDTO("Replace battery", new Amount(2500)));
        order.addRepairTask(new RepairTaskDTO("Tune motor", new Amount(450)));
        assertEquals(new Amount(3750), order.getTotalCost(),
                "Total cost must equal the sum of every task's cost.");
    }

    @Test
    public void testAcceptingMovesOrderToAcceptedState() {
        order.addRepairTask(new RepairTaskDTO("Replace brake pads", new Amount(800)));
        order.accept();
        assertEquals(RepairOrderState.ACCEPTED, order.getState(),
                "Calling accept() must move the order to the ACCEPTED state.");
    }

    @Test
    public void testDtoExposesId() {
        RepairOrderDTO dto = order.toDTO();
        assertEquals(ORDER_ID, dto.getId(),
                "The DTO must expose the id of the order.");
    }

    @Test
    public void testDtoExposesProblemDescription() {
        RepairOrderDTO dto = order.toDTO();
        assertEquals(PROBLEM, dto.getProblemDescription(),
                "The DTO must expose the problem description.");
    }

    @Test
    public void testDtoExposesCurrentState() {
        order.addDiagnosticResult(new DiagnosticResult("Worn brake pads."));
        RepairOrderDTO dto = order.toDTO();
        assertEquals(RepairOrderState.AWAITING_APPROVAL, dto.getState(),
                "The DTO must reflect the current state of the order.");
    }

    @Test
    public void testDtoExposesCustomer() {
        RepairOrderDTO dto = order.toDTO();
        assertSame(customer, dto.getCustomer(),
                "The DTO must expose the customer of the order.");
    }

    @Test
    public void testDtoExposesTotalCost() {
        order.addRepairTask(new RepairTaskDTO("Replace brake pads", new Amount(800)));
        RepairOrderDTO dto = order.toDTO();
        assertEquals(new Amount(800), dto.getTotalCost(),
                "The DTO must expose the total cost of the order.");
    }

    @Test
    public void testDtoIncludesAddedDiagnosticResult() {
        DiagnosticResult finding = new DiagnosticResult("Worn brake pads.");
        order.addDiagnosticResult(finding);
        RepairOrderDTO dto = order.toDTO();
        assertEquals(1, dto.getDiagnosticResults().size(),
                "The DTO must include the added diagnostic finding.");
    }

    @Test
    public void testDtoIncludesAddedRepairTask() {
        order.addRepairTask(new RepairTaskDTO("Replace brake pads", new Amount(800)));
        RepairOrderDTO dto = order.toDTO();
        assertEquals(1, dto.getRepairTasks().size(),
                "The DTO must include the added repair task.");
    }

    @Test
    public void testDtoRepairTaskListIsUnmodifiable() {
        order.addRepairTask(new RepairTaskDTO("Replace brake pads", new Amount(800)));
        RepairOrderDTO dto = order.toDTO();

        boolean threw = false;
        try {
            dto.getRepairTasks().clear();
        } catch (UnsupportedOperationException expected) {
            threw = true;
        }
        assertTrue(threw,
                "The DTO must expose an unmodifiable list of repair tasks.");
    }

    @Test
    public void testPrintReceiptWritesReceiptStringToPrinter() {
        order.addRepairTask(new RepairTaskDTO("Replace brake pads", new Amount(800)));
        order.accept();

        ByteArrayOutputStream captured = new ByteArrayOutputStream();
        PrintStream originalSysOut = System.out;
        System.setOut(new PrintStream(captured));
        try {
            order.printReceipt(new Printer());
        } finally {
            System.setOut(originalSysOut);
        }

        assertTrue(captured.toString().contains("REPAIR ORDER"),
                "printReceipt must send a formatted receipt to the printer.");
    }

    @Test
    public void testDtoDiagnosticResultListIsUnmodifiable() {
        order.addDiagnosticResult(new DiagnosticResult("Worn brake pads."));
        RepairOrderDTO dto = order.toDTO();

        boolean threw = false;
        try {
            dto.getDiagnosticResults().clear();
        } catch (UnsupportedOperationException expected) {
            threw = true;
        }
        assertTrue(threw,
                "The DTO must expose an unmodifiable list of diagnostic findings.");
    }
}
