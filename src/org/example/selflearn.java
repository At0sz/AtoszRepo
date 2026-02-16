package org.example;

import java.util.Random;

public class selflearn {
    public static void main(String[] args) {
        Random rand = new Random();

        for (int i = 0; i < 10; i++) {
            int randomInt = rand.nextInt(100);
            System.out.println(randomInt);
        }
    }
}
