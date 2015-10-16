/**
 * 
 */
package br.com.gv8.yeschamix.util;

import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Iterator;

import javax.imageio.ImageIO;
import javax.imageio.stream.FileImageInputStream;

import br.com.gv8.yeschamix.dto.ImagemDTO;
import br.com.gv8.yeschamix.resources.ImagemResources;

/**
 * @author Bruno Pelogia
 * 
 */
    /** 
     * Filtro para retornar arquivos que sejam imagem  
     */  
 public class FileImageFilter implements FileFilter {  
  
        public boolean accept(File pathname) {  
  
            if (!pathname.isFile()) {  
                return false;  
            }  
  
            FileImageInputStream input = null;  
            boolean ret;  
            try {  
  
                // tenta ler arquivo como image  
                input = new FileImageInputStream(pathname);  
                Iterator i = ImageIO.getImageReaders(input);  
                ret = i.hasNext();  
            } catch (FileNotFoundException e) {  
                throw new RuntimeException(e);  
            } catch (IOException e) {  
                throw new RuntimeException(e);  
            } finally {  
                if (input != null) {  
                    try {  
                        input.close();  
                    } catch (IOException e) {  
                        throw new RuntimeException(e);  
                    }  
                }  
            }  
            return ret;  
        }  
        
        
        public static File[] lerImagens(String caminho, final String nomeImagem) {
    		// lista imagens da raíz do projeto  
    		File root = new File( caminho );
    		//File[] files = root.listFiles( new FileImageFilter() );
    		
    		File[] files = root.listFiles( new FileFilter() {
				public boolean accept( File pathname ) {
					return pathname.getName().startsWith( nomeImagem );
				}
			} );
    		return files;
    	}
        
        public static File[] lerImagens(String caminho) {
    		// lista imagens da raíz do projeto  
    		File root = new File( caminho );
    		File[] files = root.listFiles( new FileImageFilter() );
    		
    		
    		return files;
    	}

    	public static  byte[] getBytes( File file ) {
    		int len = (int) file.length();
    		byte[] sendBuf = new byte[len];
    		FileInputStream inFile = null;
    		try {
    			inFile = new FileInputStream( file );
    			inFile.read( sendBuf , 0 , len );

    		} catch( FileNotFoundException fnfex ) {

    		} catch( IOException ioex ) {

    		}
    		return sendBuf;

    	}
    	
    	/**
    	 * 
    	 * Método responsável por Retirar os espacos e undercores de uma String.
    	 *
    	 * @param string
    	 * @return
    	 *
    	 * @author Bruno Pelogia <bruno.pelogia@gmail.com>
    	 * @since 07/06/2013 16:49:49
    	 * @version 1.0
    	 */
    	public static String removerEspacosString(String string){
    		String nome= string.trim().toUpperCase();  
    		while (nome.contains(" ")) {  
    			nome = nome.replaceAll(" ", "_"); 
    	        
    	    } 
    		
    		nome = nome.replace("-", "_");
    		
    		nome = nome.replace( "_" , "" );
    		
    		
    		return nome;
    	}
    	
    	
    	
    	    	
    }  
