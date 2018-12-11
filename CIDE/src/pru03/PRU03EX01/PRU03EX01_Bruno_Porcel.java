package pru03.PRU03EX01;

import java.util.Scanner;

import pru03.PRU03EX01.AlumneReal.Modul_Matricula;


public class PRU03EX01_Bruno_Porcel {
	
	//Printar asignatures
	public static void mostraAsignatures() {
		System.out.println("Aquestes son les asignatures disponibles: (N' HAS D' ELEGIR 2 PER ALUMNE)");
		System.out.println("Programació, Llenguatge de marques, FOL, Entorns de desenvolupament, Base de dades i Sistemes Informàtics.");
	}
	
	//Matricular als alumnes
	public static void matricular(AlumneReal alumne) {
		boolean error = true;
		String asignatura = "";
		
		//Matricula alumne
		//Primera asignatura
		System.out.println("Matricular a nen "+alumne.nom+" de:");
		asignatura = sc.nextLine();
		while(error) {
			try {
				alumne.matricularModul(asignatura);
				error = false;
			}catch(Exception e){
				System.out.println(e.getMessage());
				error = true;
				System.out.println("Escriu el nom de la asignatura correctament: ");
				asignatura = sc.nextLine();
			}
		}
		
		error = true;
		//Segona asignatura
		System.out.println("I de: ");
		asignatura = sc.nextLine();
		while(error) {
			try {
				alumne.matricularModul(asignatura);
				error = false;
			}catch(Exception e){
				System.out.println(e.getMessage());
				error = true;
				System.out.println("Escriu el nom de la asignatura correctament: ");
				asignatura = sc.nextLine();
			}
		}
		
		error = true;
	}
	
	//Examinar als alumnes
	public static void examinar(AlumneReal alumne) {
		String asignatura;
		int nota;
		boolean error = true;
		
		System.out.println("De que examen se examina "+alumne.nom);
		asignatura = sc.nextLine();
		
		//Examen Alumne
		//Primer examen
		while(error) {
			try{
				nota = alumne.ferExamen(asignatura);
				System.out.println("Nota de "+asignatura+": "+nota);
				error = false;
			}catch(Exception e) {
				System.out.println(e.getMessage());
				error = true;
				System.out.println("Selecciona una asignatura de la cual se haya matriculado porfavor: ");
				asignatura = sc.nextLine();
			}
		}
		System.out.println("I de: ");
		asignatura = sc.nextLine();
		error = true;
		
		//Segon examen
		while(error) {
			try{
				nota= alumne.ferExamen(asignatura);
				System.out.println("Nota de "+asignatura+": "+nota);
				error = false;
			}catch(Exception e) {
				System.out.println(e.getMessage());
				error = true;
				System.out.println("Selecciona una asignatura de la cual se haya matriculado porfavor: ");
				asignatura = sc.nextLine();
			}
		}
	}
	
	//Scanner
	static Scanner sc = new Scanner(System.in);
	
	//MAIN----------------------------------------------------------------------------------------------
	public static void main(String[] args) {
		//Instanciam dos alumnes.
		AlumneReal bruno = new AlumneReal("Bruno");
		AlumneReal marc = new AlumneReal("Marc");
		
		mostraAsignatures();
		matricular(bruno);
		matricular(marc);
		examinar(bruno);
		examinar(marc);
		System.out.println("FI DEL PROGRAMA");
	}
}