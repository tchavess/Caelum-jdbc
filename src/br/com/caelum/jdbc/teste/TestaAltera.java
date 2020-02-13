package br.com.caelum.jdbc.teste;

import java.util.Calendar;

import br.com.caelum.jdbc.dao.ContatoDao;
import br.com.caelum.jdbc.model.Contato;

public class TestaAltera {

	public static void main(String[] args) {
		
		Contato contato =  new Contato();
		
		//crie um contatoDao
		ContatoDao dao = new ContatoDao();
		
		contato.setNome("Jose Alves");
		contato.setEmail("jalves@caelum.com.br");
		contato.setEndereco("Rua bla bla bla 195");
		contato.setDataNascimento( Calendar.getInstance());
		contato.setId(2);
		
		dao.altera(contato);
		
	
		System.out.println("Alterado!");

	}

}
