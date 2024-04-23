package br.com.autenticacaoMVC;

import br.com.autenticacaoMVC.controller.UsuarioController;
import br.com.autenticacaoMVC.model.Usuario;

public class Main {

	public static void main(String[] args) {
		
		UsuarioController controller = new UsuarioController();
//		Usuario usuario = new Usuario();
//		usuario.setNome("Harry");
//		usuario.setEmail("harry@proway.com.br");
//		usuario.setSenha("Harry123");
//		usuario.setIsAtivo(true);
//		
//		controller.cadastrar(usuario);
		
		String email = "jaine.rosa_11@live.com";
		String senha = "jaine123";
		
		controller.autenticar(email, senha);
	}

}
