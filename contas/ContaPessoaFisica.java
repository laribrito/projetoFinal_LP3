package contas;

public class ContaPessoaFisica extends Conta {
    static final int QTD_SAQUES = 4;
    int saquesRealizados;
    
    public ContaPessoaFisica(String senha){
        super(senha);
        saquesRealizados = 0;
    }

    /** Para uma pessoa física há um limite de saques, então isso é verificado para 
        permitir a operação. Retorna um booleano e exibe uma mensagem de erro, se for o caso */
    protected boolean podeSacar(float x){
        if (super.podeSacar(x) && saquesRealizados<QTD_SAQUES){
            saquesRealizados++;
            return true;
        }
        System.out.println("Você já chegou no limite de saques");
        return false;
    }

    public String toString(){
        String txt;
        txt  = super.toString();
        txt += " - Limite de Saques Total: " + QTD_SAQUES + "\n";
        txt += " - Quantidade de Saques Disponíveis: " + (QTD_SAQUES - saquesRealizados) + "\n";

        return txt;
    }
}
