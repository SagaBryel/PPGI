package TPPGI;

import TPPGI.ExcecoesPPGi.QualisDesconhecidaException;
import java.util.Map;
import java.io.Serializable;
/**
 *
 * @author Gabriel Paschoal
 */

//A classe nao deve ser instanciada, apenas serve para definir coisas comuns a 
//Conferenci a e Periodico.
public abstract class Veiculo implements Serializable {
    protected String sigla;
    protected String nome;
    protected double impacto;
    //Mapa com o ano e a qualis desse ano
    protected Map<Integer, String> veiqualis;
    
    public abstract void SetAtributoEspecifico(String atributo);
    
    public abstract double getPontuacao(Regra reg, int ano);
    
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

   
    public String getQualis(int ano){
    
        String qualis = "Z";
        for(int i=ano;i>1999;i--){
            if(veiqualis.get(i) != null){
                qualis = veiqualis.get(i);
                break;
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
