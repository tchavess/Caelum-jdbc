package br.com.caelum.jdbc.teste;

import java.util.Calendar;

import br.com.caelum.jdbc.dao.ContatoDao;
import br.com.caelum.jdbc.dao.FuncionarioDao;
import br.com.caelum.jdbc.model.Contato;
import br.com.caelum.jdbc.model.Funcionario;

public class TestaInsere {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		//pronto para gravar
		
	Contato contato =  new Contato();
	contato.setNome("Thiago Chaves");
	contato.setEmail("tchaves@caelum.com.br");
	contato.setEndereco("R. Vergueiro 3185 cj57");
	contato.setDataNascimento(Calendar.getInstance());
	
	//grave nessa conexao
	ContatoDao dao = new ContatoDao();
	
	//metodo elegante
	dao.adiciona(contato);
	
	//Insere Funcionario
	Funcionario funcionario = new Funcionario();
	funcionario.setNome("Thiago Chaves");
	funcionario.setUsuario("tchaves");
	funcionario.setSenha("12345");
	
	FuncionarioDao daoFunc = new FuncionarioDao();
	
	daoFunc.adiciona(funcionario);

	
	System.out.println("Gravado!");

	}

}
