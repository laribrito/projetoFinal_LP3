package historias;

import jogadores.JogadorPJ;

public class HPessoaFisica_I extends Historia {
    
    public HPessoaFisica_I(JogadorPJ p){
        super(p);
    }

    public void play(){
        System.out.println("A história da pessoa juridica começa aqui");
    }
}
