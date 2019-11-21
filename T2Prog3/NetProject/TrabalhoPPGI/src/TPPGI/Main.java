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
        projeto.LeDocentes("docentes.csv");
        projeto.LeRegras("regras.csv");
        projeto.leVeiculos("veiculos.csv");
        projeto.MostraDocentes();
        projeto.MostraRegras();
        projeto.MostraVeiculos();
    }
    
}
