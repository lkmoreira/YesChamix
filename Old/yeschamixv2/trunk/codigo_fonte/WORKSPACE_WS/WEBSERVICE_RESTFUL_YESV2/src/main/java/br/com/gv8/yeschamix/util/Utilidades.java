package br.com.gv8.yeschamix.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Utilidades {

	/**
	 * retorna se o objeto est?ï¿½ em branco ou nulo
	 * 
	 * @param obj
	 * @return
	 */
	public static boolean isNullOrBlank(Object obj) {
		if (obj == null || obj.toString().equals("")) {
			return true;
		}
		return false;
	}

	/**
	 * retorna se alista ?ï¿½ nula ou vazia
	 * 
	 * @param lista
	 * @return
	 */
	public static boolean isNullOrEmpty(List<?> lista) {
		if (lista == null || lista.isEmpty()) {
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
	public static String getDataFormatada(Date data) {
		SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		return df.format(data);
	}

	/**
	 * retorna data e hora formatada - exemplo(08/04/2011 - 14:21)
	 * 
	 * @param data
	 * @return
	 */
	public static String getDataHoraFormatada(Date data) {
		SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy '-' HH:mm");
		return df.format(data);
	}

	public static String getDateToString( Date date, String format ) {
		if ( !Utilidades.isNullOrBlank( date ) && !( Utilidades.isNullOrBlank( format ) ) ) {
			sdf.applyPattern( format );
			return sdf.format( date );
		}
		return "";
	}

	/**
	 * retorna data e hora formatada - exemplo(08/04/2011 - 14:21)
	 * 
	 * @param data
	 * @return
	 */
	public static String getDataHoraFormatadaSegundos(Date data) {
		SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		return df.format(data);
	}

	public static String getDataAmericana(Date data) {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		return df.format(data);
	}
	/**
	 * retorna um campo do tipo Date
	 * 
	 * @param data
	 * @return
	 */

	public static Date parseDateHora(String data) {
		try {
			SimpleDateFormat format = new SimpleDateFormat(
			"dd/MM/yyyy HH:mm:ss");
			return format.parse(data);
		} catch (ParseException e) {
			return null;
		} catch (Exception e) {
			return null;
		}

	}

	/**
	 * retorna um campo do tipo Date
	 * 
	 * @param data
	 * @return
	 */

	public static Date parseDate(String data) {
		try {
			SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
			return format.parse(data);
		} catch (ParseException e) {
			return null;
		} catch (Exception e) {
			return null;
		}

	}


	/**
	 * Criptografia de senha em MD5
	 * 
	 * @param senha
	 * @return
	 */
	public static String getSenhaCriptografada(String senha) {
		String sen = "";
		MessageDigest md = null;
		try {
			md = MessageDigest.getInstance("MD5");
			BigInteger hash = new BigInteger(1, md.digest(senha.getBytes()));
			sen = hash.toString(16);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return sen;
	}

	/**
	 * Comapra senhas criptografadas
	 * 
	 * @param passwordClear
	 * @param passwordEncriptado
	 * @return
	 */
	public static boolean comparaSenhaCriptografada(String passwordClear,
			String passwordEncriptado) {
		MessageDigest md5 = null;
		try {
			md5 = MessageDigest.getInstance("MD5");
		} catch (NoSuchAlgorithmException e) {
			return false;
		}
		md5.reset();
		md5.update(passwordClear.getBytes());
		String senha1 = new String(md5.digest());
		return MessageDigest.isEqual(senha1.getBytes(),
				passwordEncriptado.getBytes());
	}

	/**
	 * retorna valor formatado
	 * 
	 * @param valor
	 * @return
	 */
	public static String getValorFormatado(Object valor) {
		DecimalFormat nf = new DecimalFormat("##.00");
		return nf.format(valor);
	}

	private static SimpleDateFormat	sdf;
	static {
		sdf = new SimpleDateFormat();
		sdf.setLenient( false );
	}

	public static Date getStringToDate( String value, String format ) {
		sdf.applyPattern( format );
		try {
			sdf.setLenient( false );
			Date retorno = sdf.parse( value );
			return retorno;
		} catch ( ParseException e ) {
			return null;
		}
	}

	public static Date montarDataYYYYMMDDToDDMMYYYY(String data){
		String dataAux = data.substring( 6, 8 ) + "/" + data.substring( 4, 6 ) + "/" + data.substring( 0, 4 );

		return parseDate(dataAux);
	}

	public static String montarDataHHMM(String data){
		String horaAux = data.substring( 0, 2 ) + ":" + data.substring( 2, 4 );

		return horaAux;
	}



	/**
	 * Método responsável por adicionar valores a esquerda
	 *
	 * @param String valueToPad = variavel que receberá os novos valores 
	 * @param String filler = valor que será inserido na variavel 
	 * @param int size = tamanho que a variavel deverá alcançar
	 * @return
	 */
	public static String lpad(String valueToPad, String filler, int size) {  
		while (valueToPad.length() < size) {  
			valueToPad = filler + valueToPad;  
		}  
		return valueToPad;  
	}  

	public static String rpad(String valueToPad, String filler, int size) {  
		while (valueToPad.length() < size) {  
			valueToPad =  valueToPad + filler;  
		}  
		return valueToPad;  
	}

	private static final Locale BRASIL = new Locale("pt","BR");
	private static final DecimalFormatSymbols REAL = new DecimalFormatSymbols(BRASIL);
	public static final DecimalFormat DINHEIRO_REAL = new DecimalFormat("¤ ###,###,##0.00",REAL);  

	/** 
	 * Mascara texto com formatacao monetaria 
	 * @param valor Valor a ser mascarado 
	 * @param moeda Padrao monetario a ser usado 
	 * @return Valor mascarado de acordo com o padrao especificado 
	 */  
	public static String mascaraDinheiro(double valor, DecimalFormat moeda){  
		return moeda.format(valor);  
	}

	public static String mascaraDinheiroPersonalizada(String valor){  		
			valor = valor.replace( "." , "" );
			if(valor.contains( "." )){
		}else if(valor.contains( "," )){
			valor = valor.replace( "," , "" );
		}
		if(valor.length() < 3){
			valor = Utilidades.lpad( valor , "0" , 3 );
		}
		return valor.substring( 0, valor.length()-2 ) + "," + valor.substring( valor.length()-2 , valor.length() );  
	}

	public static String[] REPLACES = 
	{ "a", "e", "i", "o", "u", "c" };

	public static Pattern[] PATTERNS = null;

	public static void compilePatterns() {
		PATTERNS = new Pattern[REPLACES.length];
		PATTERNS[0] = Pattern.compile(
				"[âãáàä]", Pattern.CASE_INSENSITIVE);
		PATTERNS[1] = Pattern.compile(
				"[éèêë]", Pattern.CASE_INSENSITIVE);
		PATTERNS[2] = Pattern.compile(
				"[íìîï]", Pattern.CASE_INSENSITIVE);
		PATTERNS[3] = Pattern.compile(
				"[óòôõö]", Pattern.CASE_INSENSITIVE);
		PATTERNS[4] = Pattern.compile(
				"[úùûü]", Pattern.CASE_INSENSITIVE);
		PATTERNS[5] = Pattern.compile(
				"[ç]", Pattern.CASE_INSENSITIVE);
	}

	public static String removeAcentos(String text) {
		if (PATTERNS == null) {
			compilePatterns();
		}

		String result = text.toLowerCase();
		for (int i = 0; i < PATTERNS.length; i++) {     
			Matcher matcher = PATTERNS[i].matcher(result);     
			result = matcher.replaceAll(REPLACES[i]);   
		}   
		return result; 
	} 


	/**
	 * 
	 * Método responsável por Criar um arquivo de configuração
	 * de banco de dados dinamico por Usuario
	 *
	 * @param login
	 * @param userDataBase
	 * @param passwordDataBase
	 * @param driver
	 * @param url
	 * @param dataBase
	 *
	 * @author Bruno Pelogia <bruno.pelogia@gmail.com>
	 * @since 07/08/2013 17:20:33
	 * @version 1.0
	 * @throws IOException 
	 */
	public static void criarPropertiesBD(String login, String userDataBase, String passwordDataBase, String driver, String url) throws IOException{
		//Cria um objeto da classe java.util.Properties
	    Properties properties = new Properties();
	 
	    //setando as propriedades(key) e os seus valores(value)
	    properties.setProperty("jdbc.user", Utilidades.criptografar(userDataBase));
	    properties.setProperty("jdbc.pass", Utilidades.criptografar(passwordDataBase));
	    properties.setProperty("jdbc.driver", Utilidades.criptografar(driver));
	    properties.setProperty("jdbc.url", Utilidades.criptografar(url));
	  
	 
	    	
	    	String caminho = Utilidades.class.getResource( "/" ).toString();
			caminho = caminho.substring( 5 , caminho.indexOf( "classes" ) + 8 )+"resources/";
			File diretorio = new File(caminho);
			if(!diretorio.exists()){
				diretorio.mkdirs();
			}
	    	System.out.println(caminho);
	        //Criamos um objeto FileOutputStream            
	        FileOutputStream fos = new FileOutputStream(caminho+login+"BD.properties");
	    
	        
	        //grava os dados no arquivo
	        properties.store(fos, "FILE JDBC PROPERTIES:");
	        //fecha o arquivo
	        fos.close();
	    
        
	}
	
	/**
	 * 
	 * Método responsável por Retornar os dados de conexão do Banco de Dados
	 *  lidos no arquivo '.properties'
	 *
	 * @param login
	 * @return
	 *
	 * @author Bruno Pelogia <bruno.pelogia@gmail.com>
	 * @since 07/08/2013 17:19:16
	 * @version 1.0
	 * @throws IOException 
	 */
	public static HashMap< String, String > lerPropriedadesBD(String login) throws IOException {
	    Properties properties = new Properties();
	 
	    
	        //Setamos o arquivo que será lido
	       String caminho = Utilidades.class.getResource( "/" ).toString();
	       caminho = caminho.substring( 5 , caminho.indexOf( "classes" ) + 8 )+"resources/";
	       InputStream localizacaoPropertie = new FileInputStream( new File(caminho+login+"BD.properties") );
	       //método load faz a leitura através do objeto fis
	        properties.load(localizacaoPropertie);
	    
	    
	    HashMap< String, String > dadosBanco = new HashMap< String, String >();
	    
	    //Captura o valor da propriedade, através do nome da propriedade(Key)
	    String user = properties.getProperty("jdbc.user");
	    String pass = properties.getProperty("jdbc.pass");
	    String driver = properties.getProperty("jdbc.driver");
	    String url = properties.getProperty("jdbc.url");
	    
	    
	    dadosBanco.put( "usuario", Utilidades.criptografar( user) );
	    dadosBanco.put( "senha", Utilidades.criptografar(pass) );
	    dadosBanco.put( "driver", Utilidades.criptografar(driver) );
	    dadosBanco.put( "url", Utilidades.criptografar(url) );

	    
	    
	    return dadosBanco;
	}
	
	/**
	 * 
	 * Método responsável por criptografar e descriptografar uma string.
	 *
	 * @param mensagem
	 * @return
	 *
	 * @author Bruno Pelogia <bruno.pelogia@gmail.com>
	 * @since 08/08/2013 15:58:07
	 * @version 1.0
	 */
	public static String criptografar( String mensagem ) {
		  ArrayList< String > chaves = new ArrayList< String >();
		  int x;

		  String resultado = "";

		  chaves.add( "ABCDEFGHIJLMNOPQRSTUVXZYWK ~,!@#$%^&*()/.:\\" );
		  chaves.add( "ÂÀªêùÿ5Üº¿®¬¼ëèïÙýÄÅÉ©Øû×,çø£úñÑæÆôöò»Á:.\\" );
		  chaves.add( "abcdefghijlmnopqrstuvxzywk1234567890" );
		  chaves.add( "íóÇüé¾¶§÷ÎáâäàåÏ-+ÌÓß¸°¨·¹³²Õµþîì¡«½Q" );

		  for ( x = 0; x < mensagem.trim().length(); x++ ) {

		   if ( chaves.get( 0 ).contains( mensagem.substring( x, ( x + 1 ) ) ) ) {
		    resultado = resultado + chaves.get( 1 ).substring( chaves.get( 0 ).indexOf( mensagem.substring( x, ( x + 1 ) ) ), ( chaves.get( 0 ).indexOf( mensagem.substring( x, ( x + 1 ) ) ) + 1 ) );
		   }

		   else if ( chaves.get( 1 ).contains( mensagem.substring( x, ( x + 1 ) ) ) ) {
		    resultado = resultado + chaves.get( 0 ).substring( chaves.get( 1 ).indexOf( mensagem.substring( x, ( x + 1 ) ) ), ( chaves.get( 1 ).indexOf( mensagem.substring( x, ( x + 1 ) ) ) + 1 ) );
		   }

		   else if ( chaves.get( 2 ).contains( mensagem.substring( x, ( x + 1 ) ) ) ) {
		    resultado = resultado + chaves.get( 3 ).substring( chaves.get( 2 ).indexOf( mensagem.substring( x, ( x + 1 ) ) ), ( chaves.get( 2 ).indexOf( mensagem.substring( x, ( x + 1 ) ) ) + 1 ) );
		   }

		   else if ( chaves.get( 3 ).contains( mensagem.substring( x, ( x + 1 ) ) ) ) {
		    resultado = resultado + chaves.get( 2 ).substring( chaves.get( 3 ).indexOf( mensagem.substring( x, ( x + 1 ) ) ), ( chaves.get( 3 ).indexOf( mensagem.substring( x, ( x + 1 ) ) ) + 1 ) );
		   }
		  }

		  return resultado;

		 }
		
}
