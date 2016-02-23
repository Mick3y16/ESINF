package ex01;

public class Ex01 {

    public static final String[] strings = {"B", "D", "Z", "A"};

    public static void main(String[] args) {
        System.out.println(printAscending(strings, 0));
        System.out.println(printDescending(strings, strings.length - 1));
    }

    public static String printAscending(String[] strings, int low) {
        if (low < strings.length) {
            return strings[low] + printAscending(strings, ++low);
        }

        return "";
    }

    public static String printDescending(String[] strings, int high) {
        if (high >= 0) {
            return strings[high] + printDescending(strings, --high);
        }

        return "";
    }

}
