package OOP;

public class DontGiveMeFive {
    public static int dontGiveMeFive(int start, int end) {
        int sum = 0;

        System.out.print(start + "," + end);
        if (start > end) {
            System.out.println("Start number cannot be higher then end number");
        }

        while (start <= end) {
            if (start == 5) {

            } else {
                System.out.println(start);
                sum++;
                start = start + 1;
            }
        }
        return sum;
    }

}

