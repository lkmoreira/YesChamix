package br.com.gv8.yeschamix.usuario.controller;

import java.util.HashMap;

import br.com.gv8.yeschamix.usuario.model.business.UsuarioBOService;
import br.com.gv8.yeschamix.usuario.model.business.UsuarioBOServiceImpl;
import br.com.gv8.yeschamix.usuario.model.persistence.UsuarioVO;


public final class UsuarioService{
	
	private UsuarioBOService service;

	/*SINGLETON*/
	private static UsuarioService instance;
	
	private UsuarioService(){
		service = new UsuarioBOServiceImpl();
	}
	
	public static UsuarioService getInstance() {
		if( instance == null ){
			instance = new UsuarioService();
		}
	    return instance;
    }
	/*SINGLETON*/
	
	
	public String autenticar(String dadosLogin) throws Exception {
		return service.autenticar( dadosLogin );
	}
	
	public HashMap< String, UsuarioVO > consultarTodosUsuarios(String login) throws Exception {
		return service.consultarTodosUsuarios(login);
	}

}
