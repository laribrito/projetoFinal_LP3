package historias;

import add.AuxLib;
import jogadores.Jogador;
import jogadores.JogadorPJ;
import capitulos.Inovacao;
import capitulos.FimDeVida;

public class HPessoaJuridica_I extends Historia implements Inovacao, FimDeVida {
    int pontosE; //uma pontuação simbólica da empresa e seu impacto no mundo
    JogadorPJ player;
    protected boolean ehJogadorCorreto(Jogador p){
        return p instanceof JogadorPJ;
    }

    public HPessoaJuridica_I(JogadorPJ p){
        super(p);
        if(ehJogadorCorreto(p)){
            pontosE = 0;
            hValida = true;
            player = p;
            System.out.println("Essa é a sua história!");
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
        int opc;

        localNascimentoIdeia = (player.getSaldo()<10000)? "na garagem de casa":"a partir da grande fortuna da família";

        System.out.println("É lindo de ver uma ideia nascendo "+ localNascimentoIdeia +".");
        System.out.println(AuxLib.estiloTXT3("Escolha qual a sua ideia inovadora: "));

        String[] opcs = {
            "Caneta com tinta infinita", 
            "Celular que não descarrega",
            "Saco de lixo que não rasga", 
            "Melhores óculos de realidade aumentada",
            "Computador com memória infinita",
        };
        AuxLib.exibeOpcs(opcs);
        opc = AuxLib.getOpc(opcs.length);
        ideiaInovadora = opcs[opc-1];
        System.out.println("Muito bem! Ótima escolha!");

        return player.getNome() + " construiu o seu império " + localNascimentoIdeia + ", criando "+ideiaInovadora.toLowerCase()+". Simplesmente genial! ";
    }

    @Override
    public String crescimento(){
        //chance de ganhar ou perder dinheiro
        long ganhouDinheiro = AuxLib.novoInteiro_p(5, 3, 10, 8);

        //pode até dobrar o que tem ou chegar a perder tudo e ficar com dívida
        long limiteDinheiro = (long) (player.getSaldo() + player.getLimiteCredito()) * 100;
        float qtdDinheiro = AuxLib.novoInteiro(1000, limiteDinheiro)/100.0f;
        String strDinheiro = AuxLib.formatarFloat(qtdDinheiro);
        String bioAux="";

        if(ganhouDinheiro>5){
            //se ganhou dinheiro
            System.out.println("Sua empresa lhe rendeu um "+ AuxLib.estiloTXT5("lucro") +" de "+AuxLib.estiloTXT3("R$"+strDinheiro));
            player.depositar(ganhouDinheiro/2.0f);
            bioAux = "A empresa foi muito bem sucedida ";
            AuxLib.aguarde(4);
            return bioAux + estabilizacao();
        } else {
            //se perdeu dinheiro
            String senha;
            System.out.println("Mas empresa lhe rendeu um "+ AuxLib.estiloTXT1("prejuízo") +" de R$ " + AuxLib.estiloTXT3("R$"+strDinheiro));
            System.out.println("\nInfelizmente você terá que sacar o dinheiro para");
            System.out.println("cobrir as despesas. Digite sua senha: ");
            
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
        System.out.println("se aposentou. Que vida boa! Ao todo a empresa lhe rendeu R$ "+AuxLib.formatarFloat(montanteDinheiro));
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
        float precoEscolhido, saldoDisp;
        boolean fimDaHistoria = false;
        String fimEscolhido, bio="";

        if(player.estaVivo()){
            System.out.println(AuxLib.estiloTXT3("Que bom que você chegou até aqui!") +" Já trabalhou");
            System.out.println("bastante e agora é o momento de você descansar (ou não).");

            System.out.println("\n"+ AuxLib.estiloTXT4("Esse é seu estado atualmente:"));
            System.out.println(player);

            System.out.println(AuxLib.estiloTXT2("\nEscolha o seu destino final:"));

            //informações
            String[] opcs = {
                "Aderiu à produção vegana ------------ R$  718493,99",
                "Passou a chefia para um herdeiro ---- R$ 5123459,76",
                "Combateu a poluição marítmica ------- R$   97693,23",
                "Investiu na corrida do espaço ------- R$ 9993459,15",
                "Sumiu do radar ---------------------- R$       0,00",
            };
            float[] precos = {718493.99f, 5123459.76f, 97693.23f, 3459.15f, 0.0f};

            //separa a parte escrita das opções
            String[] strOpcs = new String[opcs.length];
            for (int i = 0; i < opcs.length; i++) {
                String[] parts = opcs[i].split("---+");
                strOpcs[i] = parts[0].trim();
            }

            //pega a opção do usuário
            AuxLib.exibeOpcs(opcs);
            while(!fimDaHistoria){
                opc = AuxLib.getOpc(opcs.length);

                //valida a opção com o preço com o saldo da conta da pessoa
                fimEscolhido = strOpcs[opc-1];
                precoEscolhido = precos[opc-1];
                saldoDisp = player.getSaldo()+player.getLimiteDisponivel();
                if(precoEscolhido<=saldoDisp){
                    //pode finalizar a história
                    fimDaHistoria = true;
                    bio = "O que se sabe sobre " + player.getNome()+ "atualmente é que " + fimEscolhido.toLowerCase();
                } else {
                    //escolha novamente
                    System.out.println("Você não teve "+ AuxLib.estiloTXT1("'sorte'") +" o suficiente para poder");
                    System.out.println("escolher isso agora. " + AuxLib.estiloTXT2("Escolha um outro fim:"));
                }
            }

            return bio;
        } else {
            return "O seu legado fica marcado pela eternidade do que não se deve fazer.";
        }
    }
}
