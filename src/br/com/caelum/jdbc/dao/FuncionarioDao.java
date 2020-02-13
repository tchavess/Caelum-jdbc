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
import br.com.caelum.jdbc.model.Funcionario;
import sun.java2d.StateTracker;

public class FuncionarioDao {

	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	// a conexao com o BD
	private Connection connection;

	public FuncionarioDao() {
		this.connection = new ConnectionFactory().getConnection();
	}

	public void adiciona(Funcionario funcionario) {

		String sql = "insert into funcionarios" + "(nome,usuario,senha)" + "values(?,?,?)";

		try {
			// prepared statement para insercao
			PreparedStatement stmt = connection.prepareStatement(sql);

			// seta os valores
			stmt.setString(1, funcionario.getNome());
			stmt.setString(2, funcionario.getUsuario());
			stmt.setString(3, funcionario.getSenha());

			// executa
			stmt.execute();
			stmt.close();

		} catch (SQLException ex) {
			throw new RuntimeException(ex);
		}
	}

	public List<Funcionario> getLista() {

		try {

			List<Funcionario> funcionarios = new ArrayList<Funcionario>();
			PreparedStatement stmt = this.connection.prepareStatement("select * from funcionarios");
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				// criando o objeto funcionario
				Funcionario funcionario = new Funcionario();
				funcionario.setId(rs.getLong("id"));
				funcionario.setNome(rs.getString("nome"));
				funcionario.setUsuario(rs.getString("usuario"));
				funcionario.setSenha(rs.getString("senha"));

				// adicionando o objeto a lista
				funcionarios.add(funcionario);
			}

			rs.close();
			stmt.close();

			return funcionarios;
		} catch (SQLException ex) {
			throw new DAOException(ex);
		}
	}

	public Funcionario findById(int id) {

		try {
			Funcionario func = new Funcionario();
			PreparedStatement stmt = this.connection.prepareStatement("select * from funcionarios where id=?");
			stmt.setLong(1, id);
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				// criando o objeto funcionario
				Funcionario funcionario = new Funcionario();
				funcionario.setId(rs.getLong("id"));
				funcionario.setNome(rs.getString("nome"));
				funcionario.setUsuario(rs.getString("usuario"));
				funcionario.setSenha(rs.getString("senha"));

				func = funcionario;

			}

			rs.close();
			stmt.close();

			return func;
		} catch (SQLException ex) {
			throw new DAOException(ex);
		}
		

	}

	public void altera(Funcionario funcionario) {

		String sql = "update funcionarios set nome=?, usuario=?," + "senha=? where id=?";

		try {
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setString(1, funcionario.getNome());
			stmt.setString(2, funcionario.getUsuario());
			stmt.setString(3, funcionario.getSenha());
			stmt.setLong(5, funcionario.getId());

			stmt.execute();
			stmt.close();
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
	}

	public void remove(Funcionario funcionario) {

		try {

			PreparedStatement stmt = connection.prepareStatement("delete from funcionarios where id=?");

			stmt.setLong(1, funcionario.getId());
			stmt.execute();
			stmt.close();
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
	}

}
