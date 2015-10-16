package br.com.gv8.yeschamix.grupoproduto.model.business;

import java.util.ArrayList;

import br.com.gv8.yeschamix.grupoproduto.model.persistence.GrupoProdutoVO;


public interface GrupoProdutoBOService{
	
	public ArrayList<GrupoProdutoVO> consultarTodosGruposProduto() throws Exception;
	
}
