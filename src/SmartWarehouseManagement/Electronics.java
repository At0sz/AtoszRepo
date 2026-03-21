package SmartWarehouseManagement;

import java.util.Objects;

public class Electronics extends Product{
    private int warrantyMonths;

    public Electronics(String name, int storageTemp, double weight, int warrantyMonths) {
        super(name, storageTemp, weight);
        this.warrantyMonths = warrantyMonths;
    }

    @Override
    public boolean isFragile() {
        return true;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Electronics that = (Electronics) o;
        return warrantyMonths == that.warrantyMonths;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), warrantyMonths);
    }
}
