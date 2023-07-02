package jogadores;

import add.Data;
import contas.Conta;
import add.AuxLib;

abstract public class Jogador{    
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

    /** Exibe a biografia do personagem */
    public void exibirBiografia(){
        AuxLib.limpaTela();
        System.out.println(AuxLib.estiloTXT4("Depois de altos e baixos, felicidades e tristezas, essa é a sua história:")+"\n");
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

    public String getDataNascStr() {
        return dataNasc.toString();
    }

    @Override
    public String toString(){
        String txt="";

        txt+="Seu nome é "+nome+"\n";
        txt+="Nasceu em "+ localNasc +", "+dataNasc.toString()+"\n";

        return txt;
    }
}