package MiniRPGInventoryAndBattleSystem;

public class Warrior extends Character {

    public Warrior(String name, int health, int attackPower, Inventory inventory) {
        super(name, health, attackPower, inventory);
    }

    @Override
    public void attack(Character target) {
        if (target == null) {
            throw new IllegalArgumentException("Target cannot be null");
        }
        if (!this.isAlive()) {
            throw new IllegalStateException("Dead characters cannot attack");
        }

        target.takeDamage(getAttackPower());
    }
}
