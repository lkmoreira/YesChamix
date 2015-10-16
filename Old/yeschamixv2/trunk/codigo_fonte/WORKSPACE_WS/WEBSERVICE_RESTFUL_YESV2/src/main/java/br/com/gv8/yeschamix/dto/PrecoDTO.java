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
public class PrecoDTO {
	
	private String id;
	private ProdutoDTO produto;
	private Double preco;
	
	public PrecoDTO(){}
	
	public PrecoDTO(String id, ProdutoDTO produto, Double preco){
		this.id = id;
		this.produto = produto;
		this.preco = preco;
	}
	
	public PrecoDTO(String id){
		this.id = id;
	}
	
	
	
	public String getId() {
		return id;
	}




	public void setId( String id ) {
		this.id = id;
	}




	public ProdutoDTO getProduto() {
		return produto;
	}




	public void setProduto( ProdutoDTO produto ) {
		this.produto = produto;
	}




	public Double getPreco() {
		return preco;
	}




	public void setPreco( Double preco ) {
		this.preco = preco;
	}




	@Override
	public String toString() {
		
		return id + " " + produto.getId() + " " + preco;
	}
}
