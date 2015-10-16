package br.com.gv8.yeschamix.imagem.model.business;

import java.util.List;


public interface ImagemBOService{
	
	/**
	 * 
	 * M�todo respons�vel por fazer o download de todas as imagens de Produto
	 * atrav�s do nome passado por par�metro.
	 *
	 * @param nomeImagem
	 * @throws Exception
	 *
	 * @author Bruno Henrique Dannas Pelogia <bruno.pelogia@gmail.com>
	 * @since 27/06/2013 01:30:55
	 * @version 1.0
	 */
	public List< String > downloadImagemProduto(String nomeImagem) throws Exception;
	
	/**
	 * 
	 * M�todo respons�vel por fazer o download de todas as imagens de Folder.
	 *
	 * @throws Exception
	 *
	 * @author Bruno Henrique Dannas Pelogia <bruno.pelogia@gmail.com>
	 * @since 27/06/2013 01:30:55
	 * @version 1.0
	 */
	public List< String > downloadImagemFolder() throws Exception;
	
	/**
	 * 
	 * M�todo respons�vel por fazer o download de todas as imagens de Familia
	 * atrav�s do nome passado por par�metro.
	 *
	 * @param nomeImagem
	 * @throws Exception
	 *
	 * @author Bruno Henrique Dannas Pelogia <bruno.pelogia@gmail.com>
	 * @since 27/06/2013 01:30:55
	 * @version 1.0
	 */
	public void downloadImagemFamilia(String nomeImagem) throws Exception;
}
