package TPPGI;
import java.util.Date;
import java.util.Set;
import java.util.TreeSet;
/**
 *
 * @author Gabriel Paschoal
 */
public class Docente implements Comparable<Docente>{
    private String codigo;
    private String nome;
    private Date nascimento;
    private Date ingresso;
    private Set<Publicacao> publicacoes;
    
    
    public Docente(String nome, String codigo){
        this.publicacoes = new TreeSet<>();
        this.nome = nome;
        this.codigo = codigo;
    }
    
    @Override
    public int compareTo(Docente d){
        return 1;
    }
    @Override
    public String toString() {
        return this.nome + " " + this.codigo + ";\n";
    }
}
