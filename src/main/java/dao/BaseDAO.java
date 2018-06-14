package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class BaseDAO {
	public static Connection getConection() {
		try {
			String url = "jdbc:postgresql://localhost:5432/vendas";
			String user = "postgres";
			String pass = "981539874";
			return DriverManager.getConnection(url, user, pass);
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}

	}

	public static void main(String[] args) {
		System.out.println(BaseDAO.getConection());
	}
}
