package br.com.gv8.yeschamix.familia.controller;

import java.util.HashMap;

import br.com.gv8.yeschamix.familia.model.business.FamiliaBOService;
import br.com.gv8.yeschamix.familia.model.business.FamiliaBOServiceImpl;
import br.com.gv8.yeschamix.familia.model.persistence.FamiliaVO;


public final class FamiliaService{
	
	private FamiliaBOService service;

	/*SINGLETON*/
	private static FamiliaService instance;
	
	private FamiliaService(){
		service = new FamiliaBOServiceImpl();
	}
	
	public static FamiliaService getInstance() {
		if( instance == null ){
			instance = new FamiliaService();
		}
	    return instance;
    }
	/*SINGLETON*/
	
	
	public HashMap< String , FamiliaVO > consultarTodosFamilias(String login) throws Exception {
		return service.consultarTodosFamilias(login);
	}
}
