import add.AuxLib;
import add.Data;
import contas.Conta;
import contas.ContaPessoaFisica;
import contas.ContaPessoaJuridica;
import historias.HPessoaFisica_I;
import historias.HPessoaJuridica_I;
import jogadores.Jogador;
import jogadores.JogadorPF;
import jogadores.JogadorPJ;

public class Main {
    public static boolean DEBUG = true;
    public static final int PF = 1;
    public static final int PJ = 2;

    
    public static final String[] locaisDeNascimento= {"Brasília Amarela", "Itabuna", "Ilhéus", "Alí na esquina", "Distrito 12", "Abnegação", "Coreia"};
    public static final String[] nomes= {"Grey", "Catnip", "Yuri", "George", "Tobias", "Judas"};

    public static void main(String[] args) {
        Jogador player;
        String dnv;

        intro();
        instrucoes();
        do{
            player = inicio();
            System.out.println(player);
            
            System.out.println(AuxLib.estiloTXT5("Podemos começar?"));
            AuxLib.aperteEnter();
            if(player instanceof JogadorPF){
                HPessoaFisica_I vida = new HPessoaFisica_I((JogadorPF) player);
                vida.play();
            } else {
                HPessoaJuridica_I vida = new HPessoaJuridica_I((JogadorPJ) player);
                vida.play();
            }
            AuxLib.limpaTela();
            System.out.println(AuxLib.estiloTXT2("Assim você finalizou a sua jornada:")+"\n");
            System.out.println(player);
            AuxLib.aguarde(3);
            System.out.println("Sua biografia está pronta. Quando quiser pode abri-la");
            AuxLib.aperteEnter();
            player.exibirBiografia();

            System.out.println("\nDeseja jogar de novo? (S/n)");
            dnv = AuxLib.input.nextLine();
        }while(dnv.toLowerCase().equals("s"));

        AuxLib.limpaTela();
        System.out.println("\t"+AuxLib.estiloTXT2("Bye Bye!")+"\n\n\n");
    }

    public static void intro(){
        if(!DEBUG){
            AuxLib.limpaTela();
            System.out.println("\t"+ AuxLib.estiloTXT4("Olá! Este \u00E9 o Jogo da Vida!!!"));
            System.out.println("\t"+ AuxLib.estiloTXT3("O carrosel nunca para de girar") +". Escolha com cuidado ");
            System.out.println("\te faça história!");
            System.out.println("\n\n\n");
            AuxLib.aguarde(7);
            AuxLib.limpaTela();
        }
    }

    public static void instrucoes(){
        if(!DEBUG){
            AuxLib.limpaTela();
            System.out.println("\tAqui você escolherá "+ AuxLib.estiloTXT1("seu ponto de partida"));
            System.out.println("\tpara trilhar como bem entender, seja como");
            System.out.println("\t"+ AuxLib.estiloTXT1("uma persona ou uma empresa") +". "+AuxLib.estiloTXT3("Que a sorte"));
            System.out.println("\t"+ AuxLib.estiloTXT3("esteja sempre ao seu favor!"));
            System.out.println("\n\n\n");
            AuxLib.aguarde(11);
            AuxLib.limpaTela();
        }
    }

    public static String getNome(){
        String nome;
        while(true){
            nome = AuxLib.input.nextLine();

            if(!Jogador.validaNome(nome)){
                AuxLib.erroLeitura();
            } else break;
        }

        return nome;
    }

    public static String getLocalNascimento(){
        String localNasc;
        while(true){
            localNasc = AuxLib.input.nextLine();

            if(!Jogador.validaLocalNascimento(localNasc)){
                AuxLib.erroLeitura();
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
                AuxLib.erroLeitura();
            } else break;
        }

        return dt;
    }

    public static String getDocumento(int opcTipoPessoa){
        String doc;
        while(true){
            doc = AuxLib.input.nextLine();

            if(opcTipoPessoa==PF && !JogadorPF.validaCPF(doc)){
                AuxLib.erroLeitura();
            } else if(opcTipoPessoa==PJ && !JogadorPJ.validaCNPJ(doc)){
                AuxLib.erroLeitura();
            } else break;
        }

        return doc;
    }

