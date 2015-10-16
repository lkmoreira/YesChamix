package br.com.gv8.yeschamix.configuracaobd.controller;

import br.com.gv8.yeschamix.configuracaobd.business.ConfigBOService;
import br.com.gv8.yeschamix.configuracaobd.business.ConfigBOServiceImpl;


public final class ConfigService{
	
	private ConfigBOService service;

	/*SINGLETON*/
	private static ConfigService instance;
	
	private ConfigService(){
		service = new ConfigBOServiceImpl();
	}
	
	public static ConfigService getInstance() {
		if( instance == null ){
			instance = new ConfigService();
		}
	    return instance;
    }
	/*SINGLETON*/
	
	
	public String configurarBD(String login, String driver, String url, String user, String password) throws Exception {
		return service.configurarBD(login, driver, url, user, password);
	}
}
