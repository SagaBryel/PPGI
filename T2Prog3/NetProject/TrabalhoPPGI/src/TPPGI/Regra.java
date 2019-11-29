
package TPPGI;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/**
 *
 * @author Gabriel Paschoal
 */
public class Regra implements Serializable{
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
        //this.qualis = qualis;
        this.qtdAnos = anos;
        this.minimo = minimo;
        this.fator = fator;
        this.qualis = this.MontaHash(qualis);
    }
    
    
    //Função que monta uma hash com todos os valores de qualis
    private Map MontaHash(Map mapa){
        String[] keys = new String[]{"A1","A2","B1","B2","B3","B4","B5","C"};
        Integer pontuacao = 0;
        Map novo = new HashMap<>();
        
        for(String key : keys){
            if(mapa.containsKey(key))
                pontuacao = (Integer) mapa.get(key);
            novo.put(key, pontuacao);
        }
        
        return novo;
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

    public double getPonto(String key){
        return this.qualis.get(key).doubleValue();
    }
    
    public double getFator() {
        return fator;
    }
    
    //CUIDADOCUIDADOCUIDADOCU
    public boolean ehRegraVigente(int ano){
        //Após erros talvez por causa da utilizacao de atributos deprecated de Date
        //Foi criado um calendar para os acessos aos campos da data
        Calendar inical = new GregorianCalendar();
        Calendar fimcal = new GregorianCalendar();
        inical.setTime(this.inivig);
        fimcal.setTime(this.fimvig);
        //A linha abaixo retorna verdadeiro caso o valor do parametro esteja dentro do intervalo de atuação da regra.
        int a, b;
        //a = (inical.get(Calendar.YEAR) - this.qtdAnos);
        //b = fimcal.get(Calendar.YEAR);
        //return (a <= ano && ano <= b);
        return (inical.get(Calendar.YEAR) - this.qtdAnos) <= ano && ano <= (fimcal.get(Calendar.YEAR));
    }
    
    public boolean ehRegraVigenteP(int ano){
        //Após erros talvez por causa da utilizacao de atributos deprecated de Date
        //Foi criado um calendar para os acessos aos campos da data
        Calendar inical = new GregorianCalendar();
        Calendar fimcal = new GregorianCalendar();
        inical.setTime(this.inivig);
        fimcal.setTime(this.fimvig);
        //A linha abaixo retorna verdadeiro caso o valor do parametro esteja dentro do intervalo de atuação da regra.
        int a, b;
        //a = (inical.get(Calendar.YEAR) - this.qtdAnos);
        //b = fimcal.get(Calendar.YEAR);
        //return (a <= ano && ano <= b);
        return (inical.get(Calendar.YEAR) - this.qtdAnos) <= ano && ano < (fimcal.get(Calendar.YEAR));
    }
    
    
    @Override
    public String toString() {
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/YYYY");
        
        return "inicio: " + formato.format(this.inivig) + " anos: " + this.qtdAnos + " fator: " + this.fator + " minimo: " + this.minimo;
    }
}