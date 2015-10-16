/**
 * 
 */
package br.com.gv8.yeschamix.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import br.com.gv8.yeschamix.dto.GrupoProdutoDTO;

/**
 * @author Bruno Pelogia
 *
 */
public class GrupoProdutoDAO extends ConnectionFactory {
	
private static GrupoProdutoDAO instance = null;
	
	// Construtor padrão privado por causa do padrão Singleton.
	private GrupoProdutoDAO() {
	}

	public static GrupoProdutoDAO getInstance() {
		if (instance == null) {
			instance = new GrupoProdutoDAO();
		}
		return instance;
	}
	
	public ArrayList<GrupoProdutoDTO> consultarTodosGrupoProduto(String login) {
		Connection conexao = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		conexao = criarConexao(login);

		ArrayList<GrupoProdutoDTO> lista = new ArrayList<GrupoProdutoDTO>();

		try {
			pstmt = conexao.prepareStatement("SELECT G.BM_GRUPO, G.BM_XDSSIT, G.BM_XARQIM FROM SBM AS G ORDER BY G.BM_XDSSIT");
			rs = pstmt.executeQuery();

			while (rs.next()) {
				GrupoProdutoDTO grupoProduto = new GrupoProdutoDTO();
				grupoProduto.setId( rs.getString("BM_GRUPO"));
				grupoProduto.setDescricao( rs.getString("BM_XDSSIT"));
				grupoProduto.setNomeArquivo( rs.getString( "BM_XARQIM" ));
				lista.add(grupoProduto);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			fecharConexao(conexao, pstmt, rs);
		}
		return lista;
	}
	
	public  ArrayList<GrupoProdutoDTO> consultarGrupoProdutoPorId(String id, String login) {
		Connection conexao = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		conexao = criarConexao(login);
		String query = "";

		 ArrayList<GrupoProdutoDTO> lista = new  ArrayList<GrupoProdutoDTO>();

		try {
			query = "SELECT G.BM_GRUPO, G.BM_XDSSIT, G.BM_XARQIM FROM SBM AS G WHERE G.BM_GRUPO = ?";
			
			pstmt = conexao.prepareStatement(query);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				GrupoProdutoDTO grupoProduto = new GrupoProdutoDTO();
				grupoProduto.setId( rs.getString("BM_GRUPO"));
				grupoProduto.setDescricao( rs.getString("BM_XDSSIT"));
				grupoProduto.setNomeArquivo( rs.getString( "BM_XARQIM" ));
				lista.add( grupoProduto );
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			fecharConexao(conexao, pstmt, rs);
		}
		return lista;
	}
}
