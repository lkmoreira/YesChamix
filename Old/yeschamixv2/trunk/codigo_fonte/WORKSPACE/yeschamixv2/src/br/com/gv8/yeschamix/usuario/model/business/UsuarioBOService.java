package br.com.gv8.yeschamix.usuario.model.business;

import java.util.HashMap;

import br.com.gv8.yeschamix.usuario.model.persistence.UsuarioVO;



public interface UsuarioBOService{
	
	public String autenticar(String dadosLogin) throws Exception;
	public HashMap< String, UsuarioVO > consultarTodosUsuarios(String login) throws Exception;
}
