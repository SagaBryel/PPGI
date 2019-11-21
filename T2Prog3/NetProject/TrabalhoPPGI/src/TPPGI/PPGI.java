package TPPGI;
import java.io.File;
import java.io.FileNotFoundException;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Set;
import java.util.TreeSet;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PPGI {
    private Set<Docente> docentes;//tree
    private Set<Veiculo> veiculos;//hash
    private List<Regra> regras;
    
    
    public PPGI(){
        this.docentes = new TreeSet<>();
        this.veiculos = new HashSet<>();
        this.regras = new ArrayList<>();
    }
    
    
    
     public void LeRegras(String arquivo){
        File entrada = new File(arquivo);
        
        try {
            Scanner scan = new Scanner(entrada);
            scan.nextLine();
            String linha;
            String[] split;
            String[] chaves;
            String[] valores;
            SimpleDateFormat formato = new SimpleDateFormat("dd/MM/YYYY");
            NumberFormat inteiro = NumberFormat.getIntegerInstance(Locale.forLanguageTag("pt-BR"));
            DecimalFormat decimal = (DecimalFormat)NumberFormat.getNumberInstance(Locale.forLanguageTag("pt-BR"));
            Date inicio;
            Map<String, Integer> aux =  new HashMap<>();
            Date fim;
            
            while(scan.hasNextLine()){
                linha = scan.nextLine();
                split = linha.split(";");
                inicio = (Date)formato.parse(split[0]);
                fim = (Date)formato.parse(split[1]);
                Regra regra;
                chaves = split[2].split(",");
                valores = split[3].split(",");
                //Preenchimento do mapa
                for(int i=0;i<chaves.length;i++){
                    aux.put(chaves[i], inteiro.parse(valores[i]).intValue());
                }
                regra = new Regra(inicio, fim, aux, decimal.parse(split[4]).doubleValue(), inteiro.parse(split[5]).intValue(), decimal.parse(split[6]).doubleValue());
                regras.add(regra);
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(PPGI.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
            Logger.getLogger(PPGI.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }
    public void MostraRegras(){
        for(Regra r : this.regras){
            System.out.println(r);
        }
    }
    
    
    public void LeDocentes(String arquivo){
        File entrada = new File(arquivo);
        
        try (Scanner scan = new Scanner(entrada)) {
            scan.nextLine();
            String linha;
            //Declaração de um vetor de strings para receber o retorno da função split (de String) mais adiante no codigo
            String[] split;
            SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
            Date nascimento;
            Date ingresso;
            
            while (scan.hasNextLine()) {
                linha = scan.nextLine();
                split = linha.split(";");
                nascimento = (Date)formato.parse(split[2]);
                ingresso = (Date)formato.parse(split[3]);
                Docente docente = new Docente(split[0], split[1], nascimento, ingresso);
                //Verifica se é um coordenador
                if(split.length == 5){
                    docente.setCoordenadorTrue();
                }
                docentes.add(docente);
            }
    }   catch (FileNotFoundException ex) {
            Logger.getLogger(PPGI.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
            Logger.getLogger(PPGI.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void MostraDocentes(){
        for(Docente d : this.docentes){
            System.out.println(d);
        }
    }
    
    public void leVeiculos(){
        
    }
        
}
/*NOTAS
* Seguindo as boas práticas de orientação a objetos, você deve programar para interfaces e não para implementações.
* A recomendação é escolher uma implementação para instanciar o objeto e atribuir a nova coleção ao tipo de interface correspondente. 
* Ou ainda, passar o objeto coleção para um método que espera um argumento do tipo interface.
* Seguindo essas práticas você conseguirá o que chamamos de baixo acoplamento, ou seja, poderá mudar facilmente de implementação
* sem que isso acarrete alteração no código da aplicação. Desta forma você fica livre para mudar a implementação sempre que questões 
* relacionadas a desempenho ou detalhes de comportamento exigirem a mudança.
* ~Devmedia
*/