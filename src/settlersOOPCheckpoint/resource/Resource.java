package settlersOOPCheckpoint.resource;

public class Resource {

    private final ResourceType type;
    private final int quantity;

    public Resource(ResourceType type, int quantity) {
        this.type = type;
        this.quantity = quantity;
    }

    public ResourceType getType() {
        return type;
    }

    public int getQuantity() {
        return quantity;
    }
}
