package br.com.gv8.yeschamix.familia.model.clienteWS;

import java.util.ArrayList;
import java.util.HashMap;

import org.json.JSONArray;
import org.json.JSONObject;

import br.com.gv8.yeschamix.familia.model.persistence.FamiliaVO;
import br.com.gv8.yeschamix.wsconfig.WebService;

import com.google.gson.Gson;



public final class FamiliaREST{

	public HashMap< String , FamiliaVO > consultarTodosFamilias(String login) throws Exception {

		String[] resposta = new WebService().get( "familia/consultarTodos/"+login );

		if ( resposta[0].equals( "200" ) ) {
			Gson gson = new Gson();
			JSONObject json = new JSONObject( resposta[1] );

			JSONArray array = json.getJSONArray("familiaDTO");
			
			ArrayList< FamiliaVO > lista = new ArrayList< FamiliaVO >();
			HashMap< String , FamiliaVO > familias = new HashMap< String , FamiliaVO >();
			
			for( int i = 0; i < array.length(); i++ ) {
				lista.add( gson.fromJson( array.getString( i ) , FamiliaVO.class ) );
			}

			for( FamiliaVO familia : lista ) {
				familias.put( familia.getId() , familia );
			}
			return familias;
			
		} else {
			throw new Exception( resposta[1] );
		}
	}
}
