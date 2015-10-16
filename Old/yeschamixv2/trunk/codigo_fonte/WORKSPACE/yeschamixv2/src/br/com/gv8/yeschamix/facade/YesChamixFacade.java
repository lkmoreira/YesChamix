/**
 * @author Bruno Henrique Dannas Pelogia <bruno.pelogia@gmail.com>
 * @since 22/05/2013 16:24:47
 * @version 1.0 
 */
package br.com.gv8.yeschamix.facade;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import android.content.Context;
import br.com.gv8.yeschamix.classificacao.dao.ClassificacaoDAO;
import br.com.gv8.yeschamix.classificacao.model.persistence.ClassificacaoVO;
import br.com.gv8.yeschamix.familia.dao.FamiliaDAO;
import br.com.gv8.yeschamix.familia.model.persistence.FamiliaVO;
import br.com.gv8.yeschamix.grupoproduto.dao.GrupoProdutoDAO;
import br.com.gv8.yeschamix.grupoproduto.model.persistence.GrupoProdutoVO;
import br.com.gv8.yeschamix.imagem.dao.ImagemFolderDAO;
import br.com.gv8.yeschamix.imagem.dao.ImagemProdutoDAO;
import br.com.gv8.yeschamix.imagem.model.persistence.ImagemFolderVO;
import br.com.gv8.yeschamix.imagem.model.persistence.ImagemProdutoVO;
import br.com.gv8.yeschamix.preco.dao.PrecoDAO;
import br.com.gv8.yeschamix.preco.model.persistence.PrecoVO;
import br.com.gv8.yeschamix.produto.dao.ProdutoDAO;
import br.com.gv8.yeschamix.produto.model.persistence.ProdutoVO;
import br.com.gv8.yeschamix.relacaogrupoprodutoclassificacao.dto.RelacaoGrupoProdutoClassificacaoDTO;
import br.com.gv8.yeschamix.relacaogrupoprodutofamilia.dto.RelacaoGrupoProdutoFamiliaDTO;
import br.com.gv8.yeschamix.relacaousuariopreco.dao.RelacaoUsuarioPrecoDAO;
import br.com.gv8.yeschamix.relacaousuariopreco.model.persistence.RelacaoUsuarioPrecoVO;
import br.com.gv8.yeschamix.usuario.dao.UsuarioDAO;
import br.com.gv8.yeschamix.usuario.model.persistence.UsuarioVO;
import br.com.gv8.yeschamix.util.Utilidades;

/**
 * @author Bruno Pelogia
 * @since 23/05/2013 - 14:38:47
 */
public class YesChamixFacade {

	/*	private static DbAdapter dbAdapter;*/
	private Context context;

	/**
	 * @param database
	 */
	public YesChamixFacade( Context context ) {
		super();
		this.context = context;
	}

	
	public void limparBase(){
		ClassificacaoDAO dao = new ClassificacaoDAO( context );	
		dao.limparBase( context );
		dao.fechar();
	}
	/*
	 * CLASSIFICACAO
	 */
	public void salvarClassificacao( ClassificacaoVO vo ) {
		ClassificacaoDAO dao = new ClassificacaoDAO( context );	
		
		if(Utilidades.isNullOrBlank( consultarClassificacaoPorId( vo.getId() ).getId() )){
			dao.inserir(vo);
		}else{
			dao.alterar(vo);
		}
		dao.fechar();

	}

	public void salvarClassificacao( RelacaoGrupoProdutoClassificacaoDTO dto ) {
		ClassificacaoDAO dao = new ClassificacaoDAO( context );

		if(Utilidades.isNullOrBlank( consultarClassificacaoPorId( dto.getIdClassificacao() ).getId() )){
			dao.inserir(dto);
		}else{
			dao.alterar(dto);
		}
		dao.fechar();

	}

	public List< ClassificacaoVO > consultarTudoClassificacao() {
		ClassificacaoDAO dao = new ClassificacaoDAO( context );
		List< ClassificacaoVO > lista = new ArrayList< ClassificacaoVO >();
		try {
			lista = dao.consultarTodos();
		} catch( Exception e ) {
			lista = new ArrayList< ClassificacaoVO >();
		} finally {
			dao.fechar();
		}
		return lista;
	}

