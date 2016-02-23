package ex02;

public class Ex02a {

    public static void main(String[] args) {
        System.out.println(sum(2, 3));
    }

    public static int sum(int firstNumber, int lastNumber) {
        if (firstNumber != 0) {
            lastNumber = sum(--firstNumber, lastNumber) + 1;
        }

        return lastNumber;
    }
}
