/**
 * 
 */
package br.com.gv8.yeschamix.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import br.com.gv8.yeschamix.dto.FamiliaDTO;

/**
 * @author Bruno Pelogia
 *
 */
public class FamiliaDAO extends ConnectionFactory {
	
private static FamiliaDAO instance = null;
	
	// Construtor padrão privado por causa do padrão Singleton.
	private FamiliaDAO() {
	}

	public static FamiliaDAO getInstance() {
		if (instance == null) {
			instance = new FamiliaDAO();
		}
		return instance;
	}
	
	/**
	 * 
	 * Método responsável por consultar todas as familias existentes no banco de dados
	 *
	 * @return ArrayList<FamiliaDTO>
	 *
	 * @author Bruno Pelogia <bruno.pelogia@gmail.com>
	 * @since 25/05/2013 23:50:36
	 * @version 1.0
	 */
	public ArrayList<FamiliaDTO> consultarTodosFamilia(String login) {
		Connection conexao = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		conexao = criarConexao(login);

		ArrayList<FamiliaDTO> lista = new ArrayList<FamiliaDTO>();

		try {
			pstmt = conexao.prepareStatement("SELECT F.YC_COD, F.YC_XDSSIT, F.YC_XORDEM, F.YC_XARQIM FROM SYC AS F ORDER BY F.YC_XDSSIT");
			rs = pstmt.executeQuery();

			while (rs.next()) {
				FamiliaDTO familia = new FamiliaDTO();
				familia.setId( rs.getString("YC_COD"));
				familia.setDescricao( rs.getString("YC_XDSSIT"));
				familia.setOrdem( rs.getInt( "YC_XORDEM") );
				familia.setNomeArquivo( rs.getString( "YC_XARQIM" ));
				lista.add(familia);
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
	 * Método responsável por consultar as Familia do Produto por Codigo.
	 *
	 * @param id
	 * @return ArrayList<FamiliaDTO>
	 *
	 * @author Bruno Pelogia <bruno.pelogia@gmail.com>
	 * @since 26/05/2013 00:00:48
	 * @version 1.0
	 */
	public  ArrayList<FamiliaDTO> consultarFamiliaPorId(String id, String login) {
		Connection conexao = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		conexao = criarConexao(login);
		String query = "";

		 ArrayList<FamiliaDTO> lista = new  ArrayList<FamiliaDTO>();

		try {
			query = "SELECT F.YC_COD, F.YC_XDSSIT, F.YC_XORDEM, F.YC_XARQIM FROM SYC AS F WHERE F.YC_COD = ?";
			
			pstmt = conexao.prepareStatement(query);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				FamiliaDTO familia = new FamiliaDTO();
				familia.setId( rs.getString("YC_COD"));
				familia.setDescricao( rs.getString("YC_XDSSIT"));
				familia.setOrdem( rs.getInt( "YC_XORDEM") );
				familia.setNomeArquivo( rs.getString( "YC_XARQIM" ));
				lista.add( familia );
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			fecharConexao(conexao, pstmt, rs);
		}
		return lista;
	}
}
