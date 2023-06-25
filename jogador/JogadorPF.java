import add.Data;
import contas.Conta;
import contas.ContaPessoaFisica;

public class JogadorPF extends Jogador {
    private String cpf;
    private ContaPessoaFisica conta;

    public JogadorPF(String nome, Data nasc, String localNasc, ContaPessoaFisica conta){
        super(nome, nasc, localNasc);
        if(validaConta(conta)){
            this.conta = conta;
        } else {
            System.out.println("Não foi possível criar o jogador pessoa física");
        }
    }

    @Override
    public Conta getConta() {
        return conta;
    }
}
