/**
 * @author Bruno Henrique Dannas Pelogia <bruno.pelogia@gmail.com>
 * @since 20/05/2013 20:13:37
 * @version 1.0 
 */
package br.com.gv8.yeschamix.preco.model.persistence;

import br.com.gv8.yeschamix.produto.model.persistence.ProdutoVO;

/**
 * @author Bruno Pelogia
 * @since 20/05/2013 - 20:13:37
 * * @since 21/05/2013 - 17:25
 */
public class PrecoVO {
	
	private Integer codigo;
	private String id;
	private ProdutoVO produto;
	private Double preco;
	
	public PrecoVO(){}
	
	public PrecoVO(Integer codigo, String id, ProdutoVO produto, Double preco){
		setCodigo( codigo );
		setId( id );
		setProduto( produto );
		setPreco( preco );
	}
	
	public PrecoVO(String id){
		this.id = id;
	}
	
	public String getId() {
		return id;
	}
	public void setId( String id ) {
		this.id = id;
	}
	public ProdutoVO getProduto() {
		return produto;
	}
	public void setProduto( ProdutoVO produto ) {
		this.produto = produto;
	}
	public Double getPreco() {
		return preco;
	}
	public void setPreco( Double preco ) {
		this.preco = preco;
	}

	public final Integer getCodigo() {
		return codigo;
	}

	public final void setCodigo( Integer codigo ) {
		this.codigo = codigo;
	}

	@Override
	public String toString() {
		return "PrecoVO [getId()=" + getId() + ", getProduto()=" + getProduto() + ", getPreco()=" + getPreco() + ", getCodigo()=" + getCodigo() + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ( ( codigo == null ) ? 0 : codigo.hashCode() );
		result = prime * result + ( ( id == null ) ? 0 : id.hashCode() );
		result = prime * result + ( ( preco == null ) ? 0 : preco.hashCode() );
		result = prime * result + ( ( produto == null ) ? 0 : produto.hashCode() );
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
		PrecoVO other = (PrecoVO) obj;
		if ( codigo == null ) {
			if ( other.codigo != null )
				return false;
		} else if ( !codigo.equals( other.codigo ) )
			return false;
		if ( id == null ) {
			if ( other.id != null )
				return false;
		} else if ( !id.equals( other.id ) )
			return false;
		if ( preco == null ) {
			if ( other.preco != null )
				return false;
		} else if ( !preco.equals( other.preco ) )
			return false;
		if ( produto == null ) {
			if ( other.produto != null )
				return false;
		} else if ( !produto.equals( other.produto ) )
			return false;
		return true;
	}
	
	
	
	
	
	
}
