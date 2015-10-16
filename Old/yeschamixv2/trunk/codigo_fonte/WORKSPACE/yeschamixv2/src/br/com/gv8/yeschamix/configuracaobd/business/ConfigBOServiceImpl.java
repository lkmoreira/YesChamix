package br.com.gv8.yeschamix.configuracaobd.business;

import com.google.gson.Gson;

import br.com.gv8.yeschamix.configuracaobd.clienteWS.ConfigREST;
import br.com.gv8.yeschamix.configuracaobd.dto.ConfigDTO;

public final class ConfigBOServiceImpl implements ConfigBOService{

	private ConfigREST configREST;

	public ConfigBOServiceImpl(){
		if ( configREST == null ) {
			configREST = new ConfigREST();
		}
	}

	/**
	 * Polimorfico
	 * @see 
	 */
	@Override
	public String configurarBD(String login, String driver, String url, String user, String password) throws Exception {
		try {
			if ( configREST == null ) {
				configREST = new ConfigREST();
			}
			Gson gson = new Gson();
			ConfigDTO configuracao = new ConfigDTO( login, driver, url, user, password );
			
			String dados = gson.toJson(configuracao) ;
			
			return configREST.configurarBD(dados);
		} catch ( Exception e ) {
			e.printStackTrace();
			throw new Exception("Ocorreu um erro ao acessar o Webservice.");
		}finally{
			configREST = null;
		}
	}

}
