/**
 * @author Bruno Henrique Dannas Pelogia <bruno.pelogia@gmail.com>
 * @since 20/05/2013 20:11:14
 * @version 1.0 
 */
package br.com.bplm.yesbrasil.model;

/**
 * @author Bruno Pelogia
 * @since 20/05/2013 - 20:11:14
 * * @since 21/05/2013 - 17:22
 */
public class Usuario {
	
	private Integer id;
	private String nome;
	private String login;
	private String senha;
	private Integer bloqueado;
	
	public Usuario(){}
	
	public Usuario(Integer id, String nome, String login, String senha, Integer bloqueado){
		setId( id );
		setNome( nome );
		setLogin( login );
		setSenha( senha );
		setBloqueado( bloqueado );
	}
	
	public Usuario(Integer id){
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
	
	public void setBloqueado(Integer bloqueado){
		this.bloqueado = bloqueado;
	}
	
	public Integer getBloqueado(){
		return bloqueado;
	}
	
}
