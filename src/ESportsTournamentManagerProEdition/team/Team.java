package ESportsTournamentManagerProEdition.team;

import ESportsTournamentManagerProEdition.core.Competitor;
import ESportsTournamentManagerProEdition.core.GameType;
import ESportsTournamentManagerProEdition.exceptions.AlreadyInTheTeamException;
import ESportsTournamentManagerProEdition.exceptions.TeamFullException;
import ESportsTournamentManagerProEdition.person.Player;

import java.util.ArrayList;
import java.util.List;

public class Team implements Competitor {
    private String teamName;
    private GameType type;
    private List<Player> roster = new ArrayList();

    public Team(String teamName, GameType type) {
        this.teamName = teamName;
        this.type = type;
    }

    public void addPlayer(Player p) {
        if (roster.size() < 5) {
            if(!roster.contains(p)){
                roster.add(p);
            }else{
                throw new AlreadyInTheTeamException("The player has already been added!");
            }
        }else{
            throw new TeamFullException("Maximum number of players reached");
        }
    }

    public String getTeamName() {
        return teamName;
    }

    public GameType getType() {
        return type;
    }

    public List<Player> getRoster() {
        return roster;
    }

    @Override
    public double calculatePower() {
        return roster.stream()
                .mapToDouble(Player::calculatePower)
                .sum();
    }
}
