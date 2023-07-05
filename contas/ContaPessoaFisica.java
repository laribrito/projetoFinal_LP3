package contas;

public class ContaPessoaFisica extends Conta {
    // atributos de classe
    static final int QTD_SAQUES = 4;

    //atributos de instância
    int saquesRealizados;
    
    // métodos de validação -----------------------------------------------------
    
    /** 
     Para uma pessoa física há um limite de saques, então isso é verificado para 
     permitir a operação. Retorna um booleano e exibe uma mensagem de erro, se for o caso 
    */
    @Override
    protected boolean podeSacar(float x){
        boolean consegueSacar = saquesRealizados<QTD_SAQUES;
        if (super.podeSacar(x) && consegueSacar){
            saquesRealizados++;
            return true;
        } else if(!consegueSacar){
            System.out.println("Você já chegou no limite de saques");
        }
        return false;
    }

    // Construtor ----------------------------------------------------------------------------

    public ContaPessoaFisica(String senha){
        super(senha);
        saquesRealizados = 0;
    }

    // Ações públicas -------------------------------------------------------------------------

    @Override
    public String toString(){
        String txt;
        txt  = super.toString();
        txt += " - Limite de Saques Total: " + QTD_SAQUES + "\n";
        txt += " - Quantidade de Saques Disponíveis: " + (QTD_SAQUES - saquesRealizados) + "\n";

        return txt;
    }
}
