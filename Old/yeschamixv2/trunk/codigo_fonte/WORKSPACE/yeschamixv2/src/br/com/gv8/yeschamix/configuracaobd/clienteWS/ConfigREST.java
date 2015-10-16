package br.com.gv8.yeschamix.configuracaobd.clienteWS;

import br.com.gv8.yeschamix.wsconfig.WebService;

import com.google.gson.Gson;

public final class ConfigREST{

	public String configurarBD(String dados) throws Exception {
		dados = dados.replace( "{" , "%7B" );
		dados = dados.replace( "}" , "%7D" );
		dados = dados.replace( "/" , "%20" );
		dados = dados.replace( "\"" , "'" );
		
		String[] resposta = new WebService().get("configuracao/"+dados);
		
		if (resposta[0].equals("200")) {
			Gson gson = new Gson();
			String status = gson.fromJson(resposta[1], String.class);
			return status;
			
		} else {
			throw new Exception(resposta[1]);
		}
	}
}
