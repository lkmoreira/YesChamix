package br.com.gv8.yeschamix.imagem.controller;


import java.util.List;

import br.com.gv8.yeschamix.imagem.model.business.ImagemBOService;
import br.com.gv8.yeschamix.imagem.model.business.ImagemBOServiceImpl;

public final class ImagemService{
	
	private ImagemBOService service;

	/*SINGLETON*/
	private static ImagemService instance;
	
	private ImagemService(){
		service = new ImagemBOServiceImpl();
	}
	
	public static ImagemService getInstance() {
		if( instance == null ){
			instance = new ImagemService();
		}
	    return instance;
    }
	/*SINGLETON*/
	
	
	/**
	 * 
	 * M�todo respons�vel por fazer o download de todas as imagens de Produto
	 * atrav�s do nome passado por par�metro.
	 *
	 * @param nomeImagem
	 * @throws Exception
	 * @return lista - nomes das imagens baixadas
	 * @author Bruno Henrique Dannas Pelogia <bruno.pelogia@gmail.com>
	 * @since 27/06/2013 01:30:55
	 * @version 1.0
	 */
	public List< String > downloadImagemProduto(String nomeImagem) throws Exception {
		 return service.downloadImagemProduto( nomeImagem);
	}
	
	/**
	 * 
	 * M�todo respons�vel por fazer o download de todas as imagens de Folder.
	 *
	 * @throws Exception
	 * @return lista - nomes das imagens baixadas
	 * @author Bruno Henrique Dannas Pelogia <bruno.pelogia@gmail.com>
	 * @since 27/06/2013 01:30:55
	 * @version 1.0
	 */
	public List< String > downloadImagemFolder() throws Exception{
		return service.downloadImagemFolder();
	}
	
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
	public void downloadImagemFamilia(String nomeImagem) throws Exception{
		service.downloadImagemFamilia( nomeImagem );
	}
}
