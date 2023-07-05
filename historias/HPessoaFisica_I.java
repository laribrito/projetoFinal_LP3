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
    private final static String[] 
        opcsBrinquedoInfantil = {
            "Ursinho de pelúcia", 
            "Carrinho do Relâmpago Marquinhos", 
            "Bicicleta", 
            "Luneta"
        },
        opcsEsporte = {
            "Volei", 
            "Handebol", 
            "Futebol", 
            "Basquete"
        },
        opcsCursoTecnico = {
            "Programação ------------------- R$ Saúde mental",
            "Segurança do Trabalho --------- R$ 3123.24",
            "Farmácia ---------------------- R$ 1236.76",
            "Informática ------------------- R$ 7888.33",
            "Prefiro não fazer nada -------- R$    0.00",
            "Ver saldo da minha conta"
        },
        opcsGraduacao = {
            "Psicologia (CESUPI) --------------------- R$  121234.34",
            "Odontologia (CESUPI) -------------------- R$ 3879123.24",
            "Ciência da Computação (UESC) ------------ R$       Alma",
            "Alguma das mil engenharias (UESC) ------- R$   Sanidade",
            "Prefiro não fazer nada ------------------ R$       0.00",
            "Ver saldo da minha conta"
        },
        opcsFechamento = {
            "Viajar pelo mundo --------------------- R$  718493.00",
            "Passar o usando o Twitter ------------- R$   97693.20",
            "Ir pro espaço ------------------------- R$ 1123459.75",
            "Se mudar para o campo ----------------- R$    3459.00",
            "Viver onde está, de perna pra cima ---- R$       0.00",
            "Ver saldo da minha conta",
        }
    ;

    // Método de validação --------------------------------------------------------

    protected boolean ehJogadorCorreto(Jogador p){
        return p instanceof JogadorPF;
    }

    // Construtor --------------------------------------------------------------------
    
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

    // Métodos públicos -----------------------------------------------------------------

    public void play(){
        if(hValida){
            telaPlay("da pessoa física, edição I,");
            //chama os capítulos
            player.adicionaNaBio(escolherBrinquedoInfantil());
            player.adicionaNaBio(escolherEsporteEscolar());
            player.adicionaNaBio(cursoTecnico());
            player.adicionaNaBio(graduacao());
            player.adicionaNaBio(fechamento());
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

        String[] opcs = opcsBrinquedoInfantil;
        AuxLib.exibeOpcs(opcs);
        opc = AuxLib.getOpc(opcs.length);
        brinquedo = opcs[opc-1];

        variavel = (int) AuxLib.novoInteiro(5, 4, 12, 7);

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

        String[] opcs = opcsEsporte;
        AuxLib.exibeOpcs(opcs);
        opc = AuxLib.getOpc(opcs.length);
        esporte = opcs[opc-1];

        System.out.println("\nCaraca! Que escolha bacana!");
        AuxLib.aguarde(2);
        player.setEsporteEscolhido(esporte);

        // pode participar de campeonatos e ganhar dinheiro
        variavel = (int) AuxLib.novoInteiro(8, 9, 10, 2);
        if(variavel <= 8){
            //ganha os campeonatos

            //valor que ganhou
            premios = AuxLib.novoInteiro(10000, 1234500) / 100.0f;
            System.out.println("PARABÉNS! Você se destacou tanto no esporte que participou de vários");
            System.out.println("campeonatos. "+ AuxLib.estiloTXT5("Ganhou") +" ao todo R$ "+ AuxLib.formatarFloat(premios));
            AuxLib.aguarde(8);
            player.depositar(premios);

            //bio
            return "Já na adolescência começou a praticar "+ esporte + " por causa da escola, mas tomou gosto e chegou a participar de campeonatos, ganhando até alguns prêmios. ";
        } else {
            //vida que segue
            return "Quando estava na escola praticou " + esporte + " e se divertiu muito com o esporte. ";
        }
    }

    @Override
    public String cursoTecnico(){
        int opc;
        String curso, senha;
        boolean podePagar=false;
        String[] opcs = opcsCursoTecnico, aux, strOpcs = new String[opcs.length-1];
        float valorOpcs[] = new float[opcs.length-1], valorCurso=0;

        //trata os dados do menu
        for(int x = 0; x<opcs.length-1; x++){
            //faz o split
            aux = opcs[x].split("---+");
            
            //armazena nos devidos vetores
            strOpcs[x]=aux[0].toLowerCase().trim();
            
            if(x==0 || x==opcs.length-2){
                valorOpcs[x] = 0;
            } else {
                    //retira o espaço inicial
                aux[1]= aux[1].trim();
                    //faz um outro split e fica com a segunda parte, onde está o valor
                aux[1] = aux[1].split("\\s+")[1];
                    //transforma para float
                valorOpcs[x] = Float.parseFloat(aux[1]);
            }
        }

        //oferece os cursos
        do{
            AuxLib.limpaTela();
            System.out.println("Olá! Essa é uma "+ AuxLib.estiloTXT2("oferta imperdível!") +" Temos alguns");
            System.out.println("cursos técnicos para jovens com você:");

            //pega a opc do jogador
            AuxLib.exibeOpcs(opcs);
            opc = AuxLib.getOpc(opcs.length);
            
            //testa a opc escolhida
            if(opc==opcs.length){
                //exibe o saldo da conta
                AuxLib.limpaTela();
                player.printInfoConta();
                AuxLib.aperteEnter();
            } else {
                //se o jogador não escolheu ver o saldo, pega o valor do curso que foi escolhido
                valorCurso = valorOpcs[opc-1];
            
                if(opc==1){
                    //verifica se escolheu programação
                    System.out.println("Caramba! Dizem que é uma profissão "+ AuxLib.estiloTXT4("que dá dinheiro") +", mas só ");
                    System.out.println("querer din din não basta para estudar programação. "+ AuxLib.estiloTXT3("Boa sorte!"));
                    AuxLib.aguarde(7);
                    podePagar = true;

                } else if(valorCurso==0){
                    podePagar = true;
                }else{
                    //tenta pagar o curso
                    System.out.print("\nDigite sua senha para pagar o curso: ");
                    senha = AuxLib.getSenhaConta();
                    podePagar = player.sacar(valorOpcs[opc], senha);
                }
                
                //verifica se pode pagar pelo curso
                if(podePagar && opc < opcs.length-2){
                    System.out.println("\n"+ AuxLib.estiloTXT5("Perfeito!") +" Daqui a alguns meses você estará formado!");
                    System.out.println("Até lá é só sofrimento mesmo.");
                    AuxLib.aguarde(5);
                } else if(!podePagar){
                    System.out.print("Parece que você "+ AuxLib.estiloTXT1("não conseguiu pagar") +". Tente novamente! \n");
                    AuxLib.aperteEnter();
                    opc = opcs.length;
                }
            }

        }while(opc == opcs.length && !podePagar);
        
        if(opc==opcs.length-1){
            //escolheu não fazer nada
            return "Teve a chance de fazer um curso técnico, mas decidiu que não era aquilo que queria para a vida. ";
        } else {
            //escolheu algum curso
            curso = strOpcs[opc-1];
            player.setFormacaoTecnica(curso);
            return "Agarrou a oportunidade que teve de se formar em "+ curso + " e gostou bastante do curso. ";
        }
    }

    @Override
    public String graduacao(){
        int opc;
        String curso, senha;
        boolean podePagar=false;
        String[] opcs = opcsGraduacao, aux, strOpcs = new String[opcs.length-1];
        float valorOpcs[] = new float[opcs.length-1], valorCurso=0;

        //trata os dados do menu
        for(int x = 0; x<opcs.length-1; x++){
            //faz o split
            aux = opcs[x].split("---+");
            
            //armazena nos devidos vetores
            strOpcs[x]=aux[0].trim();
            
            if(x>1){
                valorOpcs[x] = 0;
            } else {
                    //retira o espaço inicial
                aux[1]= aux[1].trim();
                    //faz um outro split e fica com a segunda parte, onde está o valor
                aux[1] = aux[1].split("\\s+")[1];
                    //transforma para float
                valorOpcs[x] = Float.parseFloat(aux[1]);
            }

        }

        //oferece os cursos
        do{
            AuxLib.limpaTela();
            System.out.println("Iai! Vai terminar a vida acadêmica assim ou vai "+ AuxLib.estiloTXT1("sofrer") +" no ensino superior também?");

            //pega a opc do jogador
            AuxLib.exibeOpcs(opcs);
            opc = AuxLib.getOpc(opcs.length);
            
            //testa a opc escolhida
            if(opc==opcs.length){
                //exibe o saldo da conta
                AuxLib.limpaTela();
                player.printInfoConta();
                AuxLib.aperteEnter();
            } else {
                //se o jogador não escolheu ver o saldo, pega o valor do curso que foi escolhido
                valorCurso = valorOpcs[opc-1];
            
                if(opc==3 || opc==4){
                    //verifica se escolheu UESC
                    System.out.println("Nossa! Você quer batalhar mesmo. "+ AuxLib.estiloTXT3("Boa sorte!"));
                    AuxLib.aguarde(3);
                    podePagar = true;

                } else if(valorCurso==0){
                    podePagar = true;
                }else{
                    //tenta pagar o curso
                    System.out.print("\nDigite sua senha para pagar o curso: ");
                    senha = AuxLib.getSenhaConta();
                    podePagar = player.sacar(valorOpcs[opc], senha);
                }
                
                //verifica se pode pagar pelo curso
                if(podePagar && opc < opcs.length-2){
                    System.out.println("\n"+ AuxLib.estiloTXT5("Perfeito!") +" Daqui a alguns anos você estará formado!");
                    System.out.println("Até lá é só "+ AuxLib.estiloTXT4("MUITO") +" sofrimento mesmo.");
                    AuxLib.aguarde(5);
                } else if(!podePagar){
                    System.out.print("Parece que você "+ AuxLib.estiloTXT1("não conseguiu pagar") +". Tente novamente! \n");
                    AuxLib.aperteEnter();
                    opc = opcs.length;
                }
            }

        }while(opc == opcs.length && !podePagar);
        
        if(opc==opcs.length-1){
            //escolheu não fazer nada
            return "Teve a chance de fazer um curso superior, mas decidiu que não precisa disso pra ser infeliz, bastava trabalhar. ";
        } else {
            //escolheu algum curso
            curso = strOpcs[opc-1];
            player.setFormacaoTecnica(curso);
            return "Batalhou bastante e depois de muito tempo se formou em "+ curso + ". ";
        }
    }

    @Override
    public String fechamento(){
        int opc;
        float precoEscolhido;
        boolean podePagar = false;
        String senha, destinoFinal;
            
        //informações
        String[] opcs = opcsFechamento, aux, strOpcs = new String[opcs.length];
        float[] valorOpcs = new float[opcs.length];

        //trata os dados do menu
        for(int x = 0; x<opcs.length-1; x++){
            //faz o split
            aux = opcs[x].split("---+");
            
            //armazena nos devidos vetores
            strOpcs[x]=aux[0].trim().toLowerCase();
            
                //retira o espaço externo
            aux[1]= aux[1].trim();
                //faz um outro split ("R$  xxxx,xx") = ("xxxx,xx") e fica com a segunda parte, onde está o valor
            aux[1] = aux[1].split("\\s+")[1];
                //transforma para float
            valorOpcs[x] = Float.parseFloat(aux[1]);
        }
        
        //apresentação das opções
        do{
            AuxLib.limpaTela();  
            System.out.println(AuxLib.estiloTXT3("Que bom que você chegou até aqui!") +" Já trabalhou");
            System.out.println("bastante e agora é o momento de você descansar.\n");
            System.out.println(AuxLib.estiloTXT2("Escolha o destino final da tua vida:"));
            //pega a opc do jogador
            AuxLib.exibeOpcs(opcs);
            opc = AuxLib.getOpc(opcs.length);
            
            //testa a opc escolhida
            if(opc==opcs.length){
                //exibe o saldo da conta
                AuxLib.limpaTela();
                player.printInfoConta();
                AuxLib.aperteEnter();
            } else {
                //se o jogador não escolheu ver o saldo, pega o valor do que foi escolhido
                precoEscolhido = valorOpcs[opc-1];
                
                if(precoEscolhido==0){
                    podePagar = true;
                }else{
                    //tenta pagar
                    System.out.print("\nDigite sua senha para poder seguir o seu destino: ");
                    senha = AuxLib.getSenhaConta();
                    podePagar = player.sacar(precoEscolhido, senha);
                }
                
                //verifica se pode pagar pelo curso
                if(podePagar && opc < opcs.length-1){
                    
                } else if(!podePagar){
                    System.out.print("Parece que você "+ AuxLib.estiloTXT1("não conseguiu pagar") +". Tente novamente! \n");
                    opc = opcs.length;
                    AuxLib.aperteEnter();
                }
            }

        }while(opc == opcs.length && !podePagar);
    
        System.out.println("Que assim seja! "+AuxLib.estiloTXT5("Felicidades")+" pra ti!");
        AuxLib.aguarde(5);
        destinoFinal = strOpcs[opc-1];
        return "Como desfecho de vida, decidiu "+ destinoFinal + ", e assim finalizou sua jornada. ";
    }
}
