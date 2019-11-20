package TPPGI;
import java.util.Date;
import java.util.Set;
import java.util.TreeSet;
/**
 *
 * @author Gabriel Paschoal
 */
public class Docente {
    private String codigo;
    private String nome;
    private Date nascimento;
    private Date ingresso;
    private TreeSet<Publicacao> publicacoes;
    
    
    @Override
    public String toString() {
        return this.nome + " " + this.codigo + ";\n";
    }
}
