package historias;

import add.AuxLib;
import jogadores.Jogador;

public abstract class Historia {
    protected boolean hValida;

    protected void erroCriacaoHistoria(){
        System.out.println("Você não pode inicializar essa história");
    }

    protected void msgStart(){
        System.out.println("Essa é a "+ AuxLib.estiloTXT2("sua") +" história!");
    }

    protected Historia(){
        hValida = false;
    }

    protected void telaPlay(String tipoJogador){
        AuxLib.limpaTela();
        System.out.println("A história "+ tipoJogador +" começa "+ AuxLib.estiloTXT4("aqui"));
        AuxLib.aguarde(3);
    }

    public abstract void play();

    protected abstract boolean ehJogadorCorreto(Jogador p);
}
