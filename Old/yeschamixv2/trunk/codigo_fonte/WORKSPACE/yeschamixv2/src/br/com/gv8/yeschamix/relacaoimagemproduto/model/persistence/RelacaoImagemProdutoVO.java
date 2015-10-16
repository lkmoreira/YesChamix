/**
 * @author Bruno Henrique Dannas Pelogia <bruno.pelogia@gmail.com>
 * @since 20/05/2013 20:15:48
 * @version 1.0 
 */
package br.com.gv8.yeschamix.relacaoimagemproduto.model.persistence;

import br.com.gv8.yeschamix.imagem.model.persistence.ImagemProdutoVO;
import br.com.gv8.yeschamix.produto.model.persistence.ProdutoVO;

/**
 * @author Bruno Pelogia
 * @since 23/05/2013 - 14:00
 * 
 */
public class RelacaoImagemProdutoVO {
	
	private ImagemProdutoVO imagem;
	private ProdutoVO produto;
	
	public RelacaoImagemProdutoVO(ImagemProdutoVO imagem, ProdutoVO produto){
		this.imagem = imagem;
		this.produto = produto;
	}
	public ImagemProdutoVO getImagem() {
		return imagem;
	}
	public void setImagem( ImagemProdutoVO imagem ) {
		this.imagem = imagem;
	}
	public ProdutoVO getProduto() {
		return produto;
	}
	public void setProduto( ProdutoVO produto ) {
		this.produto = produto;
	}
	
	
	
	
	
	
	
	
	
	
}
