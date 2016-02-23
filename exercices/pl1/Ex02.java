package ex02;

public class Ex02 {

    public static void main(String[] args) {
        System.out.println(isPalindromic(1234321, 1234321, 0) ? "True" : "False");
        System.out.println(isPalindromic(1234567, 1234567, 0) ? "True" : "False");
    }

    public static boolean isPalindromic(int number, int numberCopy, int numberReverse) {
        if (number == 0) {
            return numberCopy == numberReverse;
        }

        numberReverse = numberReverse * 10 + (number % 10);
        return isPalindromic(number / 10, numberCopy, numberReverse);
    }

}
