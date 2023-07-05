package jogadores;

import add.Data;
import add.AuxLib;
import contas.ContaPessoaFisica;

public class JogadorPF extends Jogador {
    //atributos de instância
    static int TAMANHO_DOCUMENTO = 11; //cpf
    //atributos de classe
    private String cpf;
    private ContaPessoaFisica conta;
    private String esporteEscolhido;
    private String formacaoTecnica;
    private String formacaoSuperior;

    // Métodos de validação --------------------------------------------------------

    /** Retorna um cpf válido em string */
    static public String geraCPF(){
        String cpf="";
        // gera um cpf com 11 dígitos
        for(int x=1; x<=TAMANHO_DOCUMENTO; x++){
            cpf+=AuxLib.novoInteiroStr(9);
        }
        return cpf;
    }

    /** Recebe uma string e verifica ela é um cpf válido. Retorna um booleano */
    public static boolean validaCPF(String cpf){
        if(cpf.length()!=TAMANHO_DOCUMENTO || !AuxLib.ehApenasNumero(cpf)){
            System.out.println("\nCPF não é válido. Precisa ter "+ TAMANHO_DOCUMENTO +" números");
            return false;
        } else {
            return true;
        }
    }

    /** Retorna uma string com o cpj da instância formatada com pontos e traço */
    private String toStringCPF() {
        String cpfFormatado;

        // Adiciona os pontos e traços de formatação
        cpfFormatado=cpf.substring(0, 3);
        cpfFormatado+=".";
        cpfFormatado+=cpf.substring(3, 6);
        cpfFormatado+=".";
        cpfFormatado+=cpf.substring(6, 9);
        cpfFormatado+="-";
        cpfFormatado+=cpf.substring(9);

        return cpfFormatado;
    }

    // Construtor --------------------------------------------------------

    public JogadorPF(String nome, Data nasc, String localNasc, ContaPessoaFisica conta, String cpf){
        super(nome, nasc, localNasc);
        if(validaConta(conta) && validaCPF(cpf)){
            this.conta = conta;
            this.cpf = cpf;
        } else {
            System.out.println("Não foi possível criar o jogador pessoa física");
        }
    }

    // Métodos públicos --------------------------------------------------------

    public void setEsporteEscolhido(String esporteEscolhido) {
        this.esporteEscolhido = esporteEscolhido;
    }

    public void setFormacaoTecnica(String formacaoTecnica) {
        this.formacaoTecnica = formacaoTecnica;
    }

    public void setFormacaoSuperior(String formacaoSuperior) {
        this.formacaoSuperior = formacaoSuperior;
    }

    public String getEsporteEscolhido() {
        return esporteEscolhido;
    }

    public String getFormacaoTecnica() {
        return formacaoTecnica;
    }

    public String getFormacaoSuperior() {
        return formacaoSuperior;
    }

    public void printInfoConta(){
        System.out.println(conta);
    }

    @Override
    public boolean sacar(float valor, String senha) {
        return conta.sacar(valor, senha);
    }

    @Override
    public boolean depositar(float valor) {
        return conta.depositar(valor);
    }

    @Override
    public String toString(){
        String txt;

        txt = super.toString();
        txt+= "Seu CPF é: " + toStringCPF() +"\n";        
        txt+= conta.toString();

        return txt;
    }
}
