package historias;

import add.AuxLib;
import capitulos.BrinquedoCrianca;
import capitulos.EsporteEscolar;
import capitulos.VidaAcademica;
import capitulos.FimDeVida;
import jogadores.Jogador;
import jogadores.JogadorPF;

public class HPessoaFisica_I extends Historia implements BrinquedoCrianca, EsporteEscolar, VidaAcademica, FimDeVida{
    private JogadorPF player;

    protected boolean ehJogadorCorreto(Jogador p){
        return p instanceof JogadorPF;
    }
    
    public HPessoaFisica_I(JogadorPF p){
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
            telaPlay("da pessoa física, edição I,");
            //chama os capítulos
            // player.adicionaNaBio(escolherBrinquedoInfantil());
            player.adicionaNaBio(escolherEsporteEscolar());
        }
    }

    @Override
    public String escolherBrinquedoInfantil(){
        int opc, variavel;
        String brinquedo, bio;

        AuxLib.limpaTela();
        System.out.println("Hoje é Natal e você uma criança. Sabe o que isso significa? ");
        System.out.println(AuxLib.estiloTXT2("Diga o seu brinquedo preferido")+", e talvez o Papai Noel");
        System.out.println("te dê de presente:");

        String[] opcs = {"Ursinho de pelúcia", "Carrinho do Relâmpago Marquinhos", "Bicicleta", "Luneta"};
        AuxLib.exibeOpcs(opcs);
        opc = AuxLib.getOpc(opcs.length);
        brinquedo = opcs[opc-1];

        variavel = (int) AuxLib.novoInteiro_p(5, 4, 12, 7);

        if(variavel > 5){
            //ganha o presente
            System.out.println("Uhuhh! Você se comportou muito bem e vai ganhar seu presente!");

            bio = "Desde criança " + player.getNome() + " gostava de " + brinquedo + " e essa é uma lembrança boa que tem da infância. " ;
        } else {
            //não ganha o presente
            System.out.println("Poxa... O Papai Noel não gostou do que você fez esse ano. Quem sabe no próximo ano!");

            bio = "\"Tinha muita energia\", disse a família de " + player.getNome() + ". \"Não deixava nada do lugar, mas a gente também se divertia com isso\". ";
        }

        AuxLib.aguarde(4);
        return bio;
    }

    @Override
    public String escolherEsporteEscolar(){
        int opc, variavel;
        float premios;
        String esporte;

        AuxLib.limpaTela();
        System.out.println("Você cresceu e está na escola. Precisa escolher o esporte");
        System.out.println("para a aula de Educação Física. "+ AuxLib.estiloTXT6("Nem tudo é futebol (ou talvez seja haha).."));

        String[] opcs = {"Volei", "Handebol", "Futebol", "Basquete"};
        AuxLib.exibeOpcs(opcs);
        opc = AuxLib.getOpc(opcs.length);
        esporte = opcs[opc-1];

        System.out.println("\nCaraca! Que escolha bacana!");
        AuxLib.aguarde(2);
        player.setEsporteEscolhido(esporte);

        // pode participar de campeonatos e ganhar dinheiro
        variavel = (int) AuxLib.novoInteiro_p(8, 9, 10, 2);
        if(variavel <= 8){
            //ganha os campeonatos

            //valor que ganhou
            premios = AuxLib.novoInteiro(10000, 1234500) / 100.0f;
            System.out.println("PARABÉNS! Você se destacou tanto no esporte que participou de vários");
            System.out.println("campeonatos. "+ AuxLib.estiloTXT5("Ganhou") +" ao todo R$ "+ AuxLib.formatarFloat(premios));
            AuxLib.aguarde(4);

            //bio
            return "Já na adolescência começou a praticar "+ esporte + " por causa da escola, mas tomou gosto de chegou a participar de campeonatos, ganhando até alguns prêmios. ";
        } else {
            //vida que segue
            return "Quando estava na escola praticou " + esporte + " e se divertiu muito com o esporte. ";
        }
    }

    @Override
    public String cursoTecnico(){
        int opc;
        String curso, senha;
        String[] 
            opcs = {
                "Programação ------------------- R$ Saúde mental",
                "Segurança do Trabalho --------- R$ 3123,24",
                "Farmácia ---------------------- R$ 1236,76",
                "Informática ------------------- R$ 7888,33",
                "Prefiro não fazer nada -------- R$ 0,00",
                "Ver saldo da minha conta"
            }, 
            aux, 
            strOpcs = new String[opcs.length-2]
        ;
        float[] valorOpcs = new float[opcs.length-2];

        //trata os dados do menu
        for(int x = 0; x<opcs.length-2; x++){
            //faz o split
            aux = opcs[x+1].split("---+");

            //armazena nos devidos vetores
            strOpcs[x]=aux[0];

                //retira o ' R$ '
            aux[1]= aux[1].substring(4, aux[1].length());
            valorOpcs[x] = Float.parseFloat(aux[1]);
        }

        do{
        AuxLib.limpaTela();
        System.out.println("Olá! Essa é uma "+ AuxLib.estiloTXT2("oferta imperdível!") +" Temos alguns");
        System.out.println("cursos técnicos para jovens com você:");

        AuxLib.exibeOpcs(opcs);
        opc = AuxLib.getOpc(opcs.length);

        if(opc==opcs.length){
            //exibe o saldo da conta
            AuxLib.limpaTela();
            player.printInfoConta();
            AuxLib.aperteEnter();
        } else if(opc==1){
            //verifica se escolheu programação
            System.out.println("Tadinho. Dizem que é uma profissão "+ AuxLib.estiloTXT4("que dá dinheiro") +", mas só ");
            System.out.println("querer din din não basta para estudar programação. "+ AuxLib.estiloTXT3("Boa sorte!"));
            AuxLib.aguarde(7);
        } else {
            //verifica se pode pagar pelo curso
            System.out.print("Digite sua senha para pagar o curso:");
            senha = AuxLib.getSenhaConta();
            if(player.sacar(valorOpcs[opc], senha)){
                System.out.println("Perfeito! Daqui a alguns meses você estará formado!");
                System.out.println("Até lá é só sofrimento mesmo.");
            } else {
                System.out.println("Parece que você não conseguiu pagar. Tente novamente:");
                opc = opcs.length-1;
            }
        }

        }while(opc == opcs.length-1);

        curso = strOpcs[opc];

        return "";
    }

    @Override
    public String graduacao(){
        return "";
    }

    @Override
    public String fechamento(){
        int opc;
        float precoEscolhido, saldoDisp;
        boolean fimDaHistoria = false;

        if(player.estaVivo()){
            System.out.println(AuxLib.estiloTXT3("Que bom que você chegou até aqui!") +" Já trabalhou");
            System.out.println("bastante e agora é o momento de você descansar.");

            // player.get

            System.out.println(AuxLib.estiloTXT2("Escolha o destino final da tua vida:"));

            //informações
            String[] opcs = {
                "Viagem pelo mundo --------------------- R$  718493,99",
                "Passar o usando o Twitter ------------- R$   97693,23",
                "Ir pro espaço ------------------------- R$ 5123459,76",
                "Se mudar para o campo ----------------- R$    3459,15",
                "Viver onde está de perna pra cima ----- R$       0,00",
            };
            float[] precos = {718493.99f, 97693.23f, 5123459.76f, 3459.15f, 0.0f};

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
                precoEscolhido = precos[opc-1];
                saldoDisp = player.getSaldo();
                if(precoEscolhido<=saldoDisp){
                    //pode finalizar a história
                    fimDaHistoria = true;
                    return "Como desfecho de vida, ";
                } else {
                    //escolha novamente
                }
            }
            
            System.out.println("Muito bem! Ótima escolha!");
            
        }
        return "";            
    }
}
