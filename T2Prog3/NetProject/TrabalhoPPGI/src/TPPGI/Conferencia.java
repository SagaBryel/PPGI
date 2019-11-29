package TPPGI;

import java.util.HashMap;

public class Conferencia extends Veiculo{
    private String local;
    
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

    @Override
    public void SetAtributoEspecifico(String atributo) {
        this.local = atributo;
    }

    @Override
    public double getPontuacao(Regra reg, int ano) {
        return reg.getPonto(this.getQualis(ano));
    }
}
