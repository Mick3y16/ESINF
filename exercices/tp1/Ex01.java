package ex01;

public class Ex01 {

    public static int[] a = {6, 1, 4, 2, 7, 3, 1, 5};

    /*
     O código faz a rotação dos elementos de um vetor, tomando como centro o
     valor central do mesmo, divindindo o valor pela metade, até que o atinja
     o valor 1;
     */
    public static void main(String[] args) {
        example(a, 0, a.length);

        for (int i = 0; i < a.length; i++) {
            System.out.print(a[i] + " ");
        }
    }

    public static void process(int a[], int liminf, int limsup) {
        int i = liminf;
        int j = limsup - 1;

        while (i < j) {
            int temp = a[i];
            a[i] = a[j];
            a[j] = temp;
            i++;
            j--;
        }
    }

    public static void example(int[] a, int li, int ls) {
        if (li < ls) {
            process(a, li, ls);
            ls = ls / 2;
            example(a, li, ls);
        }
    }

}
