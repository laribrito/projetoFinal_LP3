import add.Data;
import add.AuxLib;
import contas.Conta;
import contas.ContaPessoaJuridica;

public class JogadorPJ extends Jogador {
    private String cnpj;
    private ContaPessoaJuridica conta;
    static int TAMANHO_DOCUMENTO = 14; //cnpj

    static public String geracnpj(){
        String cnpj="";

        // gera um cnpj com 14 dígitos
        // xx xxx xxx 0001 xx
        for(int x=1; x<=TAMANHO_DOCUMENTO; x++){
            if(x>=9 && x < 12) cnpj+="0";
            else if (x==12) cnpj+="1";
            else cnpj+=AuxLib.novoInteiro();
        }

        return cnpj;
    }

    private boolean validaCNPJ(String cnpj){
        if(cnpj.length()!=TAMANHO_DOCUMENTO || !AuxLib.ehApenasNumero(cnpj)){
            System.out.println("CNPJ não é válido. Precisa ter "+ TAMANHO_DOCUMENTO +" números");
            return false;
        } else {
            return true;
        }
    }

    public JogadorPJ(String nome, Data nasc, String localNasc, ContaPessoaJuridica conta, String cnpj){
        super(nome, nasc, localNasc);
        if(validaConta(conta) && validaCNPJ(cnpj)){
            this.conta = conta;
            this.cnpj = cnpj;
        } else {
            System.out.println("Não foi possível criar o jogador pessoa jurídica");
        }
    }

    @Override
    public Conta getConta() {
        return conta;
    }
}
