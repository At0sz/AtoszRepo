package ESportsTournamentManagerProEdition.person;

import ESportsTournamentManagerProEdition.core.Rank;

public class ProGamer extends Player {
    private String sponsor;

    public ProGamer(String realName, String gamerTag, Rank rank, int baseSkill, String sponsor) {
        super(realName, gamerTag, rank, baseSkill);
        this.sponsor = sponsor;
    }

    public String getSponsor() {
        return sponsor;
    }

    @Override
    public void playMatch() {
        System.out.println("The [" + this.sponsor + "] sponsored " + getGamerTag() + " joined the server.");
    }

    @Override
    public double calculatePower() {
        return getBaseSkill() * getRank().getMultiplier() * 1.2;
    }
}
