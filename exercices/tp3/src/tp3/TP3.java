package tp3;

public class TP3 {

    public static void main(String[] args) {
        int a[] = {6, 1, 4, 1, 7, 3, 1, 7};
        System.out.println(Ex01.mystery(a));

        int b[] = {1, 13, 17, 18, 22, 33, 35, 38};
        System.out.println(Ex02.mystery2(b, 35) ? "Is!" : "Is not!");
        System.out.println(Ex02.mystery2improved(b, 35) ? "Is!" : "Is not!");

        int[] A = {1, 2, 5, 6, 11, 17, 23, 31, 38};
        System.out.println(Ex03.mystery3(A, A.length));
    }

}
