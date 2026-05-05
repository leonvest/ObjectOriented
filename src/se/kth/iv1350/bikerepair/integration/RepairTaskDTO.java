

package se.kth.iv1350.bikerepair.integration;

import se.kth.iv1350.bikerepair.model.Amount;

/**
 * An immutable view of a repair task, used to expose task data
 * outside the model layer.
 */
public final class RepairTaskDTO {

    private final String description;
    private final Amount cost;

    /**
     * Creates a new repair task DTO.
     *
     * @param description what the technician will do
     * @param cost the estimated cost of the task
     */
    public RepairTaskDTO(String description, Amount cost) {

        this.description = description;
        this.cost = cost;

    }

    /** @return the description of this task */
    public String getDescription() {
        return description;
    }

    /** @return the cost of this task */
    public Amount getCost() {
        return cost;
    }

    /**
     * @return a string showing the task description and cost
     */
    @Override
    public String toString() {
        return description + " — " + cost;
    }
}
