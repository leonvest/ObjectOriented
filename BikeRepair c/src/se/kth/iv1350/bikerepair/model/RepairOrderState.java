package se.kth.iv1350.bikerepair.model;

/**
 * The lifecycle states of a repair order in the basic flow.
 */
public enum RepairOrderState {
    /** The order has been created but not yet diagnosed. */
    CREATED,
    /** Diagnosis is done; the order is waiting for the customer's decision. */
    AWAITING_APPROVAL,
    /** The customer has approved the repair. */
    ACCEPTED
}
