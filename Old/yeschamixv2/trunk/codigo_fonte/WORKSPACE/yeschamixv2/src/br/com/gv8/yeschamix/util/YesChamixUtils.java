package br.com.gv8.yeschamix.util;

import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.math.BigDecimal;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.graphics.Matrix;
import android.graphics.drawable.BitmapDrawable;
import android.media.ExifInterface;
import android.os.Environment;

public class YesChamixUtils{

	public static String getPastaDestinoFoto() {
		File dir = new File( Environment.getExternalStorageDirectory() + "/imgYesChamix/" );
		if ( !dir.exists() ) {
			dir.mkdirs();
		}
		return dir.getAbsolutePath();
	}

	public static String getPastaLog() {
		File dir = new File( Environment.getExternalStorageDirectory() + "/LOG_YesChamix/" );
		if ( !dir.exists() ) {
			dir.mkdirs();
		}
		return dir.getAbsolutePath();
	}

	/**
	 * 
	 * Método responsável por substituir espacos por um underscore e caso exista undercore no fim da string substituir por '-'(ífen)
	 * 
	 * @param string
	 * @return
	 * 
	 * @author Bruno Henrique Dannas Pelogia <bruno.pelogia@gmail.com>
	 * @since 11/06/2013 14:11:31
	 * @version 1.0
	 */
	public static String refatorarString( String string ) {
		String novaString = string.trim();

		if ( novaString.endsWith( "_%20.JPG" ) || novaString.endsWith( "__.JPG" ) ) {

			String fim = novaString.substring( novaString.length() - 6, novaString.length() );
			novaString = novaString.substring( 0, novaString.indexOf( fim ) );

			while ( fim.contains( "_" ) ) {
				fim = fim.replaceAll( "_", "-" );
			}

			novaString = novaString.concat( fim );
		}

		while ( novaString.contains( "%20%20" ) ) {
			novaString = novaString.replaceAll( "%20%20", "%20" );
		}

		novaString = novaString.replace( "%20", "_" );

		while ( novaString.contains( "__" ) ) {
			novaString = novaString.replaceAll( "__", "_" );
		}
		return novaString;
	}

	/**
	 * 
	 * Método responsável por substituir os espacos por '%20' na String.
	 * 
	 * @param string
	 * @return
	 * 
	 * @author Bruno Pelogia <bruno.pelogia@gmail.com>
	 * @since 07/06/2013 16:49:49
	 * @version 1.0
	 */
	public static String removerEspacosString( String string ) {
		String nome = string.trim();
		while ( nome.contains( " " ) ) {
			nome = nome.replaceAll( " ", "%20" );

		}

		return nome;
	}

	public static File[ ] lerImagens( String caminho ) {
		// lista imagens da raíz do projeto  
		File root = new File( caminho );
		File[ ] files = root.listFiles();
		return files;
	}

	public static Bitmap decodeFile( File f, int WIDTH, int HIGHT ) {
		try {
			//Decode image size
			BitmapFactory.Options o = new BitmapFactory.Options();
			o.inJustDecodeBounds = true;
			BitmapFactory.decodeStream( new FileInputStream( f ), null, o );

			//The new size we want to scale to
			final int REQUIRED_WIDTH = WIDTH;
			final int REQUIRED_HIGHT = HIGHT;
			//Find the correct scale value. It should be the power of 2.
			int scale = 1;
			while ( o.outWidth / scale / 2 >= REQUIRED_WIDTH && o.outHeight / scale / 2 >= REQUIRED_HIGHT )
				scale *= 2;

			//Decode with inSampleSize
			BitmapFactory.Options o2 = new BitmapFactory.Options();
			o2.inSampleSize = scale;
			return BitmapFactory.decodeStream( new FileInputStream( f ), null, o2 );
		} catch ( FileNotFoundException e ) {
		}
		return null;
	}

