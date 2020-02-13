package br.com.caelum.jdbc.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import br.com.caelum.jdbc.ConnectionFactory;
import br.com.caelum.jdbc.exception.DAOException;
import br.com.caelum.jdbc.model.Contato;

public class ContatoDao {
	
	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	//a conexao com o BD
	private Connection connection;
	
	public ContatoDao() {
		this.connection = new ConnectionFactory().getConnection();
	}

	public void  adiciona(Contato contato) {
		
		String sql = "insert into contatos"+
		"(nome,email,endereco,dataNascimento)"+
				"values(?,?,?,?)";
		
		try {
			//prepared statement para insercao
			PreparedStatement stmt = connection.prepareStatement(sql);
			
			//seta os valores
			stmt.setString(1, contato.getNome());
			stmt.setString(2, contato.getEmail());
			stmt.setString(3, contato.getEndereco());
			stmt.setDate(4, new Date(contato.getDataNascimento().getTimeInMillis()));
			
			//executa
			stmt.execute();
			stmt.close();
			
		}catch(SQLException ex) {
			throw new RuntimeException(ex);
		}
	}
	
	public List<Contato> getLista(){
		
		try {
			
			List<Contato> contatos = new ArrayList<Contato>();
			PreparedStatement stmt = this.connection.prepareStatement("select * from contatos");
			ResultSet rs = stmt.executeQuery();
			
			while(rs.next()) {
				//criando o objeto contato
				Contato contato = new Contato();
				contato.setId(rs.getInt("id"));
				contato.setNome(rs.getString("nome"));
				contato.setEmail(rs.getString("email"));
				contato.setEndereco(rs.getString("endereco"));
				
				//montando a data atraves do Calendar
				Calendar data = Calendar.getInstance();
				data.setTime(rs.getDate("dataNascimento"));
				contato.setDataNascimento(data);
				
				//adicionando o objeto a lista
				contatos.add(contato);
			}
			
			rs.close();
			stmt.close();
			
			return contatos;
		}catch(SQLException ex) {
			throw new DAOException(ex);
		}
	}

	public Contato findById(int id) {
		
try {
			Contato cont = new Contato();
			PreparedStatement stmt = this.connection.prepareStatement("select * from contatos where id=?");
			stmt.setLong(1, id);
			ResultSet rs = stmt.executeQuery();
			
			while(rs.next()) {
				//criando o objeto contato
				Contato contato2 = new Contato();
				contato2.setId(rs.getInt("id"));
				contato2.setNome(rs.getString("nome"));
				contato2.setEmail(rs.getString("email"));
				contato2.setEndereco(rs.getString("endereco"));
				
				//montando a data atraves do Calendar
				Calendar data = Calendar.getInstance();
				data.setTime(rs.getDate("dataNascimento"));
				contato2.setDataNascimento(data);
				
				cont = contato2;
				
			}
			
			
			rs.close();
			stmt.close();
			
			return cont;
		}catch(SQLException ex) {
			throw new DAOException(ex);
		}
		
	}
	
	public void altera(Contato  contato) {
		
		String sql = "update contatos set nome=?, email=?,"+
		"endereco=?, dataNascimento=? where id=?";
		
		try {
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setString(1, contato.getNome());
			stmt.setString(2, contato.getEmail());
			stmt.setString(3, contato.getEndereco());
			stmt.setDate(4, new Date(contato.getDataNascimento().getTimeInMillis()));
			stmt.setLong(5, contato.getId());
			
			stmt.execute();
			stmt.close();
		}catch(SQLException ex) {
			throw new DAOException(ex);
		}
	}
	
	public void remove(Contato contato) {
		
		try {
			
			PreparedStatement stmt = connection.prepareStatement("delete from contatos where id=?");
			
			stmt.setLong(1, contato.getId());
			stmt.execute();
			stmt.close();
		}catch(SQLException ex) {
			throw new DAOException(ex);
		}
	}
}
