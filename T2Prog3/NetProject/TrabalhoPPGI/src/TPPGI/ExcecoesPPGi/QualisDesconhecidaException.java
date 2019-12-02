/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TPPGI.ExcecoesPPGi;

/**
 *
 * @author hcliberato
 */
public class QualisDesconhecidaException extends ExcecoesPPGI{
    
    //regras
    public QualisDesconhecidaException(String data, String qualis){
            super("Qualis desconhecido para regras de " + data + ": " + qualis + ".");
    //qualificacao
    }public QualisDesconhecidaException(String sigla, String ano, String qualis){
            super("Qualis desconhecido para qualificação do veiculo " + sigla + "no ano " + ano + ": " + qualis + ".");
    }
    
    
}
