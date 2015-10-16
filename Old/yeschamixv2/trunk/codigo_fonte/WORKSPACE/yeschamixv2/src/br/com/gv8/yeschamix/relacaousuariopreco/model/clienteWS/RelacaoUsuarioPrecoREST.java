package br.com.gv8.yeschamix.relacaousuariopreco.model.clienteWS;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;

import br.com.gv8.yeschamix.relacaousuariopreco.model.persistence.RelacaoUsuarioPrecoVO;
import br.com.gv8.yeschamix.util.Utilidades;
import br.com.gv8.yeschamix.wsconfig.WebService;

import com.google.gson.Gson;

public final class RelacaoUsuarioPrecoREST {

	public ArrayList< RelacaoUsuarioPrecoVO > consultarTodosUsuarioPreco(String login) throws Exception {

		String[] resposta = new WebService().get( "usuarioPreco/consultarTodos/"+login );

		if ( resposta[0].equals( "200" ) ) {
			Gson gson = new Gson();
			JSONObject json = new JSONObject( resposta[1] );

			JSONArray array = json.getJSONArray( "usuarioPrecoDTO" );

			ArrayList< RelacaoUsuarioPrecoVO > lista = new ArrayList< RelacaoUsuarioPrecoVO >();

			for( int i = 0; i < array.length(); i++ ) {

				lista.add( gson.fromJson( array.getString( i ) , RelacaoUsuarioPrecoVO.class ) );

			}
			return lista;
		} else {
			throw new Exception( resposta[1] );
		}
	}

	public ArrayList< RelacaoUsuarioPrecoVO > consultarUsuarioPrecoPorLogin( String login ) throws Exception {

		String[] resposta = new WebService().get( "usuarioPreco/" + login );

		if ( resposta[0].equals( "200" ) ) {
			Gson gson = new Gson();

			ArrayList< RelacaoUsuarioPrecoVO > lista = new ArrayList< RelacaoUsuarioPrecoVO >();
			if ( !"null".equals( resposta[1]) ) {

				JSONObject json = new JSONObject( resposta[1] );

				JSONArray array = json.getJSONArray( "usuarioPrecoDTO" );

				for( int i = 0; i < array.length(); i++ ) {

					lista.add( gson.fromJson( array.getString( i ) , RelacaoUsuarioPrecoVO.class ) );

				}
			}
			return lista;
		} else {
			throw new Exception( resposta[1] );
		}
	}
}
