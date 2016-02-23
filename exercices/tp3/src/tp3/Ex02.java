package tp3;

public class Ex02 {

    /*
     Exercise 2
    
     a) Sums both the current value and its next and check if their result is the
     same of the value we seek.
    
     b) It is deterministic, as it runs till the end of the vector.
    
     O(n^2)
    
     c) mystery2improved
    
     */
    public static boolean mystery2(int[] A, int value) {
        boolean flag = false;
        for (int i = 0; i < (A.length - 1) && A[i] < value / 2 + 1; i++) {
            for (int j = i + 1; j < A.length; j++) {
                if (A[i] + A[j] == value) {
                    flag = true;
                    System.out.println("pos " + i + "->" + A[i] + ", pos " + j + "->" + A[j]);
                }
            }
        }
        return flag;
    }

    public static boolean mystery2improved(int[] A, int value) {
        int i = 0, j = A.length - 1;
        boolean flag = false;

        while (i < j) {
            if (A[i] + A[j] == value) {
                flag = true;
                System.out.println("pos " + i + "->" + A[i] + ", pos " + j + "->" + A[j]);
                i++;
                j--;
            } else if (A[i] + A[j] < value) {
                i++;
            } else {
                j--;
            }
        }

        return flag;
    }
}
