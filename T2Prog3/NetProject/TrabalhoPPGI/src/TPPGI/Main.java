package TPPGI;

import java.util.Scanner;

/**
 *
 * @author Gabriel Paschoal
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
        
        
        for(int i=0; i<args.length;i++){
            if(args[i].equals("--write-only")){
                desserializa = true;
                entradaCSV = false;
                //carregar os objetos serializados
                //escrever os relatorios em csv sem ler entrada csv
            } 
            else if(args[i].equals("--read-only")){
                serializa = true;
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
        
        
        //Garantir que a os metodos ja tenham seus pre requisitos quando forem chamados
        if(entradaCSV == true){
            projeto.LeDocentes(docentes);
            projeto.LeVeiculos(veiculos);
            projeto.LeQualis(qualis);
            projeto.LePublicacoes(publicacoes);
            projeto.LeRegras(regras);
        }
        if(serializa = true){
            projeto.Serializadora();
        }else if(desserializa = true){
            PPGI desserializada = new PPGI();
            //O passo abaixo ta muito errado pra um caralho, coisa de animal. Comente caso for tentar executar
            desserializada = desserializada.Desserializadora("recredenciamento.dat");
        }
        
        //projeto.MostraDocentes();
        //projeto.MostraRegras();
        //projeto.MostraVeiculos();
        //projeto.MostraPublicacoes();
        projeto.MostraPublicacoes();
        projeto.estatsiticaCSV();
        projeto.ImprimePublicacoesCSV();
        projeto.Recredenciamento(ano);
        //O passo abaixo ta muito errado pra um caralho, coisa de animal. Comente caso for tentar executar
    }
    
}
