package br.com.gv8.yeschamix.util;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class DownloadArquivoFTP {

//	FTPClient ftp = new FTPClient();
//
//	final String SERVER = "ftp.gv8premium.com.br";
//	final String USERNAME = "gv8premium";
//	final String PASSWORD = "kKkPO2Gv8";
//
//	public String baixarArquivoFTP(String nomeArquivo, String caminho, String localSalvar) {
//		try {
//			ftp.connect(SERVER);
//			ftp.enterLocalPassiveMode();
//			ftp.login(USERNAME, PASSWORD);
//			ftp.changeWorkingDirectory(caminho);
//			File root = null;
//			List<String> nomesArquivosBaixar = new ArrayList<String>(Arrays.asList(nomeArquivo));
//			FTPFile[] arquivosTxts = ftp.listFiles();
//			
//			
//			
//			
//			for (int i = 0; i < arquivosTxts.length; i++) {
//				for (String nomeDoArquivoFtp : nomesArquivosBaixar) {
//					if (arquivosTxts[i].getName().equals(nomeDoArquivoFtp)) {
//						System.out.println("Caminho 4 ---- " + localSalvar + nomeArquivo);
//						root = new File(localSalvar + nomeArquivo);
//						System.out.println("Caminho 5 ---- " + nomeDoArquivoFtp);
//						System.out.println("Caminho 6 ---- " + root.getPath().toString());
//						ftpDownload(nomeDoArquivoFtp, root.getPath().toString());
//						ftp.disconnect();
//						ftp.connect(SERVER);
//						ftp.login(USERNAME, PASSWORD);
//						ftp.changeWorkingDirectory( caminho);
//					}
//				}
//			}
//
//			return root.getPath().toString();
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return null;
//	}
//
//	public boolean ftpDownload(String srcFilePath, String desFilePath) {
//		boolean status = false;
//		try {
//			// Cria o outputStream para ser passado como parametro
//			FileOutputStream desFileStream = new FileOutputStream(desFilePath);
//
//			// Tipo de arquivo
//			ftp.setFileType(FTP.BINARY_FILE_TYPE);
//
//			// http://commons.apache.org/net/apidocs/org/apache/commons/net/ftp/FTPClient.html#enterLocalActiveMode()
//			ftp.enterLocalPassiveMode();
//
//			// Faz o download do arquivo
//			status = ftp.retrieveFile(srcFilePath, desFileStream);
//			// Fecho o output
//			desFileStream.close();
//			System.out.println("STATUS ----  "  +  status);
//			return status;
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//
//		return status;
//	}

}