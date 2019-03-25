package pru05.E01;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class PRU05E01e1_Bruno_Porcel {
	
	//Conectar a la base de dades
	public static Statement connectToDatabase() {
		Connection connect = null;
		Statement statement = null;
		
		
		try {
			//Conectarse
			connect = DriverManager.getConnection("jdbc:mysql://127.0.0.1/employees?user=root&password=toor&serverTimezone=UTC");
			
			//Crear statement
			statement = connect.createStatement();
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
		
		return statement;
	}
	
	//Executar consulta
	public static ResultSet ferConsulta(Statement statement) {
		ResultSet resultSet = null;
		
		try {
			resultSet = statement.executeQuery("select dept_no,dept_name from departments");
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		
		return resultSet;
	}
	
	//Printar resultat
	public static void printResultSet(ResultSet resultSet) {
		try {
			System.out.println("dept_no\tdept_name");
			System.out.println("======= ==========");
			while(resultSet.next()) {
				String dept_no = resultSet.getString("dept_no");
				String dept_name = resultSet.getString("dept_name");
				System.out.println(dept_no+"\t"+dept_name);
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}
	
	public static void main(String[] args) {
		Statement statement;
		ResultSet resultSet;
		
		statement = connectToDatabase();
		resultSet = ferConsulta(statement);
		printResultSet(resultSet);
	}
}