package br.com.gv8.yeschamix.wsconfig;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.net.URI;
import java.net.URL;
import java.net.URLConnection;
import java.util.Date;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.ByteArrayBuffer;

import android.util.Log;
import br.com.gv8.yeschamix.util.GerarTxtLog;
import br.com.gv8.yeschamix.util.Utilidades;
import br.com.gv8.yeschamix.util.YesChamixUtils;

public class WebService{

	private String URL_WS = "http://yesturbo.kinghost.net/webservice_gv8_yes/";
	//private  String URL_WS = "http://192.168.1.100:8088/webservice_gv8_yes/";
	private static final String PATH = YesChamixUtils.getPastaDestinoFoto(); //put the downloaded file here
	private static String caminhoArquivoLog = YesChamixUtils.getPastaLog();
	private static String nomeArquivoLog = WebService.class.getSimpleName() + "Log";

	public WebService(){}

	/**
	 * 
	 * Método responsável por fazer o download da imagem e salvar em uma pasta pré-configurada.
	 * 
	 * 
	 * @param imageURL - nome da imagem no servidor
	 * @param fileName - nome do arquivo que será salvo
	 * @throws IOException
	 * 
	 * @author Bruno Henrique Dannas Pelogia <bruno.pelogia@gmail.com>
	 * @since 27/06/2013 00:33:02
	 * @version 1.0
	 */
	public void DownloadFromUrl( String imageURL, String fileName ) throws IOException { //this is the downloader method
		URLConnection ucon = null;
		System.gc();
		Runtime.getRuntime().gc();
		try {
			URL url = new URL( URL_WS + imageURL ); //you can write here any link
			File file = new File( fileName );

			long startTime = System.currentTimeMillis();
			Log.d( "ImageManager", "download begining" );
			Log.d( "ImageManager", "download url:" + url );
			Log.d( "ImageManager", "downloaded file name:" + fileName );

			/* Open a connection to that URL. */
			if ( ucon == null ) {
				ucon = url.openConnection();
			}
			ucon.setDoInput( true );
			ucon.setConnectTimeout( 30000 );
			ucon.setReadTimeout( 30000 );

			/*
			 * Define InputStreams to read from the URLConnection.
			 */
			InputStream is = ucon.getInputStream();
			BufferedInputStream bis = new BufferedInputStream( is );

			/*
			 * Read bytes to the Buffer until there is nothing more to read(-1).
			 */
			ByteArrayBuffer baf = new ByteArrayBuffer( 50 );
			int current = 0;
			while ( ( current = bis.read() ) != -1 ) {
				baf.append( (byte) current );
			}

			/* Convert the Bytes read to a String. */
			FileOutputStream fos = new FileOutputStream( PATH + File.separator + file );
			fos.write( baf.toByteArray() );
			is.close();
			fos.close();
			Log.d( "ImageManager", "download ready in" + ( ( System.currentTimeMillis() - startTime ) / 1000 ) + " sec" );
		} catch ( Exception e ) {
			ucon = null;
			System.gc();
			Runtime.getRuntime().gc();
			Log.d( "ImageManager", "Error: " + e );
			throw new IOException( "Erro no Download de Imagem" );
		}

	}