	public ClassificacaoVO consultarClassificacaoPorId( String id ) {
		ClassificacaoDAO dao = new ClassificacaoDAO( context );
		ClassificacaoVO vo ;
		try {
			vo = dao.consultarPorCodigoClassificacao( id );
		} catch( Exception e ) {
			vo = null;
		} finally {
			dao.fechar();
		}
		return vo;
	}

	public List< ClassificacaoVO > consultarClassificacaoPorGrupoProdutoFamilia( GrupoProdutoVO grupoProduto , FamiliaVO familia ) {
		ClassificacaoDAO dao = new ClassificacaoDAO( context );
		List< ClassificacaoVO > lista = inicializaClassificacao();

		List< ClassificacaoVO > classificacoes = new ArrayList< ClassificacaoVO >();

		try {
			classificacoes = dao.consultarClassificacaoPorGrupoProdutoFamilia( grupoProduto , familia );
		} catch( Exception e ) {
			classificacoes = new ArrayList< ClassificacaoVO >();
		} finally {
			dao.fechar();
		}

		for( ClassificacaoVO c : classificacoes ) {
			c.setGrupoProduto( consultarGrupoProdutoPorIdGrupoProdutoIdFamilia( c.getGrupoProduto().getId() , c.getFamilia().getId() ) );
			c.setFamilia( consultarFamiliaPorId( c.getFamilia().getId() ) );
			lista.add( c );
		}
		return lista;
	}
	
	
	public List< ClassificacaoVO > consultarClassificacaoPorGrupoProdutoFamiliaLogin( GrupoProdutoVO grupoProduto , FamiliaVO familia, String login ) {
		ClassificacaoDAO dao = new ClassificacaoDAO( context );
		List< ClassificacaoVO > lista = inicializaClassificacao();

		List< ClassificacaoVO > classificacoes = new ArrayList< ClassificacaoVO >();

		try {
			classificacoes = dao.consultarClassificacaoPorGrupoProdutoFamiliaLogin( grupoProduto , familia, login );
		} catch( Exception e ) {
			classificacoes = new ArrayList< ClassificacaoVO >();
		} finally {
			dao.fechar();
		}

		for( ClassificacaoVO c : classificacoes ) {
			c.setGrupoProduto( consultarGrupoProdutoPorIdGrupoProdutoIdFamilia( c.getGrupoProduto().getId() , c.getFamilia().getId() ) );
			c.setFamilia( consultarFamiliaPorId( c.getFamilia().getId() ) );
			lista.add( c );
		}
		return lista;
	}
	

	public ClassificacaoVO consultarClassificacao( String idClassificacao , String idGrupoProduto , String idFamilia ) {
		ClassificacaoDAO dao = new ClassificacaoDAO( context );
		ClassificacaoVO vo;
		try {
			vo = dao.consultarClassificacao( idClassificacao , idGrupoProduto , idFamilia );
		} catch( Exception e ) {
			vo = null;
		} finally {
			dao.fechar();
		}

		return vo;
	}

	public List< ClassificacaoVO > inicializaClassificacao() {
		List< ClassificacaoVO > lista = new LinkedList< ClassificacaoVO >();
		ClassificacaoVO classificacao = new ClassificacaoVO();
		classificacao.setId( "0" );
		classificacao.setDescricao( "Classificação" );
		lista.add( classificacao );
		return lista;
	}

	/*
	 * FAMILIA
	 */
	public void salvarFamilia( FamiliaVO vo ) {
		FamiliaDAO dao = new FamiliaDAO( context );

		if(Utilidades.isNullOrBlank( consultarFamiliaPorId( vo.getId()).getId() )){
			dao.inserir(vo);
		}else{
			dao.alterar(vo);
		}
		dao.fechar();

	}

	public List< FamiliaVO > consultarTudoFamilia() {
		FamiliaDAO dao = new FamiliaDAO( context );
		List< FamiliaVO > lista = new ArrayList< FamiliaVO >();
		try {
			lista = dao.consultarTodos();
		} catch( Exception e ) {
			lista = new ArrayList< FamiliaVO >();
		} finally {
			dao.fechar();
		}
		return lista;
	}
	
	
	public List< FamiliaVO > consultarTudoFamiliaPorLogin(String login) {
		FamiliaDAO dao = new FamiliaDAO( context );
		List< FamiliaVO > lista = new ArrayList< FamiliaVO >();
		try {
			lista = dao.consultarTodosPorLogin( login );
		} catch( Exception e ) {
			lista = new ArrayList< FamiliaVO >();
		} finally {
			dao.fechar();
		}
		return lista;
	}

