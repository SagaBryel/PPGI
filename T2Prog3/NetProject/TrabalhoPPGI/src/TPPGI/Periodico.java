package TPPGI;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.HashMap;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Periodico extends Veiculo{
    private String issn;
    private int volume;
    
    public Periodico(String sigla, String nome, double impacto, String issn){
        this.sigla = sigla;
        this.nome = nome;
        this.impacto = impacto;
        this.issn = issn;
        this.veiqualis = new HashMap<>();
    }
    
    
    @Override
    public String toString() {
        
        return this.sigla + " " + this.nome + " " + this.impacto + " " + this.issn + "\n";
    }

    @Override
    public void SetAtributoEspecifico(String atributo) {
        NumberFormat inteiro = NumberFormat.getIntegerInstance(Locale.forLanguageTag("pt-BR"));
        try {
            this.volume = inteiro.parse(atributo).intValue();
        } catch (ParseException ex) {
            Logger.getLogger(Periodico.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public double getPontuacao(Regra reg, int ano) {
        return reg.getFator() * reg.getPonto(this.getQualis(ano));
    }
}
