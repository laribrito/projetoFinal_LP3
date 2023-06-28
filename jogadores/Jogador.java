package jogadores;

import add.Data;
import contas.Conta;
import add.AuxLib;

abstract public class Jogador{
    //atributos de classe
    static private int QTD_OPC_MAX=4;
    
    //atributos de instância
    private String nome;
    private Data dataNasc;
    private String localNasc;
    private String biografia;

    // métodos de validação -----------------------------------------------------

    /** STATIC - Verifica se a data enviada é válida e retorna um booleano */
    static private boolean validaNascimento(Data nascimento){
        return nascimento.getValido();
    }

    /** STATIC - Verifica se o local de nascimento não é uma string vazia e retorna um booleano */
    static private boolean validaLocalNascimento(String local){
        return !local.isBlank();
    }

    /** STATIC - Verifica se o local de nascimento não é uma string vazia e retorna um booleano */
    static private boolean validaNome(String nome){
        return !nome.isBlank();
    }

    /** STATIC - Verifica se a opção escolhida é um número entre o range de 1 até a quantidade máxima de opções que o jogador terá */
    static private boolean validaOpc(int opc){
        return (opc<=QTD_OPC_MAX && opc >0);
    }

    /** Verifica se o a conta é válida pelo seu número. Se o número da conta for maior que 1000
        significa que a conta tem um valor com mais de 4 dígitos e isso indica que a conta foi criada
        com sucesso */
    static protected boolean validaConta(Conta conta){
        return (conta.getNumeroConta()>1000);
    }
    
    // Construtor ----------------------------------------------------------------------------
    
    protected Jogador(String nome, Data nascimento, String local){
        if(validaNome(nome) && validaLocalNascimento(local) && validaLocalNascimento(local)){
            this.nome = nome;
            dataNasc = nascimento;
            localNasc = local;
            biografia = "";
        } else {
            System.out.println("Não foi possível criar o jogador");
        }
    }

    // Ações públicas -------------------------------------------------------------------------

    /** Retorna a escolha do jogador. Se o número escolhido for válido, retorna o número, senão retorna 0 */
    public int escolheUmaAcao(){
        int opc;
        System.out.println(" > ");
        opc = AuxLib.input.nextInt();

        if(validaOpc(opc)) return opc;
        else return 0;
    }

    /** Exibe a biografia do personagem */
    public void exibirBiografia(){
        System.out.println("Depois de altos e baixos, felicidades e tristezas, essa é a sua história:\n");
        System.out.println(biografia);
    }

    // Métodos abstratos ------------------------------------------------------------------------------

    abstract public Conta getConta();

    public String getNome() {
        return nome;
    }
}