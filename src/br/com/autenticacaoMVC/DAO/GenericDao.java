package br.com.autenticacaoMVC.DAO;

import java.util.List;

public interface GenericDao {

	public List<Object> listarTodos();
	public Object listarPorId(int id);
	public Boolean cadastrar(Object object);
	public Boolean alterar(Object object);
	public void excluir (int id);
	
}
