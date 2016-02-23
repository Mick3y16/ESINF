package ex02b;

public class Ex02b {

    public static void main(String[] args) {
        System.out.println(convertToBinary(6));
    }

    public static String convertToBinary(int decimalNumber) {
        if (decimalNumber <= 1) {
            return String.valueOf(decimalNumber);
        }

        return convertToBinary(decimalNumber / 2) + decimalNumber % 2;
    }

}
