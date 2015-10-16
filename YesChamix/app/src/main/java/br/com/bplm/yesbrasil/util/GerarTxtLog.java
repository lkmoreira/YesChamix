package br.com.bplm.yesbrasil.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;

public class GerarTxtLog{

	private String caminhoArquivoLog = "";

	GerarTxtLog( String caminhoArquivoLog ){
		this.caminhoArquivoLog = caminhoArquivoLog;
	}

	public String txtLog( String mensagem, String nomeArquivoLog ) throws FileNotFoundException {
		gerarArquivoTexto( mensagem, caminhoArquivoLog, nomeArquivoLog );
		return caminhoArquivoLog;
	}

	public static void gerarArquivoTexto( String texto, String caminhoArquivoLog, String nomeArquivoLog ) {
		// Utilizando um InputStream para escrever um arquivo.
		try {
			if(new File(caminhoArquivoLog + "/" + nomeArquivoLog + ".txt").exists()){
				FileReader arquivoCarregado = new FileReader( caminhoArquivoLog + "/" + nomeArquivoLog + ".txt" );
				// Fechando o arquivo carregado para que n�o sofra altera��es indesejaveis
				String fileName = caminhoArquivoLog + "/" + nomeArquivoLog + ".txt";
				BufferedReader br = new BufferedReader(arquivoCarregado);
				StringBuilder sb = new StringBuilder();
				String text;
				while((text = br.readLine()) != null){
					sb.append(text + "\n");
				}
				sb.append(texto );
				arquivoCarregado.close();
				br.close();
				new FileOutputStream(fileName).write(sb.toString().getBytes());
			}else{
				PrintStream novoArquivo = new PrintStream( caminhoArquivoLog + "/" + nomeArquivoLog + ".txt" );
				novoArquivo.print( texto );
				novoArquivo.close();
			}
		} catch ( IOException e ) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
