package pl.kszafran.sda.algo.presentation;

public class IterativeGcd {

    public static void main(String... args) {
        gcd(255, 102);
    }

    public static int gcd(int a, int b) {
        int temp;
        while (b != 0) {
            temp = a % b;
            a = b;
            b = temp;
        }
        return a;
    }
}
