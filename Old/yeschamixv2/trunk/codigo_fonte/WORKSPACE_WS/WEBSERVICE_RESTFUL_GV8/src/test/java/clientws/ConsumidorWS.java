package clientws;

import java.net.URI;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriBuilder;

import org.junit.Test;

import br.com.webservice.exemplo.dto.Cliente;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;

public final class ConsumidorWS{
	
	/*SEM THREAD
	Chamando 1º 1x
	Chamando 1º 2x
	Chamando 1º 3x
	==========================
	Chamando 2º 1x
	Chamando 2º 2x
	Chamando 2º 3x
	==========================
	Chamando 3º 1x
	Chamando 3º 2x
	Chamando 3º 3x
	12203
	7148
	7041
	Média: 8797
	
	Chamando 1º 1x
	Chamando 1º 2x
	Chamando 1º 3x
	==========================
	Chamando 2º 1x
	Chamando 2º 2x
	Chamando 2º 3x
	==========================
	Chamando 3º 1x
	Chamando 3º 2x
	Chamando 3º 3x
	7594
	7155
	7066
	Média: 7271*/
	
	/*COM THREAD
	Chamando 1º 1x
	Chamando 1º 2x
	Chamando 1º 3x
	==========================
	Chamando 2º 1x
	Chamando 2º 2x
	Chamando 2º 3x
	==========================
	Chamando 3º 1x
	Chamando 3º 2x
	Chamando 3º 3x
	1014
	647
	713
	Média: 791*/
	

	private static int HTTP_COD_SUCESSO = 200;
	private final String urlStringCliente = "http://localhost:8080/webservice_v8";

	private Cliente cliente;

	private URI getBaseURI() {
		return UriBuilder.fromUri( urlStringCliente ).build();
	}
	
	@Test
	public void testar(){
		long inicio1 = System.currentTimeMillis();
		
		int i = 0;
		while( i < 1){
			pegarTodosClientes();
			i++;
			System.out.println("Chamando 1º " + i +"x");
		}
		
		long termino1 = System.currentTimeMillis();
				
		System.out.println("==========================");
		
		long inicio2 = System.currentTimeMillis();
		
		i = 0;
		while( i < 1){
			pegarTodosClientes();
			i++;
			System.out.println("Chamando 2º " + i +"x");
		}
		
		long termino2 = System.currentTimeMillis();
		
		System.out.println("==========================");
				
		long inicio3 = System.currentTimeMillis();
		
		i = 0;
		while( i < 1){
			pegarTodosClientes();
			i++;
			System.out.println("Chamando 3º " + i +"x");
		}
		
		long termino3 = System.currentTimeMillis();
		
		System.out.println( termino1 - inicio1 );
		System.out.println( termino2 - inicio2 );
		System.out.println( termino3 - inicio3 );
		System.out.println( "Média: "+ (((termino1+termino2+termino3) - (inicio1+inicio2+inicio3)))/3 );
	}

	/***************** GET *****************/
	
	public void pegarTodosClientes() {		
		String xmlRequisicao = "<ids><id>1</id><id>12</id><id>13</id><id>14</id></ids>";

		ClientConfig config = new DefaultClientConfig();
		Client client = Client.create( config );		
		WebResource service = client.resource( getBaseURI() );

		String response = service.path( "cliente" )
								 .path( "recuperarInformacoes" )
								 .queryParam( "xml", xmlRequisicao )
								 .type( MediaType.APPLICATION_XML ).get( String.class );
		
		System.out.println(response);
	}

	
	public void pegarUnicoCliente() {
		String xmlRequisicao = "<Agora é hora, de alegriaaaaa, vamos sorrir e cantar! Lá lálálá Hey, Lá lálálá Hey, Lá lálálálálálálálálá>";

		ClientConfig config = new DefaultClientConfig();
		Client client = Client.create( config );		
		WebResource service = client.resource( getBaseURI() );

		String response = service.path( "cliente" )
								 .path( "recuperarInformacao" )
								 .queryParam( "xml", xmlRequisicao )
								 .type( MediaType.APPLICATION_XML ).get( String.class );
		
		System.out.println(response);		
	}
	
	public void pegarNomeCliente() {
		String xmlRequisicao = "<Agora é hora, de alegriaaaaa, vamos sorrir e cantar! Lá lálálá Hey, Lá lálálá Hey, Lá lálálálálálálálálá>";

		ClientConfig config = new DefaultClientConfig();
		Client client = Client.create( config );		
		WebResource service = client.resource( getBaseURI() );

		String response = service.path( "cliente" )
								 .path( "recuperarInformacaoTexto" )
								 .queryParam( "xml", xmlRequisicao )
								 .type( MediaType.TEXT_PLAIN ).get( String.class );
		
		System.out.println(response);				
	}

	
	public void getCliente() {
		String xmlRequisicao = "1";

		ClientConfig config = new DefaultClientConfig();
		Client client = Client.create( config );		
		WebResource service = client.resource( getBaseURI() );

		String response = service.path( "cliente" )
								 .path( "recuperarInformacaoTexto" )
								 .path( xmlRequisicao )
								 .type( MediaType.APPLICATION_XML ).get( String.class );
		
		System.out.println(response);
	}

	/***************** POST *****************/
	
	public void inserirCliente() {
		String xmlRequisicao = "<cliente><cpf>111222333-00</cpf><endereco>Rua das ruas, 9</endereco><id>1</id><nome>Alexandre Antunes</nome></cliente>";

		ClientConfig config = new DefaultClientConfig();
		Client client = Client.create( config );
		ClientResponse response;
		WebResource service = client.resource( getBaseURI() );

		response = service.path( "cliente" ).path( "inserir" ).type( MediaType.APPLICATION_XML ).post( ClientResponse.class, xmlRequisicao );
		System.out.println( "Form response " + response.getEntity( String.class ) );
	}
	
	
	public void inserirClienteXML() {
		String xmlRequisicao = "<cliente><cpf>111222333-00</cpf><endereco>Rua das ruas, 9</endereco><id>1</id><nome>Alexandre Antunes</nome></cliente>";

		ClientConfig config = new DefaultClientConfig();
		Client client = Client.create( config );
		ClientResponse response;
		WebResource service = client.resource( getBaseURI() );

		response = service.path( "cliente" ).path( "inserirXML" ).type( MediaType.APPLICATION_XML ).post( ClientResponse.class, xmlRequisicao );
		
		System.out.println( "Form response " + response.getEntity( String.class ) );
	}

}
