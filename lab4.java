import java.util.*;

abstract class Item {
    protected String name;
    protected String description;

    public Item(String name, String description) {
        this.name = name.isEmpty() ? "Unknown" : name;
        this.description = description.isEmpty() ? "No description available" : description;
    }

    public abstract void displayDetails();

    public final void displayName() {
        System.out.println("Name: " + name);
    }
}

class Product extends Item {
    private int productId;
    private double price;
    private int stock;
    private String category;
    private String sku;
    private double weight;
    private String dimensions;
    private String manufacturer;

    public Product(int productId, String name, String description, double price, int stock, String category, String sku, double weight, String dimensions, String manufacturer) {
        super(name, description);
        this.productId = (productId <= 0) ? 0 : productId;
        this.price = (price < 0) ? 0.0 : price;
        this.stock = (stock < 0) ? 0 : stock;
        this.category = category.isEmpty() ? "Unknown" : category;
        this.sku = sku.isEmpty() ? "N/A" : sku;
        this.weight = (weight < 0) ? 0.0 : weight;
        this.dimensions = dimensions.isEmpty() ? "Not specified" : dimensions;
        this.manufacturer = manufacturer.isEmpty() ? "Unknown" : manufacturer;
    }

    @Override
    public void displayDetails() {
        displayName();
        System.out.println("Product ID: " + productId);
        System.out.println("Description: " + description);
        System.out.println("Price: $" + price);
        System.out.println("Stock: " + stock + " units");
        System.out.println("Category: " + category);
        System.out.println("SKU: " + sku);
        System.out.println("Weight: " + weight + " kg");
        System.out.println("Dimensions: " + dimensions);
        System.out.println("Manufacturer: " + manufacturer);
        System.out.println("-----------------------------");
    }
}

public class InventorySystem {
    public static void main(String[] args) {
        Product[] products = new Product[3];
        products[0] = new Product(1, "Laptop", "High performance laptop", 999.99, 50, "Electronics", "LPT123", 1.5, "35x25x2 cm", "TechCorp");
        products[1] = new Product(2, "Chair", "Ergonomic office chair", 149.99, 100, "Furniture", "CHR456", 10.0, "60x60x120 cm", "FurniCo");
        products[2] = new Product(3, "Smartphone", "Latest model smartphone", 799.99, 200, "Electronics", "SMP789", 0.3, "15x7x0.8 cm", "MobileMakers");

        System.out.println("Product List:");
        for (Product product : products) {
            product.displayDetails();
        }
    }
}
