package LegendaryLootAndQuestManager.Player;

public class Weapon implements Item{
    private final String name;
    private final Rarity rarity;
    private final int baseDamage;

    public Weapon(String name, int baseDamage, Rarity rarity) {
        this.name = name;
        this.baseDamage = baseDamage;
        this.rarity = rarity;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public Rarity getRarity() {
        return this.rarity;
    }

    @Override
    public int getValue() {
        return baseDamage * this.rarity.getMultiplier();
    }
}
