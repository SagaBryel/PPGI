package TPPGI;
import java.text.SimpleDateFormat;
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
    private Boolean coordenador;
    
    public Docente(String codigo, String nome, Date nascimento, Date ingresso) {
        this.codigo = codigo;
        this.nome = nome;
        this.nascimento = nascimento;
        this.ingresso = ingresso;
        this.publicacoes = new TreeSet<>();
        this.coordenador = false;
    }

    public void setCoordenadorTrue() {
        coordenador = coordenador = true;
    }
    
    
    
    //Ainda falta definir forma de comparacao
    @Override
    public int compareTo(Docente d){
        return this.nome.compareTo(d.nome);
    }
    
    @Override
    public String toString() {
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/YYYY");
        
        if(coordenador){
            return "Nome: " + this.nome + " Codigo: " + this.codigo
                + " Nascimento: "+ formato.format(nascimento)
                + " Ingresso: " + formato.format(ingresso) + " É coordenador";
        }
        
        return "Nome: " + this.nome + " Codigo: " + this.codigo
            + " Nascimento: "+ formato.format(nascimento)
            + " Ingresso: " + formato.format(ingresso);
    }
}
