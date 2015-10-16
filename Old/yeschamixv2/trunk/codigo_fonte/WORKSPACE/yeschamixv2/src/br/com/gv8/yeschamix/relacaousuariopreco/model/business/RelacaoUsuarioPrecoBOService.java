package br.com.gv8.yeschamix.relacaousuariopreco.model.business;

import java.util.ArrayList;

import br.com.gv8.yeschamix.relacaousuariopreco.model.persistence.RelacaoUsuarioPrecoVO;



public interface RelacaoUsuarioPrecoBOService{
	
	public ArrayList<RelacaoUsuarioPrecoVO> consultarTodosUsuarioPreco(String login) throws Exception;
	public ArrayList<RelacaoUsuarioPrecoVO> consultarUsuarioPrecoPorLogin(String login) throws Exception;
	
}
