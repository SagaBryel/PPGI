package TPPGI;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**Classe Publicação, usada para avaliar os docentes de acordo com a quantidade 
 * de publicações e seus referidos pontos definidos na classe regras.
 *
 * @author Gabriel Paschoal
 * @author Hiuri Liberato
 */
public class Publicacao implements Serializable, Comparable<Publicacao>{
    private int ano;
    //Veiculo no qual foi publicada (deve ser referencia?)
    private Veiculo veiculo;
    private String titulo;
    private List<Docente> autores;
    private int numero;    
    private int paginaInicio;
    private int paginaFim;
   
    /**Construtor de publicação  
     * 
     * @param ano
     * @param veiculo
     * @param titulo
     * @param numero
     * @param paginaInicio
     * @param paginaFim 
     */
    public Publicacao(int ano, Veiculo veiculo, String titulo, int numero, int paginaInicio, int paginaFim) {
        this.ano = ano;
        this.veiculo = veiculo;
        this.titulo = titulo;
        this.numero = numero;
        this.paginaInicio = paginaInicio;
        this.paginaFim = paginaFim;
        autores = new ArrayList<>();
    }

  
    
    /**Método utilizado para adicionar Autor em uma publicação
     * 
     * @param docente 
     */
    public void AdcionaAutor(Docente docente){
        autores.add(docente);
    }

    public Veiculo getVeiculo() {
        return veiculo;
    }

    public int getAno() {
        return ano;
    }

    public int getNumeroAutores(){
        return autores.size();
    }
    
    public String getTitulo(){
        return this.titulo;
    }
    
    //Sobrescrita do metodo comparable para fins (atualmente) de iserção ordenada em treeset
    //Lebmrar de, futuramente conferir as saidas
    @Override
    public int compareTo(Publicacao p){
        int i = this.veiculo.getQualis(ano).trim().compareTo(p.veiculo.getQualis(ano).trim());
        if(i != 0){
            return i;
        }
        if(this.ano == p.ano){
            if(this.veiculo.sigla.compareTo(p.veiculo.sigla) == 0){
                return this.titulo.compareTo(p.titulo);
            }
            else return this.veiculo.sigla.compareTo(p.veiculo.sigla);

        }else{
            return p.ano - this.ano;
        }
    }
    
    @Override
    public String toString() {
        String auxAutores = "";
        for(Docente d : this.autores){
            auxAutores = auxAutores + d.getNome() + ",";          
        }
        if(auxAutores.length() > 0)
        auxAutores = auxAutores.substring(0, auxAutores.length() - 1);
        return ano + ";" + veiculo.getSigla() + ";" + veiculo.getNome() + ";" + this.veiculo.getQualisP(ano) + ";" + String.format("%.3f",veiculo.getImpacto()) + ";" + titulo + ";" + auxAutores;
    }
}