	public static void ajustaFoto( File imagem, String localSalvar ) {
		Bitmap bitmap = null;
		int w = 0;
		int h = 0;
		Matrix mtx = new Matrix();

		// Ajusta orientação da imagem
		try {

			// joga a imagem em uma variável
			int inSample = 4;

			Options opts = new BitmapFactory.Options();
			opts.inSampleSize = inSample;

			bitmap = BitmapFactory.decodeFile( getPastaDestinoFoto() + "/" + imagem.getName(), opts );

			// captura as dimensões da imagem
			w = bitmap.getWidth();
			h = bitmap.getHeight();
			mtx = new Matrix();

			// pega o caminho onda a imagem está salva
			ExifInterface exif = new ExifInterface( getPastaDestinoFoto() + "/" + imagem.getName() );

			// pega a orientação real da imagem
			int orientation = exif.getAttributeInt( ExifInterface.TAG_ORIENTATION, ExifInterface.ORIENTATION_NORMAL );

			// gira a imagem de acordo com a orientação
			switch ( orientation ) {
				case 3: // ORIENTATION_ROTATE_180
					mtx.postRotate( 180 );
					break;
				case 6: //ORIENTATION_ROTATE_90
					mtx.postRotate( 90 );
					break;
				case 8: //ORIENTATION_ROTATE_270
					mtx.postRotate( 270 );
					break;
				default: //ORIENTATION_ROTATE_0
					mtx.postRotate( 0 );
					break;
			}

		} catch ( IOException e ) {
			// TODO Auto-generated catch block
			e.printStackTrace();

			try {
				StringWriter sw = new StringWriter();
				PrintWriter pw = new PrintWriter( sw );
				e.printStackTrace( pw );
				pw.close();
				sw.close();
				//				GerarTxtLog.gerarArquivoTexto(Utilidades.getDataHoraFormatada( new Date() ) + ":\t 7 EXCEPTION: " + sw.getBuffer().toString(), caminhoArquivoLog, nomeArquivoLog );
			} catch ( Exception ex ) {
			}
			e.printStackTrace();
		}

		// cria variável com a imagem rotacionada
		Bitmap rotatedBmp = Bitmap.createBitmap( bitmap, 0, 0, w, h, mtx, true );
		BitmapDrawable bmpd = new BitmapDrawable( rotatedBmp );

		// redimensiona a imagem 
		Integer lateral = 800; // tamanho final da dimensão maior da imagem
		try {
			// cria um stream pra salvar o arquivo
			FileOutputStream out = new FileOutputStream( localSalvar );

			// uma nova instancia do bitmap rotacionado
			Bitmap bmp = bmpd.getBitmap();

			//define um indice = 1 pois se der erro vai manter a imagem como está.
			Integer idx = 1;

			// reupera as dimensões da imagem
			w = bmp.getWidth();
			h = bmp.getHeight();

			// verifica qual a maior dimensão e divide pela lateral final para definir qual o indice de redução
			if ( w >= h ) {
				idx = w / lateral;
			} else {
				idx = h / lateral;
			}

			if ( idx <= 0 ) {
				idx = 1;
			}

			// acplica o indice de redução nas novas dimensões
			w = w / idx;
			h = h / idx;

			// cria nova instancia da imagem já redimensionada
			Bitmap bmpReduzido = Bitmap.createScaledBitmap( bmp, w, h, true );

			// salva a imagem reduzida no disco
			bmpReduzido.compress( Bitmap.CompressFormat.JPEG, 90, out );

		} catch ( IOException e ) {
			// TODO Auto-generated catch block
			e.printStackTrace();

			try {
				StringWriter sw = new StringWriter();
				PrintWriter pw = new PrintWriter( sw );
				e.printStackTrace( pw );
				pw.close();
				sw.close();
				//				GerarTxtLog.gerarArquivoTexto(Utilidades.getDataHoraFormatada( new Date() ) + ":\t 8 EXCEPTION: " + sw.getBuffer().toString(), caminhoArquivoLog, nomeArquivoLog );
			} catch ( Exception ex ) {
			}
			e.printStackTrace();
		}
	}

	public static String quantidadeImagensPasta() {

		File file = new File( getPastaDestinoFoto() );
		Integer count = file.listFiles().length;

		return count.toString();
	}

	public static String tamanhoImagensOcupada() {
		File diretorioCartao = new File( getPastaDestinoFoto() );
		BigDecimal tamanhoUtilizado = new BigDecimal( "0" );

		if ( diretorioCartao.exists() ) {
			File[ ] files = diretorioCartao.listFiles( new FileFilter(){
				public boolean accept( File pathname ) {
					return pathname.getName().toLowerCase().endsWith( ".jpg" );
				}
			} );

			for ( int i = 0; i < files.length; i++ ) {
				File arquivo = files[ i ];
				//Pego o Tamanho do arquivo em bytes
				BigDecimal tamanho = new BigDecimal( arquivo.length() );

				//Transformo em Megabytes
				tamanho = ( tamanho.divide( new BigDecimal( "1024" ) ) ).divide( new BigDecimal( "1024" ) );

				tamanhoUtilizado = ( tamanhoUtilizado.add( tamanho ) ).setScale( 4, BigDecimal.ROUND_UP );
			}

		}
		return tamanhoUtilizado.toString();
	}
}
