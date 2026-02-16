package Gemini;

public class Szamla {

    // TULAJDONSÁGOK (Fields)
    // Megjegyzés: a double a tizedes törtekhez kell, mert forintot is lehet törni (pl. kamat)
    private double egyenleg; // PRIVATE: Csak a Szamla osztály látja! Védjük az adatot!
    private String tulajdonosNeve;


    public String getTulajdonosNeve() {
        return tulajdonosNeve;
    }

    // Helyes Java kód:
    public void setTulajdonosNeve(String nev) {
        // Helyes: A Stringeket .equals()-szal hasonlítjuk, a null-t külön vizsgáljuk
        if (nev == null || nev.isEmpty()) { // Hozzáadtam a .isEmpty()-t a "" helyett
            System.out.println("---HIBA: A név nem lehet üres! ---");
        } else {
            // Értékadás: Helyesen, egyenlőségjellel
            this.tulajdonosNeve = nev; // A 'this.'-t használd, ha a változók neve azonos
            System.out.println("Név beállítva: " + this.tulajdonosNeve);
        }
    }

    // GETTER: Az egyenleg lekérdezésére
    public double getEgyenleg() {
        return egyenleg;
    }

    // METÓDUS: Pénz betétele (A VÁLTOZTATÁS)
    public void penzBetesz(double osszeg) {
        // IF feltétel: A tranzakció validálása
        if (osszeg > 0) {
            egyenleg = egyenleg + osszeg;
            System.out.println("Sikeres befizetés! Új egyenleg: " + egyenleg);
        } else {
            System.out.println("Hiba: Csak pozitív összeget lehet betenni.");
        }
    }

    // METÓDUS: Pénz kivétele (A VÁLTOZTATÁS)
    public void penzKivesz(double osszeg) {
        // IF feltétel: A fedezet ellenőrzése
        if (osszeg > 0 && egyenleg >= osszeg) {
            egyenleg = egyenleg - osszeg;
            System.out.println("Sikeres kivétel! Új egyenleg: " + egyenleg);
        } else if (osszeg > 0) {
            System.out.println("Hiba: Nincs elegendő fedezet a számlán.");
        } else {
            System.out.println("Hiba: Csak pozitív összeget lehet kivenni.");
        }
    }

    // A többi tulajdonság getter/setterje
    // Készítsd el a 'tulajdonosNeve' getter/setterét is!

}