package exame2;

import java.util.List;
import java.util.Queue;

/**
 *
 * @author Pedro Moreira
 */
public class Misterio {

    /**
     * O(n2) 
     */
    public void misterio(Queue<Integer> qi, List<Integer> vi, List<Integer> vo) {
        while (!qi.isEmpty()) {
            int e = qi.peek();
            qi.remove();

            if (outroMisterio(vi, e)) {
                vo.add(e);
            }
        }
    }

    /**
     * O(n)
     * 
     * Verifica se uma dada lista contém um valor, devolvendo verdadeiro se 
     * existir e falso caso não exista.
     */
    public boolean outroMisterio(List<Integer> v, int e) {
        int i = 0;
        while (i < v.size() && v.get(i) != e) {
            i++;
        }

        if (i < v.size()) {
            return true;
        } else {
            return false;
        }
    }
}
