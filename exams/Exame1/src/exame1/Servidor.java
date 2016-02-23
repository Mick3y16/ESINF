/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package exame1;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 *
 * @author Pedro Moreira
 */
public class Servidor {

    private List<Mensagem> inBox;
    
    public void sortAlphabeticly() {
        Collections.sort(inBox);
        
        Collections.sort(inBox, new Comparator<Mensagem>() {
            @Override
            public int compare(Mensagem o1, Mensagem o2) {
                return o1.getAssunto().compareTo(o2.getAssunto());
            }
        });
    }
    
    public List<Mensagem> getListMaxNumDestinatarios() {
        int max = 0;
        List<Mensagem> lista = new ArrayList<>();
        
        for (Mensagem m : inBox) {
            if (m.getNumDestinatarios() > max) {
                max = m.getNumDestinatarios();
                lista.clear();
                lista.add(m);
            } else if (m.getNumDestinatarios() == max) {
                lista.add(m);
            }
        }
        
        /*int max = 0;
        
        for (Mensagem m : inBox) {
            if (m.getNumDestinatarios() > max) {
                max = m.getNumDestinatarios();
            }
        }
        
        List<Mensagem> lista = new ArrayList<>();
        
        for (Mensagem m : inBox) {
            if (m.getNumDestinatarios() == max) {
                lista.add(m);
            }
        }*/
        
        return lista;
    }
    
    public void removerMensagemComAssunto(String assunto) {
        for (Mensagem m : inBox) {
            if (m.getAssunto().equals(assunto)) {
                inBox.remove(m);
            }
        }
    }
    
}
