package br.com.gv8.yeschamix.produto.model.business;

import java.util.ArrayList;

import br.com.gv8.yeschamix.produto.model.clienteWS.ProdutoREST;
import br.com.gv8.yeschamix.produto.model.persistence.ProdutoVO;

public final class ProdutoBOServiceImpl implements ProdutoBOService{

	private ProdutoREST produtoREST;

	public ProdutoBOServiceImpl(){
		if ( produtoREST == null ) {
			produtoREST = new ProdutoREST();
		}
	}

	/**
	 * Polimorfico
	 * @see 
	 */
	@Override
	public ArrayList< ProdutoVO > consultarTodosProdutos(String login) throws Exception {
		try {
			if ( produtoREST == null ) {
				produtoREST = new ProdutoREST();
			}
			
			return produtoREST.consultarTodosProdutos(login);
		} catch ( Exception e ) {
			e.printStackTrace();
			throw new Exception("Ocorreu um erro ao acessar o Webservice.");
		}finally{
			produtoREST = null;
		}
	}
	@Override
	public ArrayList< ProdutoVO > consultarProdutoPorCodigo(String codigo, String login) throws Exception {
		try {
			if ( produtoREST == null ) {
				produtoREST = new ProdutoREST();
			}
			
			return produtoREST.consultarProdutoPorCodigo(codigo, login);
		} catch ( Exception e ) {
			e.printStackTrace();
			throw new Exception("Ocorreu um erro ao acessar o Webservice.");
		}finally{
			produtoREST = null;
		}
	}
	
	
	

	
}
