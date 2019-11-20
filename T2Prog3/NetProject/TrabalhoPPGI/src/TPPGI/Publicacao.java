package TPPGI;
import java.util.Date;
/**
 *
 * @author Gabriel Paschoal
 */
public class Publicacao implements Comparable<Publicacao>{
    private int ano;
    //Veiculo no qual foi publicada (deve ser referencia?)
    private String veiculo;
    private String titulo;
    /*Autores*/
    private int numero;
    private int volume;
    
    private String local;
    private int pagini;
    private int pagfim;
    private String qualis;
    
    
    //Sobrescrita do meto comparable para fins (atualmente) de iserção ordenada em treese
    //Lebrar de, futuramente conferir as saidas
    @Override
    public int compareTo(Publicacao p){
        int i = this.qualis.compareTo(p.qualis);
        
        if(i == 0){
            if(this.ano == p.ano){
                
            }
            return this.ano - p.ano;
        }
        
        return i;
    }
}
