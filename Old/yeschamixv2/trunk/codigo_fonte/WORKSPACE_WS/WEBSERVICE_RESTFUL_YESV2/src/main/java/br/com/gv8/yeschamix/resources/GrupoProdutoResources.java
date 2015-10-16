/**
 * 
 */
package br.com.gv8.yeschamix.resources;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import br.com.gv8.yeschamix.dto.GrupoProdutoDTO;
import br.com.gv8.yeschamix.facade.YesChamixFacade;

/**
 * @author Bruno Pelogia
 *
 */
@Path("/grupoProduto")
public class GrupoProdutoResources {
	

	@GET
	@Path("/consultarTodos/{login}")
	@Produces({"application/json; charset=UTF-8"})
	public List<GrupoProdutoDTO> getGruposProduto(@PathParam("login") String login) {
		ArrayList<GrupoProdutoDTO> lista = new ArrayList< GrupoProdutoDTO >();
		lista = (ArrayList< GrupoProdutoDTO >) YesChamixFacade.getInstance().consultarTudoGrupoProduto(login);
		return lista;
	}

	@Path("{id}/{login}")
	@GET
	@Produces({"application/json; charset=UTF-8"})
	public ArrayList<GrupoProdutoDTO> getGrupoProduto(@PathParam("id") String id, @PathParam("login") String login) {
		return (ArrayList<GrupoProdutoDTO>) YesChamixFacade.getInstance().consultarTudoGrupoProdutoPorId( id, login );
	}
}
