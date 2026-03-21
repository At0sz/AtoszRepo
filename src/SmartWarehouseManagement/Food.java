package SmartWarehouseManagement;

import java.time.LocalDate;
import java.util.Objects;

public class Food extends Product {
    private LocalDate expiryDate;

    public Food(String name, int storageTemp, double weight, LocalDate expiryDate) {
        super(name, storageTemp, weight);
        this.expiryDate = expiryDate;
    }


    @Override
    public boolean isFragile() {
        if (this.getName().equals("Egg")) {
            return true;
        }
        return false;
    }

    public LocalDate getExpiryDate() {
        return expiryDate;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Food food = (Food) o;
        return Objects.equals(expiryDate, food.expiryDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), expiryDate);
    }
}
