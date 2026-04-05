package EsportMatchMaker;

public class GameLobby {
    private int matchId;
    private int playerCount; // Max 10 játékos
    private boolean isStarted;

    public GameLobby(int matchId) {
        this.matchId = matchId;
        this.playerCount = 1; // Aki létrehozza, az már benne van
        this.isStarted = false;
    }

    public int getMatchId() { return matchId; }
    public int getPlayerCount() { return playerCount; }
    public boolean isStarted() { return isStarted; }

    public void addPlayer() { this.playerCount++; }
    public void startMatch() { this.isStarted = true; }

    @Override
    public String toString() {
        return "Lobby[ID=" + matchId + ", Players=" + playerCount + "/10, Started=" + isStarted + "]";
    }
}
