package tp3;

public class Ex01 {

    /*
     Exercise 1
    
     a) Counts the number of different values in an array.
    
     b) Its complexity is O(n^2).
    
     */
    public static int mystery(int v[]) {
        int cont = 0;
        for (int i = 0; i < v.length; i++) {
            cont++;
            boolean sing = true;
            for (int j = i + 1; j < v.length; j++) {
                if (v[i] == v[j]) {
                    sing = false;
                }
            }
            if (!sing) {
                cont--;
            }
        }
        return cont;
    }
}
