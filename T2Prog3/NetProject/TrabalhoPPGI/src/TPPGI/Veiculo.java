package TPPGI;

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
    
    public void AdicionaQualis(Integer chave, String valor){
        this.veiqualis.put(chave, valor);
    }
    
    public String GetMaiorQualis(){
        //Na realidade a ideia é passar a qualis q representa a o menor valor alfabetico
        String maior = "Z";
        
        for(Map.Entry<Integer, String> vq : veiqualis.entrySet()){
            if(vq.getValue().compareTo(maior) < 0)
                maior = vq.getValue();
        }
        return maior;
    }
    
    
    
    //Corpo de Metodos abstratos
    //public abstract tipo nome();

    public String getSigla() {
        return sigla;
    }

    public String getNome() {
        return nome;
    }

    public double getImpacto() {
        return impacto;
    }

    public String getVeiqualis(int ano) {
        System.out.println(veiqualis.get(ano));
        return veiqualis.get(ano);
        
    }
}
