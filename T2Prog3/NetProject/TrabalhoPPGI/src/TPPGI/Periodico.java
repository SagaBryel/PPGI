package TPPGI;


public class Periodico extends Veiculo{
    String issn;
    int volume;
    
    public Periodico(String sigla, String nome, double impacto, String issn){
        this.sigla = sigla;
        this.nome = nome;
        this.impacto = impacto;
        this.issn = issn;
    }
    
    @Override
    public String toString() {
        
        return this.sigla + " " + this.nome + " " + this.impacto + " " + this.issn + "\n";
    }
}
