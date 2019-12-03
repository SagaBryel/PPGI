package TPPGI;
import TPPGI.ExcecoesPPGi.CodigoRepetidoException;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;
/** Classe que representa um Docente
 * @author Gabriel Paschoal
 * @author Hiuri Carriço Liberato
 */
public class Docente implements Serializable, Comparable<Docente>{
    //MUDAR CODIGO PARA INTEIRO
    private Long codigo;
    private String nome;
    private Date nascimento;
    private Date ingresso;
    private Set<Publicacao> publicacoes;
    private Boolean coordenador;
    
    /**
     * Construtor de um docente
     * @param codigo
     * @param nome
     * @param nascimento
     * @param ingresso 
     */
    public Docente(long codigo, String nome, Date nascimento, Date ingresso) {
        this.codigo = codigo;
        this.nome = nome;
        this.nascimento = nascimento;
        this.ingresso = ingresso;
        this.publicacoes = new TreeSet<>();
        this.coordenador = false;
    }
    
    /**Setter para 
     */
    public void setCoordenadorTrue() {coordenador = coordenador = true;}

    public Long getCodigo() {
        return codigo;
    }
    
    public void AdicionaPublicacao(Publicacao p){
        publicacoes.add(p);
    }

    public String getNome() {
        return nome;
    }
    public String VerificaCondicao(int ano, boolean situacao){
        Calendar nasceu = new GregorianCalendar();
        nasceu.setTime(this.nascimento);
        Calendar ingressou = new GregorianCalendar();
        ingressou.setTime(this.ingresso);
        
        if(this.coordenador)
            return "Coordenador";
        if((ano - ingressou.get(Calendar.YEAR)) <= 3)
            return "PPJ";
        if((ano - nasceu.get(Calendar.YEAR)) > 60)
            return "PPS";
        if(situacao)
            return "Sim";
        return "Não";
        
    }
    
    
    public Iterator getPubIterator(){
        return this.publicacoes.iterator();
    }
    
    //Ainda falta definir forma de comparacao
    @Override
    public int compareTo(Docente d){
        return this.nome.compareTo(d.nome);
    }
    //CompareTo para comparar pelo codigo
    
    
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
