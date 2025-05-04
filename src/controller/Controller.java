package controller;

import integration.ExternalInventorySystem;
import integration.Printer;
import model.Sale;

public class Controller {
    private Sale currentSale;
    private ExternalInventorySystem inventory;
    private Printer printer;

    public Controller(ExternalInventorySystem inventory, Printer printer) {
        this.inventory = inventory;
        this.printer = printer;
    }

    public void startNewSale() {
        currentSale = new Sale(inventory);
    }

    public void registerItem(String itemId) {
        System.out.println(currentSale.addItem(itemId));
    }

    public void endSale() {
        System.out.println(currentSale.displayTotal());
    }

    public void enterPayment(double amountPaid) {
        String receipt = currentSale.completeSale(amountPaid);
        printer.print(receipt);
    }
}
