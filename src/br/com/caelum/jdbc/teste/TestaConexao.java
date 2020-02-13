package br.com.caelum.jdbc.teste;

import java.sql.Connection;
import java.sql.SQLException;

import br.com.caelum.jdbc.ConnectionFactory;

public class TestaConexao {

	public static void main(String[] args) throws SQLException {
		// TODO Auto-generated method stub
		
		Connection con = new ConnectionFactory().getConnection();
		System.out.println("Conexao aberta!");
		con.close();

	}

}
