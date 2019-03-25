package pru05.E01;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

public class PRU05E01e2_Bruno_Porcel {
	//Conectar a la base de dades
	public static Statement connectToDatabase() {
		Connection connect = null;
		Statement statement = null;
		
		try {
			connect = DriverManager.getConnection("jdbc:mysql://127.0.0.1/employees?user=root&password=toor&serverTimezone=UTC");
			
			statement = connect.createStatement();
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
		
		return statement;
	}
	
	//Fer consulta
	public static void ferUpdate(Statement statement,String args) {
		String nom;
		Scanner sc = new Scanner(System.in);
		
		try {
			System.out.println("Quin vols que sigui el nou nom?");
			nom = sc.nextLine();
			statement.executeUpdate("update departments set dept_name = "+nom+" where dept_no = "+args);
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	//Consulta
	public static ResultSet ferConsulta(Statement statement) {
		ResultSet resultSet = null;
		
		try {
			statement.executeQuery("select dept_no,dept_name from departments");
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
		return resultSet;
	}
	
	//Printar resultat
	public static void printResult(ResultSet resultSet) {
		try {
			while(resultSet.next()){
				String nom = resultSet.getString("dept_name");
				
				System.out.println("El nou nom es: ");
				System.out.println(nom);
			}
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	public static void main(String[] args) {
		Statement statement;
		ResultSet resultSet;
		
		if(args.length > 0) {
			statement = connectToDatabase();
			ferUpdate(statement,args[0]);
			resultSet = ferConsulta(statement);
			printResult(resultSet);
		}else {
			System.out.println("No has passat cap argument");
		}
	}
}
