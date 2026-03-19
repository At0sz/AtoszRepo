package MiniRPGInventoryAndBattleSystem;

public class ItemStack  {
    private Item item;
    private int quantity;

    public ItemStack(Item item, int quantity){
        if (item == null) {
            throw new IllegalArgumentException("Item cannot be null.");
        }
        if (quantity <= 0) {
            throw new IllegalArgumentException("Quantity cannot be negative.");
        }
        if (quantity > item.getMaxStackSize()) {
            throw new IllegalArgumentException("Initial quantity exceeds max stack size");
        }
        this.item = item;
        this.quantity = quantity;
    }

    public void add(int amount) {
        if (amount < 1) {
            throw new IllegalArgumentException("Amount cannot be negative or zero");
        }
        if (this.quantity + amount > item.getMaxStackSize()) {
            throw new IllegalArgumentException("Amount is too large.");
        }
        this.quantity += amount;
    }

    public void remove(int amount) {
        if (amount < 1) {
            throw new IllegalArgumentException("Amount cannot be negative or zero");
        }
        if (amount > this.quantity) {
            throw new IllegalArgumentException("Amount cannot be greater than the quantity");
        }
        this.quantity -= amount;
    }

    public boolean isFull() {
        return item.getMaxStackSize() == this.quantity;
    }

    public boolean isEmpty() {
        return this.quantity == 0;
    }

    public void use(Character target) {
        if (target == null) {
            throw new IllegalArgumentException("Target cannot be null.");
        }
        if (isEmpty()) {
            throw new IllegalStateException("Cannot use empty stack");
        }
        item.use(target);
        remove(1);
    }


}
