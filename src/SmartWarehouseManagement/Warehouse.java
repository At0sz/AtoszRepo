package SmartWarehouseManagement;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Warehouse {
    private List<Product> warehouseList = new ArrayList<>();

    public void addProduct(Product product) {
        if (warehouseList.contains(product)) {
            throw new AlreadyExistException("Már bent van!");
        }
        if (product.getWeight() > 500) {
            throw new TooHeavyException("Túl nehéz!");
        }
        warehouseList.add(product);
    }

    public int calculateTotalWeight() {
        return warehouseList.stream()
                .mapToInt(p -> (int) p.getWeight())
                .sum();
    }

    public int getFragileProductsCount() {
        return (int) warehouseList.stream()
                .filter(Product::isFragile)
                .count();
    }

    public Food soonToExpire() {
        return warehouseList.stream()
                .filter(p -> p instanceof Food) // Csak a kaja jöhet
                .map(p -> (Food) p)             // Alakítsuk át Food típusra (Cast)
                .min(Comparator.comparing(Food::getExpiryDate)) // A legkisebb dátum
                .orElse(null);                  // Ha nincs kaja, null
    }

}
