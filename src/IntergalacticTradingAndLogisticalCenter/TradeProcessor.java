package IntergalacticTradingAndLogisticalCenter;

import IntergalacticTradingAndLogisticalCenter.Cargos.Cargo;
import IntergalacticTradingAndLogisticalCenter.Cargos.Tradable;
import IntergalacticTradingAndLogisticalCenter.Exceptions.CargoFullException;
import IntergalacticTradingAndLogisticalCenter.Exceptions.CargoNotFoundException;

import java.util.Optional;

public class TradeProcessor {

    public void processTransfer(Freighter source, Freighter target, String cargoId) {
        // 1. Megkeressük az árut az ID alapján
        Optional<Tradable> itemOpt = source.getInventory().stream()
                .filter(p -> p instanceof Cargo && ((Cargo) p).getId().equals(cargoId))
                .findFirst();

        // 2. Ha nincs meg, dobjuk a saját kivételünket
        Tradable item = itemOpt.orElseThrow(() -> new CargoNotFoundException("Nincs ilyen áru: " + cargoId));

        // 3. Próbáljuk meg átrakni
        try {
            source.getInventory().remove(item); // Kivesszük az eredetiből
            target.loadCargo(item);             // Megpróbáljuk betölteni a célba
            System.out.println("Sikeres transzfer!");
        } catch (CargoFullException e) {
            // ROLLBACK: Ha hiba van (megtelt a cél), visszatesszük az eredetibe!
            source.getInventory().add(item);
            System.out.println("Hiba: A célhajó megtelt, az áru maradt az eredeti helyén.");
        }
    }
}