	public void downloadFromUrl2( String imageURL, String fileName ) {

		try {

			HttpGet get = new HttpGet( URL_WS + imageURL );
			get.setHeader( "Content-type", "image/jpg" );

			long startTime = System.currentTimeMillis();
			Log.d( "ImageManager", "download iniciando..." );
			Log.d( "ImageManager", "download url:" + get.getURI().getPath() );
			Log.d( "ImageManager", "downloaded nome arquivo:" + fileName );

			HttpResponse httpResponse = HttpClientSingleton.getHttpClientInstace().execute( get );
			int receivedStatusCode = httpResponse.getStatusLine().getStatusCode();

			if ( receivedStatusCode == HttpStatus.SC_OK ) {
				InputStream inputStream = httpResponse.getEntity().getContent();

				File file = new File( fileName.replace( "%20" , " " ) );

				/* Convert the Bytes read to a String. */
				FileOutputStream fos;
				try {
					//PATH- caminho da pasta imagens no celular
					fos = new FileOutputStream( PATH + File.separator + file );
					fos.write( toByte2( inputStream ) );
					inputStream.close();
					fos.close();

					Log.d( "ImageManager", "download pronto em " + ( ( System.currentTimeMillis() - startTime ) / 1000 ) + " sec" );
				} catch ( FileNotFoundException e ) {
					try {
						StringWriter sw = new StringWriter();
						PrintWriter pw = new PrintWriter( sw );
						e.printStackTrace( pw );
						pw.close();
						sw.close();
						GerarTxtLog.gerarArquivoTexto( Utilidades.getDataHoraFormatada( new Date() ) + ":\t 3 EXCEPTION: " + sw.getBuffer().toString(), caminhoArquivoLog, nomeArquivoLog );
					} catch ( Exception ex ) {
					}
					e.printStackTrace();
				} catch ( IOException e ) {
					try {
						StringWriter sw = new StringWriter();
						PrintWriter pw = new PrintWriter( sw );
						e.printStackTrace( pw );
						pw.close();
						sw.close();
						GerarTxtLog.gerarArquivoTexto( Utilidades.getDataHoraFormatada( new Date() ) + ":\t 3 EXCEPTION: " + sw.getBuffer().toString(), caminhoArquivoLog, nomeArquivoLog );
					} catch ( Exception ex ) {
					}
					e.printStackTrace();
				}

			} else {

				Log.e( WebService.class.getName(), "Erro no Download Imagem", new Exception( "Erro DownloadFromURL2" ) );

			}
		} catch ( Exception exp ) {
			try {
				StringWriter sw = new StringWriter();
				PrintWriter pw = new PrintWriter( sw );
				exp.printStackTrace( pw );
				pw.close();
				sw.close();
				GerarTxtLog.gerarArquivoTexto( Utilidades.getDataHoraFormatada( new Date() ) + ":\t 3 EXCEPTION: " + sw.getBuffer().toString(), caminhoArquivoLog, nomeArquivoLog );
			} catch ( Exception ex ) {
			}
			exp.printStackTrace();

		}
	}

	private byte[ ] toByte2( InputStream is ) throws IOException {

		BufferedInputStream bis = new BufferedInputStream( is );

		/*
		 * Read bytes to the Buffer until there is nothing more to read(-1).
		 */
		ByteArrayBuffer baf = new ByteArrayBuffer( 50 );
		int current = 0;
		while ( ( current = bis.read() ) != -1 ) {
			baf.append( (byte) current );
		}
		bis.close();
		is.close();
		return baf.toByteArray();
	}

	public final Object[ ] getImagem( String servico ) {

		Object[ ] result = new Object[ 2 ];
		HttpGet httpget = new HttpGet( URL_WS + servico );
		HttpResponse response;

		try {
			httpget.setHeader( "Content-type", "image/png" );
			httpget.addHeader( "Accept", "application/json" );
			response = HttpClientSingleton.getHttpClientInstace().execute( httpget );
			HttpEntity entity = response.getEntity();

			if ( entity != null ) {
				result[ 0 ] = String.valueOf( response.getStatusLine().getStatusCode() );
				InputStream instream = entity.getContent();

				result[ 1 ] = toByte( instream );
				instream.close();
				Log.i( "get", "Result from post JsonPost : " + result[ 0 ] + " : " + result[ 1 ] );
			}
		} catch ( Exception e ) {
			System.gc();
			Runtime.getRuntime().gc();
			try {
				StringWriter sw = new StringWriter();
				PrintWriter pw = new PrintWriter( sw );
				e.printStackTrace( pw );
				pw.close();
				sw.close();
				GerarTxtLog.gerarArquivoTexto( Utilidades.getDataHoraFormatada( new Date() ) + ":\t 3 EXCEPTION: " + sw.getBuffer().toString(), caminhoArquivoLog, nomeArquivoLog );
			} catch ( Exception ex ) {
			}
			e.printStackTrace();

			Log.e( "NGVL", "Falha ao acessar Web service", e );
			result[ 0 ] = "0";
			result[ 1 ] = "Falha de rede!";
		}
		return result;
	}

