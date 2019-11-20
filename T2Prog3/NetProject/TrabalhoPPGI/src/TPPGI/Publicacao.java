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
    
    
    //Sobrescrita do meto comparable para fins (atualmente) de iserção ordenada em treeset
    @Override
    public int compareTo(Publicacao p){
        return 1;
    }
}
