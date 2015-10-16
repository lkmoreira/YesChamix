package br.com.gv8.yeschamix.usuario.model.business;

import java.util.HashMap;

import br.com.gv8.yeschamix.usuario.model.clienteWS.UsuarioREST;
import br.com.gv8.yeschamix.usuario.model.persistence.UsuarioVO;



public final class UsuarioBOServiceImpl implements UsuarioBOService{

	private UsuarioREST usuarioREST;

	public UsuarioBOServiceImpl(){
		if ( usuarioREST == null ) {
			usuarioREST = new UsuarioREST();
		}
	}

	/**
	 * Polimorfico
	 * @see br.com.v8soft.gigachef.garcon.model.business.GarconBOService#consultarTodosGarcons()
	 */
	@Override
	public String autenticar(String dadosLogin) throws Exception {
		try {
			if ( usuarioREST == null ) {
				usuarioREST = new UsuarioREST();
			}
			return usuarioREST.autenticar( dadosLogin );
		} catch ( Exception e ) {
			e.printStackTrace();
			throw new Exception("Ocorreu um erro ao acessar o Webservice.");
		}finally{
			usuarioREST = null;
		}
	}

	@Override
	public HashMap< String, UsuarioVO > consultarTodosUsuarios(String login) throws Exception {
		try {
			if ( usuarioREST == null ) {
				usuarioREST = new UsuarioREST();
			}
			return usuarioREST.consultarTodosUsuarios(login);
		} catch ( Exception e ) {
			e.printStackTrace();
			throw new Exception("Ocorreu um erro ao acessar o Webservice.");
		}finally{
			usuarioREST = null;
		}
	}

}
