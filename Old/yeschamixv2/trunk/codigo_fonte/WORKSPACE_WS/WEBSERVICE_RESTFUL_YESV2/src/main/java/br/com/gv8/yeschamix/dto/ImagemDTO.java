/**
 * 
 */
package br.com.gv8.yeschamix.dto;

import java.io.File;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author Bruno Pelogia
 *
 */

@SuppressWarnings( "restriction" )
@XmlRootElement
public class ImagemDTO {
	
	private byte[] imagem;
	
	public ImagemDTO(byte[] imagem){
		
		
		this.imagem = imagem;
		
	}
	
	public ImagemDTO(){}

	public byte[] getImagem() {
		return imagem;
	}

	public void setImagem( byte[] imagem ) {
		this.imagem = imagem;
	}	
}
