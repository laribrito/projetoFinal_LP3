package jogadores;

import add.Data;
import add.AuxLib;
import contas.Conta;
import contas.ContaPessoaFisica;

public class JogadorPF extends Jogador {
    private String cpf;
    private ContaPessoaFisica conta;
    static int TAMANHO_DOCUMENTO = 11; //cpf

    static public String geraCPF(){
        String cpf="";

        // gera um cpf com 11 dígitos
        for(int x=1; x<=TAMANHO_DOCUMENTO; x++){
            cpf+=AuxLib.novoInteiroStr(9);
        }

        return cpf;
    }

    private boolean validaCPF(String cpf){
        if(cpf.length()!=TAMANHO_DOCUMENTO || !AuxLib.ehApenasNumero(cpf)){
            System.out.println("CPF não é válido. Precisa ter "+ TAMANHO_DOCUMENTO +" números");
            return false;
        } else {
            return true;
        }
    }

    public JogadorPF(String nome, Data nasc, String localNasc, ContaPessoaFisica conta, String cpf){
        super(nome, nasc, localNasc);
        if(validaConta(conta) && validaCPF(cpf)){
            this.conta = conta;
            this.cpf = cpf;
        } else {
            System.out.println("Não foi possível criar o jogador pessoa física");
        }
    }

    @Override
    public Conta getConta() {
        return conta;
    }
}
