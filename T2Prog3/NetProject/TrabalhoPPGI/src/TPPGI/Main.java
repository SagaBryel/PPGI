package TPPGI;

import java.util.Scanner;

/**
 *
 * @author Gabriel Paschoal
 */
public class Main {
    public static void main(String[] args){
        System.out.println("---INICIO DE TUDO---");
        PPGI projeto = new PPGI();
        Scanner entrada = new Scanner(System.in);
        projeto.LeDocentes(entrada.nextLine());
        projeto.LeRegras(entrada.nextLine());
        projeto.MostraDocentes();
        projeto.MostraRegras();
    }
    
}
