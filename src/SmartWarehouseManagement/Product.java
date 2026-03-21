package SmartWarehouseManagement;

import java.util.Objects;

public abstract class Product {
    private String name;
    private double weight;
    private int storageTemp;

    public abstract boolean isFragile();

    public Product(String name, int storageTemp, double weight) {
        this.name = name;
        this.storageTemp = storageTemp;
        this.weight = weight;
    }

    public double getWeight() { return weight; }
    public String getName() { return name; }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return Double.compare(weight, product.weight) == 0 && storageTemp == product.storageTemp && Objects.equals(name, product.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, weight, storageTemp);
    }
}
