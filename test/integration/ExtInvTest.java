package integration;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ExternalInventorySystemTest {
    private ExternalInventorySystem inventory;

    @BeforeEach
    void setUp() {
        inventory = new ExternalInventorySystem();
    }

    @Test
    void testFetchValidItem() {
        ItemDTO item = inventory.fetchItem("abc123");
        assertNotNull(item);
        assertEquals("BigWheel Oatmeal", item.getName());
    }

    @Test
    void testFetchInvalidItem() {
        ItemDTO item = inventory.fetchItem("nonexistent");
        assertNull(item);
    }
}
