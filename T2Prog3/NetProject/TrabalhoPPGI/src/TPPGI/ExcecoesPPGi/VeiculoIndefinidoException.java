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
public class VeiculoIndefinidoException  extends ExcecoesPPGI{
    public VeiculoIndefinidoException(String sigla, String tipo){
        super("Tipo de veículo desconhecido para veículo " + sigla + ": " + tipo + ".");
    }
}
