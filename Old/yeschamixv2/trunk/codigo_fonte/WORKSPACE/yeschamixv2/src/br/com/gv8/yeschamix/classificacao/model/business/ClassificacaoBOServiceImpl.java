package br.com.gv8.yeschamix.classificacao.model.business;

import java.util.ArrayList;

import br.com.gv8.yeschamix.classificacao.model.clienteWS.ClassificacaoREST;
import br.com.gv8.yeschamix.classificacao.model.persistence.ClassificacaoVO;


public final class ClassificacaoBOServiceImpl implements ClassificacaoBOService{

	private ClassificacaoREST classificacaoREST;

	public ClassificacaoBOServiceImpl(){
		if ( classificacaoREST == null ) {
			classificacaoREST = new ClassificacaoREST();
		}
	}

	/**
	 * Polimorfico
	 * @see br.com.ClassificacaoBOService.gigachef.garcon.model.business.GarconBOService#consultarTodosGarcons()
	 */
	@Override
	public ArrayList< ClassificacaoVO > consultarTodosClassificacoes() throws Exception {
		try {
			if ( classificacaoREST == null ) {
				classificacaoREST = new ClassificacaoREST();
			}
			
			return  classificacaoREST.consultarTodosClassificacoes();
		} catch ( Exception e ) {
			e.printStackTrace();
			throw new Exception("Ocorreu um erro ao acessar o Webservice.");
		}finally{
			classificacaoREST = null;
		}
	}

}
