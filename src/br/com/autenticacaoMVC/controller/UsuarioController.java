package br.com.autenticacaoMVC.controller;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import br.com.autenticacaoMVC.DAO.GenericDao;
import br.com.autenticacaoMVC.DAO.UsuarioDAOImpl;
import br.com.autenticacaoMVC.model.Usuario;


public class UsuarioController {

	public List<Usuario> listarTodos() {

		List<Usuario> listaUsuarios = new ArrayList<Usuario>();

		try {
			GenericDao dao = new UsuarioDAOImpl(); 
			
			for (Object object : dao.listarTodos()) {
				listaUsuarios.add((Usuario) object); 
			}
		} catch (Exception e) {
			System.out.println("Erro  na Controller ao listar Usuario");
			e.printStackTrace();
		}
		return listaUsuarios;
	}
	
	public void cadastrar(Usuario usuario) {
		try {
			GenericDao dao = new UsuarioDAOImpl();
			if(dao.cadastrar(usuario)) {
				JOptionPane.showMessageDialog(null, "Usuario cadastrado com sucesso!");
			} else {
				JOptionPane.showMessageDialog(null, "Problemas ao cadastrar usuario.");
			}
		} catch (Exception e) {
			e.printStackTrace();
		
		}
	}
	
	public boolean excluir(int id) {
		try {
			GenericDao dao = new UsuarioDAOImpl();
			
			if (validarId(id) == false) {
				return false;
			}
			dao.excluir(id);
		 return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	

public Usuario listarPorId(int id) {
		
		try {
			GenericDao dao = new UsuarioDAOImpl(); 
			Usuario usuario = (Usuario) dao.listarPorId(id);
			return usuario;
		} catch (Exception e) {
			System.out.println("Erro  na Controller ao listar Usuario");
			e.printStackTrace();
			return null;
		}
	
	}

	
	public void alterar(Usuario usuario) {
		try {
			GenericDao dao = new UsuarioDAOImpl();
			if(validarId(usuario.getId()) == false) {
				JOptionPane.showMessageDialog(null, "Nenhum encontrado para o ID " + usuario.getId());
				return;
			}
			if(dao.alterar(usuario) == true) {
				JOptionPane.showMessageDialog(null, "Usuario alterado com sucesso!");
			} else {
				JOptionPane.showMessageDialog(null, "Problemas ao alterar usuario.");
			}
		} catch (Exception e) {
			e.printStackTrace();
		
		}
	}
	
	private boolean validarId (int id) {
		try {
GenericDao dao = new UsuarioDAOImpl();
			
			Usuario usuario = (Usuario) dao.listarPorId(id);
			if (usuario == null) {
				return false;
			} else {
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	
	public void autenticar(String email, String senha) {
		try {
			UsuarioDAOImpl dao = new UsuarioDAOImpl();
			if (dao.autenticar(email, senha)) {
				JOptionPane.showMessageDialog(null, "Usuário autenticado com sucesso!");
			} else {
				JOptionPane.showMessageDialog(null, "Email ou Senha estão inválidos!");
			};
			
		} catch (Exception e) {
			e.printStackTrace();
			
		}
	}
	
	
}
