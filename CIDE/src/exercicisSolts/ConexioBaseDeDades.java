package exercicisSolts;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class ConexioBaseDeDades {
	 private Connection connect = null;
	 private Statement statement = null;
	 private ResultSet resultSet = null;
	
	
	public void conectarBdd() {
		try {
			//CONECTARSE A LA BASE DE DADES
			connect = DriverManager.getConnection("jdbc:mysql://127.0.0.1/employees?user=root&password=toor&serverTimezone=UTC");
			
			
			statement = connect.createStatement();
			
			//RESULTAT
			resultSet = statement.executeQuery("select distinct title from titles");
			
			//PRINTAR RESULTAT DESDE EL SEGÜENT METODE
			printarResultat(resultSet);
			
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	
	public void printarResultat(ResultSet result) {
		try {
			while(result.next()) {
				String title = result.getString("title");
				System.out.println(title);
			}
			result.close();
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}
}