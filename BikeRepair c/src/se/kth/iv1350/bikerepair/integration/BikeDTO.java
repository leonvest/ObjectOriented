package se.kth.iv1350.bikerepair.integration;

/**
 * An immutable data transfer object describing an electric bike.
 */
public final class BikeDTO {
    private final String brand;
    private final String model;
    private final String serialNumber;

    /**
     * Creates a new bike DTO.
     *
     * @param brand the manufacturer of the bike
     * @param model the model name
     * @param serialNumber the unique serial number
     */
    public BikeDTO(String brand, String model, String serialNumber) {
        this.brand = brand;
        this.model = model;
        this.serialNumber = serialNumber;
    }

    /** @return the brand */
    public String getBrand() {
        return brand;
    }

    /** @return the model name */
    public String getModel() {
        return model;
    }

    /** @return the serial number */
    public String getSerialNumber() {
        return serialNumber;
    }

    /**
     * @return a string showing the brand, model and serial number
     */
    @Override
    public String toString() {
        return brand + " " + model + " (serial " + serialNumber + ")";
    }
}
