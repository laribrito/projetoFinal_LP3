package add;

import java.util.Random;
import java.util.Scanner;

public class AuxLib {
    static public Scanner input  = new Scanner(System.in);

    static private Random gerador = new Random();

    /** STATIC - Retorna um número entre 0 e o valor absoluto de max como String */
    static public String novoInteiroStr(int max){
        return String.valueOf(gerador.nextInt(Math.abs(max+1)));
    }

    /** STATIC - Retorna um número entre 0 e o valor absoluto de max como int */
    static public int novoInteiro(int max){
        return gerador.nextInt(Math.abs(max+1));
    }

    /** STATIC - Retorna um número entre 0 e um limite, personalizado com probabilidade 
        Essa função recebe 4 valores:
            max1 - limite do intervalo1
            chances1 - valor numérico para as chances do número pertencer ao intervalo1
            max2 - limite do intervalo2
            chances2 - valor numérico para as chances do número pertencer ao intervalo2
        Retorna um valor seguindo as regras acima. O número terá (chanches1/(chances1+chances2)) 
        chances de pertencer ao intervalo1, e vice-versa
    */
    static public int novoInteiro_p(int max1, int chances1, int max2, int chances2){
        int ret, valorMenor = AuxLib.novoInteiro(max1), valorMaior = AuxLib.novoInteiro(max2);

        if (AuxLib.novoInteiro_nl(chances1+chances2)<=chances1) ret = valorMenor;
        else ret = valorMaior;

        return ret;
    }

    /** STATIC - Retorna um número não nulo, entre 1 e o valor absoluto de max como int */
    static public int novoInteiro_nl(int max){
        return gerador.nextInt(Math.abs(max))+1;
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
