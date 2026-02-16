package Gyakorlas2;

import java.util.Scanner;

public class Homework2 {
    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);

        System.out.println("Adjon meg egy évet: ");
        int year = input.nextInt();

        if (year % 4 == 0 && year % 100 != 0 || year % 400 == 0) {
            System.out.println("Szökőév, szép találat 🎉");
        }
    }
}
