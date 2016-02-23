package tp3;

public class Ex04 {

    /*
     Exercise 4
    
     The method is deterministic and its complexity is O(log(n)).
     */
    public static double power(double b, int e) {
        if (e == 0) {
            return 1;
        }
        if (e == 1) {
            return b;
        }
        if (e % 2 == 0) {
            return power(b * b, e / 2);
        } else {
            return b * power(b * b, e / 2);
        }
    }

}
