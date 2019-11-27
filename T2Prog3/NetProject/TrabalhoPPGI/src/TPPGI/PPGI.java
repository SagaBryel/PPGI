package TPPGI;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.io.Serializable;

public class PPGI implements Serializable{
    private final Set<Docente> docentes;//tree
    private final Map<String, Veiculo> veiculos;//hash
    private final List<Regra> regras;
    private final Set<Publicacao> publicacoes;
    //Como era reescrito muitas vezes foi decidido pela dupla criar um atributo para ser usado nos metodos
    private final NumberFormat stringParaInteiro = NumberFormat.getIntegerInstance(Locale.forLanguageTag("pt-BR"));
    private final DecimalFormat stringParaDecimal = (DecimalFormat)NumberFormat.getNumberInstance(Locale.forLanguageTag("pt-BR"));
    
    public PPGI(){
        this.docentes = new TreeSet<>();
        this.veiculos = new HashMap<>();
        this.regras = new ArrayList<>();
        this.publicacoes = new TreeSet<>();
    }
    
    
    
     public void LeRegras(String arquivo){
        try {
            File entrada = new File(arquivo);
            Scanner scan = new Scanner(entrada);
            scan.nextLine();//consumindo o cabeçalho
            String linha;
            String[] split;
            String[] chaves;
            String[] valores;
            double fator, pminima;
            int vigencia;
            SimpleDateFormat formato = new SimpleDateFormat("dd/MM/YYYY");
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
                    aux.put(chaves[i], stringParaInteiro.parse(valores[i]).intValue());
                }
                
                fator = stringParaDecimal.parse(split[4]).doubleValue();
                pminima = stringParaDecimal.parse(split[6]).doubleValue();
                vigencia = stringParaInteiro.parse(split[5]).intValue();
                regra = new Regra(inicio, fim, aux, fator, vigencia, pminima);
                regras.add(regra);
            }
            
