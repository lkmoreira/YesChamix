/**
 * @author Bruno Henrique Dannas Pelogia <bruno.pelogia@gmail.com>
 * @since 22/05/2013 17:55:43
 * @version 1.0 
 */
package br.com.gv8.yeschamix.imagem.dao;

import java.util.ArrayList;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.util.Log;
import br.com.gv8.yeschamix.abstracts.AbstractDAO;
import br.com.gv8.yeschamix.imagem.model.persistence.ImagemFolderVO;
import br.com.gv8.yeschamix.util.Utilidades;

/**
 * @author Bruno Pelogia
 * @since 22/05/2013 - 17:55:43
 */
public class ImagemFolderDAO extends AbstractDAO{
	
	public static final String CONTEXTO_LOGICO = "ImagemFolderDAO";

	// Nome da tabela
	public static final String NOME_TABELA = "imagem_splash";

	// Nomes das colunas da tabela.
	public static final String ID = "id";	
	public static final String NOME_ARQUIVO = "nome_arquivo";
	
	
	public ImagemFolderDAO(Context context) {
		super(context);	
	}

	public long inserir(ImagemFolderVO model){
		Log.i(CONTEXTO_LOGICO, "Inserindo...");
		ContentValues values = new ContentValues();


		//if(!Utilidades.isNullOrBlank( model.getCodigo() )){
		values.put(ID, model.getId());
		/*}else{
			values.putNull(ID);
		}*/

		if(!Utilidades.isNullOrBlank( model.getArquivo() )){
			values.put(NOME_ARQUIVO, model.getArquivo());
		}else{
			values.putNull(NOME_ARQUIVO);
		}
				

		return db.insert(NOME_TABELA, null, values);
	}
	
	public long alterar(ImagemFolderVO model){
		Log.i(CONTEXTO_LOGICO, "Alterando...");
		ContentValues values = new ContentValues();
		
		if(!Utilidades.isNullOrBlank( model.getArquivo() )){
			values.put(NOME_ARQUIVO, model.getArquivo());
		}else{
			values.putNull(NOME_ARQUIVO);
		}


		return db.update(NOME_TABELA, values, ID+"=?", new String[]{model.getId().toString()});
	}
	
	public void detetarTudoImagensFolder(){
		db.delete( NOME_TABELA , null , null );
	}

	public ArrayList<ImagemFolderVO> consultarTodos(){
		Cursor dados = null;
		try{
			dados = db.query(NOME_TABELA, new String[]{ID, NOME_ARQUIVO}, null, null, null, null, null);

			ArrayList<ImagemFolderVO> imagensSplash = new ArrayList<ImagemFolderVO>();

			if(dados.moveToFirst()){
				// Pegando os Indices das Colunas
				int indexCodigo =  dados.getColumnIndex(ID);				
				int indexNomeArquivo =  dados.getColumnIndex(NOME_ARQUIVO); 
				
				
				do{		

					ImagemFolderVO model = new ImagemFolderVO(dados.getInt(indexCodigo),dados.getString(indexNomeArquivo) );
					
					
					model.setId( dados.getInt(indexCodigo) );
					model.setArquivo( dados.getString(indexNomeArquivo) );
					
					imagensSplash.add(model);
				}while(dados.moveToNext());
			}

			return imagensSplash;
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			if(dados != null){
				dados.close();
			}
		}
		return new ArrayList<ImagemFolderVO>();
	}

	public ArrayList<ImagemFolderVO> consultarTodosPorCodigoImagemFolder(Integer id){
		Cursor dados = null;
		try{

			dados = db.query(NOME_TABELA, new String[]{ID, NOME_ARQUIVO},ImagemFolderDAO.ID+ " = '"+id+"'", null, null, null, null);

			ArrayList<ImagemFolderVO> imagensSplash = new ArrayList<ImagemFolderVO>();

			if(dados.moveToFirst()){
				// Pegando os Indices das Colunas
				int indexCodigo =  dados.getColumnIndex(ID);				
				int indexNomeArquivo =  dados.getColumnIndex(NOME_ARQUIVO);
				
				

				do{		

					ImagemFolderVO model = new ImagemFolderVO(dados.getInt(indexCodigo),dados.getString(indexNomeArquivo));
					
					
					

					model.setId( dados.getInt(indexCodigo) );
					model.setArquivo( dados.getString(indexNomeArquivo) );
									

					imagensSplash.add(model);
				}while(dados.moveToNext());
			}

			return imagensSplash;
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			if(dados != null){
				dados.close();
			}
		}
		return new ArrayList<ImagemFolderVO>();
	}

	
}
