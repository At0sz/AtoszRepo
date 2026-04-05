package FantasyRPGArenaEngine;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

abstract class Hero implements UltimateCaster {
    protected String name;
    protected int hp;
    protected Role role;
    protected boolean isAlive;
    protected LocalDateTime creationDate;

    public Hero(String name, int hp, Role role) {
        this.name = name;
        this.hp = hp;
        this.role = role;
        this.isAlive = true;
        this.creationDate = LocalDateTime.now(); // Mentjük a jelenlegi időt

    }

    public void takeDamage(int amount){
        if(amount < 0){
            throw new IllegalArgumentException("Amount must be a positive integer");
        }

        this.hp -= amount;
        if(this.hp <= 0){
            this.isAlive = false;
        }
    }

    public String getFormattedCreationDate() {
        // Legyen a formátum: "yyyy-MM-dd HH:mm:ss"

        return creationDate.format(DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss"));
    }

    // Absztrakt metódus: minden alosztálynak kötelező megírnia!
    public abstract void basicAttack(Hero target);

    public String getName() { return name; }
    public int getHp() { return hp; }
    public Role getRole() { return role; }
    public boolean isAlive() { return isAlive; }

    @Override
    public String toString() {
        return String.format("[%s] %s - HP: %d (%s)", role, name, hp, isAlive ? "Alive" : "Dead");
    }
}
