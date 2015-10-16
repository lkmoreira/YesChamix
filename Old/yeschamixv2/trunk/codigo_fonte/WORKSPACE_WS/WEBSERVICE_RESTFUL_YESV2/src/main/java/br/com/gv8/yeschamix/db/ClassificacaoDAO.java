/**
 * 
 */
package br.com.gv8.yeschamix.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import br.com.gv8.yeschamix.dto.ClassificacaoDTO;

/**
 * @author Bruno Pelogia
 *
 */
public class ClassificacaoDAO extends ConnectionFactory {
	
	private static ClassificacaoDAO instance = null;
	
	// Construtor padrão privado por causa do padrão Singleton.
	private ClassificacaoDAO() {
	}

	public static ClassificacaoDAO getInstance() {
		if (instance == null) {
			instance = new ClassificacaoDAO();
		}
		return instance;
	}
	
	/**
	 * 
	 * Método responsável por retornar todas as Classificacoes de Produto.
	 *
	 * @return ArrayList<ClassificacaoDTO>
	 *
	 * @author Bruno Pelogia <bruno.pelogia@gmail.com>
	 * @since 25/05/2013 03:06:17
	 * @version 1.0
	 */
	public ArrayList<ClassificacaoDTO> consultarTodosClassificacao(String login) {
		Connection conexao = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		conexao = criarConexao(login);

		ArrayList<ClassificacaoDTO> lista = new ArrayList<ClassificacaoDTO>();

		try {
			pstmt = conexao.prepareStatement("SELECT C.XGR_COD, C.XGR_DESC FROM XGR AS C ORDER BY C.XGR_DESC");
			rs = pstmt.executeQuery();

			while (rs.next()) {
				ClassificacaoDTO classificacao = new ClassificacaoDTO();
				classificacao.setId( rs.getString("XGR_COD"));
				classificacao.setDescricao( rs.getString("XGR_DESC"));
				lista.add(classificacao);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			fecharConexao(conexao, pstmt, rs);
		}
		return lista;
	}
	
	/**
	 * 
	 * Método responsável por consultar uma Classificação de produto por codigo.
	 *
	 * @param id
	 * @return ClassificacaoDTO
	 *
	 * @author Bruno Pelogia <bruno.pelogia@gmail.com>
	 * @since 25/05/2013 03:12:03
	 * @version 1.0
	 */
	public  ArrayList<ClassificacaoDTO> consultarClassificacaoPorId(String id, String login) {
		Connection conexao = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		conexao = criarConexao(login);
		String query = "";

		 ArrayList<ClassificacaoDTO> lista = new  ArrayList<ClassificacaoDTO>();

		try {
			query = "SELECT C.XGR_COD, C.XGR_DESC FROM XGR AS C WHERE C.XGR_COD = ?";
			
			pstmt = conexao.prepareStatement(query);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				ClassificacaoDTO classificacao = new ClassificacaoDTO();
				classificacao.setId( rs.getString("XGR_COD"));
				classificacao.setDescricao( rs.getString("XGR_DESC"));
				lista.add( classificacao );
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			fecharConexao(conexao, pstmt, rs);
		}
		return lista;
	}
}
