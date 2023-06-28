package add;

import java.util.Random;
import java.util.Scanner;

public class AuxLib {
    static public Scanner input  = new Scanner(System.in);

    static private Random gerador = new Random();

    /** STATIC - Retorna um número entre 0 e o valor absoluto de max */
    static public String novoInteiro(int max){
        return String.valueOf(gerador.nextInt(Math.abs(max+1)));
    }

    /** STATIC - Verifica se a string informada é composta apenas por número e retorna um booleano */
    static public boolean ehApenasNumero(String input){
        return input.matches("\\d+");
    }

    /** STATIC - Recebe um número float e o retorna como string formatada com duas casas decimais */
    static public String formatarFloat(float numero){
        return String.format("%.2f", numero);
    }
}
