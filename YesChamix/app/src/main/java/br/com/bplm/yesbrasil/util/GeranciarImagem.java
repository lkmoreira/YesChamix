/**
 * @author Bruno Henrique Dannas Pelogia <bruno.pelogia@gmail.com>
 * @since 24/06/2013 13:18:27
 * @version 1.0 
 */
package br.com.bplm.yesbrasil.util;

import android.util.Log;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;


/**
 * @author Bruno Pelogia
 * @since 24/06/2013 - 13:18:27
 */
public class GeranciarImagem {
	 
	private static final String PATH = YesChamixUtils.getPastaDestinoFoto();  //put the downloaded file here
     
	 
     public static void DownloadFromUrl(String imageURL, String fileName) {  //this is the downloader method
             try {
                     URL url = new URL("http://192.168.1.45:8080/webservice_gv8_yes/imagens/" + imageURL); //you can write here any link
                     File file = new File(fileName);

                     long startTime = System.currentTimeMillis();
                     Log.d("ImageManager", "download begining");
                     Log.d("ImageManager", "download url:" + url);
                     Log.d("ImageManager", "downloaded file name:" + fileName);
                     /* Open a connection to that URL. */
                     URLConnection ucon = url.openConnection();

                     /*
                      * Define InputStreams to read from the URLConnection.
                      */
                     InputStream is = ucon.getInputStream();
                     BufferedInputStream bis = new BufferedInputStream(is);

                     /*
                      * Read bytes to the Buffer until there is nothing more to read(-1).
                      */
                     /*ByteArrayBuffer baf = new ByteArrayBuffer(50);
                     int current = 0;
                     while ((current = bis.read()) != -1) {
                             baf.append((byte) current);
                     }

                     *//* Convert the Bytes read to a String. *//*
                     FileOutputStream fos = new FileOutputStream(PATH + File.separator +file);
                     fos.write(baf.toByteArray());
                     fos.close();
                     Log.d("ImageManager", "download ready in"
                                     + ((System.currentTimeMillis() - startTime) / 1000)
                                     + " sec");*/

             } catch (IOException e) {
                     Log.d("ImageManager", "Error: " + e);
             }
             
             
             
     }
}
