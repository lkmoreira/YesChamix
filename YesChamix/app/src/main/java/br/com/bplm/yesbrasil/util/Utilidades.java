package br.com.bplm.yesbrasil.util;

import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

public class Utilidades{

	public static String[ ] REPLACES = { "a", "e", "i", "o", "u", "c" };
	public static Pattern[ ] PATTERNS = null;

	public static void compilePatterns() {
		PATTERNS = new Pattern[ REPLACES.length ];
		PATTERNS[ 0 ] = Pattern.compile( "[�����]", Pattern.CASE_INSENSITIVE );
		PATTERNS[ 1 ] = Pattern.compile( "[����]", Pattern.CASE_INSENSITIVE );
		PATTERNS[ 2 ] = Pattern.compile( "[����]", Pattern.CASE_INSENSITIVE );
		PATTERNS[ 3 ] = Pattern.compile( "[�����]", Pattern.CASE_INSENSITIVE );
		PATTERNS[ 4 ] = Pattern.compile( "[����]", Pattern.CASE_INSENSITIVE );
		PATTERNS[ 5 ] = Pattern.compile( "[�]", Pattern.CASE_INSENSITIVE );
	}

	public static final String formataCnpj( String formatar ) {
		StringBuilder cnpj = new StringBuilder( formatar );
		cnpj.insert( 2, "." );
		cnpj.insert( 6, "." );
		cnpj.insert( 10, "/" );
		cnpj.insert( 15, "-" );
		formatar = cnpj.toString();
		return formatar;
	}

	public static final String formataCep( String formatar ) {
		StringBuilder cep = new StringBuilder( formatar );
		cep.insert( 2, "." );
		cep.insert( 6, "-" );
		formatar = cep.toString();
		return formatar;
	}

	public static final String formataCpf( String formatar ) {
		StringBuilder cpf = new StringBuilder( formatar );
		cpf.insert( 3, "." );
		cpf.insert( 7, "." );
		cpf.insert( 11, "-" );
		formatar = cpf.toString();
		return formatar;
	}

	/**
	 * retorna se o objeto est� em branco ou nulo
	 * 
	 * @param obj
	 * @return
	 */
	public static boolean isNullOrBlank( Object obj ) {
		if ( obj == null || obj.toString().equals( "" ) ) {
			return true;
		}
		return false;
	}

	/**
	 * retorna se alista � nula ou vazia
	 * 
	 * @param lista
	 * @return
	 */
	public static boolean isNullOrEmpty( List< ? > lista ) {
		if ( lista == null || lista.isEmpty() ) {
			return true;
		}
		return false;
	}

	/**
	 * retorna data formatada - exemplo(08/04/2011)
	 * 
	 * @param data
	 * @return
	 */
	public static String getDataFormatada( Date data ) {
		SimpleDateFormat df = new SimpleDateFormat( "dd/MM/yyyy" );
		return df.format( data );
	}

	/**
	 * retorna data e hora formatada - exemplo(08/04/2011 - 14:21)
	 * 
	 * @param data
	 * @return
	 */
	public static String getDataHoraFormatada( Date data ) {
		SimpleDateFormat df = new SimpleDateFormat( "dd/MM/yyyy '-' HH:mm" );
		return df.format( data );
	}

	/**
	 * retorna data e hora formatada - exemplo(08/04/2011 - 14:21)
	 * 
	 * @param data
	 * @return
	 */
	public static String getDataHoraFormatadaSegundos( Date data ) {
		SimpleDateFormat df = new SimpleDateFormat( "yyyy-MM-dd HH:mm:ss" );
		return df.format( data );
	}

	public static final String desformatar( String formatada ) {
		formatada = formatada.replace( ".", "" );
		formatada = formatada.replace( "/", "" );
		formatada = formatada.replace( "-", "" );
		return formatada;
	}

	public static Date parseDate( String data ) throws ParseException {

		if ( data != null && !"".equals( data ) ) {
			SimpleDateFormat dateFormat = new SimpleDateFormat();

			dateFormat.applyPattern( "dd/MM/yyyy" );

			dateFormat.setLenient( false );
			return dateFormat.parse( data );

		} else {
			throw new IllegalArgumentException();
		}

	}

