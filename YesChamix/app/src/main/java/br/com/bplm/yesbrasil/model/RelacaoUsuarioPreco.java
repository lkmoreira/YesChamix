/**
 * @author Bruno Henrique Dannas Pelogia <bruno.pelogia@gmail.com>
 * @since 20/05/2013 20:15:48
 * @version 1.0 
 */
package br.com.bplm.yesbrasil.model;

/**
 * @author Bruno Pelogia
 * @since 20/05/2013 - 20:15:48
 * * @since 21/05/2013 - 17:20
 */
public class RelacaoUsuarioPreco {
	
	private Usuario usuario;
	private Preco preco;
	
	public RelacaoUsuarioPreco(){}
	
	public RelacaoUsuarioPreco(Usuario usuario, Preco preco){
		this.usuario = usuario;
		this.preco = preco;
	}
	
	public Usuario getUsuario() {
		return usuario;
	}
	public void setUsuario( Usuario usuario ) {
		this.usuario = usuario;
	}
	public Preco getPreco() {
		return preco;
	}
	public void setPreco( Preco preco ) {
		this.preco = preco;
	}

	
	
	
	
	
	
}
