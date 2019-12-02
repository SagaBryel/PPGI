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
public class DocenteIndefinidoException extends ExcecoesPPGI{
    public DocenteIndefinidoException(String titulo, String sigla){
        super("Código de docente não definido usado na puplicacao " + titulo + ": " + sigla + ".");
    }
}
