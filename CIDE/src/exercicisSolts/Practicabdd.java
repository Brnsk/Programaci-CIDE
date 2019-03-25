package exercicisSolts;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Practicabdd {
	private Connection connect = null;
	private Statement statement = null;
	private ResultSet resultSet = null;
	
	
	public Practicabdd() {
		this.statement = conectarBdd();
		executeUpdate();
	}
	
	private Statement conectarBdd() {
		try {
			this.connect = DriverManager.getConnection("jdbc:mysql://localhost/employees?user=root&password=toor&serverTimezone=UTC");
			this.statement = connect.createStatement();
		}catch(Exception e) {
			System.out.println(e);
		}
		return statement;
	}
	
	private void executeUpdate() {
		try {
			statement.executeUpdate("insert ignore into employees(first_name,emp_no,birth_date,last_name,hire_date) values (\"Paquito\",99999999,\"0001-01-01\",\"Chocolatero\",\"0001-01-01\")");
			resultSet = statement.executeQuery("select * from employees where first_name = \"Paquito\" and last_name = \"Chocolatero\"");
			printResult();
			System.out.println("Exito!");
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	private void printResult() {
		try {
			while(resultSet.next()) {
				String name = resultSet.getString("first_name");
				String surname = resultSet.getString("last_name");
				int no = resultSet.getInt("emp_no");
				Date date = resultSet.getDate("birth_date");
				String date_hire = resultSet.getString("hire_Date");
				
				System.out.println(name);
				System.out.println(surname);
				System.out.println(no);
				System.out.println(date);
				System.out.println(date_hire);
			}
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	@SuppressWarnings("unused")
	public static void main(String[] args) {
		Practicabdd bdd = new Practicabdd();
	}
}