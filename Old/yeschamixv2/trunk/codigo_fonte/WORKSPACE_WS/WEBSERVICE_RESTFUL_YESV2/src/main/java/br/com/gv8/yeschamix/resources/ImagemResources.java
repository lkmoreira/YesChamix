/**
 * 
 */
package br.com.gv8.yeschamix.resources;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import br.com.gv8.yeschamix.dto.ImagemDTO;
import br.com.gv8.yeschamix.util.FileImageFilter;
import br.com.gv8.yeschamix.util.Utilidades;

import com.google.gson.Gson;

/**
 * @author Bruno Pelogia
 * 
 */
@Path( "/imagens" )
public class ImagemResources {
	
	/**
	 *  Responsável por retornar uma Imagem através do objeto InputStream 
	 * @param nomeImagem
	 * @return
	 */
	@GET
	@Path( "/{nomeImagem}" )
	@Produces( "image/jpg" )
	public Response getImgProduto( @PathParam( "nomeImagem" ) String nomeImagem ) {

		String caminho = ImagemResources.class.getResource( "/" ).toString();
		caminho = caminho.substring( 5 , caminho.indexOf( "webapps" ) + 8 );
		InputStream stream;

		try {
			stream = new FileInputStream( new File( caminho + "/ROOT/Tablet/Produtos/" + nomeImagem ) );
			return Response.ok().entity( stream ).build();

		} catch( FileNotFoundException e ) {
			e.printStackTrace();
			return Response.ok().entity( null ).build();

		}
	}
	
	/**
	 *  Responsável por retornar uma Imagem através do objeto InputStream 
	 * @param nomeImagem
	 * @return
	 */
	@GET
	@Path( "/folder/{nomeImagem}" )
	@Produces( "image/jpg" )
	public Response getFolder( @PathParam( "nomeImagem" ) String nomeImagem ) throws FileNotFoundException {

		String caminho = ImagemResources.class.getResource( "/" ).toString();
		caminho = caminho.substring( 5 , caminho.indexOf( "webapps" ) + 8 );
		InputStream stream;

		try {
			stream = new FileInputStream( new File( caminho + "/ROOT/Tablet/Folders/" + nomeImagem ) );
			return Response.ok().entity( stream ).build();

		} catch( FileNotFoundException e ) {
			e.printStackTrace();
			return Response.ok().entity( null ).build();

		}

	}
	
	/**
	 *  Responsável por ler as imagens da pasta no servidor e retornar todos os nomes em um objeto String. 
	 * @param nomeImagem
	 * @return
	 */
	@GET
	@Path( "/produto/{nomeImagem}" )
	@Produces( MediaType.APPLICATION_JSON )
	public String getImagensProduto( @PathParam( "nomeImagem" ) String nomeImagem ) {
		
		ArrayList< String > nomeImagens = new ArrayList< String >();
		ArrayList< String > nomeImagens2 = new ArrayList< String >();
		try {
			String caminho = ImagemResources.class.getResource( "/" ).toString();
			caminho = caminho.substring( 5 , caminho.indexOf( "webapps" ) + 8 );
			File[] arquivos = FileImageFilter.lerImagens( caminho + "/ROOT/Tablet/Produtos/", nomeImagem.replace( ".JPG" , "" ) );
		
			for( int i = 0; i < arquivos.length; i++ ) {

				nomeImagem = FileImageFilter.removerEspacosString( nomeImagem.toUpperCase() );
				String imagemEncontrada = FileImageFilter.removerEspacosString( arquivos[i].getName().toUpperCase() );
				try {
					String tamanhoNome = nomeImagem.substring( 0 , nomeImagem.indexOf( ".JPG" ) );
					//imagemEncontrada = ( imagemEncontrada.substring( 0 , imagemEncontrada.indexOf( tamanhoNome ) + ( tamanhoNome.length() ) ) ).concat( ".JPG" );
				} catch( StringIndexOutOfBoundsException e ) {
					imagemEncontrada = FileImageFilter.removerEspacosString( arquivos[i].getName().toUpperCase() );
				}
				
				String[ ] nomeImgSemExtensao = arquivos[i].getName().toUpperCase().split( "\\." );

				String[ ] nomeImgQuebra = nomeImgSemExtensao[ 0 ].split( "\\_" );
				String idProduto = nomeImgQuebra[ 0 ].trim();

				String corProduto = "";

				for ( String cor : nomeImgQuebra ) {
					if ( !idProduto.equals( cor ) && !"_".equals( cor ) ) {
						corProduto = cor.trim();
						break;
					}
				}

				if ( imagemEncontrada.replace( ".JPG" , "" ).startsWith( (nomeImagem.replace( ".JPG" , "" ) + corProduto) ) ) {
					if(Utilidades.isNullOrBlank( corProduto )){
						if(imagemEncontrada.replace( ".JPG" , "" ).equals( (nomeImagem.replace( ".JPG" , "" ) + corProduto) )){
							System.out.println( "Nome->" + nomeImagem );
							System.out.println( "Arquivo->" + imagemEncontrada );

							nomeImagens.add( arquivos[i].getName().trim() );
						}
					}else{
						System.out.println( "Nome->" + nomeImagem );
						System.out.println( "Arquivo->" + imagemEncontrada );
	
						nomeImagens.add( arquivos[i].getName().trim() );
					}
				}
				 
				/*if(imagemEncontrada.replace( ".JPG" , "" ).startsWith(nomeImagem.replace( ".JPG" , "" ) )){
					System.out.println( "Nome->" + nomeImagem );
					System.out.println( "Arquivo->" + imagemEncontrada );

					nomeImagens.add( arquivos[i].getName().trim() );
				}*/
			}
		} catch( Exception e ) {
			e.printStackTrace();
		}
		Gson gson = new Gson();
		return gson.toJson( nomeImagens );
	}

