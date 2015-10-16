/**
 * @author Bruno Henrique Dannas Pelogia <bruno.pelogia@gmail.com>
 * @since 20/05/2013 20:15:48
 * @version 1.0 
 */
package br.com.gv8.yeschamix.relacaousuariopreco.model.persistence;

import br.com.gv8.yeschamix.preco.model.persistence.PrecoVO;
import br.com.gv8.yeschamix.usuario.model.persistence.UsuarioVO;

/**
 * @author Bruno Pelogia
 * @since 20/05/2013 - 20:15:48
 * * @since 21/05/2013 - 17:20
 */
public class RelacaoUsuarioPrecoVO {
	
	private UsuarioVO usuario;
	private PrecoVO preco;
	
	public RelacaoUsuarioPrecoVO(){}
	
	public RelacaoUsuarioPrecoVO( UsuarioVO usuario, PrecoVO preco){
		this.usuario = usuario;
		this.preco = preco;
	}
	
	public UsuarioVO getUsuario() {
		return usuario;
	}
	public void setUsuario( UsuarioVO usuario ) {
		this.usuario = usuario;
	}
	public PrecoVO getPreco() {
		return preco;
	}
	public void setPreco( PrecoVO preco ) {
		this.preco = preco;
	}

	
	
	
	
	
	
}
