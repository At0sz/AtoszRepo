package Gyakorlas2;

public class Gyakorlas2_19 {

    public static void main(String[] args) {
        String[] rawData = {"21.5", "32.0", "hot", "15.8", "-5.0", "38.2"};

        System.out.println("--- Starting Sensor Audit ---");

        for (String data : rawData) {
            processSensorData(data);
        }
    }

    public static void processSensorData(String input) {
        try {
            double temp = Double.parseDouble(input);

            if (temp >= 10 && temp <= 35) {
                System.out.println("Temp OK : [" + input + "]");
            } else {
                System.out.println("ALERT: Out of range! ([" + input + "])");
            }

            // 1. Alakítsd a Stringet double típusra: Double.parseDouble(input)
            // 2. Ellenőrizd: 10 és 35 fok között van-e?
            // 3. Ha igen, írd ki: "Temp OK: [hőfok]"
            // 4. Ha nem, írd ki: "ALERT: Out of range! ([hőfok])"

        } catch (NumberFormatException e) {
            // Ez fut le, ha az "input" nem szám (pl. "hot")
            System.out.println("ERROR: Invalid sensor reading: " + input);
        }
    }

}
