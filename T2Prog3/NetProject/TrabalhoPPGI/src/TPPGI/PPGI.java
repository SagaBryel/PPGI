package TPPGI;
import TPPGI.ExcecoesPPGi.*;
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
    private Set<Publicacao> publicacoes;
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
            try (Scanner scan = new Scanner(entrada)) {
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
            } //consumindo o cabeçalho
            
        } catch (FileNotFoundException ex) {
            System.out.println("Erro I/O");
        } catch (ParseException ex) {
            System.out.println("Erro de formatação");
        }
        
        
    }
    public void MostraRegras(){
        for(Regra r : this.regras){
            System.out.println(r);
        }
    }
    
    
    public void LeDocentes(String arquivo) throws CodigoRepetidoException{
        TreeSet<Long> codigosregistrados = new TreeSet<>();
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
            Long codigo;
            String nome;
            
            
            while (scan.hasNextLine()) {
                linha = scan.nextLine();
                split = linha.split(";");
                nascimento = (Date)formato.parse(split[2]);
                ingresso = (Date)formato.parse(split[3]);
                codigo = Long.parseLong(split[0].trim());
                nome = split[1].trim();
                Docente docente = new Docente(codigo, nome, nascimento, ingresso);
                //Verifica se é um coordenador
                if(split.length == 5){
                    docente.setCoordenadorTrue();
                }
                if(codigosregistrados.contains(docente.getCodigo()))
                    throw new CodigoRepetidoException("docente", docente.getCodigo().toString());
                codigosregistrados.add(codigo);
                docentes.add(docente);
            }
            
            scan.close();
            
    }   catch (FileNotFoundException ex) {
            System.out.println("Erro I/O");
        } catch (ParseException ex) {
            System.out.println("Erro de formatação");
        }
    }
    public void MostraDocentes(){
        for(Docente d : this.docentes){
            System.out.println(d);
        }
    }
    
    public void LePublicacoes(String arquivo) throws DocenteIndefinidoException, VeiculoIndefinidoException {
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
            int qtdautores;
            
            //Valores que serão transformados de string
            int ano;
            int pagini;
            int pagfim;
            int numero;
            
            while(scan.hasNextLine()){
                linha = scan.nextLine();
                split = linha.split(";");
                veiculaux = veiculos.get(split[1].trim());
                
                if(veiculaux == null)//caso o veiculo seja indefinido
                        throw new VeiculoIndefinidoException(split[0].trim(), split[1]);//qual seria esse ano?
                
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
                    qtdautores = 0;//reseta o contador de autores
                    
                    while(ite.hasNext()){
                        docaux = (Docente)ite.next();
                        if(splitautores[i].trim().equals(docaux.getCodigo().toString())){
                            //tomar cuidado com isso
                            publi.AdcionaAutor(docaux);
                            docaux.AdicionaPublicacao(publi);
                            qtdautores++;
                        }
                    }
                    //após varrer a coleção de docentes, caso nao ache nenhum autor. Lança a a exceção
                    if(qtdautores == 0)
                        throw new DocenteIndefinidoException(publi.getTitulo(), splitautores[i].trim());
                }
                
                //Enfim, adciona a publicação na arvore de publicações
                publicacoes.add(publi);
                
            }
            
            scan.close();
            
        } catch (FileNotFoundException ex) {
            System.out.println("Erro I/O");
        } catch (ParseException ex) {
            System.out.println("Erro de formatação");
        }
    }
    
    //REVER ISSO AQUI!!!!!!!!!!
    public void MostraPublicacoes(){
        Set<Publicacao> publiaux;
        publiaux = new TreeSet<>();
        for(Publicacao p : publicacoes){
            publiaux.add(p);
        }
        publicacoes = publiaux;
    }
    
    public void ImprimePublicacoesCSV(){
        
        FileWriter  arq = null;
        try {
            arq = new FileWriter("2-publicacoes.csv");
        } catch (IOException ex) {
            System.out.println("Erro I/O");
        }
        PrintWriter print = null;
        try {
            print = new PrintWriter(arq = new FileWriter("2-publicacoes.csv"));
        } catch (IOException ex) {
        System.out.println("Erro I/O");
        }
        print.println("Ano;Sigla Veículo;Veículo;Qualis;Fator de Impacto;Título;Docentes");
        Iterator<Publicacao> ite = publicacoes.iterator();
        while(ite.hasNext()){
            print.println(ite.next());
        }
        try {
            arq.close();
        } catch (IOException ex) {
            System.out.println("Erro I/O");
        }
        
    }
   
    
    public void LeVeiculos(String arquivo) throws CodigoRepetidoException{
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
                
                if(veiculos.get(sigla) == null){
                
                    if(split[2].trim().equals("P")){//comparacao de strings
                        //Criando Periodico
                        Periodico periodico = new Periodico(sigla, nome, impacto, split[4].trim());
                        veiculos.put(split[0].trim(), periodico);
                        //System.out.println(split[0]+ " " +  split[1] + split[2] + split[3] + split[4]);
                    }else if(split[2].trim().equals("C")) {//comparacao de strings
                        //Criando Conferencia
                        Conferencia conferencia = new Conferencia(sigla, nome, impacto);
                        veiculos.put(split[0].trim(), conferencia);
                    }   
                }else{
                    throw new CodigoRepetidoException("veiculo", sigla);
                }   
            }
            scan.close();
        } catch (FileNotFoundException ex) {
            System.out.println("Erro I/O");
        } catch (ParseException ex) {
            System.out.println("Erro de formatação");
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
                if(veiculos.containsKey(split[1].trim())){
                    Veiculo vaux = veiculos.get(split[1].trim());
                    vaux.AdicionaQualis(ano, split[2].trim());
                }
                //Veiculo vaux = veiculos.get(split[1]);
                //vaux.AdicionaQualis(ano, split[2]);
                //(veiculos.get(split[1])).AdicionaQualis(ano, split[2]);
            }
            
            scan.close();
            
        } catch (FileNotFoundException ex) {
            System.out.println("Erro I/O");
        } catch (ParseException ex) {
            System.out.println("Erro de formatação");
        }
    }
    
    public void MostraVeiculos(){
        for(Map.Entry<String, Veiculo> v: this.veiculos.entrySet()){
            System.out.println(v.getValue());
        }
    }
    
    
