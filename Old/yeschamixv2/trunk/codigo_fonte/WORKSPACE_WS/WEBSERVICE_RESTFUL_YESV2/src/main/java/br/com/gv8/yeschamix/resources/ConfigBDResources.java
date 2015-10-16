/**
 * 
 */
package br.com.gv8.yeschamix.resources;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

import br.com.gv8.yeschamix.dto.ConfigDTO;
import br.com.gv8.yeschamix.util.Utilidades;

import com.google.gson.Gson;

/**
 * Classe que representa
 *
 *
 * @author Bruno Pelogia <bruno.pelogia@gmail.com>
 * @since 07/08/2013 15:23:47
 * @version 1.0
 */
@Path( "/configuracao" )
public class ConfigBDResources{

	@GET
    @Path("/{dados}")
    public String postConfiguracao(@PathParam("dados") String dados) {
		Gson gson = new Gson();
		String status = "";
		try{
			dados = dados.replace( " ", "/" );
			ConfigDTO configuracao = gson.fromJson( dados, ConfigDTO.class );
			Utilidades.criarPropertiesBD( configuracao.getLogin(), 
											configuracao.getUser(), 
											configuracao.getPassword(), 
											configuracao.getDriver(), 
											configuracao.getUrl());
		}catch(Exception e){
			e.printStackTrace();
			 status = "ERR";
			 return status;
		}
		status = "OK";
		return gson.toJson(status);
    }	
	
}
