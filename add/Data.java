package add;

public class Data{
	private int dia, mes, ano;
	private boolean valido=false;
	private final String[] nomeMeses = {"Janeiro","Fevereiro", "Março", "Abril", "Maio", "Junho", "Julho", "Agosto", "Setembro", "Outubro", "Novembro", "Dezembro"}; 
	
	//Método de validação -------------------------------------------------------------------------------------
	private boolean verificarData(int dd, int mm, int aa){
		boolean valido = true;
		if(dd<=0 || dd>31) valido=false;
		if(mm<=0 || mm>12) valido=false;
		this.valido=valido;
		return valido;
	}	
	
	// Construtor --------------------------------------------------------------
	public Data(int d, int m, int a){
		if(verificarData(d, m, a)){
			dia = d;
			mes = m;
			ano = a;
		} else {
            System.out.println("Essa data não pode ser criada");
        }
	}

	//Métodos públicos ----------------------------------------------------------------
	public String toString(){
        String texto;
		if(!valido){
            texto = "Essa data não é válida";
		} else {
            int m = mes-1;
            texto = ano<0? dia+" de "+nomeMeses[m]+" de "+ (ano*(-1))+ " A.C.": dia+" de "+nomeMeses[m]+" de "+ano;
        }
        return texto;
    }

    public boolean getValido() {
        return valido;
    }
}