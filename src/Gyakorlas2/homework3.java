package Gyakorlas2;

import java.util.Scanner;

public class homework3 {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.println("Adjon meg egy egész számot: ");
        int number = sc.nextInt();
        sc.nextLine();

        if (number % 2 == 0) {
            System.out.println("Páros");
        } else {
            System.out.println("Páratlan");
        }


    }
}
