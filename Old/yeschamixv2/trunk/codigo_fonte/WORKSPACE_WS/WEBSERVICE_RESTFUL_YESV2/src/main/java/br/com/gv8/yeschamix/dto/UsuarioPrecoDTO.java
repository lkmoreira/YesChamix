/**
 * 
 */
package br.com.gv8.yeschamix.dto;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author Bruno Pelogia
 *
 */

@SuppressWarnings( "restriction" )
@XmlRootElement
public class UsuarioPrecoDTO {

	private UsuarioDTO usuario;
	private PrecoDTO preco;
	
	public UsuarioPrecoDTO(){}
	
	public UsuarioPrecoDTO(UsuarioDTO usuario, PrecoDTO preco){
		this.usuario = usuario;
		this.preco = preco;
	}
	
	
	
	public UsuarioDTO getUsuario() {
		return usuario;
	}



	public void setUsuario( UsuarioDTO usuario ) {
		this.usuario = usuario;
	}



	public PrecoDTO getPreco() {
		return preco;
	}



	public void setPreco( PrecoDTO preco ) {
		this.preco = preco;
	}



	@Override
	public String toString() {
		
		return usuario.getId() + " " + preco.getId();
	}
}
