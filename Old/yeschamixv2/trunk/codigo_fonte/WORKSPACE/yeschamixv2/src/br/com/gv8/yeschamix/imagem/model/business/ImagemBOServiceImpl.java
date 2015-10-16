package br.com.gv8.yeschamix.imagem.model.business;

import java.io.File;
import java.io.FileFilter;
import java.util.ArrayList;
import java.util.List;

import android.util.Log;
import br.com.gv8.yeschamix.imagem.model.clienteWS.ImagemREST;
import br.com.gv8.yeschamix.util.Utilidades;
import br.com.gv8.yeschamix.util.YesChamixUtils;

public final class ImagemBOServiceImpl implements ImagemBOService {

	private ImagemREST imagemREST;
	private int qtd = 0;
	private String nomeArquivoImagem;

	public ImagemBOServiceImpl() {
		if ( imagemREST == null ) {
			imagemREST = new ImagemREST();
		}
	}

	@Override
	public List< String > downloadImagemProduto( String nomeImagem ) throws Exception {
		try {
			if ( imagemREST == null ) {
				imagemREST = new ImagemREST();
			}
			nomeImagem = YesChamixUtils.removerEspacosString( nomeImagem );
			ArrayList< String > listaDeNomes = imagemREST.consultarNomesImagemProduto( nomeImagem );
			for( String nomeImg : listaDeNomes ) {
				if ( Utilidades.isNullOrBlank( imagemREST ) ) {
					imagemREST = new ImagemREST();
				}
				long startTime = System.currentTimeMillis();
				qtd++;
				nomeImg = YesChamixUtils.removerEspacosString( nomeImg );

				nomeArquivoImagem = nomeImg;
				File diretorioCartao = new File( YesChamixUtils.getPastaDestinoFoto() + File.separator );
				File[] files = diretorioCartao.listFiles( new FileFilter() {
					public boolean accept( File pathname ) {
						return pathname.getName().startsWith( nomeArquivoImagem );
					}
				} );

				if ( files.length <= 0 ) {
					imagemREST.downloadImagemProduto( nomeImg , nomeImg );
				}
				Log.d( "ImgProduto" , "download em " + ( ( System.currentTimeMillis() - startTime ) / 1000 ) + " sec- Qtd.: " + qtd );
			}
			return listaDeNomes;
		} catch( Exception e ) {
			e.printStackTrace();
			throw new Exception( "Ocorreu um erro ao acessar o Webservice." );
		} finally {
			imagemREST = null;
		}
	}

	@Override
	public List< String > downloadImagemFolder() throws Exception {
		try {
			if ( imagemREST == null ) {
				imagemREST = new ImagemREST();
			}
			ArrayList< String > listaDeNomes = imagemREST.consultarNomesImagensFolder();
			for( String nomeImg : listaDeNomes ) {
				if ( imagemREST == null ) {
					imagemREST = new ImagemREST();
				}
				nomeImg = YesChamixUtils.removerEspacosString( nomeImg );
				imagemREST.downloadImagemFolder( nomeImg , nomeImg );
			}

			return listaDeNomes;

		} catch( Exception e ) {
			e.printStackTrace();
			throw new Exception( "Ocorreu um erro ao acessar o Webservice." );
		} finally {
			imagemREST = null;
		}

	}

	@Override
	public void downloadImagemFamilia( String nomeImagem ) throws Exception {
		try {
			if ( imagemREST == null ) {
				imagemREST = new ImagemREST();
			}
			nomeImagem = YesChamixUtils.removerEspacosString( nomeImagem );
			imagemREST.downloadImagemFamilia( nomeImagem );

		} catch( Exception e ) {
			e.printStackTrace();
			throw new Exception( "Ocorreu um erro ao acessar o Webservice." );
		} finally {
			imagemREST = null;
		}

	}

}
