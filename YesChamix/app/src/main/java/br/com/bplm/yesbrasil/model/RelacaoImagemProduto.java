/**
 * @author Bruno Henrique Dannas Pelogia <bruno.pelogia@gmail.com>
 * @since 20/05/2013 20:15:48
 * @version 1.0 
 */
package br.com.bplm.yesbrasil.model;

/**
 * @author Bruno Pelogia
 * @since 23/05/2013 - 14:00
 * 
 */
public class RelacaoImagemProduto {
	
	private ImagemProduto imagem;
	private Produto produto;
	
	public RelacaoImagemProduto(ImagemProduto imagem, Produto produto){
		this.imagem = imagem;
		this.produto = produto;
	}
	public ImagemProduto getImagem() {
		return imagem;
	}
	public void setImagem( ImagemProduto imagem ) {
		this.imagem = imagem;
	}
	public Produto getProduto() {
		return produto;
	}
	public void setProduto( Produto produto ) {
		this.produto = produto;
	}
	
	
	
	
	
	
	
	
	
	
}
