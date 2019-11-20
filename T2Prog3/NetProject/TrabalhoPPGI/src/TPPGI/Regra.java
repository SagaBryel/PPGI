package TPPGI;

import java.util.Date;
import java.util.ArrayList;
/**
 *
 * @author Gabriel Paschoal
 */
public class Regra {
    //Data do inicio da vigencia
    private Date inivig;
    //Data do fim da vigencia
    private Date fimvig;
    //Lista de qualis presentes na regra
    private ArrayList<String> qualis;
    //Quantidade de anos que devem ser considerados para analisar as publicacoes
    private int qtdanos;
    //Pontuação minima para docente se manter credenciado
    private float minimo;
    //Fator multiplicatico para ser aplicado em periodico
    private float fator;
}
