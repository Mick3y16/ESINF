package ex02d;

public class Ex02d {

    public static String palindrome = "sopapos";
    public static String palindromeNot = "engenharia";

    public static void main(String[] args) {
        System.out.println(isPalindrome(palindrome, 0, palindrome.length() - 1)
                ? "It's a palindrome!"
                : "It's not a palindrome!");
        System.out.println(isPalindrome(palindromeNot, 0, palindromeNot.length() - 1)
                ? "It's a palindrome!"
                : "It's not a palindrome!");
    }

    public static boolean isPalindrome(String word, int low, int high) {
        if (low < high) {
            return word.charAt(low) == word.charAt(high) && isPalindrome(word, ++low, --high);
        }

        return true;
    }

}
