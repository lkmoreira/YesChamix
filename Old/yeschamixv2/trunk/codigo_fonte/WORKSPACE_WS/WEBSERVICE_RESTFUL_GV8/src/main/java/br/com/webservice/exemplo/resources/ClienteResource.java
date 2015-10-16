package br.com.webservice.exemplo.resources;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import br.com.webservice.exemplo.db.Banco;
import br.com.webservice.exemplo.dto.Cliente;
import br.com.webservice.exemplo.exception.NoContentException;

@Path("/cliente")
public class ClienteResource {
	 // @Produces - Devolve
	 // @Consumes - Recebe

	/*http://localhost:8080/webservice_v8/cliente/recuperarInformacoes?xml=<idade>77</idade>*/		
	@GET
	@Path("/recuperarInformacoes")
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.APPLICATION_XML)
	public ArrayList<Cliente> pegarTodosClientes(@QueryParam("xml") String xml) {
		/*new Thread( new Runnable(){
			
			@Override
			public void run() {*/
				long cont = 0;
				while( cont < 3000000000L ){
					cont++;
				}				
				System.out.println(System.currentTimeMillis() +"ms  - " +cont+"  "+ cont);
			/*}
		}).start();*/
		
				
		return Banco.getBancoInstance().getListaClientes();
	}

	/*http://localhost:8080/webservice_v8/cliente/recuperarInformacao?xml=<sexo>masculino</sexo>*/
	@GET
	@Path("/recuperarInformacao")
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.APPLICATION_XML)
	public Cliente pegarUnicoCliente(@QueryParam("xml") String xml) {
		System.out.println("pegarUnicoCliente" + xml);
		return new Cliente(22, "Capiel", "226.420.158-40", "Rua Constantino Neves, 71 JD. Flamboyant");
	}
	
	/*http://localhost:8080/webservice_v8/cliente/recuperarInformacaoTexto?xml=<altura>1.90</altura>*/
	@GET
	@Path("/recuperarInformacaoTexto")
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.TEXT_PLAIN)
	public String pegarNomeCliente(@QueryParam("xml") String xml) {
		System.out.println("Requisição recebida por: " + xml);
		
		long cont = 0;
		while( cont < 3000000000L ){
			cont++;
		}				
		//System.out.println(System.currentTimeMillis() +"ms  - " +cont+"  "+ cont);
		
		return new SimpleDateFormat( "HH:mm:ss:SS" ).format( new Date() ) + " Nome Retornado - " + xml;
	}

	/*http://localhost:8080/webservice_v8/cliente/1*/
	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_XML)
	public Cliente getCliente(@PathParam("id") int id) {
		Cliente cliente = Banco.getBancoInstance().buscar(id);

		if (cliente == null)
			throw new NoContentException("Cliente não encontrado!");

		return cliente;
	}

	
	
	
	
	/*
		URL: http://localhost:8080/webservice_v8/cliente/inserir
		Content Type: application/xml
		Dados XML enviados: <cliente><cpf>111222333-00</cpf><endereco>Rua das ruas, 9</endereco><id>1</id><nome>Alexandre Antunes</nome></cliente>	
	*/
	@POST
	@Path("/inserir")
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.TEXT_PLAIN)
	public String inserirCliente(Cliente cliente) {
		return Banco.getBancoInstance().inserir(cliente);
	}	
	
	/*
		URL: http://localhost:8080/webservice_v8/cliente/inserirXML
		Content Type: application/xml
		Dados XML enviados: <cliente><cpf>22345654-60</cpf><endereco>Rua das ruas, 876</endereco><id>1</id><nome>Tireoide Maduro</nome></cliente>	
	*/
	@POST
	@Path("/inserirXML")
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.APPLICATION_XML)
	public Cliente inserirClienteXML(Cliente cliente) {
		Banco.getBancoInstance().inserir(cliente);
		return cliente;
	}
	
	@POST
	@Path("/findByPost")
	//@Consumes("application/xml")
	//@Produces("application/xml")
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.APPLICATION_XML)
	public ArrayList<Cliente> findByPost(Cliente cliente) {
		System.out.println("pegarTodosClientes" + cliente);
		return Banco.getBancoInstance().getListaClientes();
	}
}