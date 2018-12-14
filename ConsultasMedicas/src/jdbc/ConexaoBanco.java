package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexaoBanco {
	private static Connection CONEXAO = null;
	private static String DRIVER = "com.mysql.cj.jdbc.Driver";
	private static String BANCO = "jdbc:mysql://LOCALHOST:3306/db_consulta_medicas?useTimezone=true&serverTimezone=UTC&useSSL=false";
	private static String USUARIO = "root";
	private static String SENHA = "bcd127";

	public static Connection abrirConexao(){
		try {
			Class.forName(DRIVER);
			CONEXAO = DriverManager.getConnection(BANCO, USUARIO, SENHA);
		} catch (ClassNotFoundException erro) {
			erro.printStackTrace();
		} catch (SQLException erro) {
			erro.printStackTrace();
		}
		return CONEXAO;
	}

	public static void fecharConexao(){
		if (CONEXAO != null){
			try {
				CONEXAO.close();
			} catch (SQLException erro) {
				erro.printStackTrace();
			}
		}
	}
}
