/**
 * 
 */
package br.com.gv8.yeschamix.facade;

import java.util.ArrayList;
import java.util.List;

import br.com.gv8.yeschamix.db.ClassificacaoDAO;
import br.com.gv8.yeschamix.db.FamiliaDAO;
import br.com.gv8.yeschamix.db.GrupoProdutoDAO;
import br.com.gv8.yeschamix.db.PrecoDAO;
import br.com.gv8.yeschamix.db.ProdutoDAO;
import br.com.gv8.yeschamix.db.UsuarioDAO;
import br.com.gv8.yeschamix.db.UsuarioPrecoDAO;
import br.com.gv8.yeschamix.dto.ClassificacaoDTO;
import br.com.gv8.yeschamix.dto.FamiliaDTO;
import br.com.gv8.yeschamix.dto.GrupoProdutoDTO;
import br.com.gv8.yeschamix.dto.PrecoDTO;
import br.com.gv8.yeschamix.dto.ProdutoDTO;
import br.com.gv8.yeschamix.dto.UsuarioDTO;
import br.com.gv8.yeschamix.dto.UsuarioPrecoDTO;

/**
 * @author Bruno Pelogia
 *
 */
public class YesChamixFacade {
	
private static YesChamixFacade instance = null;
	
	// Construtor padrão privado por causa do padrão Singleton.
	private YesChamixFacade() {
	}

	public static YesChamixFacade getInstance() {
		if (instance == null) {
			instance = new YesChamixFacade();
		}
		return instance;
	}
	
	/* CLASSIFICAÇÃO */
	
	public List<ClassificacaoDTO> consultarTudoClassificacao(String login) {
		List<ClassificacaoDTO> lista = new ArrayList<ClassificacaoDTO>();
		try {
			lista = ClassificacaoDAO.getInstance().consultarTodosClassificacao(login);
		} catch (Exception e) {
			lista = new ArrayList<ClassificacaoDTO>();
		} 
		return lista;
	}


	public List<ClassificacaoDTO> consultarTudoClassificacaoPorId(String id, String login) {
		List<ClassificacaoDTO> lista = new ArrayList<ClassificacaoDTO>();
		try {
			lista = (List< ClassificacaoDTO >) ClassificacaoDAO.getInstance().consultarClassificacaoPorId( id, login );
		} catch (Exception e) {
			lista = new ArrayList<ClassificacaoDTO>();
		} 
		return lista;
	}
	
	/* FAMILIA */
	
	public List<FamiliaDTO> consultarTudoFamilia(String login) {
		List<FamiliaDTO> lista = new ArrayList<FamiliaDTO>();
		try {
			lista = FamiliaDAO.getInstance().consultarTodosFamilia(login);
		} catch (Exception e) {
			lista = new ArrayList<FamiliaDTO>();
		} 
		return lista;
	}
	
	public List<FamiliaDTO> consultarTudoFamiliaPorId(String id, String login) {
		List<FamiliaDTO> lista = new ArrayList<FamiliaDTO>();
		try {
			lista = (List< FamiliaDTO >) FamiliaDAO.getInstance().consultarFamiliaPorId( id, login );
		} catch (Exception e) {
			lista = new ArrayList<FamiliaDTO>();
		} 
		return lista;
	}
	
	/*GRUPO PRODUTO */
	
	public List<GrupoProdutoDTO> consultarTudoGrupoProduto(String login) {
		ArrayList< GrupoProdutoDTO > lista = new ArrayList<GrupoProdutoDTO>();
		try {
			lista = GrupoProdutoDAO.getInstance().consultarTodosGrupoProduto(login);
		} catch (Exception e) {
			lista = new ArrayList<GrupoProdutoDTO>();
		} 
		return lista;
	}
	
	public List<GrupoProdutoDTO> consultarTudoGrupoProdutoPorId(String id, String login) {
		List<GrupoProdutoDTO> lista = new ArrayList<GrupoProdutoDTO>();
		try {
			lista = (List< GrupoProdutoDTO >) GrupoProdutoDAO.getInstance().consultarGrupoProdutoPorId( id, login );
		} catch (Exception e) {
			lista = new ArrayList<GrupoProdutoDTO>();
		} 
		return lista;
	} 
	
