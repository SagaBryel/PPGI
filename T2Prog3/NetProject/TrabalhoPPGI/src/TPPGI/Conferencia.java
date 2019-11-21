package TPPGI;

public class Conferencia extends Veiculo{
    String local;
    
    public Conferencia(String sigla, String nome, double impacto){
        this.nome = nome;
        this.sigla = sigla;
        this.impacto = impacto;
    }
    
    @Override
    public String toString() {
        
        return this.sigla + " " + this.nome + " " + this.impacto + " " + "\n";
    }
}
