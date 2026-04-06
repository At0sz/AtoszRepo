package LegendaryLootAndQuestManager.Quests;

public class LevelTooLowException extends RuntimeException {
    public LevelTooLowException(String message) {
        super(message);
    }
}
