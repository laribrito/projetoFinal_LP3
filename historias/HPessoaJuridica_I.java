package historias;

import jogadores.JogadorPJ;

public class HPessoaJuridica_I extends Historia {
    
    public HPessoaJuridica_I(JogadorPJ p){
        super(p);
    }

    public void play(){
        System.out.println("A história da pessoa juridica começa aqui");
    }
}
