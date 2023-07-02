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
    private boolean estaVivo;

    // métodos de validação -----------------------------------------------------

    /** STATIC - Verifica se a data enviada é válida e retorna um booleano */
    static public boolean validaNascimento(Data nascimento){
        return nascimento.getValido();
    }

    /** STATIC - Verifica se o local de nascimento não é uma string vazia e retorna um booleano */
    static public boolean validaLocalNascimento(String local){
        return !local.isBlank();
    }

    /** STATIC - Verifica se o local de nascimento não é uma string vazia e retorna um booleano */
    static public boolean validaNome(String nome){
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
            estaVivo = true;
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
        AuxLib.limpaTela();
        System.out.println("Depois de altos e baixos, felicidades e tristezas, essa é a sua história:\n");
        String bio = AuxLib.formatText(biografia, 60);
        System.out.println(bio);
    }

    // Métodos abstratos ------------------------------------------------------------------------------

    abstract public float getSaldo();

    abstract public boolean sacar(float valor, String senha);

    abstract public boolean depositar(float valor);

    public void morreu(){
        estaVivo = false;
    }

    public boolean estaVivo(){
        return estaVivo;
    }

    public void adicionaNaBio(String novaFrase){
        biografia+=novaFrase;
    }

    public String getNome() {
        return nome;
    }

    @Override
    public String toString(){
        String txt="";

        txt+="Seu nome é "+nome+"\n";
        txt+="Nasceu em "+ localNasc +", "+dataNasc.toString()+"\n";

        return txt;
    }
}