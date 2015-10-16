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
import br.com.gv8.yeschamix.dto.UsuarioDTO;
import br.com.gv8.yeschamix.dto.UsuarioPrecoDTO;
import br.com.gv8.yeschamix.util.Utilidades;

/**
 * @author Bruno Pelogia
 *
 */
public class UsuarioPrecoDAO extends ConnectionFactory{
	private static UsuarioPrecoDAO instance = null;
	// Construtor padrão privado por causa do padrão Singleton.
	private UsuarioPrecoDAO() {
	}

	public static UsuarioPrecoDAO getInstance() {
		if (instance == null) {
			instance = new UsuarioPrecoDAO();
		}
		return instance;
	}
	
	public ArrayList<UsuarioPrecoDTO> consultarTodosUsuarioPreco(String login) {
		Connection conexao = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		conexao = criarConexao(login);

		ArrayList<UsuarioPrecoDTO> lista = new ArrayList<UsuarioPrecoDTO>();

		try {
			pstmt = conexao.prepareStatement("SELECT up.DA1_CODTAB, up.ID_USUARIO FROM DA0 AS up ");
			rs = pstmt.executeQuery();

			while (rs.next()) {
				
				//Preco
				PrecoDTO preco = new PrecoDTO();
				preco.setId( rs.getString( "DA1_CODTAB" ) );
				
				//Usuario
				UsuarioDTO usuario = new UsuarioDTO();
				usuario.setId( rs.getInt( "ID_USUARIO") );
				
				UsuarioPrecoDTO usuarioPreco = new UsuarioPrecoDTO();
				usuarioPreco.setUsuario( usuario);
				usuarioPreco.setPreco(preco);
				lista.add(usuarioPreco);
				
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			fecharConexao(conexao, pstmt, rs);
		}
		return lista;
	}
	
	public ArrayList<UsuarioPrecoDTO> consultarUsuarioPrecoPorLogin(String login) {
		Connection conexao = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		conexao = criarConexao(login);

		ArrayList<UsuarioPrecoDTO> lista = new ArrayList<UsuarioPrecoDTO>();

		try {
			pstmt = conexao.prepareStatement("SELECT USUARIO.ID_USUARIO, USUARIO.NOME_USUARIO, USUARIO.LOGIN, USUARIO.SENHA, USUARIO.BLOQUEADO, preco.DA1_CODTAB, preco.DA1_CODPRO, preco.DA1_PRCVEN, " +
					"produto.B1_COD, produto.B1_XDSSIT, produto.B1_XDTSIT, produto.B1_XDTQSIT, produto.B1_XOPTSIT, produto.B1_XATUTAB, produto.B1_XATUNUM, produto.B1_XATUFOT, " +
					"produto.B1_ESTOQUE, produto.B1_STATUS, familia.YC_COD, familia.YC_XDSSIT, familia.YC_XORDEM, familia.YC_XARQIM, grupoProduto.BM_GRUPO, grupoProduto.BM_XDSSIT, " +
					"grupoProduto.BM_XARQIM, classificacao.XGR_COD, classificacao.XGR_DESC " +
					"FROM DA0 AS up " +
					"INNER JOIN USUARIO ON USUARIO.ID_USUARIO = up.ID_USUARIO " +
					"INNER JOIN DA1 AS preco ON preco.DA1_CODTAB = up.DA1_CODTAB " +
					"INNER JOIN SB1 AS produto ON produto.B1_COD = preco.DA1_CODPRO " +
					"INNER JOIN SYC AS familia ON familia.YC_COD = produto.B1_FPCOD " +
					"INNER JOIN SBM AS grupoProduto ON grupoProduto.BM_GRUPO = produto.B1_GRUPO " +
					"INNER JOIN XGR AS classificacao ON classificacao.XGR_COD = produto.B1_XGRUPO AND USUARIO.LOGIN = '"+login+"' ");
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
				produto.setStatusProduto( rs.getString( "B1_STATUS" ) );
				
				//Preco
				PrecoDTO preco = new PrecoDTO();
				preco.setId( rs.getString( "DA1_CODTAB" ) );
				preco.setPreco( rs.getDouble( "DA1_PRCVEN" ) );
				preco.setProduto( produto );
				
				//Usuario
				UsuarioDTO usuario = new UsuarioDTO();
				usuario.setId( rs.getInt( "ID_USUARIO") );
				usuario.setLogin( rs.getString( "LOGIN") );
				usuario.setNome( rs.getString( "NOME_USUARIO" ) );
				usuario.setSenha( rs.getString( "SENHA" ) );
				usuario.setBloqueado( rs.getInt( "BLOQUEADO") );
				
				UsuarioPrecoDTO usuarioPreco = new UsuarioPrecoDTO();
				usuarioPreco.setUsuario( usuario);
				usuarioPreco.setPreco(preco);
				lista.add(usuarioPreco);
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
