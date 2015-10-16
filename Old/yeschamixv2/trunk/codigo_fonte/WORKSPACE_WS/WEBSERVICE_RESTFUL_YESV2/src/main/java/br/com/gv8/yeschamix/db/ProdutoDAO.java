/**
 * 
 */
package br.com.gv8.yeschamix.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Arrays;

import br.com.gv8.yeschamix.dto.ClassificacaoDTO;
import br.com.gv8.yeschamix.dto.FamiliaDTO;
import br.com.gv8.yeschamix.dto.GrupoProdutoDTO;
import br.com.gv8.yeschamix.dto.PrecoDTO;
import br.com.gv8.yeschamix.dto.ProdutoDTO;
import br.com.gv8.yeschamix.dto.UsuarioDTO;
import br.com.gv8.yeschamix.util.Utilidades;

/**
 * @author Bruno Pelogia
 *
 */
public class ProdutoDAO extends ConnectionFactory{
	
	private static ProdutoDAO instance = null;
	// Construtor padrão privado por causa do padrão Singleton.
	private ProdutoDAO() {
	}

	public static ProdutoDAO getInstance() {
		if (instance == null) {
			instance = new ProdutoDAO();
		}
		return instance;
	}
	
	public ArrayList<ProdutoDTO> consultarTodosProduto(String login) {
		Connection conexao = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		conexao = criarConexao(login);

		ArrayList<ProdutoDTO> lista = new ArrayList<ProdutoDTO>();

		try {
			pstmt = conexao.prepareStatement("SELECT produto.B1_COD, produto.B1_XDSSIT, produto.B1_XDTSIT, produto.B1_XDTQSIT, produto.B1_XOPTSIT, " +
					"produto.B1_XATUTAB, produto.B1_XATUNUM, produto.B1_XATUFOT, produto.B1_ESTOQUE, produto.B1_STATUS, familia.YC_COD, familia.YC_XDSSIT, " +
					"familia.YC_XORDEM, familia.YC_XARQIM, grupoProduto.BM_GRUPO,  grupoProduto.BM_XDSSIT,grupoProduto.BM_XARQIM, classificacao.XGR_COD, classificacao.XGR_DESC "+
					"FROM  SB1 AS produto "+
					"INNER JOIN  SYC AS familia ON familia.YC_COD = produto.B1_FPCOD "+
					"INNER JOIN  SBM AS grupoProduto ON grupoProduto.BM_GRUPO = produto.B1_GRUPO "+
					"INNER JOIN  XGR AS classificacao ON classificacao.XGR_COD = produto.B1_XGRUPO ");
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
				lista.add(produto);
				
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			fecharConexao(conexao, pstmt, rs);
		}
		return lista;
	}
	
	
	
	public  ArrayList<ProdutoDTO> consultarProdutoPorId(String id, String login) {
		Connection conexao = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		conexao = criarConexao(login);
		String query = "";

		 ArrayList<ProdutoDTO> lista = new  ArrayList<ProdutoDTO>();
		 ArrayList<String> codigos = new  ArrayList<String>(Arrays.asList( "AI132029", "BCGC" ));
//		 ArrayList<String> codigos = new  ArrayList<String>(Arrays.asList( "BD20ANS", "CX020M" ));

		try {
			for(String cod : codigos){
				id = cod;
				query = ("SELECT produto.B1_COD, produto.B1_XDSSIT, produto.B1_XDTSIT, produto.B1_XDTQSIT, produto.B1_XOPTSIT, " +
						"produto.B1_XATUTAB, produto.B1_XATUNUM, produto.B1_XATUFOT, produto.B1_ESTOQUE, produto.B1_STATUS, familia.YC_COD, familia.YC_XDSSIT, " +
						"familia.YC_XORDEM, familia.YC_XARQIM, grupoProduto.BM_GRUPO,  grupoProduto.BM_XDSSIT,grupoProduto.BM_XARQIM, classificacao.XGR_COD, classificacao.XGR_DESC "+
						"FROM  SB1 AS produto "+
						"INNER JOIN  SYC AS familia ON familia.YC_COD = produto.B1_FPCOD "+
						"INNER JOIN  SBM AS grupoProduto ON grupoProduto.BM_GRUPO = produto.B1_GRUPO "+
						"INNER JOIN  XGR AS classificacao ON classificacao.XGR_COD = produto.B1_XGRUPO WHERE produto.B1_COD like ?");
				pstmt = conexao.prepareStatement(query);
				pstmt.setString(1, id+ "%");
				rs = pstmt.executeQuery();
	
				while (rs.next()) {
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
					
					lista.add( produto );
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			fecharConexao(conexao, pstmt, rs);
		}
		return lista;
	}
}
