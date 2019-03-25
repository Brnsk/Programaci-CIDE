package puru04.E05;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PRU04E05Parking_Bruno_Porcel {
	//ATRIBUTS
	protected int places_discapacitats;
	protected int places_no_discapacitats;
	TipusPlacesParking tipus;

	//CONSTRUCTOR
	public PRU04E05Parking_Bruno_Porcel(int places_no_discapacitats,int places_discapacitats) {
		this.places_no_discapacitats = places_no_discapacitats;
		this.places_discapacitats = places_discapacitats;
	}

	//METODES PROPIS DE LA CLASSE
	//Comprobar que el fitxer existeix i te el format correcte
	public void llegirMatricules(String path) throws Exception{
		File f = new File(path);

		if(f.exists()) {
			try {
				FileReader fr = new FileReader(path);	
				BufferedReader archivo = new BufferedReader(fr);
				String linea = "";
				Pattern p = Pattern.compile("^\\d{4}[A-Z]{3}");
				Matcher m;

				linea = archivo.readLine();
				while(linea != null) {
					m = p.matcher(linea);

					if(m.matches()) {
						linea = archivo.readLine();
					}else {
						fr.close();
						archivo.close();
						throw new Exception ("ALERTA =====> Matrícula incorrecte.");
					}			
				}

				fr.close();
				archivo.close();
			}catch(IOException e) {
				System.out.println(e.getMessage());
			}
		}else {
			throw new Exception("ALERTA =====> Fitxer incorrecte o inexistent.");
		}
	}

	//Entrar cotxe
	public int entraCotxe(String matricula) throws Exception{
		boolean matricula_parking;
		int garrulo;
		String matricula_garrulo = "";
		boolean matricula_ok;
		int parking_garrulo = 0;
		
		//Matricula ben formada
		matricula_ok = comprobarMatricula(matricula);
		
		//Comprobar si esta al parking
		matricula_parking = comprobaCotxeParking(matricula);
		
		//Garrulo?
		garrulo = apareixGarrulo();
		
		if(garrulo == 17) {
			matricula_garrulo = matriculaGarrulo();
			this.places_no_discapacitats--;
			places_parking.put(95-places_no_discapacitats,matricula_garrulo);
			parking_garrulo = 95-places_no_discapacitats;
		}
		
		if(matricula_ok) {
			if(!matricula_parking) {
				if(this.places_no_discapacitats > 0) {
					
					this.places_no_discapacitats--;
					places_parking.put(95-places_no_discapacitats,matricula);
					
					if(this.places_no_discapacitats == ((getPlacesLliures(TipusPlacesParking.normals)+getPlacesOcupades(TipusPlacesParking.normals))*15/100)) {
						throw new Exception("ALERTA =====> Ocupació de places per no discapacitats supera el 85%.");
					}
					
				}else {
					throw new Exception("ALERTA =====> Parking per no discapacitats ple.");
				}
			}else {
				throw new Exception("El cotxe ja està al parking. No pot entrar.");
			}
			if(garrulo == 17) {
				throw new Exception ("ALERTA =====> Garrulo detected!!! Ha aparcat a la plaça: <"+parking_garrulo+">");
			}
		}else {
			throw new Exception("Matricula incorrecta");
		}

		return this.places_no_discapacitats;
	}

	//Entrar cotxe discapacitats
	public int entraCotxeDiscapacitat(String matricula) throws Exception{
		int num_pla = 0;
		boolean matricula_ok;
		boolean matricula_parking;
		int garrulo;
		String matricula_garrulo;
		int parking_garrulo = 0;
		
		//Matricula ben formada
		matricula_ok = comprobarMatricula(matricula);
				
		//Comprobar si esta al parking
		matricula_parking = comprobaCotxeParking(matricula);
				
		//Garrulo?
		garrulo = apareixGarrulo();
				
		if(garrulo == 17) {
			matricula_garrulo = matriculaGarrulo();
			this.places_no_discapacitats--;
			places_parking.put(95-places_no_discapacitats,matricula_garrulo);
		}

		if(!matricula_parking) {
			if(matricula_ok) {
				if(this.places_discapacitats > 0) {
					
					this.places_discapacitats--;
					places_parking.put(100-places_discapacitats,matricula);
					
				}else if(this.places_discapacitats == 0){
					num_pla = entraCotxe(matricula);
					num_pla = 95-num_pla;
					throw new Exception("ALERTA =====> Parking per discapacitats ple. Ha ocupat plaça normal num: <"+num_pla+">");
				}else if(this.places_discapacitats == ((getPlacesLliures(TipusPlacesParking.discapacitats)+getPlacesOcupades(TipusPlacesParking.discapacitats))*15/100)) {
					throw new Exception("ALERTA =====> Ocupació de places per discapacitats supera el 85%.");
				}
			}else {
				throw new Exception("Matricula incorrecta");
			}
		}else {
			throw new Exception("El cotxe ja està al parking. No pot entrar.");
		}
		
		if(garrulo == 17) {
			throw new Exception("ALERTA =====> Garrulo detected!!! Ha aparcat a la plaça: <"+parking_garrulo+">");
		}

		return this.places_discapacitats;
	}

	//Sortir cotxes
	public void surtCotxe(String matricula) throws Exception{
		boolean trobat = false;
		int spot = 0;
		
		for(int i = 1; i <= 95;i++) {
			if(matricula.equals(places_parking.get(i))) {
				trobat = true;
				spot = i;
				break;
			}			
		}
			
		if(this.places_no_discapacitats < 95 && trobat) {
			this.places_no_discapacitats++;
			places_parking.remove(spot);
			if(this.places_no_discapacitats == ((getPlacesLliures(TipusPlacesParking.normals)+getPlacesOcupades(TipusPlacesParking.normals))*15/100)) {
				throw new Exception("ALERTA =====> Ocupació de places per no discapacitats supera el 85%.");
			}
		}else {
			throw new Exception("El cotxe no és al parking.");
		}
	}

	//Sortir cotxes discapacitats
	public void surtCotxeDiscapacitats(String matricula) throws Exception{
		boolean trobat = false;
		int spot = 0;
		
		for(int i = 96; i <= 100;i++) {
			if(matricula.equals(places_parking.get(i))) {
				trobat = true;
				spot = i;
				break;
			}			
		}
		
		if(this.places_discapacitats < 5 && trobat) {
			this.places_discapacitats++;
			places_parking.remove(spot);
			if(this.places_discapacitats == ((getPlacesLliures(TipusPlacesParking.discapacitats)+getPlacesOcupades(TipusPlacesParking.discapacitats))*15/100)) {
				throw new Exception("ALERTA =====> Ocupació de places per discapacitats supera el 85%.");
			}
		}else {
			throw new Exception("El cotxe no és al parking.");
		}
	}

	//Saber plaçes OCUPADES
	public int getPlacesOcupades(TipusPlacesParking tipus) {
		if(tipus.equals(TipusPlacesParking.normals)) {
			return 95-this.places_no_discapacitats;
		}else if(tipus.equals(TipusPlacesParking.discapacitats)){
			return 5-this.places_discapacitats;
		}
		return 0;
	}

	//Saber plaçes lliures
	public int getPlacesLliures(TipusPlacesParking tipus) {
		if(tipus.equals(TipusPlacesParking.normals)) {
			return this.places_no_discapacitats;
		}else if(tipus.equals(TipusPlacesParking.discapacitats)){
			return this.places_discapacitats;
		}
		return 0;
	}

	//Guardar matricules en un fitxer
	public void guardarMatricules(String path) throws Exception{
		try {
			FileWriter fw = new FileWriter(path);	
			BufferedWriter archivo = new BufferedWriter(fw);

			for(int i = 1; i <= 100;i++) {
				String matricula = places_parking.get(i);
				if(matricula != null) {
					archivo.write(matricula+SALTO_LINEA);
				}
			}
			archivo.close();
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}

	//Generar un aleatori per saber si hi ha un garrulo o no
	private int apareixGarrulo() {
		int random;
		
		random = (int)(Math.random()*50+1);
		
		return random;
	}
	
	//Generar matricula del garrulo
	private String matriculaGarrulo() {
		String matricula = "";
		String numeros = "";
		String letras = "";
		
		//Numeros de la matricula
		for(int y = 0; y < 4; y++) {
			numeros = numeros+(char)(Math.random()*9+48+1);
		}
		
		//Lletres de la matricula
		for(int y = 0; y < 3; y++) {
			letras = letras+(char)(Math.random()*25+65+1);
		}
		
		matricula = numeros + letras;
		
		return matricula;
	}
	
	//Comprobar matricula
	private boolean comprobarMatricula(String matricula) {
		Pattern p = Pattern.compile("^\\d{4}[A-Z]{3}");
		Matcher m;
		
		m = p.matcher(matricula);

		if(m.matches()) {
			return true;
		}else {
			return false;
		}
	}
	
	//Comprobar si el cotxe esta al parking
	private boolean comprobaCotxeParking(String matricula) {
		for(int i = 1; i <= 100;i++) {
			if(places_parking.containsValue(matricula)) {
				return true;
			}
		}
		return false;
	}
	
	//HASHMAP + Bot de linea
	static HashMap <Integer,String> places_parking = new HashMap <Integer,String>();
	static final String SALTO_LINEA = System.lineSeparator();

	//Tious de places ENUM
	public enum TipusPlacesParking{
		discapacitats,
		normals
	}
}