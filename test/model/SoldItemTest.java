package model;

import integration.ItemDTO;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SoldItemTest {
    @Test
    void testVATCalculation() {
        ItemDTO item = new ItemDTO("xyz", "TestProduct", 10.0, 0.25);
        SoldItem sold = new SoldItem(item, 0);
        sold.increaseQuantity();
        assertEquals(12.5, sold.getTotalPriceWithVAT(), 0.01);
        assertEquals(2.5, sold.getVATAmount(), 0.01);
    }
}
