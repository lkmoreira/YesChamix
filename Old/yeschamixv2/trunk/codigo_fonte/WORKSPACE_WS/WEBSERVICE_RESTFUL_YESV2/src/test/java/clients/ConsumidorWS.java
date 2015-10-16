package clients;

import java.net.URI;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriBuilder;

import org.junit.Test;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;

public final class ConsumidorWS{

	private static int HTTP_COD_SUCESSO = 200;
	private final String urlStringCliente = "http://localhost:8080/webservice_gv8_yes";

	

	private URI getBaseURI() {
		return UriBuilder.fromUri( urlStringCliente ).build();
	}
	
	@Test
	public void getClassificacaoJson(){
		System.out.println("--Consultar Todos--\n");
		ClientConfig config = new DefaultClientConfig();
		Client client = Client.create( config );		
		WebResource service = client.resource( getBaseURI() );

		String response = service.path( "classificacao" )
								 .path( "consultarTodos" )
								 .get( String.class );
		
		System.out.println(response);
		
		System.out.println("\n--Consultar por codigo--\n");
		
		WebResource service1 = client.resource( getBaseURI() );

		String response1 = service1.path( "classificacao" )
								 .path( "0429" )
								 .get( String.class );
		
		System.out.println(response1);
		
	}
	
	@Test
	public void getPreco(){
		System.out.println("--Consultar Todos--\n");
		ClientConfig config = new DefaultClientConfig();
		Client client = Client.create( config );		
		WebResource service = client.resource( getBaseURI() );

		String response = service.path( "familia" )
								 .path( "consultarTodos" )
								 .get( String.class );
		
		System.out.println(response);
	}
	
	
}