            scan.close();
            
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
        try {
            File entrada = new File(arquivo);
            Scanner scan = new Scanner(entrada);
          
            scan.nextLine();
            String linha;
            //Declaração de um vetor de strings para receber o retorno da função split (de String) mais adiante no codigo
            String[] split;
            SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
            Date nascimento;
            Date ingresso;
            String codigo;
            String nome;
            
            
            while (scan.hasNextLine()) {
                linha = scan.nextLine();
                split = linha.split(";");
                nascimento = (Date)formato.parse(split[2]);
                ingresso = (Date)formato.parse(split[3]);
                codigo = split[0].trim();
                nome = split[1].trim();
                Docente docente = new Docente(codigo, nome, nascimento, ingresso);
                //Verifica se é um coordenador
                if(split.length == 5){
                    docente.setCoordenadorTrue();
                }
                docentes.add(docente);
            }
            
            scan.close();
            
    }   catch (FileNotFoundException ex) {
            Logger.getLogger(PPGI.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Erro I/O");
        } catch (ParseException ex) {
            Logger.getLogger(PPGI.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void MostraDocentes(){
        for(Docente d : this.docentes){
            System.out.println(d);
        }
    }
    
    public void LePublicacoes(String arquivo){
        try {
            //NumberFormat stringParaInteiro = NumberFormat.getIntegerInstance(Locale.forLanguageTag("pt-BR"));
            File entrada = new File(arquivo);
            Scanner scan = new Scanner(entrada);//stream de entrada
            scan.nextLine();//pular o cabecalho
            String linha;
            String[] split;
            String[] splitautores;
            Veiculo veiculaux;
            Iterator ite;
            Docente docaux;
            Publicacao publi;
            
            //Valores que serão transformados de string
            int ano;
            int pagini;
            int pagfim;
            int numero;
            
            while(scan.hasNextLine()){
                linha = scan.nextLine();
                split = linha.split(";");
                veiculaux = veiculos.get(split[1].trim());
                
                //Momento em que é atribuido Local ou Volume
                //É assumido que um e so um dos dois atributos esteja na linha do arquivo referente a uma pubicação
                if(split[4].isEmpty())
                    veiculaux.SetAtributoEspecifico(split[5].trim());
                else if(split[5].isEmpty())
                    veiculaux.SetAtributoEspecifico(split[4].trim());
                
                
                //nota: Ainda é necessario tratar os campos Volume e Local
                
                ano = stringParaInteiro.parse(split[0].trim()).intValue();
                pagini = stringParaInteiro.parse(split[7].trim()).intValue();
                pagfim = stringParaInteiro.parse(split[8].trim()).intValue();
                numero = stringParaInteiro.parse(split[4].trim()).intValue();
                
                publi = new Publicacao(ano, veiculaux, split[2].trim(), numero, pagini, pagfim);
                
                
                splitautores = split[3].split(",");
                
                //Loop que adiciona os autores a uma publicação enquanto simultaneamente adiciona as publicacoes nos docentes
                for(int i=0; i<splitautores.length; i++){
                    ite = docentes.iterator();
                    //Essa parte precisa ser repensada
                    while(ite.hasNext()){
                        docaux = (Docente)ite.next();
                        if(splitautores[i].trim().equals(docaux.getCodigo())){
                            //tomar cuidado com isso
                            publi.AdcionaAutor(docaux);
                            docaux.AdicionaPublicacao(publi);
                            
                        }
                    }
                }
                
                //Enfim, adciona a publicação na lista de publicações
                publicacoes.add(publi);
                
            }
            
            scan.close();
            
        } catch (FileNotFoundException ex) {
            Logger.getLogger(PPGI.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Erro I/O");
        } catch (ParseException ex) {
            Logger.getLogger(PPGI.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void MostraPublicacoes(){
        for(Publicacao p : publicacoes){
            System.out.println(p);
        }
    }
    
    public void leVeiculos(String arquivo){
        try {
            File entrada = new File(arquivo);
            Scanner scan = new Scanner(entrada);//stream de entrada
            scan.nextLine();//pular o cabecalho
            String linha;
            String[] split;
            
            String sigla, nome;
            double impacto;
            //DecimalFormat decimal = (DecimalFormat)NumberFormat.getNumberInstance(Locale.forLanguageTag("pt-BR"));
            
            while(scan.hasNextLine()){
                linha = scan.nextLine();
                split = linha.split(";");
                
                sigla = split[0].trim();
                nome = split[1].trim();
                impacto = stringParaDecimal.parse(split[3].trim()).doubleValue();
                
                if(split[2].equals("P")){//comparacao de strings
                    //Criando Periodico
                    Periodico periodico = new Periodico(sigla, nome, impacto, split[4].trim());
                    veiculos.put(split[0], periodico);
                    //System.out.println(split[0]+ " " +  split[1] + split[2] + split[3] + split[4]);
                }else if(split[2].equals("C")) {//comparacao de strings
                    //Criando Conferencia
                    Conferencia conferencia = new Conferencia(sigla, nome, impacto);
                    veiculos.put(split[0].trim(), conferencia);
                    
                }   
            }
            
            scan.close();
            
        } catch (FileNotFoundException ex) {
            Logger.getLogger(PPGI.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Erro I/O");
        } catch (ParseException ex) {
            Logger.getLogger(PPGI.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    public void LeQualis(String arquivo){
        try {
            File entrada = new File(arquivo);
            Scanner scan = new Scanner(entrada);
            scan.nextLine();//pular o cabecalho
            String linha;
            String[] split;
            Integer ano;
            
            while(scan.hasNextLine()){
                linha = scan.nextLine();
                split = linha.split(";");
                ano = stringParaInteiro.parse(split[0]).intValue();
                
                //Tratar a existencia de whitespaces entre os argumentos das linhas
                if(veiculos.containsKey(split[1])){
                    Veiculo vaux = veiculos.get(split[1].trim());
                    vaux.AdicionaQualis(ano, split[2].trim());
                }
                //Veiculo vaux = veiculos.get(split[1]);
                //vaux.AdicionaQualis(ano, split[2]);
                //(veiculos.get(split[1])).AdicionaQualis(ano, split[2]);
            }
            
            scan.close();
            
        } catch (FileNotFoundException ex) {
            Logger.getLogger(PPGI.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Erro I/O");
        } catch (ParseException ex) {
            Logger.getLogger(PPGI.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void MostraVeiculos(){
        for(Map.Entry<String, Veiculo> v: this.veiculos.entrySet()){
            System.out.println(v.getValue());
        }
    }
    
    public void Recredenciamento(String anostr){
        int ano = Integer.parseInt(anostr);//Talvez isso enxugasse o codigo onde é utilizado numberformat
        Iterator ite = docentes.iterator();
        try {
            FileWriter arq = new FileWriter("1 - recredenciamento.csv");
            PrintWriter print = new PrintWriter(arq);
            print.println("Docente;Pontuação;Recredenciado?2");
            
            
            
            
            arq.close();
            
        } catch (IOException ex) {
            Logger.getLogger(PPGI.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    
    
    public void Serializadora(){
        try {
            File arq = new File("recredenciamento.dat");
            ObjectOutputStream serial = new ObjectOutputStream(new FileOutputStream(arq));
            serial.writeObject(this);
            serial.close();
            
        } catch (FileNotFoundException ex) {
            Logger.getLogger(PPGI.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(PPGI.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public PPGI Desserializadora(String arquivodat){
        try {
            File arq = new File(arquivodat);
            if (arq.exists()) {
                //REVISAR ESSE TRECHO
                ObjectInputStream serial = new ObjectInputStream(new FileInputStream(arq));
                PPGI novo = new PPGI();
                novo = (PPGI)serial.readObject();
                serial.close();
                return novo;
            }
            
        } catch (FileNotFoundException ex) {
            Logger.getLogger(PPGI.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(PPGI.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(PPGI.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("Erro de Desserialização");
        return null;
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