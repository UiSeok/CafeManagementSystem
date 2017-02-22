package tmon.manager;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import tmon.cafe.Barista;
import tmon.data.entity.Beverage;

public class DBManager {

	private static DBManager instance;

	public static DBManager getInstnace() {
		if (instance == null) {
			instance = new DBManager();
		}
		return instance;
	}

	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver"; // Use MySQL and JDBC
	static final String DB_URL = "jdbc:mysql://127.0.0.1:3306/test"; // local DB Access
	static final String USERNAME = "root";
	static final String PASSWORD = "k3898k";

	private static Statement stmt = null;
	private static Connection conn = null;

	private DBManager() {
		conn = getConnection();

	}

	private static Connection getConnection() {
		try {
			if (conn == null) {
				Class.forName(JDBC_DRIVER);
				return DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
			} else {
				return conn;
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			return null;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	public void insertEmployee(Barista data) {
		try {
			stmt = null;
			stmt = conn.createStatement();
			String sql;
			sql = "INSERT INTO employee values(";
			sql += data.getId() + ", ";
			sql += data.getName() + ", ";
			sql += data.getAge() + ", ";
			sql += data.getSalary() + ", ";
			sql += data.getJoin_date() + ", ";
			sql += data.getRetire_date();
			sql += ");";
			ResultSet rs = stmt.executeQuery(sql);

			while (rs.next()) {
				String name = rs.getString("name");
				System.out.print("\nName : " + name);

			}
			rs.close();
			stmt.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public List<Barista> getBaristas() {
		List<Barista> baristas = new ArrayList<Barista>();

		try {

			stmt = null;
			stmt = conn.createStatement();

			String sql;
			sql = "SELECT * FROM employee";
			ResultSet rs = stmt.executeQuery(sql);

			while (rs.next()) {
				String id = rs.getString("id");
				String name = rs.getString("name");
				String age = rs.getString("age");
				String salary = rs.getString("salary");
				String join = rs.getString("join_day");
				String retire = rs.getString("retire_day");

				baristas.add(new Barista(Integer.parseInt(id), name,
						Integer.parseInt(age), null, null, Integer
								.parseInt(salary)));
			}
			rs.close();
			stmt.close();
		

		} catch (Exception e) {
			e.printStackTrace();
		}

		return baristas;
	}

	public void showAllMembers() {
		try {

			stmt = null;
			stmt = conn.createStatement();

			String sql;
			sql = "SELECT * FROM employee";
			ResultSet rs = stmt.executeQuery(sql);

			while (rs.next()) {
				String id = rs.getString("id");
				String name = rs.getString("name");
				String age = rs.getString("age");
				String salary = rs.getString("salary");
				String join = rs.getString("join_day");
				String retire = rs.getString("retire_day");

				System.out.print("ID : " + id);
				System.out.print("\nName : " + name);
				System.out.print("\nAge : " + age);
				System.out.print("\nSalary : " + salary);
				System.out.print("\nJoin_Date : " + join);
				System.out.print("\nRetire_Date : " + retire);

			}
			rs.close();
			stmt.close();
		
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public List<Beverage> getMenu() {
		List<Beverage> beverages = new ArrayList<Beverage>();
		
		try {

			stmt = null;
			stmt = conn.createStatement();

			String sql;
			sql = "SELECT * FROM menu";
			ResultSet rs = stmt.executeQuery(sql);

			while (rs.next()) {
				String id = rs.getString("id");
				String name = rs.getString("name");
				beverages.add(new Beverage(Integer.parseInt(id), name, 0));
			}
			rs.close();
			stmt.close();
			

		} catch (Exception e) {
			e.printStackTrace();
		}

		return beverages;
	}
	
	public void removeConnection(){
		try {
			if( stmt!= null )
				stmt.close();
				stmt = null;
			if( conn != null ) {
				conn.close();
				conn = null;
			}


		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
