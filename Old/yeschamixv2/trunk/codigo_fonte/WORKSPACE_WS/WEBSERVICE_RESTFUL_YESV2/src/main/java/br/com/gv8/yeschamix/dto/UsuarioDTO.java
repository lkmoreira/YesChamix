/**
 * 
 */
package br.com.gv8.yeschamix.dto;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author Bruno Pelogia
 *
 */

@XmlRootElement
public class UsuarioDTO {
	
	private Integer id;
	private String nome;
	private String login;
	private String senha;
	private Integer bloqueado;
	
	public UsuarioDTO(){}
	
	public UsuarioDTO(Integer id, String nome, String login, String senha, Integer bloqueado){
		this.id = id;
		this.nome = nome;
		this.login = login;
		this.senha = senha;
		this.bloqueado = bloqueado;
	}
	
	public UsuarioDTO(Integer id){
		this.id = id;
	}
	
	
	public Integer getId() {
		return id;
	}



	public void setId( Integer id ) {
		this.id = id;
	}



	public String getNome() {
		return nome;
	}



	public void setNome( String nome ) {
		this.nome = nome;
	}



	public String getLogin() {
		return login;
	}



	public void setLogin( String login ) {
		this.login = login;
	}



	public String getSenha() {
		return senha;
	}



	public void setSenha( String senha ) {
		this.senha = senha;
	}



	public Integer getBloqueado() {
		return bloqueado;
	}

	public void setBloqueado( Integer bloqueado ) {
		this.bloqueado = bloqueado;
	}

	@Override
	public String toString() {
		
		return id + " " + nome+ " " + login+ " " + senha + " "+bloqueado;
	}
}
