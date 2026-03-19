package MiniRPGInventoryAndBattleSystem;

abstract class Item {
    private String name;
    private boolean stackable;
    private int maxStackSize;

    public Item( String name, boolean stackable, int maxStackSize) {
        if(maxStackSize <= 0){
            throw new IllegalArgumentException("Max stack size must be greater than 0.");
        }
        if (name == null || name.isBlank()){
            throw new IllegalArgumentException("Name cannot be null.");
        }
        if(stackable){
            this.maxStackSize = maxStackSize;
        }else {
            this.maxStackSize = 1;
        }

        this.name = name;
        this.stackable = stackable;
    }

    public abstract void use(Character target);

    public int getMaxStackSize() {
        return maxStackSize;
    }

    public String getName() {
        return name;
    }

    public boolean isStackable() {
        return stackable;
    }
}
