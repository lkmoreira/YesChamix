/**
 * @author Bruno Henrique Dannas Pelogia <bruno.pelogia@gmail.com>
 * @since 20/05/2013 20:11:14
 * @version 1.0 
 */
package br.com.gv8.yeschamix.usuario.model.persistence;

/**
 * @author Bruno Pelogia
 * @since 20/05/2013 - 20:11:14
 * * @since 21/05/2013 - 17:22
 */
public class UsuarioVO {
	
	private Integer id;
	private String nome;
	private String login;
	private String senha;
	private Integer bloqueado;
	
	public UsuarioVO (){}
	
	public UsuarioVO(Integer id, String nome, String login, String senha, Integer bloqueado){
		setId( id );
		setNome( nome );
		setLogin( login );
		setSenha( senha );
		setBloqueado( bloqueado );
	}
	
	public UsuarioVO(Integer id){
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

	@Override
	public String toString() {
		return "UsuarioVO [getId()=" + getId() + ", getNome()=" + getNome() + ", getLogin()=" + getLogin() + ", getSenha()=" + getSenha() + ", getBloqueado()=" + getBloqueado() + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ( ( bloqueado == null ) ? 0 : bloqueado.hashCode() );
		result = prime * result + ( ( id == null ) ? 0 : id.hashCode() );
		result = prime * result + ( ( login == null ) ? 0 : login.hashCode() );
		result = prime * result + ( ( nome == null ) ? 0 : nome.hashCode() );
		result = prime * result + ( ( senha == null ) ? 0 : senha.hashCode() );
		return result;
	}

	@Override
	public boolean equals( Object obj ) {
		if ( this == obj )
			return true;
		if ( obj == null )
			return false;
		if ( getClass() != obj.getClass() )
			return false;
		UsuarioVO other = (UsuarioVO) obj;
		if ( bloqueado == null ) {
			if ( other.bloqueado != null )
				return false;
		} else if ( !bloqueado.equals( other.bloqueado ) )
			return false;
		if ( id == null ) {
			if ( other.id != null )
				return false;
		} else if ( !id.equals( other.id ) )
			return false;
		if ( login == null ) {
			if ( other.login != null )
				return false;
		} else if ( !login.equals( other.login ) )
			return false;
		if ( nome == null ) {
			if ( other.nome != null )
				return false;
		} else if ( !nome.equals( other.nome ) )
			return false;
		if ( senha == null ) {
			if ( other.senha != null )
				return false;
		} else if ( !senha.equals( other.senha ) )
			return false;
		return true;
	}
	
	
	
	
	
}
