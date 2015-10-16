/**
 * @author Bruno Henrique Dannas Pelogia <bruno.pelogia@gmail.com>
 * @since 20/05/2013 20:13:37
 * @version 1.0 
 */
package br.com.bplm.yesbrasil.model;

/**
 * @author Bruno Pelogia
 * @since 20/05/2013 - 20:13:37
 * * @since 21/05/2013 - 17:25
 */
public class Preco {
	
	private Integer codigo;
	private String id;
	private Produto produto;
	private Double preco;
	
	public Preco(){}
	
	public Preco(Integer codigo, String id, Produto produto, Double preco){
		setCodigo( codigo );
		setId( id );
		setProduto( produto );
		setPreco( preco );
	}
	
	public Preco(String id){
		this.id = id;
	}
	
	public String getId() {
		return id;
	}
	public void setId( String id ) {
		this.id = id;
	}
	public Produto getProduto() {
		return produto;
	}
	public void setProduto( Produto produto ) {
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

}
