package add;

import java.util.Scanner;

public class AuxLib {
    static public Scanner input;
    /** Recebe um número float e o retorna como string formatada com duas casas decimais */
    static public String formatarFloat(float numero){
        return String.format("%.2f", numero);
    }
}
