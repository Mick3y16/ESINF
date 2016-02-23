package exame2;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

/**
 *
 * @author Pedro Moreira
 */
public class Seguradora {

    public Map<TipoSeguro, List<Apolice>> listaApolicesPorSeguro;
    
    public double calcularPremioClient(String cliente) {
        double premio = 0;
        
        for (Entry<TipoSeguro, List<Apolice>> entry : listaApolicesPorSeguro.entrySet()) {
            
            for(Apolice apolice : entry.getValue()) {
                
                if (apolice.nomeCli.equals(cliente)) {
                    premio += apolice.premio;
                }
                
            }
            
        }
        
        return premio;
    }
    
    public void inserirNovaCC(TipoSeguro tipoSeguro, String novaCC) {
        List<Apolice> listaApolices = listaApolicesPorSeguro.get(tipoSeguro);
        
        for (Apolice apolice : listaApolices) {
            apolice.lcc.add(novaCC);
        }
    }
    
    public void aumentarPremio() {
        Map<String, Integer> listaClientes = new HashMap();
        
        // Contar CC por cliente
        for (Entry<TipoSeguro, List<Apolice>> entry : listaApolicesPorSeguro.entrySet()) {
            for(Apolice apolice : entry.getValue()) {
                if (listaClientes.containsKey(apolice.nomeCli)) {
                    listaClientes.replace(apolice.nomeCli, listaClientes.get(apolice.nomeCli) + apolice.lcc.size());
                } else {
                    listaClientes.put(apolice.nomeCli, apolice.lcc.size());
                }
            }
        }
        
        List<String> clientesComMaisTresCoberturas = new ArrayList();
        
        // Filtrar clientes com mais de 3 CC
        for (Entry<String, Integer> entry : listaClientes.entrySet()) {
            int numCC = entry.getValue();
            
            if (numCC > 3) {
                clientesComMaisTresCoberturas.add(entry.getKey());
            }
        }
        
        // Aumentar o valor do prémio em 15% desses clientes.
        for (Entry<TipoSeguro, List<Apolice>> entry : listaApolicesPorSeguro.entrySet()) {
            
            for(Apolice apolice : entry.getValue()) {
                if (clientesComMaisTresCoberturas.contains(apolice.nomeCli)) {
                    apolice.premio *= 1.15;
                }
            }
            
        }
    }
    
}
