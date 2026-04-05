package FantasyRPGArenaEngine;

public class Main {
    public static void main(String[] args) {

        ArenaEngine arena = ArenaEngine.getInstance();

        Paladin paladinOne = new Paladin("Rasmussen");
        Assassin assassinOne = new Assassin("Slicer");
        Assassin assassinTwo = new Assassin("Ghost");

        arena.addHero(paladinOne);
        arena.addHero(assassinOne);
        arena.addHero(assassinTwo);

        paladinOne.basicAttack(assassinOne);
        assassinOne.castUltimate(paladinOne);

        System.out.println("All the heroes: ");
        arena.getAliveHeroes().forEach(System.out::println);
        System.out.println("\nHeroes by role: ");
        arena.groupHeroesByRole().forEach((role, heroes) -> {
            System.out.println(role + " heroes: " + heroes);
        });
        System.out.println("\nHero with most health: ");
        System.out.println(arena.getHealthiestAliveHero());

        paladinOne.takeDamage(170);
        assassinOne.castUltimate(paladinOne);
        System.out.println("\nPaladin hp: " + paladinOne.getHp());
        System.out.println("\nPaladin get killed");

        System.out.println("\nAll the heroes: ");
        arena.getAliveHeroes().forEach(System.out::println);
    }
}
