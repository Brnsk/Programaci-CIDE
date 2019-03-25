package exercicisSolts;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.text.SimpleDateFormat;

public class sondaTemperatura extends Sonda {
	//Atributs relatius a BDD
	private Connection connect = null;
	private Statement statement = null;
	private ResultSet resultSet = null;
	
	
	@Override
	public boolean enregistrarTemperatura(String ip, String ubicacio, double valor) {
		Date data;
		boolean registrada = false;
		statement = conexioBd();
		data = getDate();
		
		registrada = executeEnregistrarQuery(statement,ip,valor,ubicacio,data);
		
		tancarBd();
		
		return registrada;
	}
	
	@Override
	public double obtenirTemperatura(String ubicacio, Date data) {
		statement = conexioBd();
		double temperatura = 0;
		
		resultSet = executeObtenirQuery(ubicacio, data);
		
		try {
			temperatura = resultSet.getDouble("valor");
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		
		tancarBd();
		
		return temperatura;
	}
	
	//Conectar a base de dades
	private Statement conexioBd() {
		try {
			connect = DriverManager.getConnection("jdbc:mysql://172.16.26.200/Temperatures?user=alumne&password=tofol&serverTimezone=UTC");
			statement = connect.createStatement();
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
		return statement;
	}
	
	//Executar consulta INSERT
	private boolean executeEnregistrarQuery(Statement statement, String ip, double valor,String ubicacio, Date data) {
		SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		
		try {
			statement.executeUpdate("insert into Temperatures.Temperatures(2,3,4,5) values('"+ip+"' '"+formatoFecha.format(data)+"' '"+ubicacio+"' "+valor+")");
			return true;
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			return false;
		}
	}
	
	//Executar consulta SELECT
	private ResultSet executeObtenirQuery(String ubicacio, Date data) {
		SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		try {
			resultSet = statement.executeQuery("select valor from Temperatures.Temperatures where ubicacio = '"+ubicacio+"' and data = '"+formatoFecha.format(data)+"'");
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
		return resultSet;
	}
	
	//Aconseguir data
	private Date getDate() {
		Date data = new Date();
		return data;
	}
	private void tancarBd() {
		try {
			connect.close();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}
}