	public static boolean isCnpjValid( String str_cnpj ) {
		if ( str_cnpj == null )
			return false;
		str_cnpj = str_cnpj.trim();
		str_cnpj = str_cnpj.replaceAll( "\\.", "" );
		str_cnpj = str_cnpj.replaceFirst( "/", "" );
		str_cnpj = str_cnpj.replaceFirst( "-", "" );

		if ( ( str_cnpj.length() != 14 ) || ( str_cnpj.equals( "00000000000000" ) ) || ( str_cnpj.equals( "11111111111111" ) ) || ( str_cnpj.equals( "22222222222222" ) ) || ( str_cnpj.equals( "33333333333333" ) ) || ( str_cnpj.equals( "44444444444444" ) ) || ( str_cnpj.equals( "55555555555555" ) ) || ( str_cnpj.equals( "66666666666666" ) ) || ( str_cnpj.equals( "77777777777777" ) ) || ( str_cnpj.equals( "88888888888888" ) ) || ( str_cnpj.equals( "99999999999999" ) ) )
			return false;
		try {
			Long.parseLong( str_cnpj );
		} catch ( NumberFormatException e ) {
			return false;
		}
		if ( str_cnpj.length() != 14 ) {
			return false;
		}

		int soma = 0;
		String cnpj_calc = str_cnpj.substring( 0, 12 );
		char[ ] chr_cnpj = str_cnpj.toCharArray();

		for ( int i = 0; i < 4; i++ )
			if ( ( chr_cnpj[ i ] - '0' >= 0 ) && ( chr_cnpj[ i ] - '0' <= 9 ) )
				soma += ( chr_cnpj[ i ] - '0' ) * ( 6 - ( i + 1 ) );
		for ( int i = 0; i < 8; i++ )
			if ( ( chr_cnpj[ ( i + 4 ) ] - '0' >= 0 ) && ( chr_cnpj[ ( i + 4 ) ] - '0' <= 9 ) )
				soma += ( chr_cnpj[ ( i + 4 ) ] - '0' ) * ( 10 - ( i + 1 ) );
		int dig = 11 - soma % 11;
		cnpj_calc = cnpj_calc + ( ( dig == 10 ) || ( dig == 11 ) ? "0" : Integer.toString( dig ) );

		soma = 0;
		for ( int i = 0; i < 5; i++ )
			if ( ( chr_cnpj[ i ] - '0' >= 0 ) && ( chr_cnpj[ i ] - '0' <= 9 ) )
				soma += ( chr_cnpj[ i ] - '0' ) * ( 7 - ( i + 1 ) );
		for ( int i = 0; i < 8; i++ )
			if ( ( chr_cnpj[ ( i + 5 ) ] - '0' >= 0 ) && ( chr_cnpj[ ( i + 5 ) ] - '0' <= 9 ) )
				soma += ( chr_cnpj[ ( i + 5 ) ] - '0' ) * ( 10 - ( i + 1 ) );
		dig = 11 - soma % 11;
		cnpj_calc = cnpj_calc + ( ( dig == 10 ) || ( dig == 11 ) ? "0" : Integer.toString( dig ) );
		boolean valido = str_cnpj.equals( cnpj_calc );
		return valido;
	}

	public static Bitmap getBitmap( Context c, String arquivo, boolean option, int size ) throws Exception {
		Bitmap bitmap = null;
		FileInputStream in = null;
//		File file;
		System.gc();
		Runtime.getRuntime().gc();
		try {
			in = new FileInputStream( new File(YesChamixUtils.getPastaDestinoFoto()+File.separator+arquivo) );
			//in = c.openFileInput(YesChamixUtils.getPastaDestinoFoto()+File.separator+arquivo);
//			file = new File( YesChamixUtils.getPastaDestinoFoto() + File.separator + arquivo );
			if ( option ) {
				BitmapFactory.Options opts = new BitmapFactory.Options(); // opts.inJustDecodeBounds
				// =
				// true;
				// opts.inSampleSize=2;
				opts.inJustDecodeBounds = true;
				opts.inSampleSize = size;
				opts.inJustDecodeBounds = false;

							bitmap = BitmapFactory.decodeStream(in, null, opts);
//				bitmap = decodeFile( file );
							in.close();
							in=null;
			} else {
					    	bitmap = BitmapFactory.decodeStream(in);
//				bitmap = decodeFile( file );
					    	in.close();
					    	in=null;
			}
			return bitmap;
		} catch ( Exception ex ) {
			System.gc();
			Runtime.getRuntime().gc();
			throw new Exception();
		} catch ( OutOfMemoryError ex ) {
			System.gc();
			Runtime.getRuntime().gc();
			throw new Exception();
		} finally {
			if ( in != null ) {
				try {
					in.close();
				} catch ( IOException e ) {
					System.gc();
					Runtime.getRuntime().gc();
					throw new Exception();

				} catch ( OutOfMemoryError ex ) {
					System.gc();
					Runtime.getRuntime().gc();
					// largeVar is now out of scope, so is garbage
					throw new Exception();
				}
			}
		}
	}
	
	