	public FamiliaVO consultarFamiliaPorId( String id ) {
		FamiliaDAO dao = new FamiliaDAO( context );
		FamiliaVO vo;
		try {
			vo = dao.consultarPorCodigoFamilia( id );
		} catch( Exception e ) {
			vo = null;
		} finally {
			dao.fechar();
		}
		return vo;
	}

	/*
	 * GRUPO DE PRODUTO
	 */
	public void salvarGrupoProduto( GrupoProdutoVO vo ) {
		GrupoProdutoDAO dao = new GrupoProdutoDAO( context );

		if(Utilidades.isNullOrBlank( consultarGrupoProdutoPorId( vo.getId()).getId() )){
			dao.inserir(vo);
		}else{
			dao.alterar(vo);
		}
		dao.fechar();

	}

	public void salvarGrupoProduto( RelacaoGrupoProdutoFamiliaDTO dto ) {
		GrupoProdutoDAO dao = new GrupoProdutoDAO( context );

		if(Utilidades.isNullOrBlank( consultarGrupoProdutoPorId( dto.getIdGrupoProduto()).getId())){
			dao.inserir(dto);
		}else{
			dao.alterar(dto);
		}
		dao.fechar();

	}

	public List< GrupoProdutoVO > consultarTudoGrupoProduto() {
		GrupoProdutoDAO dao = new GrupoProdutoDAO( context );
		List< GrupoProdutoVO > lista = new ArrayList< GrupoProdutoVO >();
		try {

			lista = dao.consultarTodos();
		} catch( Exception e ) {
			lista = new ArrayList< GrupoProdutoVO >();
		} finally {
			dao.fechar();
		}
		return lista;
	}
	
	public List< GrupoProdutoVO > consultarTudoGrupoProdutoPorLogin(String login) {
		GrupoProdutoDAO dao = new GrupoProdutoDAO( context );
		List< GrupoProdutoVO > lista = new ArrayList< GrupoProdutoVO >();
		try {

			lista = dao.consultarTodosPorLogin( login );
		} catch( Exception e ) {
			lista = new ArrayList< GrupoProdutoVO >();
		} finally {
			dao.fechar();
		}
		return lista;
	}

	public GrupoProdutoVO consultarGrupoProdutoPorId( String id ) {
		GrupoProdutoDAO dao = new GrupoProdutoDAO( context );
		GrupoProdutoVO vo ;
		try {
			vo = dao.consultarPorCodigoGrupoProduto( id );
		} catch( Exception e ) {
			vo = null;
		} finally {
			dao.fechar();
		}
		return vo;
	}

	public List< GrupoProdutoVO > inicializaGrupoProduto() {
		List< GrupoProdutoVO > lista = new LinkedList< GrupoProdutoVO >();
		GrupoProdutoVO grupoProduto = new GrupoProdutoVO();
		grupoProduto.setId( "0" );
		grupoProduto.setDescricao( "Grupo de Produtos" );
		lista.add( grupoProduto );
		return lista;
	}

	public List< GrupoProdutoVO > consultarGrupoProdutoFamilia( FamiliaVO familia ) {
		GrupoProdutoDAO dao = new GrupoProdutoDAO( context );
		List< GrupoProdutoVO > lista = inicializaGrupoProduto();
		List< GrupoProdutoVO > grupos = new ArrayList< GrupoProdutoVO >();

		try {
			grupos = dao.consultarGrupoProdutoFamilia( familia );
		} catch( Exception e ) {
			grupos = new ArrayList< GrupoProdutoVO >();
		} finally {
			dao.fechar();
		}

		for( GrupoProdutoVO g : grupos ) {
			g.setFamilia( consultarFamiliaPorId( g.getFamilia().getId() ) );
			lista.add( g );
		}
		return lista;
	}
	
