/**
 * 
 */
package br.com.gv8.yeschamix.resources;

import java.util.ArrayList;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import br.com.gv8.yeschamix.dto.ProdutoDTO;
import br.com.gv8.yeschamix.facade.YesChamixFacade;

/**
 * @author Bruno Pelogia
 *
 */
@Path("/produto")
public class ProdutoResources {


	@GET
	@Path("/consultarTodos/{login}")
	@Produces({"application/json; charset=UTF-8"})
	public ArrayList< ProdutoDTO > getProdutos(@PathParam("login") String login) {
		ArrayList<ProdutoDTO> lista = new ArrayList< ProdutoDTO >();
		lista = (ArrayList< ProdutoDTO >) YesChamixFacade.getInstance().consultarTudoProduto(login);
	
		return lista;
	}

	@Path("{id}/{login}")
	@GET
	@Produces({"application/json; charset=UTF-8"})
	public ArrayList<ProdutoDTO> getProduto(@PathParam("id") String id, @PathParam("login") String login) {
		return (ArrayList<ProdutoDTO>) YesChamixFacade.getInstance().consultarTudoProdutoPorId( id, login );
	}
}
