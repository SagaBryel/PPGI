package TPPGI;

import TPPGI.ExceptionsPPGi.QualisDesconhecidaException;
import java.util.Map;
import java.io.Serializable;
/** Classe mãe que define atributos e métodos comuns para periódico e conferência 
 * @author Gabriel Luiz de Oliveira Paschoal
 * @author Hiuri Carriço Liberato
 */

//A classe nao deve ser instanciada, apenas serve para definir coisas comuns a 
//Conferenci a e Periodico.
public abstract class Veiculo implements Serializable {
    protected String sigla;
    protected String nome;
    protected double impacto;
    //Mapa com o ano e a qualis desse ano
    protected Map<Integer, String> veiqualis;
    
    
    /**Método abstrato utilizado para definir volume ou local caso seja um periódico ou
     * conferencia respectivamente
     * 
     * @param atributo 
     */
    public abstract void SetAtributoEspecifico(String atributo);
    
    /**Método utilizado para retornar a pontuação
     * 
     * @param reg
     * @param ano
     * @return 
     */
    public abstract double getPontuacao(Regra reg, int ano);
    
    
    /**Método utilizado para inserir as qualis no Map de qualis de cada veículo
     * verificando se a qualis existe(é permitida).
     * @param chave
     * @param valor
     * @throws QualisDesconhecidaException 
     */
    public void AdicionaQualis(Integer chave, String valor) throws QualisDesconhecidaException{
        //"tem" é criada como false
        boolean tem = false;
        String[] validas = new String[]{"A1","A2","B1","B2","B3","B4","B5","C"};
        for(String qualis : validas){
            if(qualis.equals(valor))
                //Se algum elemento de validas for a string passada, "tem" passa a ser true
                tem = true;
        }
        //Se tiver define a qualis
        if(tem)
            this.veiqualis.put(chave, valor);
        
        //Se validas não tiver uma string igual a valor, então valor é uma qualis invalida
        else
            throw new QualisDesconhecidaException(this.sigla, chave, valor);
    }

    /**Método para retornar a qualis do veículo
     * 
     * @param ano
     * @return 
     */
    public String getQualis(int ano){
    
        String qualis = "Z";
        for(int i = ano;i>1999;i--){
            if(veiqualis.get(i) != null){
                qualis = veiqualis.get(i);
                break;
            }
        }
        return qualis;
    }
    
    public String getQualisP(int ano){
    
        String qualis = "Z";
        for(int i = ano;i>1999;i--){
            if(veiqualis.get(i) != null){
                qualis = veiqualis.get(i);
                return qualis;
            }
        }
        return qualis;
    }

    
    
    public String getSigla() {
        return sigla;
    }

    public String getNome() {
        return nome;
    }

    public double getImpacto() {
        return impacto;
    }

}
