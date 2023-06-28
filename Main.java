import add.AuxLib;
import add.Data;
import contas.Conta;
import contas.ContaPessoaFisica;
import contas.ContaPessoaJuridica;
import jogadores.Jogador;
import jogadores.JogadorPF;
import jogadores.JogadorPJ;

public class Main {
    public static final String BG_RED = "\u001B[41m";
    public static final String BOLD = "\u001B[1m";
    public static final String CLEAR = "\u001B[0m";
    public static final String UNDERLINE = "\u001B[4m";

    public static final String[] locaisDeNascimento= {"Brasília Amarela", "Itabuna", "Ilhéus", "Alí na esquina", "Distrito 12", "Abnegação", "Coreia"};
    public static final String[] nomes= {"Grey", "Catnip", "Yuri", "George", "Tobias", "Judas"};

    public static void main(String[] args) {
        // intro();
        // instrucoes();
        inicio();
    }

    public static void intro(){
        limpaTela();
        System.out.println("\tOlá! Este é o Jogo da Vida!!!");
        System.out.println("\tO carrosel nunca para de girar. Escolha com cuidado ");
        System.out.println("\te faça história!");
        System.out.println("\n\n\n");
        aguarde(7);
        limpaTela();
    }

    public static void instrucoes(){
        limpaTela();
        System.out.println("\tAqui você escolherá seu ponto de partida");
        System.out.println("\tpara trilhar como bem entender, seja como");
        System.out.println("\tuma persona ou uma empresa. E que a sorte");
        System.out.println("\testeja sempre ao seu favor!");
        System.out.println("\n\n\n");
        aguarde(11);
        limpaTela();
    }
    public static int getOpc(int max){
        int opc;
        while(true){
            System.out.print("> ");
            opc = AuxLib.input.nextInt();

            if (!ehOpcValido(opc, max)){
                System.out.println("Esse valor não é válido, tente novamente");
            } else break;
        }
        return opc;
    }

    public static boolean ehOpcValido(int valor, int max){
        return valor>=1 && valor <=max;
    }

    public static void inicio(){
        int opc;
        limpaTela();
        System.out.println("Inicialmente "+estiloTXT1("você pode") +" escolher entre "+ estiloTXT1("ser uma pessoa") +",");
        System.out.println("como eu que escrevo esses textos, ou "+ estiloTXT1("ser uma empresa") +",");
        System.out.println("como a TecnoJr, empresa de sua região. Pode ainda");
        System.out.println(estiloTXT1("determinar o dia e local do seu nascimento") +", e chegará");
        System.out.println("ao mundo com uma conta bancária (dessa parte deixemos");
        System.out.println("que o destino faça as honras.. 'risos').\n");
        System.out.println(estiloTXT2("Se preferir que o destino cuide de tudo, pode falar")+":\n");

        System.out.println(estiloTXTOpc("[1] - Quero criar meu personagem"));
        System.out.println(estiloTXTOpc("[2] - Faça o trabalho por mim"));

        opc = getOpc(2);  

        if(opc==1) criaPersonagem(false);
        else criaPersonagem(true);

    } 

    public static Jogador criaPersonagem(boolean ehAleatorio){
        String nome, localNasc, documento, senhaConta;
        Data nasc;
        
        //aleatorio
            int opcNome, dia, mes, ano, opcLocalNasc, opcTipoPessoa;
            
            //nome
            opcNome = AuxLib.novoInteiro(nomes.length);
            nome = nomes[opcNome];

            //uma data de nascimento
            dia = AuxLib.novoInteiro(28);
            mes = AuxLib.novoInteiro(12);
            ano = AuxLib.novoInteiro(2023)-AuxLib.novoInteiro(1000);
            nasc = new Data(dia, mes, ano);

            //um local de nascimento
            opcLocalNasc = AuxLib.novoInteiro(locaisDeNascimento.length);
            localNasc = locaisDeNascimento[opcLocalNasc];

            //pessoa ou empresa, conta e tipo jogador
            opcTipoPessoa = AuxLib.novoInteiro(2);
            if(opcTipoPessoa==1){
                senhaConta = Conta.geraSenha();
                ContaPessoaFisica conta = new ContaPessoaFisica(senhaConta);
                documento = JogadorPF.geraCPF();
                JogadorPF jogador = new JogadorPF(nome, nasc, localNasc, conta, documento);
                return jogador;
            } else {
                senhaConta = Conta.geraSenha();
                ContaPessoaJuridica conta = new ContaPessoaJuridica(senhaConta);
                documento = JogadorPF.geraCPF();
                JogadorPJ jogador = new JogadorPJ(nome, nasc, localNasc, conta, documento);
                return jogador;
            }

        
    }


    public static String estiloTXT1(String txt){
        return BG_RED+BOLD + txt + CLEAR;
    }

    public static String estiloTXT2(String txt){
        return UNDERLINE+BOLD + txt + CLEAR;
    }

    public static String estiloTXTOpc(String txt){
        return BOLD + txt.substring(0, 3) + CLEAR + txt.substring(3, txt.length());
    }

    public static void limpaTela(){
        for (short i = 1; i<100; i++) System.out.println("\n");
    }

    public static void aguarde(int segundos){
        try {
            Thread.sleep(segundos*1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
