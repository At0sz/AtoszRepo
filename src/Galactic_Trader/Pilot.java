package Galactic_Trader;

public class Pilot {
    private String name;
    private int experience;
    private int credits;

    public Pilot(String name, int experience) {
        this.name = name;
        this.experience = experience;
        this.credits = 500;
    }

    public int getCredits() {
        return credits;
    }

    public int getExperience() {
        return experience;
    }

    public String getName() {
        return name;
    }

    public void setCredits(int credits) {
        this.credits = credits;
    }

    public void setExperience(int experience) {
        this.experience = experience;
    }

    public void setName(String name) {
        this.name = name;
    }
}
