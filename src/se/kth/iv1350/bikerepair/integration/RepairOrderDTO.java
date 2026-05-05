

package se.kth.iv1350.bikerepair.integration;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import se.kth.iv1350.bikerepair.model.Amount;
import se.kth.iv1350.bikerepair.model.DiagnosticResult;
import se.kth.iv1350.bikerepair.model.OrderId;
import se.kth.iv1350.bikerepair.model.ProblemDescription;
import se.kth.iv1350.bikerepair.model.RepairOrderState;

/**
 * An immutable snapshot of a repair order, used to pass order data
 * out of the model layer without exposing mutable model objects.
 */
public final class RepairOrderDTO {
    private final OrderId id;
    private final LocalDate creationDate;
    private final ProblemDescription problemDescription;
    private final RepairOrderState state;
    private final CustomerDTO customer;
    private final List<DiagnosticResult> diagnosticResults;
    private final List<RepairTaskDTO> repairTasks;
    private final Amount totalCost;

    /**
     * Creates a new repair order DTO.
     *
     * @param id the unique id of the order
     * @param creationDate the date the order was created
     * @param problemDescription the customer's description of the problem
     * @param state the current state of the order
     * @param customer the customer who owns the bike
     * @param diagnosticResults the diagnostic findings so far
     * @param repairTasks the planned repair tasks
     * @param totalCost the sum of all repair task costs
     */
    public RepairOrderDTO(OrderId id, LocalDate creationDate,
                          ProblemDescription problemDescription,

                          RepairOrderState state, CustomerDTO customer,
                          List<DiagnosticResult> diagnosticResults,
                          List<RepairTaskDTO> repairTasks,
                          Amount totalCost) {

        this.id = id;
        this.creationDate = creationDate;
        this.problemDescription = problemDescription;
        this.state = state;
        this.customer = customer;
        this.diagnosticResults = Collections.unmodifiableList(diagnosticResults);
        this.repairTasks = Collections.unmodifiableList(repairTasks);
        this.totalCost = totalCost;
    }

    /** @return the order id */
    public OrderId getId() {
        return id;
    }

    /** @return the date the order was created */
    public LocalDate getCreationDate() {
        return creationDate;
    }

    /** @return the customer's description of the problem */
    public ProblemDescription getProblemDescription() {
        return problemDescription;
    }

    /** @return the current state of the order */
    public RepairOrderState getState() {
        return state;
    }


    /** @return the customer who owns the bike */
    public CustomerDTO getCustomer() {
        return customer;
    }

    /** @return an unmodifiable list of diagnostic findings */
    public List<DiagnosticResult> getDiagnosticResults() {
        return diagnosticResults;
    }

    /** @return an unmodifiable list of planned repair tasks */
    public List<RepairTaskDTO> getRepairTasks() {
        return repairTasks;
    }

    /** @return the sum of all repair task costs */
    public Amount getTotalCost() {
        return totalCost;
    }

    /**
     * @return a multi-line string showing the order id, state, total cost,
     *         problem description, diagnostic findings and repair tasks
     */
    @Override
    public String toString() {


        StringBuilder out = new StringBuilder();

        out.append("Order ").append(id)
                .append(" | state: ").append(state)
                .append(" | total: ").append(totalCost);
        out.append("\n  Problem: ").append(problemDescription);

        if (!diagnosticResults.isEmpty()) {
            out.append("\n  Diagnostics: ").append(diagnosticResults);
        }

        if (!repairTasks.isEmpty()) {
            out.append("\n  Tasks: ").append(repairTasks);
        }


        return out.toString();
    }
}
