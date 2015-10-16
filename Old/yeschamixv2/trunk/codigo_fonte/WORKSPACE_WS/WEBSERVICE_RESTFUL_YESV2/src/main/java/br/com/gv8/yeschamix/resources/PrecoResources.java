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

import br.com.gv8.yeschamix.dto.PrecoDTO;
import br.com.gv8.yeschamix.facade.YesChamixFacade;

/**
 * @author Bruno Pelogia
 *
 */
@Path("/preco")
public class PrecoResources {

	@GET
	@Path("/consultarTodos/{login}")
	@Produces({"application/json; charset=UTF-8"})
	public List<PrecoDTO> getPrecos(@PathParam("login") String login) {
		ArrayList<PrecoDTO> lista = new ArrayList< PrecoDTO >();
		lista = (ArrayList< PrecoDTO >) YesChamixFacade.getInstance().consultarTudoPreco(login);
		return lista;
	}

	@Path("/codigo/{id}/{login}")
	@GET
	@Produces({"application/json; charset=UTF-8"})
	public ArrayList<PrecoDTO> getPreco(@PathParam("id") String id, @PathParam("login") String login) {
		return (ArrayList<PrecoDTO>) YesChamixFacade.getInstance().consultarTudoPrecoPorId( id, login );
	}
	
	@Path("/login/{usuario}")
	@GET
	@Produces({"application/json; charset=UTF-8"})
	public ArrayList<PrecoDTO> getPrecosPorUsuario(@PathParam("login") String usuario) {
		ArrayList<PrecoDTO> lista = new ArrayList< PrecoDTO >();
		lista = (ArrayList< PrecoDTO >) YesChamixFacade.getInstance().consultarTodosPrecoPorUsuario(usuario);
		return lista;
	}
}