	public List< GrupoProdutoVO > consultarGrupoProdutoFamiliaLogin( FamiliaVO familia, String login ) {
		GrupoProdutoDAO dao = new GrupoProdutoDAO( context );
		List< GrupoProdutoVO > lista = inicializaGrupoProduto();
		List< GrupoProdutoVO > grupos = new ArrayList< GrupoProdutoVO >();

		try {
			grupos = dao.consultarGrupoProdutoFamiliaLogin( familia, login );
		} catch( Exception e ) {
			grupos = new ArrayList< GrupoProdutoVO >();
		} finally {
			dao.fechar();
		}

		for( GrupoProdutoVO g : grupos ) {
			g.setFamilia( consultarFamiliaPorId( g.getFamilia().getId() ) );
			lista.add( g );
		}
		return lista;
	}

	public GrupoProdutoVO consultarGrupoProdutoPorIdGrupoProdutoIdFamilia( String idGrupo , String idFamilia ) {
		GrupoProdutoDAO dao = new GrupoProdutoDAO( context );
		GrupoProdutoVO vo;
		try {
			vo = dao.consultarGrupoProdutoPorIdGrupoProdutoIdFamilia( idGrupo , idFamilia );
		} catch( Exception e ) {
			vo = null;
		} finally {
			dao.fechar();
		}
		return vo;
	}

	/*
	 * IMAGEM PRODUTO
	 */
	public void salvarImagemProduto( ImagemProdutoVO vo ) {
		ImagemProdutoDAO dao = new ImagemProdutoDAO( context );
		//Alterado em 02/01
		//if(Utilidades.isNullOrBlank( consultarImagemProdutoPorCorEProd( vo.getCor(), vo.getProduto().getId() ).getId())){
		if(!Utilidades.isNullOrBlank( vo )){
			dao.inserir(vo);
		}else{
			dao.alterar(vo);
		}
		dao.fechar();
		

	}

	public List< ImagemProdutoVO > consultarTudoImagemProduto() {
		ImagemProdutoDAO dao = new ImagemProdutoDAO( context );
		List< ImagemProdutoVO > lista = new ArrayList< ImagemProdutoVO >();
		try {
			lista = dao.consultarTodos();
		} catch( Exception e ) {
			lista = new ArrayList< ImagemProdutoVO >();
		} finally {
			dao.fechar();
		}
		return lista;
	}
	
	public List< ImagemProdutoVO > consultarTudoImagemProdutoPorIdProduto(String idProduto) {
		ImagemProdutoDAO dao = new ImagemProdutoDAO( context );
		List< ImagemProdutoVO > lista = new ArrayList< ImagemProdutoVO >();
		try {
			lista = dao.consultarTudoImagemProdutoPorIdProduto( idProduto );
		} catch( Exception e ) {
			lista = new ArrayList< ImagemProdutoVO >();
		} finally {
			dao.fechar();
		}
		return lista;
	}

	public ImagemProdutoVO consultarImagemProdutoPorNomeArquivo( String nomeArquivo ) {
		ImagemProdutoDAO dao = new ImagemProdutoDAO( context );
		ImagemProdutoVO  vo;
		try {
			vo = dao.consultarImagemProdutoPorNomeArquivo( nomeArquivo );
		} catch( Exception e ) {
			vo = null;
		} finally {
			dao.fechar();
		}
		return vo;
	}
	
	public ImagemProdutoVO consultarImagemProdutoPorCorEProd( String cor, String idProduto ) {
		ImagemProdutoDAO dao = new ImagemProdutoDAO( context );
		ImagemProdutoVO  vo;
		try {
			vo = dao.consultarImagemProdutoPorCorEProd( cor, idProduto );
		} catch( Exception e ) {
			vo = null;
		} finally {
			dao.fechar();
		}
		return vo;
	}

	/*
	 * IMAGEM FOLDER
	 */
	public void salvarImagemFolder( ImagemFolderVO vo ) {
		ImagemFolderDAO dao = new ImagemFolderDAO( context );
		dao.inserir( vo );
		dao.fechar();

	}

	public ArrayList< ImagemFolderVO > consultarTudoImagemFolder() {
		ImagemFolderDAO dao = new ImagemFolderDAO( context );
		ArrayList< ImagemFolderVO > lista = new ArrayList< ImagemFolderVO >();
		try {
			lista = dao.consultarTodos();
		} catch( Exception e ) {
			lista = new ArrayList< ImagemFolderVO >();
		} finally {
			dao.fechar();
		}
		return lista;
	}

