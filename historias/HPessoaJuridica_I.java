package historias;

import add.AuxLib;
import jogadores.Jogador;
import jogadores.JogadorPJ;
import capitulos.Inovacao;
import capitulos.FimDeVida;

public class HPessoaJuridica_I extends Historia implements Inovacao, FimDeVida {
    private JogadorPJ player;
    private final static String[] 
        opcsInovacao = {
            "Caneta com tinta infinita", 
            "Celular que não descarrega",
            "Saco de lixo que não rasga", 
            "Melhores óculos de realidade aumentada",
            "Computador com memória infinita",
        }, 
        opcsFimDeJogo = {
            "Aderiu à produção vegana ------------ R$  718493.99",
            "Passou a chefia para um herdeiro ---- R$ 5123459.76",
            "Combate a poluição marítmica -------- R$   97693.23",
            "Investiu na corrida espacial -------- R$ 9993459.15",
            "Sumiu do radar ---------------------- R$       0.00",
        }
    ;

    protected boolean ehJogadorCorreto(Jogador p){
        return p instanceof JogadorPJ;
    }

    public HPessoaJuridica_I(JogadorPJ p){
        super();
        if(ehJogadorCorreto(p)){
            hValida = true;
            player = p;
            msgStart();
        } else {
            erroCriacaoHistoria();
        }
    }

    public void play(){
        if(hValida){
            telaPlay("da pessoa jurídica, edição I,");
            AuxLib.limpaTela();
            //chama os capitulos
            player.adicionaNaBio(novaIdeia());
            player.adicionaNaBio(crescimento());
            player.adicionaNaBio(fechamento());
        }
    }

    @Override
    public String novaIdeia(){
        String localNascimentoIdeia, ideiaInovadora;
        String[] opcs = opcsInovacao;
        int opc;

        //ponto de partida varia de acordo com o dinheiro que a empresa tem inicialmente
        localNascimentoIdeia = (player.getSaldo()<10000)? "na garagem de casa":"a partir da grande fortuna da família";

        System.out.println("É lindo de ver uma ideia nascendo "+ localNascimentoIdeia +".");
        System.out.println(AuxLib.estiloTXT3("Escolha qual a sua ideia inovadora: "));

        //exibe e coleta as opções
        AuxLib.exibeOpcs(opcs);
        opc = AuxLib.getOpc(opcs.length);
        ideiaInovadora = opcs[opc-1];
        System.out.println("Muito bem! Ótima escolha!");
        AuxLib.aguarde(3);

        return player.getNome() + " construiu o seu império " + localNascimentoIdeia + ", criando "+ideiaInovadora.toLowerCase()+". Simplesmente genial! ";
    }

    @Override
    public String crescimento(){
        String bioAux="", strDinheiro;

        //chance de ganhar ou perder dinheiro
        long ganhouDinheiro = AuxLib.novoInteiro_p(5, 3, 10, 8);

        //pode até dobrar o que tem ou chegar a perder tudo e ficar com dívida
        long limiteDinheiro = (long) (player.getSaldo() + player.getLimiteCredito()) * 100;
        float qtdDinheiro = AuxLib.novoInteiro(1000, limiteDinheiro)/100.0f;
        strDinheiro = AuxLib.formatarFloat(qtdDinheiro);

        ganhouDinheiro = 7;
        if(ganhouDinheiro>5){
            //se ganhou dinheiro
            System.out.println("Sua empresa lhe rendeu um "+ AuxLib.estiloTXT5("lucro") +" inicial de "+AuxLib.estiloTXT3("R$ "+strDinheiro));
            player.depositar(qtdDinheiro);
            bioAux = "A empresa foi muito bem sucedida ";
            AuxLib.aguarde(4);
            return bioAux + estabilizacao();
        } else {
            //se perdeu dinheiro
            String senha;
            System.out.println("Mas empresa lhe rendeu um "+ AuxLib.estiloTXT1("prejuízo") +" de R$ " + AuxLib.estiloTXT3("R$"+strDinheiro));
            System.out.println("\nInfelizmente você terá que sacar o dinheiro para");
            System.out.println("cobrir as despesas. Digite sua senha: ");
            
            /*
             O dinheiro para montar a empresa foi pego com agiotas, e eles querem de volta
             O jogador tem até 3 oportunidades para pagar, se não o jogador morre
             */
            int tentativas=0, maxTent = 3;
            boolean conseguiuSacar = false;

            while(!conseguiuSacar || player.estaVivo()){
                senha = AuxLib.getSenhaConta();
                
                conseguiuSacar = player.sacar(qtdDinheiro, senha);
                if(!conseguiuSacar){
                    System.out.println("\n"+AuxLib.estiloTXT1("NÃO PODE DEIXAR DE PAGAR!")+" Tentativa "+ ++tentativas + ". Você tem até");
                    System.out.println(AuxLib.estiloTXT3(maxTent + " chances")+" para pagar o dinheiro antes que "+ AuxLib.estiloTXT1("os agiotas te matem") +".");
                    System.out.println(AuxLib.estiloTXT3("Tente novamente:"));
                }

                if(tentativas==maxTent){
                    AuxLib.limpaTela();
                    System.out.println("Você "+ AuxLib.estiloTXT1("morreu") +" :(");
                    player.morreu();
                    bioAux = "E isso acabou lhe custando a vida. Talvez não tenha sido o melhor caminho a ser seguido. ";
                }
            }
            AuxLib.aguarde(4);
            return declinio()+bioAux;
        }
    }
    
