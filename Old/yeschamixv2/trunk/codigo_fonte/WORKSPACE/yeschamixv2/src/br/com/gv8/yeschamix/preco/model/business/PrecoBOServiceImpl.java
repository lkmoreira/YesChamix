package br.com.gv8.yeschamix.preco.model.business;

import java.util.ArrayList;

import br.com.gv8.yeschamix.preco.model.clienteWS.PrecoREST;
import br.com.gv8.yeschamix.preco.model.persistence.PrecoVO;

public final class PrecoBOServiceImpl implements PrecoBOService{

	private PrecoREST precoREST;

	public PrecoBOServiceImpl(){
		if ( precoREST == null ) {
			precoREST = new PrecoREST();
		}
	}

	/**
	 * Polimorfico
	 * @see 
	 */
	@Override
	public ArrayList<PrecoVO > consultarTodosPrecos(String login) throws Exception {
		try {
			if ( precoREST == null ) {
				precoREST = new PrecoREST();
			}
			
			return precoREST.consultarTodosPrecos(login);
		} catch ( Exception e ) {
			e.printStackTrace();
			throw new Exception("Ocorreu um erro ao acessar o Webservice.");
		}finally{
			precoREST = null;
		}
	}
	
	@Override
	public ArrayList<PrecoVO > consultarTodosPrecosPorUsuario(String login) throws Exception {
		try {
			if ( precoREST == null ) {
				precoREST = new PrecoREST();
			}
			
			return precoREST.consultarTodosPrecos(login);
		} catch ( Exception e ) {
			e.printStackTrace();
			throw new Exception("Ocorreu um erro ao acessar o Webservice.");
		}finally{
			precoREST = null;
		}
	}

}
