package model;

import integration.ItemDTO;

public class SoldItem {
    private final ItemDTO item;
    private int quantity;

    public SoldItem(ItemDTO item, int quantity) {
        this.item = item;
        this.quantity = quantity;
    }

    public void increaseQuantity() {
        quantity++;
    }

    public String getInfo() {
        return String.format("Item: %s, Cost: %.2f SEK, VAT: %.0f%%, Qty: %d",
                item.getName(), item.getPrice(), item.getVAT() * 100, quantity);
    }

    public double getTotalPriceWithVAT() {
        return quantity * item.getPrice() * (1 + item.getVAT());
    }

    public double getVATAmount() {
        return quantity * item.getPrice() * item.getVAT();
    }

    public String toReceiptLine() {
        return item.getName() + " " + quantity + " x " + item.getPrice() + " = " + String.format("%.2f", getTotalPriceWithVAT()) + " SEK";
    }
}
