package GenericStorageSystem;

public abstract class Item {
    String name;

    public Item(String name) {
        this.name = name;
    }

    public abstract void use();
    public abstract int getValue();
}




