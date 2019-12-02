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
public class CodigoRepetidoException extends ExcecoesPPGI{
    public CodigoRepetidoException(String objeto, String codigo){
        super("Código repetido para " + objeto + ": " + codigo + ".");
    }
}
