package br.com.gv8.yeschamix.familia.model.business;

import java.util.HashMap;

import br.com.gv8.yeschamix.familia.model.clienteWS.FamiliaREST;
import br.com.gv8.yeschamix.familia.model.persistence.FamiliaVO;


public final class FamiliaBOServiceImpl implements FamiliaBOService{

	private FamiliaREST familiaREST;

	public FamiliaBOServiceImpl(){
		if ( familiaREST == null ) {
			familiaREST = new FamiliaREST();
		}
	}

	/**
	 * Polimorfico
	 * @see 
	 */
	@Override
	public HashMap< String , FamiliaVO > consultarTodosFamilias(String login) throws Exception {
		try {
			if ( familiaREST == null ) {
				familiaREST = new FamiliaREST();
			}
			
			return familiaREST.consultarTodosFamilias(login);
		} catch ( Exception e ) {
			e.printStackTrace();
			throw new Exception("Ocorreu um erro ao acessar o Webservice.");
		}finally{
			familiaREST = null;
		}
	}

}
