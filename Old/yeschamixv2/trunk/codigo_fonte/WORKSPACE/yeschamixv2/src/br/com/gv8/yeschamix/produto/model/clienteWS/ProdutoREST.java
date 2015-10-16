package br.com.gv8.yeschamix.produto.model.clienteWS;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;

import br.com.gv8.yeschamix.produto.model.persistence.ProdutoVO;
import br.com.gv8.yeschamix.wsconfig.WebService;

import com.google.gson.Gson;

public final class ProdutoREST{

	public ArrayList< ProdutoVO > consultarTodosProdutosBack(String login) throws Exception {
		
		String[] resposta = new WebService().get( "produto/110/"+login );

		if ( resposta[0].equals( "200" ) ) {
			Gson gson = new Gson();
			
			JSONObject json = new JSONObject(resposta[1]);
            
            JSONArray array = json.getJSONArray("produtoDTO");
			
			ArrayList< ProdutoVO > lista = new ArrayList< ProdutoVO >();

			for( int i = 0; i < array.length(); i++ ) {
				
				lista.add( gson.fromJson( array.getString( i ) , ProdutoVO.class ) );
				
			}
			return lista;
		} else {
			throw new Exception( resposta[1] );
		}
			
	}
	
public ArrayList< ProdutoVO > consultarProdutoPorCodigo(String codigoProduto, String login) throws Exception {
		
		String[] resposta = new WebService().get( "produto/" + codigoProduto + "/"+login );

		if ( resposta[0].equals( "200" ) ) {
			Gson gson = new Gson();
			
			JSONObject json = new JSONObject(resposta[1]);
            
            JSONArray array = json.getJSONArray("produtoDTO");
			
			ArrayList< ProdutoVO > lista = new ArrayList< ProdutoVO >();

			for( int i = 0; i < array.length(); i++ ) {
				
				lista.add( gson.fromJson( array.getString( i ) , ProdutoVO.class ) );
				
			}
			return lista;
		} else {
			throw new Exception( resposta[1] );
		}
			
	}

	public ArrayList< ProdutoVO > consultarTodosProdutos( String login ) throws Exception {

		String[] resposta = new WebService().get( "produto/consultarTodos/" + login );

		if ( resposta[0].equals( "200" ) ) {
			Gson gson = new Gson();

			JSONObject json = new JSONObject( resposta[1] );

			JSONArray array = json.getJSONArray( "produtoDTO" );

			ArrayList< ProdutoVO > lista = new ArrayList< ProdutoVO >();

			for( int i = 0; i < array.length(); i++ ) {

				lista.add( gson.fromJson( array.getString( i ) , ProdutoVO.class ) );

			}
			return lista;
		} else {
			throw new Exception( resposta[1] );
		}

	}

}

