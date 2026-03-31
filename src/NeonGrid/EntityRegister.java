package NeonGrid;

public class EntityRegister {
    private EntityRegister() {
        /* This utility class should not be instantiated */
    }

    public static Entity registerEntity(String command){
        Entity entity = null;
        String[] commands;
        boolean couple = command.contains(",");

        if(couple){
            command = command.replace("(",", ").replace(")","");
            commands = command.split(", ");
            entity = new DevSyndicate(Double.parseDouble(commands[1]),Double.parseDouble(commands[2]));
        }else{
            command = command.replace("("," ").replace(")","");
            commands = command.split(" ");

            if (command.startsWith("solo")){
                entity = new SoloRunner(Double.parseDouble(commands[1]));
            }else if (command.startsWith("rogue")){
               entity = new RogueAI(Double.parseDouble(commands[1]));
            }else{
                System.out.println("Invalid Command");
            }
        }
        return entity;
    }

}
