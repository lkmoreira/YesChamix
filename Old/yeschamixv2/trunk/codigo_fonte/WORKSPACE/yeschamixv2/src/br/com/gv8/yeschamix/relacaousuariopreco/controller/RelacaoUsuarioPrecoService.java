package br.com.gv8.yeschamix.relacaousuariopreco.controller;

import java.util.ArrayList;

import br.com.gv8.yeschamix.relacaousuariopreco.model.business.RelacaoUsuarioPrecoBOService;
import br.com.gv8.yeschamix.relacaousuariopreco.model.business.RelacaoUsuarioPrecoBOServiceImpl;
import br.com.gv8.yeschamix.relacaousuariopreco.model.persistence.RelacaoUsuarioPrecoVO;


public final class RelacaoUsuarioPrecoService{
	
	private RelacaoUsuarioPrecoBOService service;

	/*SINGLETON*/
	private static RelacaoUsuarioPrecoService instance;
	
	private RelacaoUsuarioPrecoService(){
		service = new RelacaoUsuarioPrecoBOServiceImpl();
	}
	
	public static RelacaoUsuarioPrecoService getInstance() {
		if( instance == null ){
			instance = new RelacaoUsuarioPrecoService();
		}
	    return instance;
    }
	/*SINGLETON*/
	
	
	public ArrayList< RelacaoUsuarioPrecoVO > consultarUsuarioPrecoPorLogin(String login) throws Exception {
		return service.consultarUsuarioPrecoPorLogin( login );
	}
	
	public ArrayList< RelacaoUsuarioPrecoVO > consultarTudoUsuarioPreco(String login) throws Exception {
		return service.consultarTodosUsuarioPreco(login);
	}
}
