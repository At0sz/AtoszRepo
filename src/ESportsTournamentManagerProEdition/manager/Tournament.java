package ESportsTournamentManagerProEdition.manager;

import ESportsTournamentManagerProEdition.core.GameType;
import ESportsTournamentManagerProEdition.core.Rank;
import ESportsTournamentManagerProEdition.exceptions.InvalidGamerTagException;
import ESportsTournamentManagerProEdition.exceptions.TeamFullException;
import ESportsTournamentManagerProEdition.person.Player;
import ESportsTournamentManagerProEdition.person.ProGamer;
import ESportsTournamentManagerProEdition.person.Streamer;
import ESportsTournamentManagerProEdition.team.Team;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Tournament {
    private  Map<String, Team> teams = new HashMap<>();


    public List<Player> getTopPlayers(int limit) {
        return teams.values().stream()
                .flatMap(team->team.getRoster().stream())
                .distinct()
                .sorted((p1,p2) -> Double.compare(p2.calculatePower(),  p1.calculatePower()))
                .limit(limit)
                .toList();
    }

    public Map<Rank,List<Player>> groupPlayersByRank(){
        return teams.values().stream()
                .flatMap(team->team.getRoster().stream())
                .collect(Collectors.groupingBy(Player::getRank));
    }

    public void processInput(List<String> commands) {
        for (String command : commands) {
            try {
                commandMaker(command);
            } catch (InvalidGamerTagException | TeamFullException e){
                System.err.println("Error occured : " + e.getMessage());
            } catch (Exception e) {
                System.err.println("Format problem : " + command);
            }
        }
    }

    private void commandMaker(String command) throws InvalidGamerTagException, TeamFullException {
        String[] parts = command.split(":");
        if (parts.length < 2) return;

        String action = parts[0];
        String[] params = parts[1].split(",");

        switch (action) {
            case "ADD_TEAM":
                teams.put(params[0], new Team(params[0], GameType.valueOf(params[1])));
                break;

            case "ADD_PRO":
                addPlayerToTeam(params, true);
                break;

            case "ADD_STREAMER":
                addPlayerToTeam(params, false);
                break;
        }
    }


    private void addPlayerToTeam(String[] p, boolean isPro) throws InvalidGamerTagException, TeamFullException {
        Team team = teams.get(p[0]);
        if (team == null) return;

        Player newPlayer;
        if (isPro) {
            // ProGamer: [0]:team, [1]:tag, [2]:real, [3]:rank, [4]:skill, [5]:sponsor
            newPlayer = new ProGamer(p[2], p[1], Rank.valueOf(p[3]), Integer.parseInt(p[4]), p[5]);
        } else {
            // Streamer: [0]:team, [1]:tag, [2]:real, [3]:rank, [4]:skill, [5]:followers
            newPlayer = new Streamer(p[2], p[1], Rank.valueOf(p[3]), Integer.parseInt(p[4]), Integer.parseInt(p[5]));
        }

        team.addPlayer(newPlayer);
    }

}
