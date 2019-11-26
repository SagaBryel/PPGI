package TPPGI;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Gabriel Paschoal
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
    //Atributo temporario a ser repensado futuramente. Guarda uma string representando o local para conferencia ou o volume para periodico
    private String LocalVolume;

    public Publicacao(int ano, Veiculo veiculo, String titulo, int numero, int paginaInicio, int paginaFim) {
        this.ano = ano;
        this.veiculo = veiculo;
        this.titulo = titulo;
        this.autores = autores;
        this.numero = numero;
        this.paginaInicio = paginaInicio;
        this.paginaFim = paginaFim;
        autores = new ArrayList<>();
    }
    
    public void AdcionaAutor(Docente d){
        autores.add(d);
    }
    

    
    //Sobrescrita do metodo comparable para fins (atualmente) de iserção ordenada em treeset
    //Lebmrar de, futuramente conferir as saidas
    @Override
    public int compareTo(Publicacao p){
        int i = this.veiculo.GetMaiorQualis().compareTo(p.veiculo.GetMaiorQualis());
        
        //
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
    
    @Override
    public String toString() {
        return ano + ";" + veiculo.sigla + ";" + veiculo.nome + ";" + "aqui Qualis" + ";" + "aqui Fatorimpacto" + ";" + titulo + ";";
    }
}
