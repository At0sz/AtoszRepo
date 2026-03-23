package GenericStorageSystem;

public class Main {
    public static void main(String[] args) {

        Storage<Weapon> weaponStorage = new Storage<>();
        Storage<Potion> potionStorage = new Storage<>();

        weaponStorage.addItem(new Weapon("Király gyilkos",20));
        weaponStorage.addItem(new Weapon("Suhintó",10));
        weaponStorage.addItem(new Weapon("Barbárság",15));

        potionStorage.addItem(new Potion("Heal",30));
        potionStorage.addItem(new Potion("Medkit",70));
        potionStorage.addItem(new Potion("Bandage",5));

        System.out.println(weaponStorage);
        System.out.println("-------------");
        System.out.println(potionStorage);


        Weapon bestWeapon = weaponStorage.getBestItem();
        Potion bestPotion = potionStorage.getBestItem();

        System.out.println("\n--------------------------------------");
        System.out.println("A legdurvább fegyver: " + bestWeapon);
        System.out.println("A legerősebb főzet: " + bestPotion);

        printAnyStorage(weaponStorage);
        printAnyStorage(potionStorage);

    }


    public static void printAnyStorage(Storage<? extends Item> storage) {
        System.out.println("--- Külső raktár ellenőrzés ---");
        storage.showAll();
    }

}
