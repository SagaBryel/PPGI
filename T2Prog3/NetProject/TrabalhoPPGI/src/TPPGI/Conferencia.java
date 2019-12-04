package TPPGI;

import java.util.HashMap;

/** Classe que representa um Veiculo do tipo conferecia
 * Esta classe extende Veiculo
 * @author Gabriel Paschoal
 * @author Hiuri Carriço Liberato
 */
public class Conferencia extends Veiculo{
    private String local;
    
    /**Construtor de Conferencia
     * 
     * @param sigla
     * @param nome
     * @param impacto 
     */
    public Conferencia(String sigla, String nome, double impacto){
        this.nome = nome;
        this.sigla = sigla;
        this.impacto = impacto;
        this.veiqualis = new HashMap<>();
    }

    
    @Override
    public String toString() {
        
        return this.sigla + " " + this.nome + " " + this.impacto + " " + "\n";
    }

    /** Metodo que define um atributo que pode variar para cada herdeiro de Veiculo
     * Utilza-se do conceito de polimorfismo.
     * @param atributo 
     */
    @Override
    public void SetAtributoEspecifico(String atributo) {
        this.local = atributo;
    }

    @Override
    public double getPontuacao(Regra reg, int ano) {
        return reg.getPonto(this.getQualis(ano));
    }
}
