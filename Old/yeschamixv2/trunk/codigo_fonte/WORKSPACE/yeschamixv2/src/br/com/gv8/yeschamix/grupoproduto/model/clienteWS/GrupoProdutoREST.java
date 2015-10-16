package br.com.gv8.yeschamix.grupoproduto.model.clienteWS;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;

import br.com.gv8.yeschamix.grupoproduto.model.persistence.GrupoProdutoVO;
import br.com.gv8.yeschamix.wsconfig.WebService;

import com.google.gson.Gson;


public final class GrupoProdutoREST{

	public ArrayList< GrupoProdutoVO > consultarTodosGruposProduto() throws Exception {

		String[] resposta = new WebService().get( "grupoProduto/consultarTodos" );

		if ( resposta[0].equals( "200" ) ) {
			Gson gson = new Gson();
			JSONObject json = new JSONObject( resposta[1] );

			JSONArray array = json.getJSONArray("grupoProdutoDTO");
			
			ArrayList< GrupoProdutoVO > lista = new ArrayList< GrupoProdutoVO >();

			for( int i = 0; i < array.length(); i++ ) {
				lista.add( gson.fromJson( array.getString( i ) , GrupoProdutoVO.class ) );
			}

			return lista;
		} else {
			throw new Exception( resposta[1] );
		}
	}
}
