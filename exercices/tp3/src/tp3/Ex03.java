package tp3;

public class Ex03 {

    /*
     Exercise 3
    
     a) Returns the highest value in an array, to do so it decomposses the array
     recieved by parameter in half, saving only the size/2 highest numbers, until
     there is only one left, than it returns it.
    
     b) The method is deterministic and its complexity is O(n.log(n)).
    
     */
    public static int mystery3(int[] A, int n) {
        if (n == 1) {
            return A[0];
        } else {
            int k = n / 2;
            for (int i = 0; i < k; i++) {
                if (n % 2 == 0) {
                    A[i] = test(A[i], A[i + k]);
                } else {
                    A[i] = test(A[i], A[i + k + 1]);
                }
            }
            if (n / 2 != 0) {
                A[0] = test(A[0], A[(n / 2)]);
            }
            return mystery3(A, k);
        }
    }

    /*
     Return the highest value between two.
     */
    public static int test(int j, int k) {
        if (j > k) {
            return j;
        } else {
            return k;
        }
    }

}
