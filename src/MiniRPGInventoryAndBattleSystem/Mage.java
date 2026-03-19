package MiniRPGInventoryAndBattleSystem;

public class Mage extends Character {

    private int mana;
    private int spellCost;

    public Mage(String name, int health, int attackPower, Inventory inventory, int mana, int spellCost) {
        super(name, health, attackPower, inventory);

        if (mana < 0) {
            throw new IllegalArgumentException("Mana cannot be negative");
        }
        if (spellCost <= 0) {
            throw new IllegalArgumentException("Spell cost must be positive");
        }

        this.mana = mana;
        this.spellCost = spellCost;
    }

    @Override
    public void attack(Character target) {
        if (target == null) {
            throw new IllegalArgumentException("Target cannot be null");
        }
        if (!this.isAlive()) {
            throw new IllegalStateException("Dead characters cannot attack");
        }

        if (mana >= spellCost) {
            mana -= spellCost;
            target.takeDamage(getAttackPower() * 2); // spell damage
        } else {
            target.takeDamage(getAttackPower()); // basic attack
        }
    }

    public int getMana() {
        return mana;
    }
}