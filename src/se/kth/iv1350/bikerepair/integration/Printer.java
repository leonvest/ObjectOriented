


package se.kth.iv1350.bikerepair.integration;

import se.kth.iv1350.bikerepair.model.Receipt;

/**
 * Stands in for the external printer. Prints the receipt of a repair
 * order to <code>System.out</code>.
 */
public class Printer {

    /**
     * Prints the specified receipt.
     *
     * @param receipt the receipt to print
     */
    public void printReceipt(Receipt receipt) {

        System.out.println(receipt.createReceiptString());
    }
}
