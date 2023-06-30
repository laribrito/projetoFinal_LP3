import add.AuxLib;
import add.Data;
import contas.Conta;
import contas.ContaPessoaFisica;
import contas.ContaPessoaJuridica;
import jogadores.Jogador;
import jogadores.JogadorPF;
import jogadores.JogadorPJ;

public class Main {
    public static boolean DEBUG = true;
    public static final int PF = 1;
    public static final int PJ = 2;

    public static final String BG_RED = "\u001B[41m";
    public static final String BOLD = "\u001B[1m";
    public static final String CLEAR = "\u001B[0m";
    public static final String UNDERLINE = "\u001B[4m";

    public static final String[] locaisDeNascimento= {"Brasília Amarela", "Itabuna", "Ilhéus", "Alí na esquina", "Distrito 12", "Abnegação", "Coreia"};
    public static final String[] nomes= {"Grey", "Catnip", "Yuri", "George", "Tobias", "Judas"};

    public static void main(String[] args) {
        Jogador player;
        if(!DEBUG){
            intro();
            instrucoes();
        }
        player = inicio();
        System.out.println(player);
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
        System.out.println("\tuma persona ou uma empresa. Que a sorte");
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
            AuxLib.input.nextLine();//limpar o buffer. estava dando problemas

            if (!ehOpcValido(opc, max)){
                erroLeitura();
            } else break;
        }
        return opc;
    }

    public static void erroLeitura(){
        System.out.println("Esse valor não é válido, tente novamente");
    }

    public static boolean ehOpcValido(int valor, int max){
        return valor>=1 && valor <=max;
    }

    public static String getNome(){
        String nome;
        while(true){
            nome = AuxLib.input.nextLine();

            if(!Jogador.validaNome(nome)){
                erroLeitura();
            } else break;
        }

        return nome;
    }

    public static String getLocalNascimento(){
        String localNasc;
        while(true){
            localNasc = AuxLib.input.nextLine();

            if(!Jogador.validaLocalNascimento(localNasc)){
                erroLeitura();
            } else break;
        }

        return localNasc;
    }

    public static Data getData(){
        int dia, mes, ano;
        Data dt;
        while(true){
            dia = AuxLib.input.nextInt();        
            mes = AuxLib.input.nextInt();
            ano = AuxLib.input.nextInt();
            AuxLib.input.nextLine();

            dt = new Data(dia, mes, ano);
            if(!Jogador.validaNascimento(dt)){
                erroLeitura();
            } else break;
        }

        return dt;
    }

    public static String getDocumento(int opcTipoPessoa){
        String doc;
        while(true){
            doc = AuxLib.input.nextLine();

            if(opcTipoPessoa==PF && !JogadorPF.validaCPF(doc)){
                erroLeitura();
            } else if(opcTipoPessoa==PJ && !JogadorPJ.validaCNPJ(doc)){
                erroLeitura();
            } else break;
        }

        return doc;
    }

    public static String getSenhaConta(){
        String senha;
        while(true){
            senha = AuxLib.input.next();

            if(!Conta.ehSenhaValida(senha)){
                erroLeitura();
            } else break;
        }

        return senha;
    }

    public static Jogador inicio(){
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

        if(opc==1) return criaPersonagem(false);
        else return criaPersonagem(true);
    } 

    public static Jogador criaPersonagem(boolean ehAleatorio){
        String nome, localNasc, documento, senhaConta;
        Data nasc;
        
        int opcNome, dia, mes, ano, opcLocalNasc, opcTipoPessoa;
        
        //construção/escolha das variáveis
        if(ehAleatorio){
            //nome - string
            opcNome = AuxLib.novoInteiro_nl(nomes.length);
            nome = nomes[opcNome-1];

            //uma data de nascimento - data
            dia = AuxLib.novoInteiro_nl(28);
            mes = AuxLib.novoInteiro_nl(12);
            ano = AuxLib.novoInteiro(2023)-AuxLib.novoInteiro(1000);
            nasc = new Data(dia, mes, ano);

            //um local de nascimento - string
            opcLocalNasc = AuxLib.novoInteiro_nl(locaisDeNascimento.length);
            localNasc = locaisDeNascimento[opcLocalNasc-1];

            //pessoa ou empresa, conta e tipo jogador
            opcTipoPessoa = AuxLib.novoInteiro_nl(2); //int
            senhaConta = Conta.geraSenha(); // string

            //exibe a senha para a pessoa decorar
            System.out.println("A sua senha é muito importante. Geramos essa para você:");
            System.out.println("                       ****");
            aguarde(2);
            System.out.println("A sua senha é muito importante. Geramos essa para você:");            
            System.out.println("                       "+senhaConta);
            aguarde(1);
            
            documento = (opcTipoPessoa==PF)? JogadorPF.geraCPF(): JogadorPJ.geraCNPJ();
        } else {
            //nome
            System.out.print("Seu nome: ");
            nome = getNome();

            //uma data de nascimento
            System.out.print("Data de nascimento (separada por espaços): ");
            nasc = getData();

            //um local de nascimento
            System.out.print("Local de nascimento: ");
            localNasc = getLocalNascimento();

            //pessoa ou empresa, conta e tipo jogador
            System.out.println("Escolha o que você deseja ser...");
            System.out.println(estiloTXTOpc("[1] - Um bípede qualquer que tem CPF"));
            System.out.println(estiloTXTOpc("[2] - Uma marca, empresa, com CNPJ"));
            opcTipoPessoa = getOpc(2);

            //documento
            System.out.println("Faltam pouco para ter sua conta");
            System.out.println("ativa. Informe seu documento (CPF/CNPJ):");
            documento = getDocumento(opcTipoPessoa);

            System.out.println("Para ter sua conta bancária realmente em");
            System.out.println("operação, cadastre uma senha de 4 DÍGITOS NUMÉRICOS:");
            senhaConta = getSenhaConta();

            for(int x=0; x<3;x++){
                limpaTela();
                System.out.print("Pois bem, só mais uns instantes.");
                aguarde(1);
                System.out.print(".");
                aguarde(1);
                System.out.print(".");
                aguarde(1);
            }
            limpaTela();
        }

        //criação do personagem                    
        //pessoa ou empresa, conta e tipo jogador
        limpaTela();
        if(opcTipoPessoa==PF){
            ContaPessoaFisica conta = new ContaPessoaFisica(senhaConta);
            JogadorPF jogador = new JogadorPF(nome, nasc, localNasc, conta, documento);
            return jogador;
        } else {
            ContaPessoaJuridica conta = new ContaPessoaJuridica(senhaConta);
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
