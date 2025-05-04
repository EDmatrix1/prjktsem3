package model;

import integration.ExternalInventorySystem;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SaleTest {
    private Sale sale;

    @BeforeEach
    void setUp() {
        sale = new Sale(new ExternalInventorySystem());
    }

    @Test
    void testAddItem() {
        sale.addItem("abc123");
        sale.addItem("abc123");
        assertTrue(sale.displayTotal().contains("74.70 SEK"));
    }

    @Test
    void testInvalidItem() {
        String response = sale.addItem("invalidID");
        assertTrue(response.contains("Invalid"));
    }
}
