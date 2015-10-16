package br.com.gv8.yeschamix.produto.model.business;

import java.util.ArrayList;

import br.com.gv8.yeschamix.produto.model.persistence.ProdutoVO;


public interface ProdutoBOService{
	
	public ArrayList<ProdutoVO> consultarTodosProdutos(String login) throws Exception;
	public ArrayList< ProdutoVO > consultarProdutoPorCodigo(String codigo, String login) throws Exception;
	

}
