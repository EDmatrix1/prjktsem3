package integration;

import java.util.HashMap;
import java.util.Map;

public class ExternalInventorySystem {
    private final Map<String, ItemDTO> itemRegistry = new HashMap<>();

    public ExternalInventorySystem() {
        itemRegistry.put("abc123", new ItemDTO("abc123", "BigWheel Oatmeal", 29.90, 0.06));
        itemRegistry.put("def456", new ItemDTO("def456", "YouGoGo Blueberry", 14.90, 0.06));
    }

    public ItemDTO fetchItem(String id) {
        return itemRegistry.get(id);
    }
}
