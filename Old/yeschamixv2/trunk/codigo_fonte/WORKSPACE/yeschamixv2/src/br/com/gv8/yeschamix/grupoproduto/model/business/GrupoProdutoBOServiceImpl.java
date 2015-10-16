package br.com.gv8.yeschamix.grupoproduto.model.business;

import java.util.ArrayList;

import br.com.gv8.yeschamix.grupoproduto.model.clienteWS.GrupoProdutoREST;
import br.com.gv8.yeschamix.grupoproduto.model.persistence.GrupoProdutoVO;



public final class GrupoProdutoBOServiceImpl implements GrupoProdutoBOService{

	private GrupoProdutoREST grupoProdutoREST;

	public GrupoProdutoBOServiceImpl(){
		if ( grupoProdutoREST == null ) {
			grupoProdutoREST = new GrupoProdutoREST();
		}
	}

	/**
	 * Polimorfico
	 * @see 
	 */
	@Override
	public ArrayList< GrupoProdutoVO > consultarTodosGruposProduto() throws Exception {
		try {
			if ( grupoProdutoREST == null ) {
				grupoProdutoREST = new GrupoProdutoREST();
			}
			
			return grupoProdutoREST.consultarTodosGruposProduto();
		} catch ( Exception e ) {
			e.printStackTrace();
			throw new Exception("Ocorreu um erro ao acessar o Webservice.");
		}finally{
			grupoProdutoREST = null;
		}
	}

}
