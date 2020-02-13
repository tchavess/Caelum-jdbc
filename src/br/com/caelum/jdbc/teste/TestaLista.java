package br.com.caelum.jdbc.teste;

import java.text.SimpleDateFormat;
import java.util.List;

import br.com.caelum.jdbc.dao.ContatoDao;
import br.com.caelum.jdbc.dao.FuncionarioDao;
import br.com.caelum.jdbc.model.Contato;
import br.com.caelum.jdbc.model.Funcionario;

public class TestaLista {

	public static void main(String[] args) {

		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

		// crie um contatoDao
		ContatoDao dao = new ContatoDao();

		// Lista os contatos com o dao
		List<Contato> contatos = dao.getLista();

		// contato por id
		Contato cont = dao.findById(3);
		System.out.println("Busca por id:");
		System.out.println("Nome: " + cont.getNome());
		System.out.println("Email: " + cont.getEmail());
		System.out.println("Endereco: " + cont.getEndereco());
		System.out.println("Data de nascimento: " + sdf.format(cont.getDataNascimento().getTime()) + "\n");

		System.out.println("Busca listagem:");

		// traz toda lista de contatos
		for (Contato contato : contatos) {
			System.out.println("Nome: " + contato.getNome());
			System.out.println("Email: " + contato.getEmail());
			System.out.println("Endereco: " + contato.getEndereco());
			System.out.println("Data de nascimento: " + sdf.format(contato.getDataNascimento().getTime()) + "\n");
		}

		// lista de funcionarios
		Funcionario funcionario = new Funcionario();
		FuncionarioDao daoFunc = new FuncionarioDao();
		List<Funcionario> funcionarios = daoFunc.getLista();
		for (Funcionario func : funcionarios) {
			System.out.println("Nome: " + func.getNome());
			System.out.println("Usuario: " + func.getUsuario());
			System.out.println("Senha: " + func.getSenha());
			

		}
	}
}
