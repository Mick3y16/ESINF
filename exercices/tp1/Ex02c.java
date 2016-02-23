package ex02c;

public class Ex02c {

    public static void main(String[] args) {
        System.out.println(isPrime(7, 2) ? "It's a prime!" : "It's not a prime!");
        System.out.println(isPrime(6, 2) ? "It's a prime!" : "It's not a prime!");
    }

    public static boolean isPrime(int number, int divider) {
        // Should we handle number one, as it is not a prime number??
        if (divider > number / 2) {
            return true;
        }

        return (number % divider != 0 && isPrime(number, divider + 1));
    }

}
