package se.kth.iv1350.bikerepair.model;

/**
 * A single repair task that the technician plans to perform on a bike,
 * with a description and an estimated cost.
 */
public class RepairTask {
    private final String description;
    private final Amount cost;

    /**
     * Creates a new repair task.
     *
     * @param description what the technician will do
     * @param cost the estimated cost of the task
     */
    public RepairTask(String description, Amount cost) {
        this.description = description;
        this.cost = cost;
    }

    /**
     * @return the description of this task
     */
    public String getDescription() {
        return description;
    }

    /**
     * @return the estimated cost of this task
     */
    public Amount getCost() {
        return cost;
    }
}
