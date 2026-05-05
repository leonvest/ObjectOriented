package se.kth.iv1350.bikerepair.model;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import se.kth.iv1350.bikerepair.integration.BikeDTO;
import se.kth.iv1350.bikerepair.integration.CustomerDTO;
import se.kth.iv1350.bikerepair.integration.RepairOrderDTO;
import se.kth.iv1350.bikerepair.integration.RepairTaskDTO;

/**
 * Unit tests for {@link Receipt}. Verifies that the receipt string
 * contains every piece of information from the underlying order.
 */
public class ReceiptTest {
    private Receipt receipt;
    private String receiptString;

    @BeforeEach
    public void setUp() {
        CustomerDTO customer = new CustomerDTO(
                "Anna Andersson",
                "anna@example.com",
                new PhoneNumber("0701234567"),
                new BikeDTO("VanMoof", "S5", "VM-S5-00123"));
        List<DiagnosticResult> diagnostics = new ArrayList<>(Arrays.asList(
                new DiagnosticResult("Worn brake pads.")));
        List<RepairTaskDTO> tasks = new ArrayList<>(Arrays.asList(
                new RepairTaskDTO("Replace brake pads", new Amount(800))));
        RepairOrderDTO order = new RepairOrderDTO(
                new OrderId(7),
                LocalDate.of(2026, 1, 15),
                new ProblemDescription("Brakes don't work."),
                RepairOrderState.ACCEPTED,
                customer,
                diagnostics,
                tasks,
                new Amount(800));
        receipt = new Receipt(order);
        receiptString = receipt.createReceiptString();
    }

    @AfterEach
    public void tearDown() {
        receipt = null;
        receiptString = null;
    }

    @Test
    public void testReceiptContainsOrderId() {
        assertTrue(receiptString.contains("7"),
                "The receipt must contain the order id.");
    }

    @Test
    public void testReceiptContainsCreationDate() {
        assertTrue(receiptString.contains("2026-01-15"),
                "The receipt must contain the creation date.");
    }

    @Test
    public void testReceiptContainsState() {
        assertTrue(receiptString.contains("ACCEPTED"),
                "The receipt must contain the order state.");
    }

    @Test
    public void testReceiptContainsCustomerName() {
        assertTrue(receiptString.contains("Anna Andersson"),
                "The receipt must contain the customer's name.");
    }

    @Test
    public void testReceiptContainsBikeBrand() {
        assertTrue(receiptString.contains("VanMoof"),
                "The receipt must contain the bike brand.");
    }

    @Test
    public void testReceiptContainsProblemDescription() {
        assertTrue(receiptString.contains("Brakes don't work."),
                "The receipt must contain the problem description.");
    }

    @Test
    public void testReceiptContainsDiagnosticFinding() {
        assertTrue(receiptString.contains("Worn brake pads."),
                "The receipt must contain each diagnostic finding.");
    }

    @Test
    public void testReceiptContainsRepairTaskDescription() {
        assertTrue(receiptString.contains("Replace brake pads"),
                "The receipt must contain each repair task's description.");
    }

    @Test
    public void testReceiptContainsTotalCost() {
        assertTrue(receiptString.contains("800 SEK"),
                "The receipt must contain the total cost.");
    }

    @Test
    public void testReceiptHandlesEmptyDiagnostics() {
        RepairOrderDTO empty = new RepairOrderDTO(
                new OrderId(8),
                LocalDate.of(2026, 1, 15),
                new ProblemDescription("Brakes don't work."),
                RepairOrderState.CREATED,
                new CustomerDTO("X", "x@example.com",
                        new PhoneNumber("0"),
                        new BikeDTO("b", "m", "s")),
                new ArrayList<>(),
                new ArrayList<>(),
                new Amount(0));
        Receipt emptyReceipt = new Receipt(empty);
        assertTrue(emptyReceipt.createReceiptString().contains("(none)"),
                "The receipt must show '(none)' when there are no findings or tasks.");
    }
}
