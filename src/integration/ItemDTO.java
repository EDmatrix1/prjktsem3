package integration;

public class ItemDTO {
    private final String id, name;
    private final double price, vat;

    public ItemDTO(String id, String name, double price, double vat) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.vat = vat;
    }

    public String getId() { return id; }
    public String getName() { return name; }
    public double getPrice() { return price; }
    public double getVAT() { return vat; }
}
