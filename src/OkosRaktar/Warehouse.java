package OkosRaktar;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Warehouse {
    // Alapértelmezett adatok feltöltése (15 tétel)
    private List<Product> productList = new ArrayList<>(Arrays.asList(
            new Product("Cisco Router C1111", "Networking", 120000, 5),
            new Product("Ubiquiti Dream Machine", "Networking", 150000, 3),
            new Product("Cat6 Ethernet Cable 305m", "Cabling", 45000, 10),
            new Product("MikroTik hAP ac2", "Networking", 25000, 15),
            new Product("Patch Panel 24 Port", "Cabling", 18000, 8),
            new Product("Dell PowerEdge R740", "Server", 2500000, 2),
            new Product("HP ProLiant DL380", "Server", 2100000, 0), // Kifogyott!
            new Product("Logitech MX Master 3", "Peripherals", 35000, 12),
            new Product("Keychron K2 Keyboard", "Peripherals", 42000, 7),
            new Product("Samsung 980 Pro 1TB NVMe", "Storage", 38000, 20),
            new Product("Western Digital Red 4TB HDD", "Storage", 48000, 14),
            new Product("Rack Cabinet 12U", "Cabling", 65000, 4),
            new Product("TP-Link PoE Injector", "Networking", 8000, 0), // Kifogyott!
            new Product("APC UPS 1500VA", "Power", 185000, 6),
            new Product("Fiber Optic Patch Cord 2m", "Cabling", 4500, 50)
    ));

    public void addProduct(Product p) {
        productList.add(p);
    }

    public List<Product> getProductList() {
        return new ArrayList<>(productList);
    }

    public List<Product> getProductByCategory(String category) {
        return productList.stream()
                .filter(p -> p.getCategory().equalsIgnoreCase(category))
                .toList();
    }

    public List<Product> getOutOfStockProducts() {
        return productList.stream()
                .filter(p -> p.getStock() == 0)
                .toList();
    }

    public boolean updateStock(String productName, int amount) {
        for (Product p : productList) {
            if (p.getName().equalsIgnoreCase(productName)) {
                p.setStock(p.getStock() + amount);
                return true;
            }
        }
        return false;
    }

    public long getTotalWarehouseValue() {
        return productList.stream()
                .mapToLong(Product::getTotalValue)
                .sum();
    }
}