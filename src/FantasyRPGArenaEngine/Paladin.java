package FantasyRPGArenaEngine;

class Paladin extends Hero {
    public Paladin(String name) {
        super(name,200,Role.TANK);
    }

    @Override
    public void basicAttack(Hero target) {
        target.takeDamage(10);
    }

    @Override
    public void castUltimate(Hero target) {
        target.hp = target.hp + 50;
    }
}

