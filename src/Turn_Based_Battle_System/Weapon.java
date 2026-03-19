package Turn_Based_Battle_System;

public class Weapon {
    private String name;
    private int bonusDamage;

    public Weapon(int bonusDamage, String name) {
        this.bonusDamage = bonusDamage;
        this.name = name;
    }

    public int getBonusDamage() {
        return bonusDamage;
    }

    public void setBonusDamage(int bonusDamage) {
        this.bonusDamage = bonusDamage;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }



}