    @Override
    public String estabilizacao(){
        int anos = (int)AuxLib.novoInteiro(10, 1000);
        float montanteDinheiro=0;
        for(int x=1; x<=anos; x++){
            montanteDinheiro+=AuxLib.novoInteiro(100000);
        }
        montanteDinheiro/=100;
        System.out.println("A empresa vigorou por "+ anos + " longos anos. Depois disso você");
        System.out.println("se aposentou. Que vida boa! A empresa lhe rendeu mais "+ AuxLib.estiloTXT3("R$ "+AuxLib.formatarFloat(montanteDinheiro)));
        player.depositar(montanteDinheiro);
        AuxLib.aguarde(8);
        return "e vigorou por "+ anos + " longos anos. ";
    }
    
    @Override
    public String declinio(){
        return "Infelizmente as pessoas não estavam preparadas para o que " + player.getNome() +" trazia para o mundo. ";
    }

    @Override
    public String fechamento(){
        int opc;
        float precoEscolhido;
        boolean conseguiuPagar = false;
        String fimEscolhido, bio="", senha;

        if(player.estaVivo()){
            AuxLib.limpaTela();
            System.out.println(AuxLib.estiloTXT4("Que bom que você chegou até aqui!") +" Já trabalhou");
            System.out.println("bastante e agora é o momento de você descansar (ou não).\n");

            System.out.println(AuxLib.estiloTXT4("Esse é seu estado atualmente:"));
            System.out.println(player);

            System.out.println(AuxLib.estiloTXT2("\nEscolha o seu destino final:"));

            //informações
            String[] opcs = opcsFimDeJogo, auxOpcs, auxPreco, strOpcs = new String[opcs.length];
            float[] valorOpcs = new float[opcs.length];

            //trata os dados do menu
            for(int x = 0; x<opcs.length; x++){
                //faz o split
                auxOpcs = opcs[x].split("---+");

                //armazena nos devidos vetores
                strOpcs[x]=auxOpcs[0].toLowerCase();

                    //retira o espaço inicial
                auxOpcs[1]= auxOpcs[1].substring(1, auxOpcs[1].length());
                    //faz um outro split e fica com a segunda parte, onde está o valor
                auxPreco = auxOpcs[1].split("\\s+");
                auxOpcs[1] = auxPreco[1];
                    //transforma para float
                valorOpcs[x] = Float.parseFloat(auxOpcs[1]);
            }

            //pega a opção do usuário
            AuxLib.exibeOpcs(opcs);
            while(!conseguiuPagar){
                opc = AuxLib.getOpc(opcs.length);

                fimEscolhido = strOpcs[opc-1];
                precoEscolhido = valorOpcs[opc-1];

                if(precoEscolhido!=0){
                    //valida a opção com o preço com o saldo da conta da pessoa
                    System.out.print("Digite sua senha:");
                    senha = AuxLib.getSenhaConta();
                    conseguiuPagar = player.sacar(precoEscolhido, senha);
                    if(!conseguiuPagar){
                        //escolha novamente
                        System.out.println("Você não teve "+ AuxLib.estiloTXT1("'sorte'") +" o suficiente para poder");
                        System.out.println("escolher isso agora. " + AuxLib.estiloTXT2("Escolha um outro fim:"));
                    }
                } else {
                    conseguiuPagar = true;
                }

                bio = "O que se sabe sobre " + player.getNome()+ " atualmente é que " + fimEscolhido.toLowerCase();
            }

            return bio;
        } else {
            return "O seu legado fica marcado pela eternidade do que não se deve fazer.";
        }
    }
}