	public List< ImagemFolderVO > consultarTudoImagemFolderPorId( Integer id ) {
		ImagemFolderDAO dao = new ImagemFolderDAO( context );
		List< ImagemFolderVO > lista = new ArrayList< ImagemFolderVO >();
		try {
			lista = dao.consultarTodosPorCodigoImagemFolder( id );
		} catch( Exception e ) {
			lista = new ArrayList< ImagemFolderVO >();
		} finally {
			dao.fechar();
		}
		return lista;
	}

	public void detetarTudoImagensFolder() {
		ImagemFolderDAO dao = new ImagemFolderDAO( context );
		try {
			dao.detetarTudoImagensFolder();
		} catch( Exception e ) {
			e.printStackTrace();
		} finally {
			dao.fechar();
		}
	}

	/*
	 * PRECO
	 */
	public void salvarPreco( PrecoVO  vo ) {
		PrecoDAO dao = new PrecoDAO( context );

			if(Utilidades.isNullOrBlank( consultarPrecoPorIdPrecoIdProduto( vo.getId(), vo.getProduto().getId()).getCodigo())){
				dao.inserir(vo);
			}else{
				dao.alterar(vo);
			}
			
		dao.fechar();
	}

	public List< PrecoVO > consultarTudoPreco() {
		PrecoDAO dao = new PrecoDAO( context );
		List< PrecoVO > lista = new ArrayList< PrecoVO >();
		try {
			lista = dao.consultarTodos();
		} catch( Exception e ) {
			lista = new ArrayList< PrecoVO >();
		} finally {
			dao.fechar();
		}
		return lista;
	}

	public PrecoVO consultarPrecoPorIdPrecoIdProduto( String idPreco, String idProduto ) {
		PrecoDAO dao = new PrecoDAO( context );
		PrecoVO vo = null;
		try {
			vo = dao.consultarPorCodPrecoCodProduto( idPreco , idProduto );
		} catch( Exception e ) {
			vo = null;
		} finally {
			dao.fechar();
		}
		return vo;
	}
	
	public ArrayList< PrecoVO> consultarPrecoPorIdProduto(String idProduto, String login ) {
		PrecoDAO dao = new PrecoDAO( context );
		ArrayList< PrecoVO> lista = new ArrayList< PrecoVO>();
		try {
			lista = dao.consultarPorCodigoProduto(idProduto, login );
		} catch( Exception e ) {
			lista = new ArrayList< PrecoVO>();
		} finally {
			dao.fechar();
		}
		return lista;
	}

	/*
	 * PRODUTO
	 */
	public void salvarProduto( ProdutoVO vo ) {
		ProdutoDAO dao = new ProdutoDAO( context );

		if(Utilidades.isNullOrBlank( consultarProdutoPorId( vo.getId()).getId() )){
			dao.inserir(vo);
		}else{
			dao.alterar(vo);
		}
		dao.fechar();

	}

	public void deletarProduto( String id ) {
		ProdutoDAO dao = new ProdutoDAO( context );
		try {
			dao.deletarProduto( id );
		} catch( Exception e ) {
			e.printStackTrace();
		} finally {
			dao.fechar();
		}
	}

	public HashMap< String , ProdutoVO > consultarTudoProduto() {
		ProdutoDAO dao = new ProdutoDAO( context );
		List< ProdutoVO > lista = new ArrayList< ProdutoVO >();
		try {
			lista = dao.consultarTodos();
		} catch( Exception e ) {
			lista = new ArrayList< ProdutoVO >();
		} finally {
			dao.fechar();
		}
		return preencherHashProdutos( lista );
	}
	
	public HashMap< String , ProdutoVO > consultarTudoProdutoPorLogin(String login) {
		ProdutoDAO dao = new ProdutoDAO( context );
		List< ProdutoVO > lista = new ArrayList< ProdutoVO >();
		try {
			lista = dao.consultarTodosPorLogin( login );
		} catch( Exception e ) {
			lista = new ArrayList< ProdutoVO >();
		} finally {
			dao.fechar();
		}
		return preencherHashProdutos( lista );
	}

	public List< ProdutoVO > consultarProduto( String idFamilia , String idGrupoProduto , String idClassificacao , String lancamento , String oportunidade) {
		ProdutoDAO dao = new ProdutoDAO( context );
		List< ProdutoVO > lista = new ArrayList< ProdutoVO >();
		try {
			lista = dao.consultarProduto( idFamilia , idGrupoProduto , idClassificacao , lancamento , oportunidade);
		} catch( Exception e ) {
			lista = new ArrayList< ProdutoVO >();
		} finally {
			dao.fechar();
		}

		return this.preencherProdutos( lista );
	}

