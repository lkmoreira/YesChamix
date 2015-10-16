package br.com.gv8.yeschamix.classificacao.model.business;

import java.util.ArrayList;

import br.com.gv8.yeschamix.classificacao.model.persistence.ClassificacaoVO;


public interface ClassificacaoBOService{
	
	public ArrayList<ClassificacaoVO> consultarTodosClassificacoes() throws Exception;
	
}
