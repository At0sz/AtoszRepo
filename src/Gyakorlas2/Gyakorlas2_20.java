package Gyakorlas2;

public class Gyakorlas2_20 {

    public static void main(String[] args) {
        String[] passwords = {"123", "password!", "admin123", "StrongOne?", "short!"};

        for (String pw : passwords) {
            if (isStrongPassword(pw)) {
                System.out.println("SAFE: " + pw);
            } else {
                System.out.println("WEAK: " + pw);
            }
        }
    }

    public static boolean isStrongPassword(String password) {
        // TODO:
        // 1. Ellenőrizd a hosszát: password.length()
        // 2. Ellenőrizd a speciális karaktert: password.contains("!") || password.contains("?")
        // 3. Csak akkor térj vissza true-val, ha mindkettő teljesül!

        return password.length() >= 8 && (password.contains("!") || password.contains("?"));
    }
}