	private HashMap< String , ProdutoVO > preencherHashProdutos( List< ProdutoVO > lista ) {
		HashMap< String , ProdutoVO > listaProdutos = new HashMap< String , ProdutoVO >();
		for( ProdutoVO p : lista ) {
			listaProdutos.put( p.getId() , carregarProduto( p ) );
		}
		return listaProdutos;
	}

	private ProdutoVO carregarProduto(ProdutoVO p) {
		p.setClassificacao(consultarClassificacao( p.getClassificacao().getId(), p.getGrupoProduto().getId(),p.getFamilia().getId()));
		p.setGrupoProduto(consultarGrupoProdutoPorIdGrupoProdutoIdFamilia( p.getGrupoProduto()
			.getId(), p.getFamilia().getId()));
		p.setFamilia(consultarFamiliaPorId( p.getFamilia().getId()));
		p.getClassificacao().setFamilia(consultarFamiliaPorId( p.getFamilia().getId()));
		p.getClassificacao().setGrupoProduto(consultarGrupoProdutoPorIdGrupoProdutoIdFamilia( p.getGrupoProduto().getId(), p.getFamilia().getId()));
		p.setImagensProduto(consultarTudoImagemProdutoPorIdProduto(p.getId()));
		return p;
	    }

	public List< ProdutoVO > consultarProdutosRandom() {
		ProdutoDAO dao = new ProdutoDAO( context );
		List< ProdutoVO > lista = new ArrayList< ProdutoVO >();
		try {
			lista = dao.consultarProdutosRandom();
		} catch( Exception e ) {
			lista = new ArrayList< ProdutoVO >();
		} finally {
			dao.fechar();
		}
		return this.preencherProdutos( lista );
	}
	
	public List< ProdutoVO > consultarProdutosRandomPorLogin(String login) {
		ProdutoDAO dao = new ProdutoDAO( context );
		List< ProdutoVO > lista = new ArrayList< ProdutoVO >();
		try {
			lista = dao.consultarProdutosRandomPorLogin(login);
		} catch( Exception e ) {
			lista = new ArrayList< ProdutoVO >();
		} finally {
			dao.fechar();
		}
		return this.preencherProdutos( lista );
	}

	public List< ProdutoVO > consultarProdutoPorNomeOuCodigo( String nomeProduto ) {
		ProdutoDAO dao = new ProdutoDAO( context );
		List< ProdutoVO > lista = new ArrayList< ProdutoVO >();
		try {
			lista = dao.consultarProdutoPorNomeOuCodigo( nomeProduto );
		} catch( Exception e ) {
			lista = new ArrayList< ProdutoVO >();
		} finally {
			dao.fechar();
		}
		return this.preencherProdutos( lista );
	}
	
	public List< ProdutoVO > consultarProdutoPorNomeOuCodigoLogin( String nomeProduto, String login ) {
		ProdutoDAO dao = new ProdutoDAO( context );
		List< ProdutoVO > lista = new ArrayList< ProdutoVO >();
		try {
			lista = dao.consultarProdutoPorNomeOuCodigoLogin( nomeProduto, login );
		} catch( Exception e ) {
			lista = new ArrayList< ProdutoVO >();
		} finally {
			dao.fechar();
		}
		return this.preencherProdutos( lista );
	}

	public List< ProdutoVO > consultarProdutoPorFamilia( FamiliaVO familia ) {
		ProdutoDAO dao = new ProdutoDAO( context );
		List< ProdutoVO > lista = new ArrayList< ProdutoVO >();
		try {
			lista = dao.consultarProdutoPorFamilia( familia );
		} catch( Exception e ) {
			lista = new ArrayList< ProdutoVO >();
		} finally {
			dao.fechar();
		}
		return this.preencherProdutos( lista );
	}
	
	public List< ProdutoVO > consultarProdutoPorFamiliaLogin( FamiliaVO familia, String login ) {
		ProdutoDAO dao = new ProdutoDAO( context );
		List< ProdutoVO > lista = new ArrayList< ProdutoVO >();
		try {
			lista = dao.consultarProdutoPorFamiliaLogin( familia, login );
		} catch( Exception e ) {
			lista = new ArrayList< ProdutoVO >();
		} finally {
			dao.fechar();
		}
		return this.preencherProdutos( lista );
	}

