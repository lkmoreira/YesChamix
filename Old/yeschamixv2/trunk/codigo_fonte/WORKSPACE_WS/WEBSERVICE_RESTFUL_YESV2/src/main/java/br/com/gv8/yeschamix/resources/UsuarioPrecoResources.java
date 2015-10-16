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

import br.com.gv8.yeschamix.dto.UsuarioDTO;
import br.com.gv8.yeschamix.dto.UsuarioPrecoDTO;
import br.com.gv8.yeschamix.facade.YesChamixFacade;

/**
 * @author Bruno Pelogia
 *
 */
@Path("/usuarioPreco")
public class UsuarioPrecoResources {
	
	@GET
	@Path("/consultarTodos/{login}")
	@Produces({"application/json; charset=UTF-8"})
	public List<UsuarioPrecoDTO> getUsuariosPrecos(@PathParam("login") String login) {
		ArrayList<UsuarioPrecoDTO> lista = new ArrayList< UsuarioPrecoDTO >();
		lista = (ArrayList< UsuarioPrecoDTO >) YesChamixFacade.getInstance().consultarTudoUsuarioPreco(login);
		return lista;
	}
	
	@Path("/{login}")
	@GET
	@Produces({"application/json; charset=UTF-8"})
	public List<UsuarioPrecoDTO> getUsuarioPreco(@PathParam("login") String login) {
		ArrayList<UsuarioPrecoDTO> lista = new ArrayList< UsuarioPrecoDTO >();
		lista = (ArrayList< UsuarioPrecoDTO >) YesChamixFacade.getInstance().consultarUsuarioPrecoPorLogin(login);
		return lista;
	}
}
