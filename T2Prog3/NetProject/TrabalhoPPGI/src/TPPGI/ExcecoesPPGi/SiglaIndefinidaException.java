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
public class SiglaIndefinidaException extends ExcecoesPPGI {
    public SiglaIndefinidaException(String titulo, String sigla){
        super("Sigla de veículo não definida usada na publicaco " + titulo + ": " + sigla + ".");
    }
    public SiglaIndefinidaException(int ano, String sigla){
        super("Sigla de veículo não definida usada na qualificacao do ano" + ano + ": " + sigla + ".");
    }
}
