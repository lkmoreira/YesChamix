package br.com.gv8.yeschamix.classificacao.controller;

import java.util.ArrayList;

import br.com.gv8.yeschamix.classificacao.model.business.ClassificacaoBOService;
import br.com.gv8.yeschamix.classificacao.model.business.ClassificacaoBOServiceImpl;
import br.com.gv8.yeschamix.classificacao.model.persistence.ClassificacaoVO;

public final class ClassificacaoService{
	
	private ClassificacaoBOService service;

	/*SINGLETON*/
	private static ClassificacaoService instance;
	
	private ClassificacaoService(){
		service = new ClassificacaoBOServiceImpl();
	}
	
	public static ClassificacaoService getInstance() {
		if( instance == null ){
			instance = new ClassificacaoService();
		}
	    return instance;
    }
	/*SINGLETON*/
	
	
	public ArrayList< ClassificacaoVO > consultarTodosClassificacoes() throws Exception {
		return service.consultarTodosClassificacoes();
	}
}
