package settlersOOPCheckpoint.unit;

public abstract class Unit {

    private int health;
    private final int attackDamage;

    protected Unit(int health, int attackDamage) {
        if (health <= 0) {
            throw new IllegalArgumentException("Health must be positive!");
        }
        if (attackDamage < 0) {
            throw new IllegalArgumentException("Damage cannot be negative!");
        }
        this.health = health;
        this.attackDamage = attackDamage;
    }

    public void setHealth(int health) {
        this.health = Math.max(0, health);
    }

    public int getHealth() {
        return health;
    }

    public int getAttackDamage() {
        return attackDamage;
    }
}
