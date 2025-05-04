package view;

import controller.Controller;
import integration.ExternalInventorySystem;
import integration.Printer;

public class View {
    public View(Controller controller) {
        controller.startNewSale();

        controller.registerItem("abc123");
        controller.registerItem("abc123"); // Alternative flow 3-4b: duplicate item
        controller.registerItem("def456");

        controller.endSale();
        controller.enterPayment(100);
    }

    public static void main(String[] args) {
        ExternalInventorySystem inventory = new ExternalInventorySystem();
        Printer printer = new Printer();
        Controller controller = new Controller(inventory, printer);

        new View(controller); // triggers the sale simulation
    }
}
