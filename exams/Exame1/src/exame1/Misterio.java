/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package exame1;

import java.util.List;

/**
 *
 * @author Pedro Moreira
 */
public class Misterio {

    public void misterio(List<Integer> vi, List<Integer> vo) {
        int s = 3;
        for (int i = 0; i < vi.size() - s; i++) {
            vo.set(i, outroMisterio(vi, i, s));
        }
    }

    public int outroMisterio(List<Integer> v, int i, int s) {
        int r = 0;
        for (int j = i; j < i + s; j++) { 
            r = r + v.get(j);
        }
        return r / s;
    }

}
