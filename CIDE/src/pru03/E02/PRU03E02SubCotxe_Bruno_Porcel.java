package pru03.E02;

public class PRU03E02SubCotxe_Bruno_Porcel extends PRU03E02Cotxe_Bruno_Porcel {
	protected MarxaManual marxa_man;
	protected MarxaAutomatic marxa_aut;

	//Constructors--------------------------------
		//Constructor per a cotxe manual
	public PRU03E02SubCotxe_Bruno_Porcel(String marca, String model, TipusCanvi tipuscanvi,EstatsMotorCotxe motor){
		super(marca,model,tipuscanvi,motor);
		this.tipuscanvi = TipusCanvi.CanviManual;
	}

	//Metodes per canviar de marxa propis de la clase
		//Manual
	public MarxaManual canviarMarxaManual (char puja_baixa) throws Exception{

			//Pujar marxes
		if(puja_baixa == '+') {
			switch(this.marxa_man) {
				case primera: this.marxa_man = MarxaManual.segona;
					break;
				case segona: this.marxa_man = MarxaManual.tercera;
					break;
				case tercera: this.marxa_man = MarxaManual.cuarta;
					break;
				case cuarta: this.marxa_man = MarxaManual.quinta;
					break;
				case quinta: this.marxa_man = MarxaManual.sexta;
					break;
				case enrera: this.marxa_man = MarxaManual.primera;
					break;
				case sexta: throw new Exception ("Error: Em sap greu, el cotxe no te més marxes...");

			}
			//Baixar marxes
		}else {
			switch(this.marxa_man) {
				case sexta: this.marxa_man = MarxaManual.quinta;
					break;
				case quinta: this.marxa_man = MarxaManual.cuarta;
					break;
				case cuarta: this.marxa_man = MarxaManual.tercera;
					break;
				case tercera: this.marxa_man = MarxaManual.segona;
					break;
				case segona: this.marxa_man = MarxaManual.primera;
					break;
				case primera: this.marxa_man = MarxaManual.enrera;
					break;
				case enrera: throw new Exception ("Error: Me permi que encara no hi ha cotxes amb marxes negatives...");
				
			}
		}
		return this.marxa_man;
	}

		//Automatic
	public MarxaAutomatic canviarMarxaAutomatic(char puja_baixa) throws Exception{
		
			//Pujar marxes
		if(puja_baixa == '+') {
			switch(this.marxa_aut) {
				case N: this.marxa_aut = MarxaAutomatic.F;
					break;
				case R: this.marxa_aut = MarxaAutomatic.N;
					break;
				case F: throw new Exception ("Error: No pots pujar mes marxes, recorda que el cotxe es automàtic...");
			}
			//Baixar marxes
		}else {
			switch(this.marxa_aut) {
				case N: this.marxa_aut = MarxaAutomatic.R;
					break;
				case F: this.marxa_aut = MarxaAutomatic.N;
					break;
				case R: throw new Exception ("Error: Ja estas anant marxa enrera.");
				
			}
		}
		return this.marxa_aut;
	}
}