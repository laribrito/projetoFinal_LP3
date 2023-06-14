package contas;
import java.util.Random;
import add.AuxLib;

public class ContaPessoaJuridica extends Conta {
    static Random random = new Random();
    int limiteCredito;
    int creditoUtilizado;
    
    /**  Para a conta jurídica há um limite de crédito disponível, mas como isso varia de acordo 
        com a reputação da empresa, esse valor é aleatório, de mínimo R$ 1000,00 */
    public ContaPessoaJuridica(String senha){
        super(senha);
        limiteCredito = random.nextInt(10000) + 1000;
        creditoUtilizado = 0;
    }

    public String toString(){
        String txt;
        txt  = super.toString();
        txt += " - Limite Crédito Total: R$ " + AuxLib.formatarFloat(limiteCredito) + "\n"; 
        txt += " - Limite Crédito Disponível: R$ " + AuxLib.formatarFloat(limiteCredito-creditoUtilizado) + "\n"; 

        return txt;
    }
}
