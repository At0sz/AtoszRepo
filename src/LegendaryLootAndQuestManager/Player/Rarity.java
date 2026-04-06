package LegendaryLootAndQuestManager.Player;

public enum Rarity {
    COMMON(1),
    RARE(2),
    EPIC(3),
    LEGENDARY(5);

    Rarity(int multiplier) {
        this.multiplier = multiplier;
    }

    private final int multiplier;

    public int getMultiplier() {
        return multiplier;
    }
}
