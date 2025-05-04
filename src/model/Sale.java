package model;

import java.time.LocalDateTime;
import java.util.*;

import integration.ExternalInventorySystem;
import integration.ItemDTO;

public class Sale {
    private Map<String, SoldItem> items = new LinkedHashMap<>();
    private ExternalInventorySystem inventory;

    public Sale(ExternalInventorySystem inventory) {
        this.inventory = inventory;
    }

    public String addItem(String itemId) {
        ItemDTO item = inventory.fetchItem(itemId);
        if (item == null) return "Invalid item ID: " + itemId;

        SoldItem soldItem = items.getOrDefault(itemId, new SoldItem(item, 0));
        soldItem.increaseQuantity();
        items.put(itemId, soldItem);
        return soldItem.getInfo() + "\n" + getRunningTotal();
    }

    public String displayTotal() {
        return "Total cost (incl VAT): " + String.format("%.2f", getTotalCost()) +
               " SEK\nTotal VAT: " + String.format("%.2f", getTotalVAT()) + " SEK";
    }

    public String completeSale(double amountPaid) {
        double total = getTotalCost();
        double change = amountPaid - total;
        return generateReceipt(amountPaid, change);
    }

    private double getTotalCost() {
        return items.values().stream().mapToDouble(SoldItem::getTotalPriceWithVAT).sum();
    }

    private double getTotalVAT() {
        return items.values().stream().mapToDouble(SoldItem::getVATAmount).sum();
    }

    private String getRunningTotal() {
        return "Running Total: " + String.format("%.2f", getTotalCost()) + " SEK";
    }

    private String generateReceipt(double paid, double change) {
        StringBuilder sb = new StringBuilder("\n--- Receipt ---\n");
        sb.append("Time: ").append(LocalDateTime.now()).append("\n\n");
        for (SoldItem item : items.values()) {
            sb.append(item.toReceiptLine()).append("\n");
        }
        sb.append("Total: ").append(String.format("%.2f", getTotalCost())).append(" SEK\n");
        sb.append("VAT: ").append(String.format("%.2f", getTotalVAT())).append("\n");
        sb.append("Paid: ").append(String.format("%.2f", paid)).append(" SEK\n");
        sb.append("Change: ").append(String.format("%.2f", change)).append(" SEK\n");
        sb.append("--- End Receipt ---\n");
        return sb.toString();
    }
}
