package contas;
import add.AuxLib;

public abstract class Conta {
    // Toda conta terá:
    //  Uma senha para realizar saques que deve ser cadastrada na inicialização do objeto
    //  Um número que a identifique
    //  Saldo inicial aleatório

    // atributos de classe
    static final private int TAMANHO_SENHA = 4;
    static private int numero_livre = 1011;

    // atributos de instância
    private int numeroConta;
    protected float saldo;
    private String senha;

    // métodos de validação -----------------------------------------------------
    
    /** STATIC - Recebe um valor float e exibe uma mensagem de erro se o valor for negativo, além de retornar um booleano */
    private static boolean valorPositivo(float x){
        if(x<0){
            System.out.println("Valor informado não pode ser negativo");
            return false;
        }
        return true;
    }

    /** STATIC - Verifica se a string informada é composta apenas por números e tem o tamanho de uma senha. Retorna um booleano */
    private static boolean ehSenhaValida(String inputSenha){
        return AuxLib.ehApenasNumero(inputSenha) && inputSenha.length()==Conta.TAMANHO_SENHA;
    }

    /** PROTECTED - Recebe um valor float e verifica se existe saldo suficiente para sacar esse valor e exibe uma mensagem de erro, além de retorna um booleano */
    protected boolean podeSacar(float x){
        if(valorPositivo(x) && x > saldo){
            System.out.println("Saldo insuficiente");
            return false;
        }
        return true;
    }

    /** Verifica se a string informada corresponde à senha cadastrada pelo usuário. Retorna um booleano */
    private boolean ehSenhaCorreta(String inputSenha){
        return inputSenha == this.senha;
    }

    /** Verifica se a string informada corresponde à senha do usuário e exibe uma mensagem de erro se não for a correta. Retorna um booleano */
    protected boolean checaSenha(String inputSenha){
        if(!ehSenhaCorreta(senha)) {
            System.out.println("A senha não está correta");
            return false;
        }
        return true;
    }

    // Construtor ----------------------------------------------------------------------------

    /** O objeto só deve ser criado se a senha informada for válida, contendo somente número e com tamanho como especificado na classe */
    protected Conta(String senha){
        if(ehSenhaValida(senha)){
            this.senha = senha;
            numeroConta = numero_livre++;
            saldo = AuxLib.novoInteiro_p(10, 8, 1000000, 2);
        } else {
            System.out.println("A senha não é válida! Digite uma senha de tamanho "+ TAMANHO_SENHA +" somente com números");
        }
    }

    // Ações públicas -------------------------------------------------------------------------

    /** Para poder sacar o responsável deve ter saldo e digitar a senha correta. Retorna um booleano */
    public boolean sacar(float valor, String senha){
        if(checaSenha(senha) && podeSacar(valor)){
            this.saldo -= valor;
            return true;
        }
        return false;
    }

    /** Basta informa um valor positivo que este será acrescentado no saldo da conta. Retorna um booleano */
    public boolean depositar(float valor){
        if(valorPositivo(valor)){
            this.saldo += valor;
            return true;
        }

        return false;
    }

    static public String geraSenha(){
        String senha="";

        // gera uma senha
        for(int x=1; x<=TAMANHO_SENHA; x++){
            senha+=AuxLib.novoInteiroStr(9);
        }

        return senha;
    }

    public int getNumeroConta() {
        return numeroConta;
    }

    @Override
    public String toString(){
        String txt;
        txt  = "\n\n Informações da Conta: \n";
        txt += " - Número: " + numeroConta + " \n";
        txt += " - Saldo: R$ " + AuxLib.formatarFloat(saldo) + " \n";

        return txt;
    }
}