package pru03.E02;

public class PRU03E02Cotxe_Bruno_Porcel extends CotxeAbstracte implements InterfaceCotxe {
	//Atributs propis de la subclase
	protected EstatsMotorCotxe motor;
	
	//Constructor
	public PRU03E02Cotxe_Bruno_Porcel(String marca, String model, TipusCanvi tipuscanvi,EstatsMotorCotxe motor){
		super(marca,model,tipuscanvi);
		this.motor = motor;
	}
	
	//Metodes que implementam de l'interficie
	@Override
	public void arrancarMotor() throws Exception{
		if(motor.equals(EstatsMotorCotxe.Aturat)) {
			motor = EstatsMotorCotxe.EnMarxa;
		}else {
			throw new Exception ("Error: El motor ja està enllegat.");
		}
	}
	
	@Override
	public EstatsMotorCotxe comprovaMotor() {
		return this.motor;
	}
	
	@Override
	public int getRevolucions() {
		int revolucions = 0;
		
		if(this.motor.equals(EstatsMotorCotxe.EnMarxa)) {
			revolucions = (int)(Math.random()*6500+1);
		}
		
		return revolucions;
	}
	
	@Override
	public void aturarMotor() throws Exception{
		if(motor.equals(EstatsMotorCotxe.EnMarxa)) {
			motor = EstatsMotorCotxe.Aturat;
		}else {
			throw new Exception ("Error: El motor ja està aturat.");
		}
	}
}
