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

import br.com.gv8.yeschamix.dto.ClassificacaoDTO;
import br.com.gv8.yeschamix.facade.YesChamixFacade;

/**
 * @author Bruno Pelogia
 *
 */
@Path("/classificacao")
public class ClassificacaoResources {
	


	@GET
	@Path("/consultarTodos/{login}")
	@Produces({"application/json; charset=UTF-8"})
	public List<ClassificacaoDTO> getClassificacoes(@PathParam("login") String login) {
		ArrayList<ClassificacaoDTO> listaClassificacao = new ArrayList< ClassificacaoDTO >();
		listaClassificacao = (ArrayList< ClassificacaoDTO >) YesChamixFacade.getInstance().consultarTudoClassificacao(login);
		
		return listaClassificacao;
	}

	@Path("{id}/{login}")
	@GET
	@Produces({"application/json; charset=UTF-8"})
	public ArrayList<ClassificacaoDTO> getClassificacao(@PathParam("id") String id, @PathParam("login") String login) {
		return (ArrayList<ClassificacaoDTO>) YesChamixFacade.getInstance().consultarTudoClassificacaoPorId( id, login );
	}

	
	
}
