package br.com.autenticacaoMVC.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.autenticacaoMVC.model.Usuario;
import br.com.autenticacaoMVC.util.ConnectionFactory;



public class UsuarioDAOImpl implements GenericDao {

	private Connection conn;

	public  UsuarioDAOImpl() throws Exception {
		try {
			this.conn = ConnectionFactory.getConnection();
			System.out.println("Conectado com sucesso!");
		} catch (Exception ex) {
			throw new Exception(ex.getMessage());
		}
	}

	@Override
	public List<Object> listarTodos() {
		List<Object> lista = new ArrayList<Object>();
		PreparedStatement stmt = null; 
		ResultSet rs = null;
		String sql = "SELECT id, nome, email FROM usuario ORDER BY nome";

		try {
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();
			while (rs.next()) { 
				Usuario usuario = new Usuario();
				usuario.setId(rs.getInt("id"));
				usuario.setNome(rs.getString("nome"));
				usuario.setEmail(rs.getString("email"));				
				lista.add(usuario);
			}
		} catch (SQLException ex) {
			System.out.println("Problemas na DAO ao listar Usuário Erro: " + ex.getMessage());
			ex.printStackTrace();
		} finally { 
			try {
				ConnectionFactory.closeConnetion(conn, stmt, rs);
			} catch (Exception ex) {
				System.out.println("Problemas ao fechar a conexão! Erro: " + ex.getMessage());
			}
		}

		return lista;
	}

	@Override
	public Object listarPorId(int id) {
		Usuario usuario = null;
		PreparedStatement stmt = null; 
		ResultSet rs = null;
		String sql = "SELECT id, nome, email FROM usuario WHERE id = ? ";

		try {
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, id);
			rs = stmt.executeQuery();
			
			if (rs.next()) { 
				usuario = new Usuario();
				usuario.setId(rs.getInt("id"));
				usuario.setNome(rs.getString("nome"));
				usuario.setEmail(rs.getString("email"));
							}
		} catch (SQLException ex) {
			System.out.println("Problemas na DAO ao listar Usuário! Erro: " + ex.getMessage());
			ex.printStackTrace();
		} finally { 
			try {
				ConnectionFactory.closeConnetion(conn, stmt, rs);
			} catch (Exception ex) {
				System.out.println("Problemas ao fechar a conexão! Erro: " + ex.getMessage());
			}
		}

		return usuario;
	}
	

	@Override
	public Boolean cadastrar(Object object) {
		Usuario usuario = (Usuario) object;
		PreparedStatement stmt = null;
		String sql = "INSERT INTO usuario (nome, email, senha, isAtivo) VALUES(?,?,MD5(?),?)";
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, usuario.getNome());
			stmt.setString(2, usuario.getEmail());
			stmt.setString(3, usuario.getSenha());
			stmt.setBoolean(4, usuario.getIsAtivo());
			stmt.execute();
			return true;
		} catch (SQLException ex) {
			System.out.println("Problemas na DAO ao cadastrar Usuario! Erro: " + ex.getMessage());
			ex.printStackTrace();
			return false;
		} finally {
			try {
				ConnectionFactory.closeConnetion(conn, stmt);
			} catch (Exception ex) {
				System.out.println("Problemas ao fechar a conexão! Erro: " + ex.getMessage());
				ex.printStackTrace();
			}
		}

	}

	@Override
	public Boolean alterar(Object object) {
		Usuario usuario = (Usuario) object;
		PreparedStatement stmt = null;
		String sql = "UPDATE usuario SET nome = ?, email = ?, isAtivo = ? WHERE id = ?";
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, usuario.getNome());
			stmt.setString(2, usuario.getEmail());
			stmt.setBoolean(3, usuario.getIsAtivo());
			stmt.setInt(4, usuario.getId());
			stmt.execute();
			return true;
		} catch (SQLException ex) {
			System.out.println("Problemas na DAO ao alterar Usuario! Erro: " + ex.getMessage());
			ex.printStackTrace();
			return false;
		} finally {
			try {
				ConnectionFactory.closeConnetion(conn, stmt);
			} catch (Exception ex) {
				System.out.println("Problemas ao fechar a conexão! Erro: " + ex.getMessage());
				ex.printStackTrace();
			}
		}

	}

	@Override
	public void excluir(int id) {
		PreparedStatement stmt = null;
		String sql = "DELETE FROM usuario WHERE id = ?";
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, id);
			stmt.executeUpdate();
		} catch (SQLException ex) {
			System.out.println("Problemas na DAO ao excluir Usuário! Erro: " + ex.getMessage());
			ex.printStackTrace();
		} finally {
			try {
				ConnectionFactory.closeConnetion(conn, stmt);
			} catch (Exception ex) {
				System.out.println("Problemas ao fechar a conexão! Erro: " + ex.getMessage());
				ex.printStackTrace();
			}
		}
	}
	
	public boolean autenticar(String email, String senha) {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql = "SELECT id, nome FROM usuario WHERE "
				+ " email = ?"
				+ " AND senha = MD5(?)";

		try {
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, email);
			stmt.setString(2, senha);
			rs = stmt.executeQuery();

			if(rs.next()) {
				return true;
			} else {
				return false;
			}

		} catch (SQLException ex) {
			System.out.println("Problemas na DAO ao autenticar usuário.");
			ex.printStackTrace();
			return false;
		} finally {
			try {
				ConnectionFactory.closeConnetion(conn, stmt);
			} catch (Exception e) {
				System.out.println("Problemas para fechar conexão!");
				e.printStackTrace();
			}
		}
	}

}
