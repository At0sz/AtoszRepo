package TheGenericDungeonCrawler.Entities;

public class Weapon extends Item {
    int damage;

    public Weapon(String name, int damage) {
        super(name);
        this.damage = damage;
    }

    @Override
    public void use() {
        System.out.println("Suhintás: " + name + " (" + damage + " sebzés)");
    }

    @Override
    public int getValue() {
        return damage;
    }

    @Override
    public String toString() {
        return "Weapon" +
                " name: " + name +
                ", damage: " + damage + "\n";
    }
}
