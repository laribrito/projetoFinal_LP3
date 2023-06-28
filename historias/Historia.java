package historias;

import jogadores.Jogador;

public abstract class Historia {
    private Jogador player;

    private boolean verificaJogador(Jogador p){
        return p.getNome()=="";
    }

    protected Historia(Jogador p){
        if(verificaJogador(p)){
            player = p;
        } else {
            System.out.println("A história não pode ser criada. Verifique o jogador");
        }
    }

    abstract void play();
}
