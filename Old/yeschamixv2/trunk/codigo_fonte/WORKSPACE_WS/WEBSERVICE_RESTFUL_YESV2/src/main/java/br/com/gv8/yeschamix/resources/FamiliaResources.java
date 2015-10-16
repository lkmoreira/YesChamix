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

import br.com.gv8.yeschamix.dto.FamiliaDTO;
import br.com.gv8.yeschamix.facade.YesChamixFacade;

/**
 * @author Bruno Pelogia
 *
 */

@Path("/familia")
public class FamiliaResources {

	
	@GET
	@Path("/consultarTodos/{login}")
	@Produces({"application/json; charset=UTF-8"})
	public List<FamiliaDTO> getFamilias(@PathParam("login") String login) {
		ArrayList<FamiliaDTO> lista = new ArrayList< FamiliaDTO >();
		lista = (ArrayList< FamiliaDTO >) YesChamixFacade.getInstance().consultarTudoFamilia(login);
		
		return lista;
	}

	@Path("{id}/{login}")
	@GET
	@Produces({"application/json; charset=UTF-8"})
	public ArrayList<FamiliaDTO> getFamilia(@PathParam("id") String id, @PathParam("login") String login) {
		return (ArrayList<FamiliaDTO>) YesChamixFacade.getInstance().consultarTudoFamiliaPorId( id, login );
	}
}
