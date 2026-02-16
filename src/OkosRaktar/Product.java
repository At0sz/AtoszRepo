package OkosRaktar;

public class Product {
    private String name;
    private String category;
    private int price;
    private int stock;

    public Product(String name, String category, int price, int stock) {
        this.name = name;
        this.category = category;
        this.price = price;
        this.stock = stock;
    }

    public int getTotalValue() {
        return price * stock;
    }

    // Getterek és Setterek
    public String getName() { return name; }
    public String getCategory() { return category; }
    public int getPrice() { return price; }
    public int getStock() { return stock; }
    public void setStock(int stock) { this.stock = stock; }

    @Override
    public String toString() {
        return String.format("📦 [%s] - Kategória: %s | Ár: %d Ft | Készlet: %d db (Összérték: %d Ft)",
                name, category, price, stock, getTotalValue());
    }
}