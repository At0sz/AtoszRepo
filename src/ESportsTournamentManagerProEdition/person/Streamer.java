package ESportsTournamentManagerProEdition.person;

import ESportsTournamentManagerProEdition.core.Rank;

public class Streamer extends Player{
    private int followers;

    public Streamer(String realName, String gamerTag, Rank rank, int baseSkill, int followers) {
        super(realName, gamerTag, rank, baseSkill);
        this.followers = followers;
    }

    public int getFollowers() {
        return followers;
    }

    @Override
    public void playMatch() {
        System.out.println("Hi there chat! I'm ["+getGamerTag()+"] ,let's get to it!");
    }

    @Override
    public double calculatePower() {
        return getBaseSkill() * getRank().getMultiplier();
    }
}
