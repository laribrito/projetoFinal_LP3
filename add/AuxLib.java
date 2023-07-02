package add;

import java.util.Random;
import java.util.Scanner;

import contas.Conta;

public abstract class AuxLib {
    static public Scanner input  = new Scanner(System.in);
    private static final String BG_RED = "\u001B[41m";
    private static final String BG_GREEN = "\u001B[42m";
    private static final String BOLD = "\u001B[1m";
    private static final String ITALIC = "\u001B[3m";
    private static final String CLEAR = "\u001B[0m";
    private static final String UNDERLINE = "\u001B[4m";

    static private Random gerador = new Random();

    /** STATIC - Retorna um número entre 0 e o valor absoluto de max como String */
    static public String novoInteiroStr(long max){
        return String.valueOf(novoInteiro(max));
    }

    /** STATIC - Retorna um número entre 0 e o valor absoluto de max como long */
    static public long novoInteiro(long max){
        return novoInteiro(0, max);
    }

    /** STATIC - Retorna um número entre min e o valor absoluto de max como long */
    static public long novoInteiro(long min, long max){
        return (long)(gerador.nextDouble() * max-min)+min;
    }

    /** STATIC - Retorna um número entre 0 e um limite, personalizado com probabilidade 
        Essa função recebe 4 valores:
            max1 - limite do intervalo1 [0, max1]
            chances1 - valor numérico para as chances do número pertencer ao intervalo1
            max2 - limite do intervalo2 ]max1, max2]
            chances2 - valor numérico para as chances do número pertencer ao intervalo2
        Retorna um long int seguindo as regras acima. O número terá (chanches1/(chances1+chances2)) 
        chances de pertencer ao intervalo1, e vice-versa
    */
    static public long novoInteiro_p(long max1, long chances1, long max2, long chances2){
        long ret, valorMenor = AuxLib.novoInteiro(max1), valorMaior = AuxLib.novoInteiro(max1+1, max2);

        if (AuxLib.novoInteiro_nl(chances1+chances2)<=chances1) ret = valorMenor;
        else ret = valorMaior;

        return ret;
    }

    /** STATIC - Retorna um número não nulo, entre 1 e o valor absoluto de max como long */
    static public long novoInteiro_nl(long max){
        return novoInteiro(1, max);
    }

    static public void exibeOpcs(String[] opcs){
        int qtd = opcs.length;
        System.out.println();
        for(int x=1; x<=qtd; x++){
            System.out.println(AuxLib.estiloTXTOpc("["+ x +"] - "+ opcs[x-1]));
        }
    }

    /** STATIC - Verifica se a string informada é composta apenas por número e retorna um booleano */
    static public boolean ehApenasNumero(String input){
        return input.matches("\\d+");
    }

    /** STATIC - Recebe um número float e o retorna como string formatada com duas casas decimais */
    static public String formatarFloat(float numero){
        return String.format("%.2f", numero);
    }

    public static String estiloTXT1(String txt){
        return BG_RED+BOLD + txt + CLEAR;
    }

    public static String estiloTXT2(String txt){
        return UNDERLINE+BOLD + txt + CLEAR;
    }

    public static String estiloTXT3(String txt){
        return UNDERLINE+ txt + CLEAR;
    }

    public static String estiloTXT4(String txt){
        return BOLD+ txt + CLEAR;
    }

    public static String estiloTXT5(String txt){
        return BG_GREEN+BOLD+ txt + CLEAR;
    }

    public static String estiloTXT6(String txt){
        return ITALIC+ txt + CLEAR;
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

    public static int getOpc(int max){
        int opc;
        String txt;
        while(true){
            System.out.print("> ");
            txt = AuxLib.input.nextLine();
            opc = Integer.parseInt(txt);

            if (!ehOpcValido(opc, max)){
                erroLeitura();
            } else break;
        }
        return opc;
    }

    public static void aperteEnter(){
        System.out.println("\nPressione "+ AuxLib.estiloTXT4("[ENTER]") +" para continuar ");
        AuxLib.input.nextLine();
    }

    public static void erroLeitura(){
        System.out.println("Esse valor não é válido. "+ AuxLib.estiloTXT3("Tente novamente") +":");
    }

    public static boolean ehOpcValido(int valor, int max){
        return valor>=1 && valor <=max;
    }

    public static String getSenhaConta(){
        String senha;
        while(true){
            senha = AuxLib.input.nextLine();

            if(!Conta.ehSenhaValida(senha)){
                AuxLib.erroLeitura();
            } else break;
        }

        return senha;
    }

    public static String formatText(String text, int lineLength) {
        StringBuilder textoFormatado = new StringBuilder();
        String[] palavras = text.split("\\s+");
        StringBuilder linhaAtual = new StringBuilder();

        for (String palavra : palavras) {
            if (linhaAtual.length() + palavra.length() <= lineLength) {
                // Se a palavra cabe na linha atual, adiciona a palavra à linha
                if (linhaAtual.length() > 0) {
                    linhaAtual.append(" ");
                }
                linhaAtual.append(palavra);
            } else {
                // Se a palavra não cabe na linha atual, adiciona a linha atual ao resultado final
                textoFormatado.append(linhaAtual.toString()).append(System.lineSeparator());
                linhaAtual.setLength(0); // Reseta a linha atual para começar a nova linha com a palavra atual
                linhaAtual.append(palavra);
            }
        }

        // Adiciona a última linha ao resultado final
        textoFormatado.append(linhaAtual.toString());

        return textoFormatado.toString();
    }

}
