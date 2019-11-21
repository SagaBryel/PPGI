package TPPGI;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ArrayList;
import java.util.Map;
/**
 *
 * @author Gabriel Paschoal
 */
public class Regra {
    //Data do inicio da vigencia
    private Date inivig;
    //Data do fim da vigencia
    private Date fimvig;
    //Quantidade de anos que devem ser considerados para analisar as publicacoes
    private int qtdAnos;
    //Pontuação minima para docente se manter credenciado
    private double minimo;
    //Fator multiplicatico para ser aplicado em periodico
    private double fator;
    
    private Map<String, Integer> qualis;

    public Regra(Date inivig, Date fimvig, Map qualis, double fator, int anos, double minimo) {
        this.inivig = inivig;
        this.fimvig = fimvig;
        this.qtdAnos = anos;
        this.minimo = minimo;
        this.fator = fator;
    }

    Regra() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public Date getInivig() {
        return inivig;
    }

    public Date getFimvig() {
        return fimvig;
    }

    public int getQtdAnos() {
        return qtdAnos;
    }

    public double getMinimo() {
        return minimo;
    }

    public double getFator() {
        return fator;
    }
    
    @Override
    public String toString() {
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/YYYY");
        
        return "inicio: " + formato.format(this.inivig) + " anos: " + this.qtdAnos + " fator: " + this.fator + " minimo: " + this.minimo;
    }
}
