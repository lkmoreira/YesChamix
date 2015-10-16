package br.com.gv8.yeschamix.imagem.model.clienteWS;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

import br.com.gv8.yeschamix.imagem.model.dto.ImagemDTO;
import br.com.gv8.yeschamix.util.YesChamixUtils;
import br.com.gv8.yeschamix.wsconfig.WebService;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonParser;

/**
 * Classe responsável por trazer dados referente a imagem, através de uma consulta ao WebService
 * 
 * @author Bruno Pelogia
 * @since 27/06/2013 - 00:24:03
 */
public final class ImagemREST {
	
	String fileName;
	
	/**
	 * 
	 * Método responsável por fazer o download da imagem de Produto e salvar em uma pasta pré-configurada
	 * no WebService.
	 * 
	 * @param nomeImagem - nome da imagem pesquisada e nome do arquivo a ser salvo.
	 * @throws IOException
	 * 
	 * @author Bruno Henrique Dannas Pelogia <bruno.pelogia@gmail.com>
	 * @since 27/06/2013 00:33:02
	 * @version 1.0
	 * @throws Exception
	 */
	public void downloadImagemProduto( String imagemURL , String nomeArquivo ) throws Exception {

		imagemURL = "imagens/" + imagemURL;
		new WebService().downloadFromUrl2( imagemURL , nomeArquivo );
		
	}

	/**
	 * 
	 * Método responsável por fazer o download da imagem de Folder e salvar em uma pasta pré-configurada
	 * no WebService.
	 * 
	 * @param imagemURL - nome da imagem no servidor
	 * @param nomeArquivo - nome do arquivo que será salvo
	 * @throws IOException
	 * 
	 * @author Bruno Henrique Dannas Pelogia <bruno.pelogia@gmail.com>
	 * @since 27/06/2013 00:33:02
	 * @version 1.0
	 */
	public void downloadImagemFolder( String imagemURL , String nomeArquivo ) throws IOException {

		imagemURL = "imagens/folder/" + imagemURL;
		new WebService().DownloadFromUrl( imagemURL , nomeArquivo );

	}

	/**
	 * 
	 * Método responsável por fazer o download da imagem de Familia e salvar em uma pasta do celular pré-configurada
	 * no WebService.
	 * 
	 * 
	 * @param nomeImagem - nome da imagem pesquisada e nome do arquivo a ser salvo.
	 * @throws IOException
	 * 
	 * @author Bruno Henrique Dannas Pelogia <bruno.pelogia@gmail.com>
	 * @since 27/06/2013 00:33:02
	 * @version 1.0
	 */
	public void downloadImagemFamilia( String nomeImagem ) throws Exception {

		String[] resposta = new WebService().get( "imagens/familia/" + nomeImagem );

		if ( resposta[0].equals( "200" ) ) {
			Gson gson = new Gson();

			ImagemDTO imagem = gson.fromJson( resposta[1] , ImagemDTO.class );

			byte[] arrayImagem = imagem.getImagem();

			if ( arrayImagem != null ) {

				FileOutputStream outPut = new FileOutputStream( YesChamixUtils.getPastaDestinoFoto() + File.separator + nomeImagem );
				outPut.write( arrayImagem );
				outPut.close();
			}
		} else {
			throw new Exception( resposta[1] );
		}
	}

	/**
	 * 
	 * Método responsável por trazer todos os nomes de imagem de Folders (que está no servidor ).
	 * 
	 * 
	 * @return lista - lista de nomes de imagem de Folders.
	 * @throws Exception
	 * 
	 * @author Bruno Henrique Dannas Pelogia <bruno.pelogia@gmail.com>
	 * @since 27/06/2013 00:24:52
	 * @version 1.0
	 */
	public ArrayList< String > consultarNomesImagensFolder() throws Exception {

		String[] resposta = new WebService().get( "imagens/folders" );

		if ( resposta[0].equals( "200" ) ) {
			Gson gson = new Gson();
			JsonParser parser = new JsonParser();

			JsonArray array = parser.parse( resposta[1] ).getAsJsonArray();
			ArrayList< String > nomeImagens = new ArrayList< String >();

			for( int i = 0; i < array.size(); i++ ) {
				nomeImagens.add( gson.fromJson( array.get( i ) , String.class ) );
			}

			return nomeImagens;
		} else {
			throw new Exception( resposta[1] );
		}
	}

	/**
	 * 
	 * Método responsável por trazer todos os nomes de imagem de Produto (que está no servidor ) que inicia com o nome da
	 * imagem enviada por parametro.
	 * 
	 * @param nomeImagem - nome da imagem
	 * @return lista - lista de nomes de imagem que iniciam com o 'nomeImagem'.
	 * @throws Exception
	 * 
	 * @author Bruno Henrique Dannas Pelogia <bruno.pelogia@gmail.com>
	 * @since 27/06/2013 00:24:52
	 * @version 1.0
	 */
	public ArrayList< String > consultarNomesImagemProduto( String nomeImagem ) throws Exception {

		String[] resposta = new WebService().get( "imagens/produto/" + nomeImagem );

		if ( resposta[0].equals( "200" ) ) {
			Gson gson = new Gson();
			JsonParser parser = new JsonParser();

			JsonArray array = parser.parse( resposta[1] ).getAsJsonArray();

			ArrayList< String > lista = new ArrayList< String >();

			for( int i = 0; i < array.size(); i++ ) {
				lista.add( gson.fromJson( array.get( i ) , String.class ) );
			}

			return lista;
		} else {
			throw new Exception( resposta[1] );
		}
	}


}
