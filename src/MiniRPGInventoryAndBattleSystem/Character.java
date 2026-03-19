package MiniRPGInventoryAndBattleSystem;

abstract class Character {
    private String name;
    private int health;
    private int attackPower;
    private Inventory inventory;

    public Character(String name, int health, int attackPower, Inventory inventory) {
        if (inventory == null) {
            throw new IllegalArgumentException("Inventory cannot be null");
        }
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Name cannot be null or blank");
        }
        if (health <= 0) {
            throw new IllegalArgumentException("Health must be positive");
        }
        if (attackPower <= 0) {
            throw new IllegalArgumentException("Attack power must be positive");
        }
        this.attackPower = attackPower;
        this.health = health;
        this.inventory = inventory;
        this.name = name;
    }

    public abstract void attack(Character target);

    public boolean isAlive(){
        return health > 0;
    }

    public void takeDamage(int damage){
        if (damage < 0) {
            throw new IllegalArgumentException("Damage cannot be negative");
        }

        health -= damage;
        if (health < 0) {
            health = 0;
        }
    }

    public int getAttackPower() {
        return attackPower;
    }


    public int getHealth() {
        return health;
    }


    public Inventory getInventory() {
        return inventory;
    }



    public String getName() {
        return name;
    }


    @Override
    public String toString() {
        return "Character{" +
                "attackPower=" + attackPower +
                ", name='" + name + '\'' +
                ", health=" + health +
                ", inventory=" + inventory +
                '}';
    }
}
