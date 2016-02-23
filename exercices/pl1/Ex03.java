package ex03;

public class Ex03 {

    public static void main(String[] args) {
        System.out.println(mdc(100, 50));
        System.out.println(mdc(20, 9));
    }

    public static int mdc(int m, int n) {
        if (n > m) {
            return mdc(n, m);
        } else if (m % n == 0) {
            return n;
        }

        return mdc(n, m % n);
    }

}
