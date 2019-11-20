package TPPGI;
import java.util.Date;
/**
 *
 * @author Gabriel Paschoal
 */
public class Publicacao implements Comparable<Publicacao>{
    private int ano;
    //Veiculo no qual foi publicada (deve ser referencia?)
    private Veiculo veiculo;
    private String titulo;
    /*Autores*/
    private int numero;
    private int volume;
    
    private String local;
    private int pagini;
    private int pagfim;
    private String qualis;
    
    
    //Sobrescrita do meto comparable para fins (atualmente) de iserção ordenada em treeset
    //Lebmrar de, futuramente conferir as saidas
    @Override
    public int compareTo(Publicacao p){
        int i = this.qualis.compareTo(p.qualis);
        
        if(i == 0){
            if(this.ano == p.ano){
                if(this.veiculo.sigla.compareTo(p.veiculo.sigla) == 0){
                    return this.titulo.compareTo(p.titulo);
                }
                else return this.veiculo.sigla.compareTo(p.veiculo.sigla);
            }
            return this.ano - p.ano;
        }
        //Como a ordem é decrescente por qualis, é invertido o valor da comparação entre as strings qualis
        return -i;
    }
}
