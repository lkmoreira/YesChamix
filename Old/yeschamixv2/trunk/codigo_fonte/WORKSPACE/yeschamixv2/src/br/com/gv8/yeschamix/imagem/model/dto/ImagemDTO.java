/**
 * @author Bruno Henrique Dannas Pelogia <bruno.pelogia@gmail.com>
 * @since 06/06/2013 16:36:20
 * @version 1.0 
 */
package br.com.gv8.yeschamix.imagem.model.dto;

/**
 * @author Bruno Pelogia
 * @since 06/06/2013 - 16:36:20
 */
public class ImagemDTO {

	private byte[] imagem;

	public ImagemDTO() {
	}

	public ImagemDTO( byte[] imagem ) {
		this.imagem = imagem;
	}

	public byte[] getImagem() {
		return imagem;
	}

	public void setImagem( byte[] imagem ) {
		this.imagem = imagem;
	}

}
