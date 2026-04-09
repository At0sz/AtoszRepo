package ESportsTournamentManagerProEdition.person;

import ESportsTournamentManagerProEdition.core.Competitor;
import ESportsTournamentManagerProEdition.core.Rank;
import ESportsTournamentManagerProEdition.exceptions.InvalidGamerTagException;

import java.util.Objects;

public abstract class Player implements Competitor {
    private String realName;
    private String gamerTag;
    private Rank rank;
    private int baseSkill;
    private static final String TAG_PATTERN = "^[a-zA-Z][a-zA-Z0-9]{3,11}$";


    public Player(String realName, String gamerTag, Rank rank, int baseSkill) {
        if (gamerTag == null || !gamerTag.matches(TAG_PATTERN)) {
            throw new InvalidGamerTagException("Invalid gamerTag: " + gamerTag);
        }
        this.realName = realName;
        this.gamerTag = gamerTag;
        this.rank = rank;
        this.baseSkill = baseSkill;
    }

    public abstract void playMatch();

    public String getRealName() {
        return realName;
    }

    public String getGamerTag() {
        return gamerTag;
    }

    public Rank getRank() {
        return rank;
    }

    public int getBaseSkill() {
        return baseSkill;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Player player = (Player) o;
        return Objects.equals(gamerTag, player.gamerTag);
    }

    @Override
    public int hashCode() {
        return Objects.hash(gamerTag);
    }
}
