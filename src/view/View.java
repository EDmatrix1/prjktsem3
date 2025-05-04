package view;

import controller.Controller;
import integration.ExternalInventorySystem;
import integration.Printer;

public class View {
    public View(Controller controller) {
        controller.startNewSale();

        controller.registerItem("abc123");
        controller.registerItem("abc123"); // Alt flow 3-4b
        controller.registerItem("def456");

        controller.endSale();
        controller.enterPayment(100);
    }
}
