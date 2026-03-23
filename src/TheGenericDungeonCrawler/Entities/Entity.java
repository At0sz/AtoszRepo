package TheGenericDungeonCrawler.Entities;

import lombok.Setter;

public abstract class Entity implements Combatant {
    private String name;
    @Setter
    private int hp;
    @Setter
    private int mana;
    private int strength;

    public Entity(String name, int strength, int hp, int mana) {
        this.name = name;
        this.strength = strength;
        this.hp = hp;
        this.mana = mana;
    }

    public int getHp() {
        return hp;
    }

    public int getMana() {
        return mana;
    }

    public String getName() {
        return name;
    }

    public int getStrength() {
        return strength;
    }


}
