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
public class Mensagem implements Comparable<Mensagem> {
    
    private String assunto;
    
    private List<String> destinatarios;
    
    
    public String getAssunto() {
        return this.assunto;
    }
    
    public int getNumDestinatarios() {
        return this.destinatarios.size();
    }

    @Override
    public int compareTo(Mensagem otherMensagem) {
        return this.assunto.compareTo(otherMensagem.assunto);
    }
    
}
