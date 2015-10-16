package br.com.gv8.yeschamix.preco.model.clienteWS;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;

import br.com.gv8.yeschamix.preco.model.persistence.PrecoVO;
import br.com.gv8.yeschamix.wsconfig.WebService;

import com.google.gson.Gson;

public final class PrecoREST{

	public ArrayList<PrecoVO > consultarTodosPrecos(String login) throws Exception {

		String[] resposta = new WebService().get( "preco/consultarTodos/"+login );

		if ( resposta[0].equals( "200" ) ) {
			Gson gson = new Gson();
			JSONObject json = new JSONObject( resposta[1] );

			JSONArray array = json.getJSONArray("precoDTO");
			
			ArrayList< PrecoVO > lista = new ArrayList< PrecoVO >();

			for( int i = 0; i < array.length(); i++ ) {
				lista.add( gson.fromJson( array.getString( i ) , PrecoVO.class ) );
			}
			
			return lista;
		} else {
			throw new Exception( resposta[1] );
		}
	}
	
	public ArrayList<PrecoVO > consultarTodosPrecosPorUsuario(String login) throws Exception {

		String[] resposta = new WebService().get( "preco/"+login );

		if ( resposta[0].equals( "200" ) ) {
			Gson gson = new Gson();
			JSONObject json = new JSONObject( resposta[1] );

			JSONArray array = json.getJSONArray("precoDTO");
			
			ArrayList< PrecoVO > lista = new ArrayList< PrecoVO >();

			for( int i = 0; i < array.length(); i++ ) {
				lista.add( gson.fromJson( array.getString( i ) , PrecoVO.class ) );
			}
			
			return lista;
		} else {
			throw new Exception( resposta[1] );
		}
	}
}