public void RecredenciamentoCSV(String anostr){
        int ano = Integer.parseInt(anostr);//Talvez isso enxugasse o codigo onde é utilizado numberformat
        int anopub;//Para armazenar o ano de uma publicacao
        boolean situacao;
        Iterator itedoc = docentes.iterator();
        Iterator itepub;
        double pontuacao;
        Regra regvig = null;
        Docente docaux;
        Publicacao pubaux = null;
        Locale l = new Locale("pt","BR");
        String condicao = null;

        //Encontrando a regra vigente no ano em questão
        for(Regra regra : regras){
            if(regra.ehRegraVigente(ano))
                regvig = regra;
        }
        
        try {
            FileWriter arq = new FileWriter("1-recredenciamento.csv");
            PrintWriter print = new PrintWriter(arq);
            print.println("Docente;Pontuação;Recredenciado?");
            
            
            //Varrer a coleção de docentes calculando sua pontuação
            while(itedoc.hasNext()){
                pontuacao = 0.0;//is "0.0" a emote?
                docaux = (Docente)itedoc.next();
                itepub = docaux.getPubIterator();
                
                //Varrer a lista de publicacçoes somando as pontuaçoes obtidas por aquelas dentro das regras
                while(itepub.hasNext()){
                    pubaux = (Publicacao)itepub.next();
                    anopub = pubaux.getAno();
                    //Verifica se o ano da publicação está no intervalo da regra vigente
                    if(regvig.ehRegraVigenteP(anopub)){
                        
                        pontuacao += pubaux.getVeiculo().getPontuacao(regvig, anopub);
                    }
                }
                situacao = pontuacao >= regvig.getMinimo();
                condicao = docaux.VerificaCondicao(ano, situacao);
                print.println(docaux.getNome() + ";" + String.format("%.1f", pontuacao) + ";" + condicao);
            }
            
            
            arq.close();
            
        } catch (IOException ex) {
            System.out.println("Erro I/O");
        } catch (java.lang.NullPointerException e){
            //Provavelmete em regvig, casp ocorra
            System.out.println("NULL MALDITO");
        }
        
    }
    
    
    
    public void Serializadora(){
        try {
            File arq = new File("recredenciamento.dat");
            ObjectOutputStream serial = new ObjectOutputStream(new FileOutputStream(arq));
            serial.writeObject(this);
            serial.close();
            
        } catch (FileNotFoundException ex) {
             System.out.println("Erro I/O");
        } catch (IOException ex) {
             System.out.println("Erro I/O");
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
            System.out.println("Erro I/O");
        } catch (IOException ex) {
            System.out.println("Erro I/O");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(PPGI.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("Erro de Desserialização");
        return null;
    }
 
    public void estatsiticaCSV(){
        FileWriter  arquivo = null;
        PrintWriter print = null;
        //Contadores para numero de artigos em cada qualis
        int A1=0,A2=0,B1=0,B2=0,B3=0,B4=0,B5=0,C=0;
        //contadores para numero de artigos por docentes
        double autoresA1=0,autoresA2=0,autoresB1=0,autoresB2=0,autoresB3=0,autoresB4=0,autoresB5=0,autoresC=0;
        try {
            int i=0;
            arquivo = new FileWriter("3-estatisticas.csv");
            print = new PrintWriter(arquivo);
            for (Publicacao p : publicacoes) {
                String qualis = p.getVeiculo().getQualis(p.getAno());
                if(qualis.equals("A1")){
                    A1++;
                    autoresA1 = autoresA1 + (double)1/p.getNumeroAutores();
                }else if(qualis.equals("A2")){
                    A2++;
                    autoresA2 = autoresA2 + (double)1/p.getNumeroAutores();
                }else if(qualis.equals("B1")){
                    B1++;
                    autoresB1 = autoresB1 + (double)1/p.getNumeroAutores();
                }else if(qualis.equals("B2")){
                    B2++;
                    autoresB2 = autoresB2 + (double)1/p.getNumeroAutores();
                }else if(qualis.equals("B3")){
                    B3++;
                    autoresB3 = autoresB3 + (double)1/p.getNumeroAutores();
                }else if(qualis.equals("B4")){
                    B4++;
                    autoresB4 = autoresB4 + (double)1/p.getNumeroAutores();
                }else if(qualis.equals("B5")){
                    B5++;
                    autoresB5 = autoresB5 + (double)1/p.getNumeroAutores();
                }else if(qualis.equals("C")){
                    C++;
                    autoresC = autoresC + (double)1/p.getNumeroAutores();
                }              
            }
            print.println("Qualis;Qtd. Artigos;Média Artigos / Docente");
            print.println("A1;" + A1 +";" + String.format("%.2f",autoresA1));
            print.println("A2;" + A2 +";" + String.format("%.2f",autoresA2));
            print.println("B1;" + B1 +";" + String.format("%.2f",autoresB1));
            print.println("B2;" + B2 +";" + String.format("%.2f",autoresB2));
            print.println("B3;" + B3 +";" + String.format("%.2f",autoresB3));
            print.println("B4;" + B4 +";" + String.format("%.2f",autoresB4));
            print.println("B5;" + B5 +";" + String.format("%.2f",autoresB5));
            print.println("C;" + C  +";" + String.format("%.2f",autoresC)); 
            
            arquivo.close();
        } catch (IOException ex) {
            System.out.println("Erro I/O");
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
