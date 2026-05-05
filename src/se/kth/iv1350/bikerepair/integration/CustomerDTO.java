package se.kth.iv1350.bikerepair.integration;

import se.kth.iv1350.bikerepair.model.PhoneNumber;

/**
 * An immutable data transfer object describing a customer and their bike.
 */
public final class CustomerDTO {

    private final String name;
    private final String email;
    private final PhoneNumber phoneNumber;
    private final BikeDTO bike;

    /**
     * Creates a new customer DTO.
     *
     * @param name the customer's full name
     * @param email the customer's email address
     * @param phoneNumber the customer's phone number
     * @param bike the customer's bike
     */
    public CustomerDTO(String name, String email, PhoneNumber phoneNumber, BikeDTO bike) {

        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.bike = bike;

    }

    /** @return the customer's name */
    public String getName() {
        return name;
    }

    /** @return the customer's email */
    public String getEmail() {

        return email;
    }

    /** @return the customer's phone number */
    public PhoneNumber getPhoneNumber() {
        return phoneNumber;
    }

    /** @return the customer's bike */
    public BikeDTO getBike() {
        return bike;
    }

    /**
     * @return a string showing the customer's name, email, phone number
     *         and bike
     */
    @Override
    public String toString() {
        return  name + " <" + email +  ">, phone " +  phoneNumber + ", bike: " +  bike;
    }
}
