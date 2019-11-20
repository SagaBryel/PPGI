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

    public Docente(String codigo, String nome, Date nascimento, Date ingresso) {
        this.codigo = codigo;
        this.nome = nome;
        this.nascimento = nascimento;
        this.ingresso = ingresso;
        this.publicacoes = new TreeSet<>();
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
