package br.com.gv8.yeschamix.produto.controller;

import java.util.ArrayList;

import br.com.gv8.yeschamix.produto.model.business.ProdutoBOService;
import br.com.gv8.yeschamix.produto.model.business.ProdutoBOServiceImpl;
import br.com.gv8.yeschamix.produto.model.persistence.ProdutoVO;


public final class ProdutoService{
	
	private ProdutoBOService service;

	/*SINGLETON*/
	private static ProdutoService instance;
	
	private ProdutoService(){
		service = new ProdutoBOServiceImpl();
	}
	
	public static ProdutoService getInstance() {
		if( instance == null ){
			instance = new ProdutoService();
		}
	    return instance;
    }
	/*SINGLETON*/
	
	
	public ArrayList< ProdutoVO > consultarTodosProdutos(String login) throws Exception {
		return service.consultarTodosProdutos(login);
	}
	
	public ArrayList< ProdutoVO > consultarProdutoPorCodigo(String codigoProduto, String login) throws Exception {
		return service.consultarProdutoPorCodigo(codigoProduto, login);
	}
	

}
