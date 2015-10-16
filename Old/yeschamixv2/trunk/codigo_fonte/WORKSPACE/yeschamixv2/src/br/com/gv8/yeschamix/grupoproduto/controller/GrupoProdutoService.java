package br.com.gv8.yeschamix.grupoproduto.controller;

import java.util.ArrayList;

import br.com.gv8.yeschamix.grupoproduto.model.business.GrupoProdutoBOService;
import br.com.gv8.yeschamix.grupoproduto.model.business.GrupoProdutoBOServiceImpl;
import br.com.gv8.yeschamix.grupoproduto.model.persistence.GrupoProdutoVO;


public final class GrupoProdutoService{
	
	private GrupoProdutoBOService service;

	/*SINGLETON*/
	private static GrupoProdutoService instance;
	
	private GrupoProdutoService(){
		service = new GrupoProdutoBOServiceImpl();
	}
	
	public static GrupoProdutoService getInstance() {
		if( instance == null ){
			instance = new GrupoProdutoService();
		}
	    return instance;
    }
	/*SINGLETON*/
	
	
	public ArrayList< GrupoProdutoVO > consultarTodosGruposProduto() throws Exception {
		return service.consultarTodosGruposProduto();
	}
}
