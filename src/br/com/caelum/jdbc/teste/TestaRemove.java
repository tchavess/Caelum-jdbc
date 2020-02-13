package br.com.caelum.jdbc.teste;

import br.com.caelum.jdbc.dao.ContatoDao;
import br.com.caelum.jdbc.model.Contato;

public class TestaRemove {

	public static void main(String[] args) {
		
		Contato contato = new Contato();
		ContatoDao dao = new ContatoDao();
		
		contato.setId(1);
		
		dao.remove(contato);
		
		System.out.println("removido");

	}

}
