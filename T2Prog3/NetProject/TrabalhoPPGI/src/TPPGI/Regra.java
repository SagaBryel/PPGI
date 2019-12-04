package TPPGI;

import TPPGI.ExceptionsPPGi.QualisDesconhecidaException;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 *
 * @author Gabriel Paschoal
 * @author Hiuri Liberato
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
    
    /**Construtor de regra
     * 
     * @param inivig
     * @param fimvig
     * @param qualis
     * @param fator
     * @param anos
     * @param minimo
     * @throws QualisDesconhecidaException 
     */
    public Regra(Date inivig, Date fimvig, Map qualis, double fator, int anos, double minimo) throws QualisDesconhecidaException{
        this.inivig = inivig;
        this.fimvig = fimvig;
        //this.qualis = qualis;
        this.qtdAnos = anos;
        this.minimo = minimo;
        this.fator = fator;
        this.qualis = this.MontaHash(qualis);
    }
    
    
    
    /**Método para atribuir a pontuação de cada qualis de acordo com as regras
     * 
     * @param mapa
     * @return
     * @throws QualisDesconhecidaException 
     */
    private Map MontaHash(Map mapa) throws QualisDesconhecidaException{
        String[] keys = new String[]{"A1","A2","B1","B2","B3","B4","B5","C"};
        Integer pontuacao = 0;
        Map novo = new HashMap<>();
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
        boolean tem = false;
        Iterator it = mapa.entrySet().iterator();
        
        while(it.hasNext()) {
            Map.Entry pair = (Map.Entry)it.next();
            for(String key : keys){
                if(key.equals(pair.getKey()))
                    tem = true;
            }
            if(!tem){
                
                throw new QualisDesconhecidaException(formato.format(inivig), (String) pair.getKey());
            }
            tem = false;
            
        }
                        
                        
        for(String key : keys){
            if(mapa.containsKey(key))
                pontuacao = (Integer) mapa.get(key);
            novo.put(key, pontuacao);
        }
        
        return novo;
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

    /**Método para retornar os pontos do map de qualis 
     * 
     * @param key
     * @return 
     */
    public double getPonto(String key){
        return this.qualis.get(key).doubleValue();
    }
    
    public double getFator() {
        return fator;
    }
    
    /**Método para verificar qual a regra vigente ano ano passado como parâmetro 
     * Para a execução do sistema
     * 
     * @param ano
     * @return 
     */
    public boolean ehRegraVigente(int ano){
        Calendar inical = new GregorianCalendar();
        Calendar fimcal = new GregorianCalendar();
        inical.setTime(this.inivig);
        fimcal.setTime(this.fimvig);
        //A linha abaixo retorna verdadeiro caso o valor do parametro esteja dentro do intervalo de atuação da regra.
        return (inical.get(Calendar.YEAR) - this.qtdAnos) <= ano && ano <= (fimcal.get(Calendar.YEAR));
    }
    
    /**Método para verificar se o ano da publicação está no intervalo a ser considerado para pontuação
     * 
     * @param ano
     * @return 
     */
    public boolean ehRegraVigenteP(int ano){
        Calendar inical = new GregorianCalendar();
        Calendar fimcal = new GregorianCalendar();
        inical.setTime(this.inivig);
        fimcal.setTime(this.fimvig);
        //A linha abaixo retorna verdadeiro caso o valor do parametro esteja dentro do intervalo de atuação da regra.
        return (inical.get(Calendar.YEAR) - this.qtdAnos) <= ano && ano < (fimcal.get(Calendar.YEAR));
    }
    
    
    @Override
    public String toString() {
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/YYYY");
        
        return "inicio: " + formato.format(this.inivig) + " anos: " + this.qtdAnos + " fator: " + this.fator + " minimo: " + this.minimo;
    }
}