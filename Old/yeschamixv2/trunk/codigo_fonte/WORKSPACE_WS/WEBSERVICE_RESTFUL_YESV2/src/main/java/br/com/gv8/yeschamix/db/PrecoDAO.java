/**
 * 
 */
package br.com.gv8.yeschamix.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import br.com.gv8.yeschamix.dto.ClassificacaoDTO;
import br.com.gv8.yeschamix.dto.FamiliaDTO;
import br.com.gv8.yeschamix.dto.GrupoProdutoDTO;
import br.com.gv8.yeschamix.dto.PrecoDTO;
import br.com.gv8.yeschamix.dto.ProdutoDTO;
import br.com.gv8.yeschamix.util.Utilidades;

/**
 * @author Bruno Pelogia
 *
 */
public class PrecoDAO extends ConnectionFactory {
	
private static PrecoDAO instance = null;
	
	// Construtor padrão privado por causa do padrão Singleton.
	private PrecoDAO() {
	}

	public static PrecoDAO getInstance() {
		if (instance == null) {
			instance = new PrecoDAO();
		}
		return instance;
	}
	
	public ArrayList<PrecoDTO> consultarTodosPreco(String login) {
		Connection conexao = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		conexao = criarConexao(login);

		ArrayList<PrecoDTO> lista = new ArrayList<PrecoDTO>();

		try {
			pstmt = conexao.prepareStatement("SELECT p.DA1_CODTAB, p.DA1_CODPRO, p.DA1_PRCVEN FROM DA1 AS p ORDER BY p.DA1_PRCVEN");
			rs = pstmt.executeQuery();

			while (rs.next()) {
				PrecoDTO preco = new PrecoDTO();
				preco.setId( rs.getString("DA1_CODTAB"));
				preco.setProduto( new ProdutoDTO(rs.getString("DA1_CODPRO")));
				preco.setPreco( rs.getDouble( "DA1_PRCVEN" ));
				lista.add(preco);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			fecharConexao(conexao, pstmt, rs);
		}
		return lista;
	}
	
	public  ArrayList<PrecoDTO> consultarPrecoPorId(String id, String login) {
		Connection conexao = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		conexao = criarConexao(login);
		String query = "";

		 ArrayList<PrecoDTO> lista = new  ArrayList<PrecoDTO>();

		try {
			query = "SELECT p.DA1_CODTAB, p.DA1_CODPRO, p.DA1_PRCVEN FROM DA1 AS p WHERE p.DA1_CODTAB = ?";
			
			pstmt = conexao.prepareStatement(query);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				PrecoDTO preco = new PrecoDTO();
				preco.setId( rs.getString("DA1_CODTAB"));
				preco.setProduto( new ProdutoDTO(rs.getString("DA1_CODPRO")));
				preco.setPreco( rs.getDouble( "DA1_PRCVEN" ));
				lista.add( preco );
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			fecharConexao(conexao, pstmt, rs);
		}
		return lista;
	}
	
	public ArrayList<PrecoDTO> consultarTodosPrecoPorUsuario(String login) {
		Connection conexao = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		conexao = criarConexao(login);

		ArrayList<PrecoDTO> lista = new ArrayList<PrecoDTO>();

		try {
			pstmt = conexao.prepareStatement("SELECT usuario.ID_USUARIO,usuario.NOME_USUARIO,usuario.LOGIN,usuario.SENHA,usuario.BLOQUEADO, "+
					"preco.DA1_CODTAB,preco.DA1_CODPRO,preco.DA1_PRCVEN, "+
					"produto.B1_COD, produto.B1_XDSSIT, produto.B1_XDTSIT, produto.B1_XDTQSIT, produto.B1_XOPTSIT, "+
					"produto.B1_XATUTAB,produto.B1_XATUNUM, produto.B1_XATUFOT, produto.B1_ESTOQUE, produto.B1_STATUS, "+
					"familia.YC_COD, familia.YC_XDSSIT, familia.YC_XORDEM,familia.YC_XARQIM, "+
					"grupoProduto.BM_GRUPO,  grupoProduto.BM_XDSSIT,grupoProduto.BM_XARQIM, "+
					"classificacao.XGR_COD,classificacao.XGR_DESC "+
						"FROM  SB1 AS produto "+
			      			"INNER JOIN  DA1 AS preco ON preco.DA1_CODPRO = produto.B1_COD "+
						"INNER JOIN  SYC AS familia ON familia.YC_COD = produto.B1_FPCOD "+
						"INNER JOIN  SBM AS grupoProduto ON grupoProduto.BM_GRUPO = produto.B1_GRUPO "+
						"INNER JOIN  XGR AS classificacao ON classificacao.XGR_COD = produto.B1_XGRUPO "+
						"INNER JOIN  DA0 AS usuario_preco ON usuario_preco.DA1_CODTAB = preco.DA1_CODTAB "+
						"INNER JOIN  USUARIO ON USUARIO.ID_USUARIO = usuario_preco.ID_USUARIO AND "+
			      "USUARIO.LOGIN = '"+login+"' "+
			      "ORDER BY produto.B1_XDSSIT ");
			rs = pstmt.executeQuery();

			while (rs.next()) {
				
				//Classificacao
				ClassificacaoDTO classificacao = new ClassificacaoDTO();
				classificacao.setId( rs.getString( "XGR_COD" ) );
				classificacao.setDescricao( rs.getString("XGR_DESC"));
				
				//Familia
				FamiliaDTO familia = new FamiliaDTO();
				familia.setId( rs.getString("YC_COD"));
				familia.setDescricao( rs.getString("YC_XDSSIT"));
				familia.setOrdem( rs.getInt( "YC_XORDEM") );
				familia.setNomeArquivo( rs.getString( "YC_XARQIM" ));
				
				//GrupoProduto
				GrupoProdutoDTO grupoProduto = new GrupoProdutoDTO();
				grupoProduto.setId( rs.getString("BM_GRUPO"));
				grupoProduto.setDescricao( rs.getString("BM_XDSSIT"));
				grupoProduto.setNomeArquivo( rs.getString( "BM_XARQIM" ));
				
				//Produto
				ProdutoDTO produto = new ProdutoDTO();
				produto.setId( rs.getString("B1_COD"));
				produto.setClassificacao( classificacao);
				produto.setFamilia( familia);
				produto.setGrupoProduto( grupoProduto);
				produto.setContadorAtualizacao( rs.getInt( "B1_XATUNUM" ));
				produto.setContadorFoto( rs.getInt( "B1_XATUFOT" ) );
				produto.setDataUltimaAtualizacao(Utilidades.getDataFormatada( rs.getDate( "B1_XATUTAB" )));
				produto.setDescricao( rs.getString( "B1_XDSSIT" ) );
				produto.setDetalhesProduto( rs.getString( "B1_XDTSIT" ) );
				produto.setExisteEmEstoque( rs.getString( "B1_ESTOQUE" ) );
				produto.setOportunidadeDestaque( rs.getString( "B1_XDTQSIT" ) );
				produto.setOportunidadeVenda( rs.getString( "B1_XOPTSIT" ) );
				produto.setStatusProduto( rs.getString( "B1_STATUS" ));
			
				
				//Preco
				PrecoDTO preco = new PrecoDTO();
				preco.setId( rs.getString( "DA1_CODTAB" ) );
				preco.setPreco( rs.getDouble( "DA1_PRCVEN" ) );
				preco.setProduto( produto );
				lista.add(preco);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			fecharConexao(conexao, pstmt, rs);
		}
		return lista;
	}
}
