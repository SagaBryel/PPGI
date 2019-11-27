package TPPGI;

import java.util.Scanner;

/**
 *
 * @author Gabriel Paschoal
 */
public class Main {
    public static void main(String[] args){
        String ano = null;
        System.out.println("---INICIO DE TUDO---");
        PPGI projeto = new PPGI();
        Scanner entrada = new Scanner(System.in);
        for(int i=0; i<args.length;i++){
            if(args[i].equals("-d")){
                projeto.LeDocentes(args[i+1]);
            }
            else if(args[i].equals("-v")){
                projeto.leVeiculos(args[i+1]);
            }
            else if(args[i].equals("-p")){
                projeto.LePublicacoes(args[i+1]);
            }
            else if(args[i].equals("-q")){
                projeto.LeQualis(args[i+1]);
            }
            else if(args[i].equals("-r")){
                projeto.LeRegras(args[i+1]);
            }
            else if(args[i].equals("-a")){
                ano = args[i+1];
            }
        }    
        //projeto.MostraDocentes();
        //projeto.MostraRegras();
        //projeto.MostraVeiculos();
        projeto.Serializadora();
        //projeto.MostraPublicacoes();
        projeto.Recredenciamento(ano);
        PPGI desserializada = new PPGI();
        //O passo abaixo ta muito errado pra um caralho, coisa de animal. Comente caso for tentar executar
        desserializada = desserializada.Desserializadora("recredenciamento.dat");
    }
    
}
