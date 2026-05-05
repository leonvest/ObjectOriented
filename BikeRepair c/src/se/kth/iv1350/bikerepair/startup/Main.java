package se.kth.iv1350.bikerepair.startup;

import se.kth.iv1350.bikerepair.controller.Controller;
import se.kth.iv1350.bikerepair.integration.Printer;
import se.kth.iv1350.bikerepair.integration.RegistryCreator;
import se.kth.iv1350.bikerepair.view.View;

/**
 * The application's entry point. Wires together the integration, controller,
 * and view layers, then runs the hard-coded basic flow.
 */
public class Main {

    /**
     * Starts the application.
     *
     * @param args command-line arguments (not used)
     */
    public static void main(String[] args) {
        RegistryCreator registryCreator = new RegistryCreator();
        Printer printer = new Printer();
        Controller controller = new Controller(registryCreator, printer);
        View view = new View(controller);
        view.runBasicFlow();
    }
}
