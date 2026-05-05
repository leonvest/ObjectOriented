package se.kth.iv1350.bikerepair.model;

import se.kth.iv1350.bikerepair.integration.RepairOrderDTO;
import se.kth.iv1350.bikerepair.integration.RepairTaskDTO;

/**
 * The receipt of an accepted repair order. Knows how to format itself
 * as a string suitable for printing.
 */
public class Receipt {
    private final RepairOrderDTO order;

    /**
     * Creates a new receipt for the specified order.
     *
     * @param order the order to be receipted
     */
    public Receipt(RepairOrderDTO order) {
        this.order = order;
    }

    /**
     * Creates a well-formatted string with the entire content of the
     * receipt.
     *
     * @return the well-formatted receipt string
     */
    public String createReceiptString() {
        StringBuilder out = new StringBuilder();
        out.append("\n=== REPAIR ORDER ").append(order.getId()).append(" ===\n");
        appendLine(out, "Date: ", order.getCreationDate().toString());
        appendLine(out, "State: ", order.getState().toString());
        appendLine(out, "Customer: ", order.getCustomer().getName());
        appendLine(out, "Bike: ", order.getCustomer().getBike().toString());
        appendLine(out, "Problem: ", order.getProblemDescription().toString());
        appendDiagnostics(out);
        appendTasks(out);
        appendLine(out, "Total cost: ", order.getTotalCost().toString());
        out.append("============================\n");
        return out.toString();
    }

    private void appendLine(StringBuilder out, String label, String value) {
        out.append(label).append(value).append("\n");
    }

    private void appendDiagnostics(StringBuilder out) {
        out.append("Diagnostic findings:\n");
        if (order.getDiagnosticResults().isEmpty()) {
            out.append("  (none)\n");
        } else {
            for (DiagnosticResult finding : order.getDiagnosticResults()) {
                out.append("  - ").append(finding).append("\n");
            }
        }
    }

    private void appendTasks(StringBuilder out) {
        out.append("Planned repair tasks:\n");
        if (order.getRepairTasks().isEmpty()) {
            out.append("  (none)\n");
        } else {
            for (RepairTaskDTO task : order.getRepairTasks()) {
                out.append("  - ").append(task).append("\n");
            }
        }
    }
}