    public static Jogador inicio(){
        int opc;
        AuxLib.limpaTela();
        System.out.println("Como já dito antes, "+ AuxLib.estiloTXT1("você pode") +" escolher entre "+ AuxLib.estiloTXT1("ser uma pessoa") +",");
        System.out.println("como eu que escrevo esses textos, ou "+ AuxLib.estiloTXT1("ser uma empresa") +",");
        System.out.println("como a TecnoJr, empresa de sua região. Pode ainda");
        System.out.println(AuxLib.estiloTXT1("determinar o dia e local do seu nascimento") +", e chegará");
        System.out.println("ao mundo com uma conta bancária (dessa parte deixemos");
        System.out.println("que o destino faça as honras.. 'risos').\n");
        System.out.println(AuxLib.estiloTXT2("Se preferir que o destino cuide de tudo, pode falar")+":");

        String[] opcs = {"Quero criar meu personagem", "Faça o trabalho por mim"};
        AuxLib.exibeOpcs(opcs);
        opc = AuxLib.getOpc(opcs.length);  

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
            opcNome = (int) AuxLib.novoInteiro_nl(nomes.length);
            nome = nomes[opcNome-1];

            //uma data de nascimento - data
            dia = (int) AuxLib.novoInteiro_nl(28);
            mes = (int) AuxLib.novoInteiro_nl(12);
            ano = (int) (AuxLib.novoInteiro(2023)-AuxLib.novoInteiro(1000));
            nasc = new Data(dia, mes, ano);

            //um local de nascimento - string
            opcLocalNasc = (int) AuxLib.novoInteiro_nl(locaisDeNascimento.length);
            localNasc = locaisDeNascimento[opcLocalNasc-1];

            //pessoa ou empresa, conta e tipo jogador
            opcTipoPessoa = (int) AuxLib.novoInteiro_nl(2); //int
            senhaConta = Conta.geraSenha(); // string

            //exibe a senha para a pessoa decorar
            telaEspera1();
            System.out.println("A sua senha é muito importante. Geramos essa para você:");
            System.out.println("                       ****");
            AuxLib.aguarde(2);
            AuxLib.limpaTela();
            System.out.println("A sua senha é muito importante. Geramos essa para você:");            
            System.out.println("                       "+senhaConta);
            AuxLib.aguarde(1);
            
            documento = (opcTipoPessoa==PF)? JogadorPF.geraCPF(): JogadorPJ.geraCNPJ();
        } else {
            //nome
            System.out.print("\n"+AuxLib.estiloTXT4("Seu nome: "));
            nome = getNome();

            //uma data de nascimento
            System.out.print(AuxLib.estiloTXT4("Data de nascimento (separada por espaços): "));
            nasc = getData();

            //um local de nascimento
            System.out.print(AuxLib.estiloTXT4("Local de nascimento: "));
            localNasc = getLocalNascimento();

            //pessoa ou empresa, conta e tipo jogador
            System.out.println("\n"+AuxLib.estiloTXT2("Escolha o que você deseja ser..."));
            String[] opcs = {"Um bípede qualquer que tem CPF", "Uma marca, empresa, com CNPJ"};
            AuxLib.exibeOpcs(opcs);
            opcTipoPessoa = AuxLib.getOpc(opcs.length);

            //documento
            System.out.println("\n"+AuxLib.estiloTXT2("Falta pouco para ter sua conta ativa."));
            System.out.println(AuxLib.estiloTXT6("11 dígitos para CPF - 14 dígitos para CNPJ"));
            System.out.print("Informe seu documento (CPF/CNPJ): ");
            documento = getDocumento(opcTipoPessoa);

            System.out.print("\n"+AuxLib.estiloTXT2("Para ter sua conta bancária realmente em\noperação"));
            System.out.print(", cadastre uma senha de 4 DÍGITOS NUMÉRICOS: ");
            senhaConta = AuxLib.getSenhaConta();

            telaEspera1();
        }

        //criação do personagem                    
        //pessoa ou empresa, conta e tipo jogador
        AuxLib.limpaTela();
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

    public static void telaEspera1(){
        if(!DEBUG) {
            for(int x=0; x<3;x++){
                AuxLib.limpaTela();
                System.out.print("Pois bem, só mais uns instantes.");
                AuxLib.aguarde(1);
                System.out.print(".");
                AuxLib.aguarde(1);
                System.out.print(".");
                AuxLib.aguarde(1);
            }
            AuxLib.limpaTela();
        }
    }
}
