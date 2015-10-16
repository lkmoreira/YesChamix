/**
 * 
 */
package br.com.gv8.yeschamix.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import br.com.gv8.yeschamix.dto.UsuarioDTO;

/**
 * @author Bruno Pelogia
 *
 */
public class UsuarioDAO extends ConnectionFactory {
	
	private static UsuarioDAO instance = null;
	// Construtor padrão privado por causa do padrão Singleton.
	private UsuarioDAO() {
	}

	public static UsuarioDAO getInstance() {
		if (instance == null) {
			instance = new UsuarioDAO();
		}
		return instance;
	}
	
	public ArrayList<UsuarioDTO> consultarTodosUsuario(String login) {
		Connection conexao = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		conexao = criarConexao(login);

		ArrayList<UsuarioDTO> lista = new ArrayList<UsuarioDTO>();

		try {
			pstmt = conexao.prepareStatement("SELECT u.ID_USUARIO, u.NOME_USUARIO, u.LOGIN, u.SENHA, u.BLOQUEADO FROM USUARIO AS u ORDER BY u.NOME_USUARIO");
			rs = pstmt.executeQuery();

			while (rs.next()) {
				UsuarioDTO usuario = new UsuarioDTO();
				usuario.setId( rs.getInt("ID_USUARIO"));
				usuario.setNome( rs.getString("NOME_USUARIO"));
				usuario.setLogin( rs.getString( "LOGIN" ));
				usuario.setSenha( rs.getString( "SENHA" ) );
				usuario.setBloqueado( rs.getInt( "BLOQUEADO" ) );
				lista.add(usuario);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			fecharConexao(conexao, pstmt, rs);
		}
		return lista;
	}
	
	public  UsuarioDTO consultarUsuarioPorId(Integer id,String login) {
		Connection conexao = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		conexao = criarConexao(login);
		String query = "";

		 UsuarioDTO usuario = new UsuarioDTO();

		try {
			query = "SELECT u.ID_USUARIO, u.NOME_USUARIO, u.LOGIN, u.SENHA, u.BLOQUEADO FROM USUARIO AS u WHERE u.ID_USUARIO = ?";
			
			pstmt = conexao.prepareStatement(query);
			pstmt.setInt(1, id);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				usuario = new UsuarioDTO();
				usuario.setId( rs.getInt("ID_USUARIO"));
				usuario.setNome( rs.getString("NOME_USUARIO"));
				usuario.setLogin( rs.getString( "LOGIN" ));
				usuario.setSenha( rs.getString( "SENHA" ) );
				usuario.setBloqueado( rs.getInt( "BLOQUEADO" ) );

			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			fecharConexao(conexao, pstmt, rs);
		}
		return usuario;
	}
	
	public  UsuarioDTO consultarUsuarioPorLoginSenha(String login, String senha) {
		Connection conexao = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		conexao = criarConexao(login);
		String query = "";

		 UsuarioDTO usuario = new UsuarioDTO();

		try {
			query = "SELECT u.ID_USUARIO, u.NOME_USUARIO, u.LOGIN, u.SENHA, u.BLOQUEADO FROM USUARIO AS u WHERE u.LOGIN = ? AND u.SENHA = ? ";
			
			pstmt = conexao.prepareStatement(query);
			pstmt.setString(1, login);
			pstmt.setString(2, senha);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				usuario = new UsuarioDTO();
				usuario.setId( rs.getInt("ID_USUARIO"));
				usuario.setNome( rs.getString("NOME_USUARIO"));
				usuario.setLogin( rs.getString( "LOGIN" ));
				usuario.setSenha( rs.getString( "SENHA" ) );
				usuario.setBloqueado( rs.getInt( "BLOQUEADO" ) );

			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			fecharConexao(conexao, pstmt, rs);
		}
		return usuario;
	}
}
