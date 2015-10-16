package br.com.gv8.yeschamix.relacaousuariopreco.model.business;

import java.util.ArrayList;

import br.com.gv8.yeschamix.relacaousuariopreco.model.clienteWS.RelacaoUsuarioPrecoREST;
import br.com.gv8.yeschamix.relacaousuariopreco.model.persistence.RelacaoUsuarioPrecoVO;



public final class RelacaoUsuarioPrecoBOServiceImpl implements RelacaoUsuarioPrecoBOService{

	private RelacaoUsuarioPrecoREST usuarioPrecoREST;

	public RelacaoUsuarioPrecoBOServiceImpl(){
		if ( usuarioPrecoREST == null ) {
			usuarioPrecoREST = new RelacaoUsuarioPrecoREST();
		}
	}

	/**
	 * Polimorfico
	 * @see 
	 */
	@Override
	public ArrayList< RelacaoUsuarioPrecoVO > consultarTodosUsuarioPreco(String login) throws Exception {
		try {
			if ( usuarioPrecoREST == null ) {
				usuarioPrecoREST = new RelacaoUsuarioPrecoREST();
			}
			
			return usuarioPrecoREST.consultarTodosUsuarioPreco(login);
		} catch ( Exception e ) {
			e.printStackTrace();
			throw new Exception("Ocorreu um erro ao acessar o Webservice.");
		}finally{
			usuarioPrecoREST = null;
		}
	}
	
	@Override
	public ArrayList< RelacaoUsuarioPrecoVO > consultarUsuarioPrecoPorLogin(String login) throws Exception {
		try {
			if ( usuarioPrecoREST == null ) {
				usuarioPrecoREST = new RelacaoUsuarioPrecoREST();
			}
			
			return usuarioPrecoREST.consultarUsuarioPrecoPorLogin(login);
		} catch ( Exception e ) {
			e.printStackTrace();
			throw new Exception("Ocorreu um erro ao acessar o Webservice.");
		}finally{
			usuarioPrecoREST = null;
		}
	}

}
