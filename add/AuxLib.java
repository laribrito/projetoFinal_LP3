package add;
import java.text.DecimalFormat;

public class AuxLib {
    /** Recebe um número float e o retorna como string formatada com duas casas decimais */
    static public String formatarFloat(float numero){
        String retorno = "";
        DecimalFormat formatter = new DecimalFormat("#.00");
        try{
          retorno = formatter.format(numero);
        }catch(Exception ex){
          System.err.println("Erro ao formatar numero: " + ex);
        }
        return retorno;
    }
}
