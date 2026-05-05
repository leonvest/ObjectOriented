package se.kth.iv1350.bikerepair.integration;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import se.kth.iv1350.bikerepair.model.Amount;
import se.kth.iv1350.bikerepair.model.DiagnosticResult;
import se.kth.iv1350.bikerepair.model.OrderId;
import se.kth.iv1350.bikerepair.model.PhoneNumber;
import se.kth.iv1350.bikerepair.model.ProblemDescription;
import se.kth.iv1350.bikerepair.model.Receipt;
import se.kth.iv1350.bikerepair.model.RepairOrderState;

/**
 * Unit tests for {@link Printer}. The output is captured by replacing
 * <code>System.out</code> with an in-memory stream, as illustrated in
 * the textbook (chapter 7).
 */
public class PrinterTest {
    private ByteArrayOutputStream outContent;
    private PrintStream originalSysOut;
    private Printer printer;
    private Receipt receipt;

    @BeforeEach
    public void setUp() {
        originalSysOut = System.out;
        outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        printer = new Printer();

        CustomerDTO customer = new CustomerDTO(
                "Anna Andersson",
                "anna@example.com",
                new PhoneNumber("0701234567"),
                new BikeDTO("VanMoof", "S5", "VM-S5-00123"));
        List<DiagnosticResult> diagnostics = new ArrayList<>(
                Arrays.asList(new DiagnosticResult("Worn brake pads.")));
        List<RepairTaskDTO> tasks = new ArrayList<>(
                Arrays.asList(new RepairTaskDTO("Replace brake pads", new Amount(800))));
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
    }

    @AfterEach
    public void tearDown() {
        outContent = null;
        System.setOut(originalSysOut);
        printer = null;
        receipt = null;
    }

    @Test
    public void testPrintReceiptWritesTheReceiptStringToSystemOut() {
        printer.printReceipt(receipt);
        assertTrue(outContent.toString().contains(receipt.createReceiptString()),
                "The printer must write the entire receipt string to System.out.");
    }
}
