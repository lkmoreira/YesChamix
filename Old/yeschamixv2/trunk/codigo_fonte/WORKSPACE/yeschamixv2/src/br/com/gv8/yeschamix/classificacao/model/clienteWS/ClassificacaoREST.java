package br.com.gv8.yeschamix.classificacao.model.clienteWS;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;

import br.com.gv8.yeschamix.classificacao.model.persistence.ClassificacaoVO;
import br.com.gv8.yeschamix.wsconfig.WebService;

import com.google.gson.Gson;

public final class ClassificacaoREST {

	public ArrayList< ClassificacaoVO > consultarTodosClassificacoes() throws Exception {

		String[] resposta = new WebService().get( "classificacao/consultarTodos" );

		if ( resposta[0].equals( "200" ) ) {
			Gson gson = new Gson();
			JSONObject json = new JSONObject( resposta[1] );

			JSONArray array = json.getJSONArray("classificacaoDTO");
			
			ArrayList< ClassificacaoVO > lista = new ArrayList< ClassificacaoVO >();

			for( int i = 0; i < array.length(); i++ ) {
				lista.add( gson.fromJson( array.getString( i ) , ClassificacaoVO.class ) );
			}

			return lista;
		} else {
			throw new Exception( resposta[1] );
		}
	}
}