	/**
	 * Converte uma mensagem criptografada para uma string de sua representa��o
	 * hexadecimal.
	 * 
	 * @param byte[] hex
	 * @return String str
	 */
	public static String fromHex( byte[ ] hex ) {
		StringBuffer sb = new StringBuffer();
		for ( int i = 0; i < hex.length; i++ ) {
			sb.append( Integer.toString( ( hex[ i ] & 0xff ) + 0x100, 16 ).substring( 1 ) );
		}
		return sb.toString();
	}

	/**
	 * Converte uma representa��o hexadecimal para seus bytes hexadecimal (valor
	 * encriptado)
	 * 
	 * @param String
	 *        s
	 * @return byte[] data
	 */
	public static byte[ ] toHex( String s ) {
		int len = s.length();
		byte[ ] data = new byte[ len / 2 ];
		for ( int i = 0; i < len; i += 2 ) {
			data[ i / 2 ] = (byte) ( ( Character.digit( s.charAt( i ), 16 ) << 4 ) + Character.digit( s.charAt( i + 1 ), 16 ) );
		}
		return data;
	}

	public static void unbindDrawables( View view ) {
		if ( view.getBackground() != null ) {
			view.getBackground().setCallback( null );
		}
		if ( view instanceof ViewGroup ) {
			for ( int i = 0; i < ( (ViewGroup) view ).getChildCount(); i++ ) {
				unbindDrawables( ( (ViewGroup) view ).getChildAt( i ) );
			}
			( (ViewGroup) view ).removeAllViews();
		}
	}

	public static void nullViewDrawablesRecursive( View view ) {
		if ( view != null ) {
			try {
				ViewGroup viewGroup = (ViewGroup) view;

				int childCount = viewGroup.getChildCount();

				for ( int index = 0; index < childCount; index++ ) {
					View child = viewGroup.getChildAt( index );
					nullViewDrawablesRecursive( child );
				}
			} catch ( Exception e ) {
			}

			nullViewDrawable( view );
		}
	}

	private static void nullViewDrawable( View view ) {
		try {
			view.setBackgroundDrawable( null );
		} catch ( Exception e ) {
		}

		try {
			ImageView imageView = (ImageView) view;
			imageView.setImageDrawable( null );
			imageView.setBackgroundDrawable( null );
		} catch ( Exception e ) {
		}
	}

	public static String removeAcentos( String text ) {
		if ( PATTERNS == null ) {
			compilePatterns();
		}

		String result = text;
		for ( int i = 0; i < PATTERNS.length; i++ ) {
			Matcher matcher = PATTERNS[ i ].matcher( result );
			result = matcher.replaceAll( REPLACES[ i ] );
		}
		return result.toUpperCase();
	}

	public static String letterAlfabeto( Integer posicao ) {
		String[ ] alfabeto = { "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z" };

		return alfabeto[ posicao ];
	}

	//decodes image and scales it to reduce memory consumption
	private static Bitmap decodeFile( File f ) {
		try {
			//Decode image size
			BitmapFactory.Options o = new BitmapFactory.Options();
			o.inJustDecodeBounds = true;
			BitmapFactory.decodeStream( new FileInputStream( f ), null, o );

			//The new size we want to scale to
			final int REQUIRED_SIZE = 200;

			//Find the correct scale value. It should be the power of 2.
			int scale = 1;
			while ( o.outWidth / scale / 2 >= REQUIRED_SIZE && o.outHeight / scale / 2 >= REQUIRED_SIZE )
				scale *= 2;

			//Decode with inSampleSize
			BitmapFactory.Options o2 = new BitmapFactory.Options();
			o2.inSampleSize = scale;
			return BitmapFactory.decodeStream( new FileInputStream( f ), null, o2 );
		} catch ( FileNotFoundException e ) {
		}
		return null;
	}
}