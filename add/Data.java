package add;

import java.util.Calendar;

public class Data{
	private int dia, mes, ano;
	private boolean valido=false;
	private final String[] nomeMeses = {"Janeiro","Fevereiro", "Março", "Abril", "Maio", "Junho", "Julho", "Agosto", "Setembro", "Outubro", "Novembro", "Dezembro"}; 

	public Data(int d, int m, int a){
		if(verificarData(d, m, a)){
			dia = d;
			mes = m;
			ano = a;
		} else {
            System.out.println("Essa data não pode ser criada");
        }
	}

	public Data(){
		this(Calendar.getInstance().get(Calendar.DAY_OF_MONTH));
	}

	public Data(int dia){
		this(dia, Calendar.getInstance().get(Calendar.MONTH)+1);
	}

	public Data(int dia, int mes){
		this(dia, mes, Calendar.getInstance().get(Calendar.YEAR));
	}

	private boolean verificarData(int dd, int mm, int aa){
		boolean valido = true;
		if(dd<=0 || dd>31) valido=false;
		if(mm<=0 || mm>12) valido=false;
		this.valido=valido;
		return valido;
	}	

	public String stringData(){
		String texto;
		if (valido){
			texto = ano<0? dia+"/"+mes+"/"+(ano*(-1))+" A.C.": dia+"/"+mes+"/"+ano;
		} else {
			texto = "Essa data não é válida";
		}
		return texto;
	}

	public String stringDataExtenso(){
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