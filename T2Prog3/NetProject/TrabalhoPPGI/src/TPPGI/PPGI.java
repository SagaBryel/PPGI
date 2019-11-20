package TPPGI;
import java.io.File;
import java.io.FileNotFoundException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Set;
import java.util.TreeSet;
import java.util.HashSet;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PPGI {
    private Set<Docente> docentes;//tree
    private Set<Veiculo> veiculos;//hash
    
    
    public PPGI(){
        this.docentes = new TreeSet<>();
        this.veiculos = new HashSet<>();
    }
    
    
    
    
    
    
    
    public void LeDocentes(String arquivo){
        File entrada = new File(arquivo);
        
        try (Scanner scan = new Scanner(entrada)) {
            scan.nextLine();
            String linha;
            String[] split;
            SimpleDateFormat formato;
            Date nascimento;
            Date ingresso;
            while (scan.hasNextLine()) {
                linha = scan.nextLine();
                split = linha.split(";");
                if(split[4] == "X"){
                    //Ainda tem q ser tratado caso seja um coordenador
                }
                nascimento = formato.parseObject(split[2]);
                Docente docente = new Docente(split[0], split[1]);
                System.out.println(docente);
                System.out.println(linha);
                docentes.add(docente);
            }
    }   catch (FileNotFoundException ex) {
            Logger.getLogger(PPGI.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void MostraDocentes(){
        for(Docente d : this.docentes){
            System.out.println(d);
        }
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