	public final String[ ] get( String servico ) {

		String[ ] result = new String[ 2 ];
		HttpGet httpget = new HttpGet( URL_WS + servico );
		HttpResponse response;

		try {

			httpget.addHeader( "Accept", "application/json" );
			response = HttpClientSingleton.getHttpClientInstace().execute( httpget );
			HttpEntity entity = response.getEntity();

			if ( entity != null ) {
				result[ 0 ] = String.valueOf( response.getStatusLine().getStatusCode() );
				InputStream instream = entity.getContent();
				result[ 1 ] = toString( instream );
				instream.close();
				Log.i( "get", "Result from post JsonPost : " + result[ 0 ] + " : " + result[ 1 ] );
			}
		} catch ( Exception e ) {
			System.gc();
			Runtime.getRuntime().gc();
			try {
				StringWriter sw = new StringWriter();
				PrintWriter pw = new PrintWriter( sw );
				e.printStackTrace( pw );
				pw.close();
				sw.close();
				GerarTxtLog.gerarArquivoTexto( Utilidades.getDataHoraFormatada( new Date() ) + ":\t 3 EXCEPTION: " + sw.getBuffer().toString(), caminhoArquivoLog, nomeArquivoLog );
			} catch ( Exception ex ) {
			}
			e.printStackTrace();

			Log.e( "NGVL", "Falha ao acessar Web service", e );
			result[ 0 ] = "0";
			result[ 1 ] = "Falha de rede!";
		}
		return result;
	}

	public String converterStreamEmString( InputStream stream ) {
		java.util.Scanner s = new java.util.Scanner( stream ).useDelimiter( "\\A" );
		return s.hasNext() ? s.next() : "";
	}

	public final String[ ] post( String url, String json ) {
		String[ ] result = new String[ 2 ];
		try {

			StringEntity sEntity = new StringEntity( json, HTTP.UTF_8 );
			sEntity.setChunked( false );

			HttpPost httpPost = new HttpPost( new URI( URL_WS + url ) );
			httpPost.setHeader( "Content-type", "application/json" );
			httpPost.addHeader( "Accept", "application/json" );
			httpPost.setEntity( sEntity );

			HttpResponse response;
			response = HttpClientSingleton.getHttpClientInstace().execute( httpPost );
			HttpEntity entity = response.getEntity();

			if ( entity != null ) {
				result[ 0 ] = String.valueOf( response.getStatusLine().getStatusCode() );
				InputStream instream = entity.getContent();
				result[ 1 ] = toString( instream );
				instream.close();
				Log.d( "post", "Result from post JsonPost : " + result[ 0 ] + " : " + result[ 1 ] );
			}

		} catch ( Exception e ) {
			System.gc();
			Runtime.getRuntime().gc();
			try {
				StringWriter sw = new StringWriter();
				PrintWriter pw = new PrintWriter( sw );
				e.printStackTrace( pw );
				pw.close();
				sw.close();
				GerarTxtLog.gerarArquivoTexto( Utilidades.getDataHoraFormatada( new Date() ) + ":\t 3 EXCEPTION: " + sw.getBuffer().toString(), caminhoArquivoLog, nomeArquivoLog );
			} catch ( Exception ex ) {
			}
			e.printStackTrace();

			Log.e( "NGVL", "Falha ao acessar Web service", e );
			result[ 0 ] = "0";
			result[ 1 ] = "Falha de rede!";
		}
		return result;
	}

	private String toString( InputStream is ) throws IOException {
		String decoded = null;
		byte[] bBuffer = new byte[300000]; // 
		ByteArrayBuffer baf = new ByteArrayBuffer(300000);
		ByteArrayOutputStream baos = null;
		try {
			baos = new ByteArrayOutputStream();
			int len = 0;
			while ( ( len = is.read( bBuffer, 0, bBuffer.length ) ) != -1 ) {
				baos.write( bBuffer, 0, len );
			}

			byte[ ] b = baos.toByteArray();
			decoded = new String( b, "UTF-8" );

		} catch ( OutOfMemoryError e ) {
			baos = new ByteArrayOutputStream();
			int len = 0;
			while ( ( len = is.read( bBuffer, 0, bBuffer.length ) ) != -1 ) {
				baos.write( bBuffer, 0, len );
			}

			byte[ ] b = baos.toByteArray();
			decoded = new String( b, "UTF-8" );
			Log.i("OutofMemoryError","Ehhhhbaaa");
		}finally{
			baos.reset();
			baos.close();
			baos = null;
			is.close();
			is = null;
			System.gc();
			Runtime.getRuntime().gc();
		}
		
		return decoded.trim();
	}

	private byte[ ] toByte( InputStream is ) throws IOException {

		byte[ ] bytes = new byte[ 1024 ];
		ByteArrayOutputStream baos = null;
		baos = new ByteArrayOutputStream();
		int lidos;
		while ( ( lidos = is.read( bytes ) ) > 0 ) {
			baos.write( bytes, 0, lidos );
		}
		byte[ ] byteArray = baos.toByteArray();
		baos.close();
		is.close();
		return byteArray;
	}

}