package historias;

import add.AuxLib;
import jogadores.Jogador;

public abstract class Historia {
    protected boolean hValida;

    private boolean jogadorValido(Jogador p){
        return p.getNome()!="";
    }

    protected void erroCriacaoHistoria(){
        System.out.println("Você não pode inicializar essa história");
    }

    protected Historia(Jogador p){
        if(jogadorValido(p)){
            hValida = false;
        } else {
            System.out.println("A história não pode ser criada. Verifique o jogador");
        }
    }

    protected void telaPlay(String tipoJogador){
        AuxLib.limpaTela();
        System.out.println("A história "+ tipoJogador +" começa aqui");
        AuxLib.aguarde(3);
    }

    protected abstract void play();

    protected abstract boolean ehJogadorCorreto(Jogador p);
}
