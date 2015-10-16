package br.com.gv8.yeschamix.usuario.model.clienteWS;

import java.util.ArrayList;
import java.util.HashMap;

import org.json.JSONArray;
import org.json.JSONObject;

import br.com.gv8.yeschamix.usuario.model.persistence.UsuarioVO;
import br.com.gv8.yeschamix.wsconfig.WebService;

import com.google.gson.Gson;

public final class UsuarioREST{

	public HashMap< String, UsuarioVO > consultarTodosUsuarios(String login) throws Exception {

		String[] resposta = new WebService().get( "usuario/consultarTodos/"+login );

		if ( resposta[0].equals( "200" ) ) {
			Gson gson = new Gson();
			JSONObject json = new JSONObject( resposta[1] );

			JSONArray array = json.getJSONArray("usuarioDTO");
			
			ArrayList< UsuarioVO > lista = new ArrayList< UsuarioVO >();

			for( int i = 0; i < array.length(); i++ ) {
				lista.add( gson.fromJson( array.getString( i ) , UsuarioVO.class ) );
			}

			HashMap< String, UsuarioVO > usuarios = new HashMap< String, UsuarioVO >();
			for( UsuarioVO usuario : lista ) {
				usuarios.put( usuario.getId().toString() , usuario );
			}
			return usuarios;
		} else {
			throw new Exception( resposta[1] );
		}
	}
	
	
	public String autenticar(String dadosLogin) throws Exception {
		dadosLogin = dadosLogin.replace( "{" , "%7B" );
		dadosLogin = dadosLogin.replace( "}" , "%7D" );
		dadosLogin = dadosLogin.replace( "\"" , "" );
		String[] resposta = new WebService().get("usuario/login/"+dadosLogin);
		
		if (resposta[0].equals("200")) {
			Gson gson = new Gson();
			String status = gson.fromJson(resposta[1], String.class);
			
			return status;
			
		} else {
			throw new Exception(resposta[1]);
		}
	}	
}