	/**
	 * 
	 * Método Original Comentado
	 * 
	 */

	//	@GET
	//	@Path( "/produto/{nomeImagem}" )
	//	@Produces( MediaType.APPLICATION_JSON )
	//	public String getImagensProduto( @PathParam( "nomeImagem" ) String nomeImagem ) {
	//
	//		ImagemDTO imagem = new ImagemDTO();
	//		ArrayList< ImagemDTO > imagens = new ArrayList< ImagemDTO >();
	//		String caminho = ImagemResources.class.getResource( "/" ).toString();
	//		caminho = caminho.substring( 5 , caminho.indexOf( "webapps" ) + 8 );
	//		File[] arquivos = FileImageFilter.lerImagens( caminho + "/ROOT/Tablet/Produtos/" );
	//		try {
	//			for( int i = 0; i < arquivos.length; i++ ) {
	//
	//				nomeImagem = FileImageFilter.removerEspacosString( nomeImagem.toUpperCase() );
	//				String imagemEncontrada = FileImageFilter.removerEspacosString( arquivos[i].getName().toUpperCase() );
	//				try {
	//					String tamanhoNome = nomeImagem.substring( 0 , nomeImagem.indexOf( ".JPG" ) );
	//					imagemEncontrada = ( imagemEncontrada.substring( 0 , imagemEncontrada.indexOf( tamanhoNome ) + ( tamanhoNome.length() ) ) ).concat( ".JPG" );
	//				} catch( StringIndexOutOfBoundsException e ) {
	//					imagemEncontrada = FileImageFilter.removerEspacosString( arquivos[i].getName().toUpperCase() );
	//				}
	//
	//				//classe utilizada para redimencionar imagem que vem do webservice
	//				ImageResizerService imgRedimendionada = new ImageResizerService();
	//
	//				if ( nomeImagem.equals( imagemEncontrada ) ) {
	//					System.out.println( "Nome->" + nomeImagem );
	//					System.out.println( "Arquivo->" + imagemEncontrada );
	//					//					imagem = new ImagemDTO( FileImageFilter.getBytes( arquivos[i] ) );
	//					imagem = new ImagemDTO( imgRedimendionada.read( arquivos[i].getAbsolutePath() , 500 , 500 ) );
	//					imagens.add( imagem );
	//				}
	//			}
	//		} catch( Exception e ) {
	//			e.printStackTrace();
	//		}
	//		Gson gson = new Gson();
	//		return gson.toJson( imagens );
	//	}

	
	/**
	 *  Responsável por retornar uma Imagem em Array de Bytes. 
	 * @param nomeImagem
	 * @return
	 */
	@GET
	@Path( "/familia/{nomeImagem}" )
	@Produces( MediaType.APPLICATION_JSON )
	public String getImagensFamilia( @PathParam( "nomeImagem" ) String nomeImagem ) {

		ImagemDTO imagem = new ImagemDTO();
		String caminho = ImagemResources.class.getResource( "/" ).toString();
		caminho = caminho.substring( 5 , caminho.indexOf( "webapps" ) + 8 );
		File[] arquivos = FileImageFilter.lerImagens( caminho + "/ROOT/Tablet/Familias/" );
		try {
			for( int i = 0; i < arquivos.length; i++ ) {

				nomeImagem = FileImageFilter.removerEspacosString( nomeImagem.toUpperCase() );
				String imagemEncontrada = FileImageFilter.removerEspacosString( arquivos[i].getName().toUpperCase() );
				try {
					String tamanhoNome = nomeImagem.substring( 0 , nomeImagem.indexOf( ".JPG" ) );
					imagemEncontrada = ( imagemEncontrada.substring( 0 , imagemEncontrada.indexOf( tamanhoNome ) + ( tamanhoNome.length() ) ) ).concat( ".JPG" );
				} catch( StringIndexOutOfBoundsException e ) {
					imagemEncontrada = FileImageFilter.removerEspacosString( arquivos[i].getName().toUpperCase() );
				}

				if ( nomeImagem.equals( imagemEncontrada ) ) {
					System.out.println( "Nome->" + nomeImagem );
					System.out.println( "Arquivo->" + imagemEncontrada );
					imagem = new ImagemDTO( FileImageFilter.getBytes( arquivos[i] ) );

				}
			}
		} catch( Exception e ) {
			e.printStackTrace();
		}

		Gson gson = new Gson();
		return gson.toJson( imagem );
	}

	/**
	 *  Responsável por ler as imagens da pasta no servidor e retornar todos os nomes em um objeto String. 
	 * @param nomeImagem
	 * @return
	 */
	@GET
	@Path( "/folders" )
	@Produces( MediaType.APPLICATION_JSON )
	public String getImagensFolders() {

		ArrayList< String > nomeImagens = new ArrayList< String >();
		String caminho = ImagemResources.class.getResource( "/" ).toString();
		caminho = caminho.substring( 5 , caminho.indexOf( "webapps" ) + 8 );
		File[] arquivos = FileImageFilter.lerImagens( caminho + "/ROOT/Tablet/Folders/" );
		try {
			for( int i = 0; i < arquivos.length; i++ ) {

				String imagemEncontrada = FileImageFilter.removerEspacosString( arquivos[i].getName());
				System.out.println( "Arquivo->" + imagemEncontrada );
				nomeImagens.add( arquivos[i].getName() );

			}
		} catch( Exception e ) {
			e.printStackTrace();
		}

		Gson gson = new Gson();
		return gson.toJson( nomeImagens );
	}

}
