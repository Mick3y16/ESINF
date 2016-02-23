package pl3;

public class Ex01 {

    public static void main(String[] args) {
        int A[] = {1, 23, 16, 15, 190, 215, 17, 49, 64, 2, 7};

        // a)
        insertionSort(A);

        // b)
        //int vecCopy[] = new int[A.length];
        //mergeSort(A, vecCopy, 0, A.length - 1);

        // c)
        //quickSort(A, 0, A.length - 1);

        // d)
        //bubbleSort(A);

        System.out.print("Sorted: ");
        for (int i = 0; i < A.length; i++) {
            System.out.print(A[i] + " ");
        }
        System.out.println("");

    }

    /**
     * Worst case performance:      О(n2) comparisons, swaps
     * Best case performance:       O(n) comparisons, O(1) swaps
     * Average case performance:    О(n2) comparisons, swaps
     * Worst case space complexity: О(n) total, O(1) auxiliary
     */
    public static void insertionSort(int vec[]) {
        int x, j;

        for (int i = 1; i < vec.length; i++) {
            x = vec[i];
            j = i;

            while (j > 0 && vec[j - 1] > x) {
                vec[j] = vec[j - 1];
                j--;
            }

            vec[j] = x;
        }

    }

    /**
     * Worst case performance:      O(n log n)
     * Best case performance:       O(n log n) typical, O(n) natural variant
     * Average case performance:    O(n log n)
     * Worst case space complexity: О(n) total, O(n) auxiliary
     */
    public static void mergeSort(int vec[], int vecCopy[], int botLimit, int topLimit) {
        int midLimit = (botLimit + topLimit) / 2 + 1;

        if (botLimit < topLimit) {

            mergeSort(vec, vecCopy, botLimit, midLimit - 1);
            mergeSort(vec, vecCopy, midLimit, topLimit);
        }

        for (int pos = botLimit; pos <= topLimit; pos++) {
            vecCopy[pos] = vec[pos];
        }

        int i = botLimit;
        int j = midLimit;
        int k = botLimit;

        while (i <= midLimit && j <= topLimit) {
            if (vecCopy[i] <= vecCopy[j]) {
                vec[k] = vecCopy[i];
                i++;
            } else {
                vec[k] = vecCopy[j];
                j++;
            }
            k++;
        }

        while (i < midLimit) {
            vec[k] = vecCopy[i];
            k++;
            i++;
        }
    }

    
    /**
     * Worst case performance:      O(n^2)
     * best case performance: 	    O(n log n) (simple partition) or O(n) (three-way partition and equal keys)
     * Average case performance:    O(n log n)
     * Worst case space complexity: O(n)
     */
    public static void quickSort(int vec[], int botLimit, int topLimit) {
        int temp, i = botLimit, j = topLimit, pivot = vec[botLimit + (topLimit - botLimit) / 2];
        
        while (i <= j) {
            while(vec[i] < pivot) {
                i++;
            }
            
            while(vec[j] > pivot) {
                j--;
            }
            
            if(i <= j) {
                temp = vec[i];
                vec[i] = vec[j];
                vec[j] = temp;
                
                i++;
                j--;
            }
            
            if(botLimit < j) {
                quickSort(vec, botLimit, j);
            }
            
            if(i < topLimit) {
                quickSort(vec, i, topLimit);
            }            
            
        }
        
    }

    /**
     * Worst case performance:      O(n^2)
     * Best case performance:       O(n)
     * Average case performance:    O(n^2)
     * Worst case space complexity: O(1) auxiliary
     */
    public static void bubbleSort(int vec[]) {
        int n = vec.length, temp;

        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (vec[j] > vec[j + 1]) {
                    temp = vec[j];
                    vec[j] = vec[j + 1];
                    vec[j + 1] = temp;
                }
            }
        }
    }

}
