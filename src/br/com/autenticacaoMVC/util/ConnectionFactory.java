package br.com.autenticacaoMVC.util;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class ConnectionFactory {

	public static final String PATH = "jdbc:postgresql://localhost:5432/db_adas"; // nome da base do banco de dados.
	public static final String USER = "postgres";
	public static final String PASSWORD = "admin";

	/**
	 * metodo utilizado para abrir a conexao com o banco de dados
	 * 
	 * @return retorno objeto da classe connection.
	 * @throws Exception
	 */

	public static Connection getConnection() throws Exception {
		try {
			Class.forName("org.postgresql.Driver");
			return DriverManager.getConnection(PATH, USER, PASSWORD);
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}

	}
	//metodo publico utilizado para chamar o fechamento de dados que é privado
	public static void closeConnetion(Connection conn, Statement stmt, ResultSet rs) throws Exception{
		close(conn, stmt,rs);
	}
	public static void closeConnetion(Connection conn, Statement stmt) throws Exception{
		close(conn, stmt,null);
	}
	public static void closeConnetion(Connection conn) throws Exception{
		close(conn, null,null);
	}
	
	
	/**
	 * metodo utilizado para fechar as conexoes com o banco de dados.
	 * 
	 * @param conn
	 * @param stmt
	 * @param rs
	 * @throws Exception
	 */
	private static void close(Connection conn, Statement stmt, ResultSet rs) throws Exception {
		try { // if para testar as 3 conexoes para fechar o banco.
			if (rs != null) {
				rs.close();
			}
			if (stmt != null) {
				stmt.close();
			}
			if (conn != null) {
				conn.close();
			}
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}
}
