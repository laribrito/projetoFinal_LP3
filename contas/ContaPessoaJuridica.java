package contas;
import add.AuxLib;

public class ContaPessoaJuridica extends Conta {
    private int limiteCredito;
    private int creditoUtilizado;

    // métodos de validação -----------------------------------------------------

    /** Uma pessoa jurídica terá um limite de crédito que pode ou não ser utilizado na hora do saque */
    @Override
    protected boolean podeSacar(float x){
        String opc;
        // se não retornar true, o saldo é menor que o valor de saque
        if(super.podeSacar(x)) return true;
        //calcula o excedente
        float excedente = x-saldo;
       
        if(creditoUtilizado + excedente <= limiteCredito){
            System.out.println("Deseja utilizar seu limite de crédito? (S/n) ");
            opc = AuxLib.input.next();

            if(opc.toLowerCase().equals("s")){
                return true;
            }
        }
        return false;
    }

    // Construtor ----------------------------------------------------------------------------
    
    /**  Para a conta jurídica há um limite de crédito disponível, mas como isso varia de acordo 
        com a reputação da empresa, esse valor é aleatório, de mínimo R$ 1000,00 */
    public ContaPessoaJuridica(String senha){
        super(senha);
        limiteCredito = (int) AuxLib.novoInteiro_nl(9900) + 1000;
        creditoUtilizado = 0;
    }

    public float getLimiteCredito() {
        return limiteCredito;
    }

    public float getLimiteDisponivel(){
        return limiteCredito-creditoUtilizado;
    }

    @Override
    public boolean sacar(float valor, String senha){
        if(checaSenha(senha) && podeSacar(valor)){
            this.saldo -= valor;
            //se o saldo ficou negativo precisa atualiza o crédito utilizado
            if(saldo<0){
                creditoUtilizado-=saldo;
                saldo = 0;
            }
            return true;
        }
        return false;
    }

    @Override
    public String toString(){
        String txt;
        txt  = super.toString();
        txt += " - Limite Crédito Total: R$ " + AuxLib.formatarFloat(limiteCredito) + "\n"; 
        txt += " - Limite Crédito Disponível: R$ " + AuxLib.formatarFloat(limiteCredito-creditoUtilizado) + "\n"; 

        return txt;
    }
}
