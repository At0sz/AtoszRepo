package TheGenericDungeonCrawler.Entities;

public class Potion extends Item {
    int healAmount;

    public Potion(String name, int healAmount) {
        super(name);
        this.healAmount = healAmount;
    }

    @Override
    public void use() {
        System.out.println("Megittad: " + name + " (+" + healAmount + " HP)");
    }

    @Override
    public int getValue() {
        return healAmount;
    }

    @Override
    public String toString() {
        return "Potion " +
                "name: " + name +
                ", healAmount: " + healAmount + "\n";
    }
}
