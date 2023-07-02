package historias;

import add.AuxLib;
import jogadores.Jogador;
import jogadores.JogadorPF;

public class HPessoaFisica_I extends Historia{

    protected boolean ehJogadorCorreto(Jogador p){
        return p instanceof JogadorPF;
    }
    
    public HPessoaFisica_I(Jogador p){
        super(p);
        if(ehJogadorCorreto(p)){
            System.out.println("Essa é a sua história!");
            hValida = true;
        } else {
            erroCriacaoHistoria();
        }
    }

    public void play(){
        if(hValida){
            telaPlay("da pessoa física, edição I,");
            //chama o primeiro capítulo da história
        }
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
                saldoDisp = player.getSaldo()+player.getLimiteDisponivel();
                if(precoEscolhido<=saldoDisp){
                    //pode finalizar a história
                    fimDaHistoria = true;
                    return "Como desfecho de vida, "
                } else {
                    //escolha novamente
                }
            }
            
            

            System.out.println("Muito bem! Ótima escolha!");
        }
    }
}
