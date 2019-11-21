package TPPGI;

public class Periodico extends Veiculo{
    String issn;
    
    public Periodico(String sigla, String nome, Double impacto, String issn){
        this.sigla = sigla;
        this.nome = nome;
        this.impacto = impacto;
        this.nome = issn;
    }
    
}
