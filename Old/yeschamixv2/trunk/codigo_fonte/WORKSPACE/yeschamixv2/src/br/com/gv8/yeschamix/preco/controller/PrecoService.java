package br.com.gv8.yeschamix.preco.controller;

import java.util.ArrayList;

import br.com.gv8.yeschamix.preco.model.business.PrecoBOService;
import br.com.gv8.yeschamix.preco.model.business.PrecoBOServiceImpl;
import br.com.gv8.yeschamix.preco.model.persistence.PrecoVO;


public final class PrecoService{
	
	private PrecoBOService service;

	/*SINGLETON*/
	private static PrecoService instance;
	
	private PrecoService(){
		service = new PrecoBOServiceImpl();
	}
	
	public static PrecoService getInstance() {
		if( instance == null ){
			instance = new PrecoService();
		}
	    return instance;
    }
	/*SINGLETON*/
	
	
	public ArrayList<PrecoVO > consultarTodosPrecos(String login) throws Exception {
		return service.consultarTodosPrecos(login);
	}
	
	public ArrayList<PrecoVO > consultarTodosPrecosPorUsuario(String login) throws Exception {
		return service.consultarTodosPrecos(login);
	}
}
