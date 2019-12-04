package TPPGI;
import TPPGI.ExceptionsPPGi.VeiculoIndefinidoException;
import TPPGI.ExceptionsPPGi.QualisDesconhecidaException;
import TPPGI.ExceptionsPPGi.DocenteIndefinidoException;
import TPPGI.ExceptionsPPGi.CodigoRepetidoException;
import TPPGI.ExceptionsPPGi.SiglaIndefinidaException;
import java.util.Scanner;

/**
 *
 * @author Gabriel Paschoal
 * @author Hiuri Liberato
 */
public class Main {
    public static void main(String[] args){
        PPGI projeto = new PPGI();
        Scanner entrada = new Scanner(System.in);
        
        String docentes = null;
        String veiculos = null;
        String publicacoes = null;
        String qualis = null;
        String regras = null;
        String ano = null;        
        
        //Controle de entrada  
        boolean serializa = false;//verifica as opcoes de execucao
        boolean desserializa = false;
        boolean entradaCSV = true;
        boolean saidaCSV = true;
        
        for(int i=0; i<args.length;i++){
            if(args[i].equals("--write-only")){
                desserializa = true;
                entradaCSV = false;
                //carregar os objetos serializados
                //escrever os relatorios em csv sem ler entrada csv
            } 
            else if(args[i].equals("--read-only")){
                serializa = true;
                saidaCSV = false;
                //ler o csv
                //serializar sem gerar os relatorios
            }
            else if(args[i].equals("-d")){
                docentes = args[i+1];
            }
            else if(args[i].equals("-v")){
                veiculos = args[i+1];
            }
            else if(args[i].equals("-p")){
                publicacoes = args[i+1];
            }
            else if(args[i].equals("-q")){
                qualis = args[i+1];
            }
            else if(args[i].equals("-r")){
                regras = args[i+1];
            }
            else if(args[i].equals("-a")){
                ano = args[i+1];
            }
                
        }    
        
        try{
        //Garantir que a os metodos ja tenham seus parametros inicializados quando forem chamados
            if(entradaCSV == true){
                projeto.LeDocentes(docentes);
                projeto.LeVeiculos(veiculos);
                projeto.LeQualis(qualis);
                projeto.LePublicacoes(publicacoes);
                projeto.LeRegras(regras);
                projeto.setAno(ano);
            }
            if(serializa == true){
                projeto.Serializadora();
            }if(desserializa == true){
                //ler o arquivo serializado
                projeto = projeto.Desserializadora("recredenciamento.dat");
            }
            projeto.OrdenaPublicacoes();
            
            if(saidaCSV == true){
                projeto.estatsiticaCSV();
                projeto.ImprimePublicacoesCSV();
                projeto.RecredenciamentoCSV(projeto.getAno());
            }
      
        }catch(NullPointerException e){
            //caso não seja especificado os arquivos de entrada devidamente
            System.out.println("Erro I/O");
        }catch(CodigoRepetidoException e){
            System.out.println(e.getMessage());
        }catch(DocenteIndefinidoException e){
            System.out.println(e.getMessage());
        }catch(VeiculoIndefinidoException e){
            System.out.println(e.getMessage());
        }catch(QualisDesconhecidaException e) {
            System.out.println(e.getMessage());
        }catch(SiglaIndefinidaException e) {
            System.out.println(e.getMessage());
        }
        
    }
}
