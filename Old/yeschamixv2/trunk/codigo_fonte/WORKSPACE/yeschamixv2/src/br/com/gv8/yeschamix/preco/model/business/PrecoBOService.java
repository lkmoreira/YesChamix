package br.com.gv8.yeschamix.preco.model.business;

import java.util.ArrayList;

import br.com.gv8.yeschamix.preco.model.persistence.PrecoVO;



public interface PrecoBOService{
	
	public ArrayList<PrecoVO > consultarTodosPrecos(String login) throws Exception;
	public ArrayList<PrecoVO > consultarTodosPrecosPorUsuario(String login) throws Exception;
}
