package br.com.fiap.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBManager {
    
    // methods
    public static Connection getConnection() {
        Connection conexao = null;
		try {
			Class.forName("oracle.jdbc.OracleDriver");
			conexao = DriverManager.getConnection("jdbc:oracle:thin:@oracle.fiap.com.br:1521:orcl", "rm552270", "030604");
			//banco utilizado: conex�o:porta, usu�rio, senha
		} catch(SQLException erro) {
			erro.printStackTrace();
		} catch(ClassNotFoundException e) {
			e.printStackTrace();
		}
		return conexao;
    }
}