	private List< ProdutoVO > preencherProdutos( List< ProdutoVO > lista ) {
		List< ProdutoVO > listaProdutos = new ArrayList< ProdutoVO >();
		for( ProdutoVO p : lista ) {
			listaProdutos.add( carregarProduto( p ) );
		}
		return listaProdutos;
	}

	public ProdutoVO consultarProdutoPorId( String id ) {
		ProdutoDAO dao = new ProdutoDAO( context );
		ProdutoVO vo;
		try {
			vo = dao.consultarPorCodigoProduto( id );
		} catch( Exception e ) {
			vo = null;
		} finally {
			dao.fechar();
		}
		return  vo ;
	}

	/*
	 * USUARIO
	 */
	public void salvarUsuario( UsuarioVO  vo ) {
		UsuarioDAO dao = new UsuarioDAO( context );
		
			if(Utilidades.isNullOrBlank( consultarUsuarioPorId( vo.getId().toString()).getId())){
				dao.inserir(vo);
			}else{
				dao.alterar(vo);
			}
			
		
		dao.fechar();

	}

	

	public List< UsuarioVO > consultarTudoUsuario() {
		UsuarioDAO dao = new UsuarioDAO( context );
		List< UsuarioVO > lista = new ArrayList< UsuarioVO >();
		try {

			lista = dao.consultarTodos();
		} catch( Exception e ) {
			lista = new ArrayList< UsuarioVO >();
		} finally {
			dao.fechar();
		}
		return lista;
	}

	public UsuarioVO consultarUsuarioPorId( String id ) {
		UsuarioDAO dao = new UsuarioDAO( context );
		UsuarioVO vo = null;
		try {
			vo = dao.consultarPorCodigoUsuario( id );
		} catch( Exception e ) {
			vo = null;
		} finally {
			dao.fechar();
		}
		return vo;
	}
	
	public UsuarioVO consultarUsuarioPorLogin( String login ) {
		UsuarioDAO dao = new UsuarioDAO( context );
		UsuarioVO vo = null;
		try {
			vo = dao.consultarPorLogin( login );
		} catch( Exception e ) {
			vo = null;
		} finally {
			dao.fechar();
		}
		return vo;
	}
	
	/*RELAÇÃO USUARIO PRECO*/
	
	public void salvarRelacaoUsuarioPreco( List< RelacaoUsuarioPrecoVO > lista ) {
		RelacaoUsuarioPrecoDAO dao = new RelacaoUsuarioPrecoDAO( context );

		for( RelacaoUsuarioPrecoVO itera : lista ) {
			if(Utilidades.isNullOrEmpty( consultarTudoRelacaoUsuarioPrecoPorId( itera.getPreco().getId() , itera.getUsuario().getId().toString() ))){
				dao.inserir(itera);
			}else{
				dao.alterar(itera);
			}
		}
		dao.fechar();
	}
	
	public List< RelacaoUsuarioPrecoVO > consultarTudoRelacaoUsuarioPrecoPorId( String idPreco, String idUsuario ) {
		RelacaoUsuarioPrecoDAO dao = new RelacaoUsuarioPrecoDAO( context );
		List< RelacaoUsuarioPrecoVO > lista = new ArrayList< RelacaoUsuarioPrecoVO >();
		try {
			lista = dao.consultarTodosPorCodigoRelacaoUsuarioPreco( idPreco , idUsuario );
		} catch( Exception e ) {
			lista = new ArrayList< RelacaoUsuarioPrecoVO >();
		} finally {
			dao.fechar();
		}
		return lista;
	}
	
	public ArrayList< RelacaoUsuarioPrecoVO > consultarRelacaoUsuarioPrecoPorLogin(String login ) {
		RelacaoUsuarioPrecoDAO dao = new RelacaoUsuarioPrecoDAO( context );
		ArrayList< RelacaoUsuarioPrecoVO > lista = new ArrayList< RelacaoUsuarioPrecoVO >();
		try {
			lista = dao.consultarRelacaoUsuarioPrecoPorLogin(login );
		} catch( Exception e ) {
			lista = new ArrayList< RelacaoUsuarioPrecoVO >();
		} finally {
			dao.fechar();
		}
		return lista;
	}
	
	
}
