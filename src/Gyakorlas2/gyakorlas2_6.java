package Gyakorlas2;

import java.util.*;

public class gyakorlas2_6 {
    public static void main(String[] args) {

        List<Account> data = Arrays.asList(
                new Account("Kovács Péter", 150000, "Lakossági"),
                new Account("Nagy Tech Kft", 2500000, "Céges"),
                new Account("Szabó Anna", 45000, "Lakossági"),
                new Account("WebShop Bt", 890000, "Céges")
        );

        System.out.println();
        data.stream()
                .filter(p -> p.type.equals("Céges"))
                .forEach(p -> System.out.println("Kedves " + p.owner + " ,mivel fontos számunkra mint céges ügyfelünk ezért 5% kamatot kapott" +
                        ", az új egyenlege: " + (p.balance * 1.05)));
        System.out.println();
        data.stream()
                .filter(p -> p.type.equals("Lakossági") && (p.balance - 2000) > 0)
                .forEach(p -> System.out.println("Sajnálattal közöljük " + p.owner + " 2000 Ft kezelési költséget számolunk fel" +
                        ", ezáltal az új egyenlege: " + (p.balance - 2000)));
        System.out.println();
        data.stream()
                .max(Comparator.comparingDouble(a -> a.balance))
                .ifPresent(a -> System.out.println("🏆 A legnagyobb egyenleggel rendelkező számla tulajdonos: " + a.owner));

        var topAccounts = data.stream()
                .filter(p -> p.balance > 100000)
                .toList();

        System.out.println("\n🥇 Top ügyfeleink: ");
        topAccounts.forEach(p -> System.out.println(p.owner + " egyenlege: " + p.balance));


    }
}

class Account {
    String owner;
    double balance;
    String type;

    public Account(String owner, double balance, String type) {
        this.owner = owner;
        this.balance = balance;
        this.type = type;
    }

}