	/* PRECO */
	
	public List<PrecoDTO> consultarTudoPreco(String login) {
		ArrayList< PrecoDTO > lista = new ArrayList<PrecoDTO>();
		try {
			lista = PrecoDAO.getInstance().consultarTodosPreco(login);
		} catch (Exception e) {
			lista = new ArrayList<PrecoDTO>();
		} 
		return lista;
	}
	
	public List<PrecoDTO> consultarTudoPrecoPorId(String id, String login) {
		List<PrecoDTO> lista = new ArrayList<PrecoDTO>();
		try {
			lista = (List< PrecoDTO >) PrecoDAO.getInstance().consultarPrecoPorId( id, login );
		} catch (Exception e) {
			lista = new ArrayList<PrecoDTO>();
		} 
		return lista;
	} 
	
	public List<PrecoDTO> consultarTodosPrecoPorUsuario(String usuario) {
		List<PrecoDTO> lista = new ArrayList<PrecoDTO>();
		try {
			lista = (List< PrecoDTO >) PrecoDAO.getInstance().consultarTodosPrecoPorUsuario( usuario );
		} catch (Exception e) {
			lista = new ArrayList<PrecoDTO>();
		} 
		return lista;
	} 
	
/* PRODUTO */
	
	public List<ProdutoDTO> consultarTudoProduto(String login) {
		ArrayList< ProdutoDTO > lista = new ArrayList<ProdutoDTO>();
		try {
			lista = ProdutoDAO.getInstance().consultarTodosProduto(login);
		} catch (Exception e) {
			lista = new ArrayList<ProdutoDTO>();
		} 
		return lista;
	}
	
	public List<ProdutoDTO> consultarTudoProdutoPorId(String id, String login) {
		List<ProdutoDTO> lista = new ArrayList<ProdutoDTO>();
		try {
			lista = (List< ProdutoDTO >) ProdutoDAO.getInstance().consultarProdutoPorId( id, login );
		} catch (Exception e) {
			lista = new ArrayList<ProdutoDTO>();
		} 
		return lista;
	} 
	
	/* USUARIO */
	
	public List<UsuarioDTO> consultarTudoUsuario(String login) {
		ArrayList< UsuarioDTO > lista = new ArrayList<UsuarioDTO>();
		try {
			lista = UsuarioDAO.getInstance().consultarTodosUsuario(login);
		} catch (Exception e) {
			lista = new ArrayList<UsuarioDTO>();
		} 
		return lista;
	}
	
	public UsuarioDTO consultarTudoUsuarioPorId(Integer id, String login) {
		UsuarioDTO usuario = new UsuarioDTO();
		try {
			usuario = (UsuarioDTO ) UsuarioDAO.getInstance().consultarUsuarioPorId( id, login );
		} catch (Exception e) {
			usuario = new UsuarioDTO();
		} 
		return usuario;
	} 
	
	public UsuarioDTO consultarTudoUsuarioPorLoginSenha(String login, String senha) {
		UsuarioDTO usuario = new UsuarioDTO();
		try {
			usuario = (UsuarioDTO ) UsuarioDAO.getInstance().consultarUsuarioPorLoginSenha(login, senha);
		} catch (Exception e) {
			usuario = new UsuarioDTO();
		} 
		return usuario;
	} 
	
	/* USUARIO PRECO */
	
	public List<UsuarioPrecoDTO> consultarTudoUsuarioPreco(String login) {
		ArrayList< UsuarioPrecoDTO > lista = new ArrayList<UsuarioPrecoDTO>();
		try {
			lista = UsuarioPrecoDAO.getInstance().consultarTodosUsuarioPreco(login);
		} catch (Exception e) {
			lista = new ArrayList<UsuarioPrecoDTO>();
		} 
		return lista;
	}
	
	public List<UsuarioPrecoDTO> consultarUsuarioPrecoPorLogin(String login) {
		List<UsuarioPrecoDTO> lista = new ArrayList<UsuarioPrecoDTO>();;
		try {
			lista = UsuarioPrecoDAO.getInstance().consultarUsuarioPrecoPorLogin(login);
		} catch (Exception e) {
			lista = new ArrayList<UsuarioPrecoDTO>();
		} 
		return lista;
	}
	
	
	
	
}
