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

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

import br.com.gv8.yeschamix.dto.UsuarioDTO;
import br.com.gv8.yeschamix.facade.YesChamixFacade;
import br.com.gv8.yeschamix.util.Utilidades;

/**
 * @author Bruno Pelogia
 *
 */
@Path("/usuario")
public class UsuarioResources {

	@GET
	@Path("/consultarTodos/{login}")
	@Produces({"application/json; charset=UTF-8"})
	public List<UsuarioDTO> getUsuarios(@PathParam("login") String login) {
		ArrayList<UsuarioDTO> lista = new ArrayList< UsuarioDTO >();
		lista = (ArrayList< UsuarioDTO >) YesChamixFacade.getInstance().consultarTudoUsuario(login);
		return lista;
	}

	@Path("/{id}/{login}")
	@GET
	@Produces({"application/json; charset=UTF-8"})
	public UsuarioDTO getUsuario(@PathParam("id") Integer id, @PathParam("login") String login) {
		return (UsuarioDTO) YesChamixFacade.getInstance().consultarTudoUsuarioPorId( id, login );
	}
	
	@Path("/login/{dadosLogin}")
	@GET
	@Produces({"application/json; charset=UTF-8"})
	public String getLoginSenha(@PathParam("dadosLogin") String dadosLogin) {
		Gson gson = new Gson();
		
		UsuarioDTO usuario;
		try{	
			usuario = gson.fromJson(dadosLogin, UsuarioDTO.class);
		}catch(JsonSyntaxException e){
			usuario = new UsuarioDTO();
		}
		String status= "";
		if(!Utilidades.isNullOrBlank(usuario)&&!Utilidades.isNullOrBlank(usuario.getLogin())&&!Utilidades.isNullOrBlank(usuario.getSenha())){
			usuario = YesChamixFacade.getInstance().consultarTudoUsuarioPorLoginSenha(usuario.getLogin(), usuario.getSenha());

			if(Utilidades.isNullOrBlank(usuario.getId())){
				status = "ERR";
			}else if(new Integer(1).equals(usuario.getBloqueado())){
				status = "BLOQUEADO";
			}else{
				status= "OK";
			}
			
		}
		
		return gson.toJson(status) ;
	}
}
