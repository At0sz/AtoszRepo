package Gyakorlas2;

import java.util.Scanner;

public class homework1 {
    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);

        System.out.println("Első szám: ");
        int numberOne = input.nextInt();
        input.nextLine();
        System.out.println("Második szám: ");
        int numberTwo = input.nextInt();
        input.nextLine();
        System.out.println("Válasszon a lehetőségek közül: + - * /");
        String choice = input.nextLine();

        if (choice.equals("+")) {
            System.out.println(numberOne + "+" + numberTwo + " = " + (numberOne + numberTwo));
        } else if (choice.equals("-")) {
            System.out.println(numberOne + "-" + numberTwo + " = " + (numberOne - numberTwo));
        } else if (choice.equals("/")) {
            double result = (double) numberOne / numberTwo;
            System.out.println(numberOne + "/" + numberTwo + " = " + (result));
        } else if (choice.equals("*")) {
            System.out.println(numberOne + "*" + numberTwo + " = " + (numberOne * numberTwo));
        } else {
            System.out.println("Hiba történt,próbálja újra.⛔");
        }


    }
}
