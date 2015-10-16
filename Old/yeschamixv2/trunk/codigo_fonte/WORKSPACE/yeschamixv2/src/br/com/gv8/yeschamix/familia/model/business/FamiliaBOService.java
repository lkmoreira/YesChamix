package br.com.gv8.yeschamix.familia.model.business;

import java.util.HashMap;

import br.com.gv8.yeschamix.familia.model.persistence.FamiliaVO;



public interface FamiliaBOService{
	
	public HashMap< String , FamiliaVO > consultarTodosFamilias(String login) throws Exception;
	
}
