package pru03.E04;

public class PRU03E04Temps_Bruno_Porcel {
	protected int hores;
	protected int minuts;
	protected int segons;
	
	//Constructor
	public PRU03E04Temps_Bruno_Porcel(int hores, int minuts, int segons) throws Exception{
		if(hores < 0 || minuts < 0 || segons < 0) {
			throw new Exception("Error: has introduit colque valor menor que 0");
		}else {
			this.hores = hores;
			this.minuts = minuts;
			this.segons = segons;
		}	
	}
	//Constructor buid
	public PRU03E04Temps_Bruno_Porcel() {}
	
	//Metodes propis de la clase
	public PRU03E04Temps_Bruno_Porcel suma(PRU03E04Temps_Bruno_Porcel t2) {
		PRU03E04Temps_Bruno_Porcel tSuma = new PRU03E04Temps_Bruno_Porcel();
		int hores = 0,minuts = 0,segons = 0;
		
		//SEGONS
		segons = this.segons + t2.segons;
		if (segons >= 60) {
			minuts = segons / 60;
			segons = segons % 60;
		}
		
		//MINUTS
		minuts = minuts + this.minuts + t2.minuts;
		if(minuts >= 60) {
			hores = minuts / 60;
			minuts = minuts % 60;
		}
		//HORES
		hores = hores + this.hores + t2.hores;
		
		//Guardar el resultat
		tSuma.hores = hores;
		tSuma.minuts = minuts;
		tSuma.segons = segons;
		
		return tSuma;
	}
	public PRU03E04Temps_Bruno_Porcel resta(PRU03E04Temps_Bruno_Porcel t2) {
		PRU03E04Temps_Bruno_Porcel tResta = new PRU03E04Temps_Bruno_Porcel();
		int hores = 0,minuts = 0,segons = 0;
		
		//SEGONS
		segons = this.segons - t2.segons;
		if(segons < 0) {
			minuts = 1;
			segons = - segons;
		}
		
		//MINUTS
		minuts = minuts + this.minuts - t2.minuts;
		if(minuts<0) {
			minuts = -minuts;
			hores = 1;
		}
		
		//HORES
		hores = hores + this.hores - t2.hores;
		
		//Guardar el resultat
				tResta.hores = hores;
				tResta.minuts = minuts;
				tResta.segons = segons;
		
		return tResta;
	}
	
	@Override
	public String toString() {
		return this.hores+"h"+this.minuts+"m"+this.segons+"s";
	}
}