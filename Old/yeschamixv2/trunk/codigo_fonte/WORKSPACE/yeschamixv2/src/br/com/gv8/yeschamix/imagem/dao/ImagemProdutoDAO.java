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
import br.com.gv8.yeschamix.imagem.model.persistence.ImagemProdutoVO;
import br.com.gv8.yeschamix.produto.model.persistence.ProdutoVO;
import br.com.gv8.yeschamix.util.Utilidades;

/**
 * @author Bruno Pelogia
 * @since 22/05/2013 - 17:55:43
 */
public class ImagemProdutoDAO extends AbstractDAO{
	
	public static final String CONTEXTO_LOGICO = "ImagemProdutoDAO";

	// Nome da tabela
	public static final String NOME_TABELA = "imagem_produto";

	// Nomes das colunas da tabela.
	public static final String ID = "id";	
	public static final String NOME_ARQUIVO = "nome_arquivo";
	public static final String COR = "cor";
	public static final String COD_PRODUTO = "cod_produto";
	public static final String EXISTE_EM_ESTOQUE = "existe_em_estoque";
	public static final String STATUS_PRODUTO = "status_produto";
	
	
	public ImagemProdutoDAO(Context context) {
		super(context);	
	}

	public long inserir(ImagemProdutoVO model){
		Log.i(CONTEXTO_LOGICO, "Inserindo...");
		ContentValues values = new ContentValues();


		//if(!Utilidades.isNullOrBlank( model.getCodigo() )){
		//values.put(ID, model.getId());
		/*}else{
			values.putNull(ID);
		}*/

		if(!Utilidades.isNullOrBlank( model.getNomeArquivo() )){
			values.put(NOME_ARQUIVO, model.getNomeArquivo());
		}else{
			values.putNull(NOME_ARQUIVO);
		}
		
		if(!Utilidades.isNullOrBlank( model.getCor() )){
			values.put(COR, model.getCor());
		}else{
			values.putNull(COR);
		}
		
		if(!Utilidades.isNullOrBlank( model.getProduto() )){
			values.put(COD_PRODUTO, model.getProduto().getId());
		}else{
			values.putNull(COD_PRODUTO);
		}
		
		if(!Utilidades.isNullOrBlank( model.getExisteEmEstoque() )){
			values.put(EXISTE_EM_ESTOQUE, model.getExisteEmEstoque());
		}else{
			values.putNull(EXISTE_EM_ESTOQUE);
		}
		
		if(!Utilidades.isNullOrBlank( model.getStatusProduto() )){
			values.put(STATUS_PRODUTO, model.getStatusProduto());
		}else{
			values.putNull(STATUS_PRODUTO);
		}
		

		return db.insert(NOME_TABELA, null, values);
	}
	
	public long alterar(ImagemProdutoVO model){
		Log.i(CONTEXTO_LOGICO, "Alterando...");
		ContentValues values = new ContentValues();
		
		if(!Utilidades.isNullOrBlank( model.getNomeArquivo() )){
			values.put(NOME_ARQUIVO, model.getNomeArquivo());
		}else{
			values.putNull(NOME_ARQUIVO);
		}
		
		if(!Utilidades.isNullOrBlank( model.getCor() )){
			values.put(COR, model.getCor());
		}else{
			values.putNull(COR);
		}
		
		if(!Utilidades.isNullOrBlank( model.getProduto() )){
			values.put(COD_PRODUTO, model.getProduto().getId());
		}else{
			values.putNull(COD_PRODUTO);
		}

		if(!Utilidades.isNullOrBlank( model.getExisteEmEstoque() )){
			values.put(EXISTE_EM_ESTOQUE, model.getExisteEmEstoque());
		}else{
			values.putNull(EXISTE_EM_ESTOQUE);
		}
		
		if(!Utilidades.isNullOrBlank( model.getStatusProduto() )){
			values.put(STATUS_PRODUTO, model.getStatusProduto());
		}else{
			values.putNull(STATUS_PRODUTO);
		}
		
		return db.update(NOME_TABELA, values, NOME_ARQUIVO+"=?", new String[]{model.getNomeArquivo().toString()});
	}

	public ArrayList<ImagemProdutoVO> consultarTodos(){
		Cursor dados = null;
		try{
			dados = db.query(NOME_TABELA, new String[]{ID, NOME_ARQUIVO, COR, COD_PRODUTO, EXISTE_EM_ESTOQUE, STATUS_PRODUTO}, null, null, null, null, null);

			ArrayList<ImagemProdutoVO> imagensProduto = new ArrayList<ImagemProdutoVO>();

			if(dados.moveToFirst()){
				// Pegando os Indices das Colunas
				int indexCodigo =  dados.getColumnIndex(ID);				
				int indexNomeArquivo =  dados.getColumnIndex(NOME_ARQUIVO); 
				int indexCor =  dados.getColumnIndex(COR);
				int indexCodProduto = dados.getColumnIndex( COD_PRODUTO );
				int indexExisteEmEstoque = dados.getColumnIndex( EXISTE_EM_ESTOQUE );
				int indexStatusProduto = dados.getColumnIndex( STATUS_PRODUTO );
				
				
				do{		

					ImagemProdutoVO model = new ImagemProdutoVO(dados.getInt(indexCodigo),dados.getString(indexNomeArquivo), dados.getString( indexCor ), new ProdutoVO( dados.getString( indexCodProduto ) ), dados.getString( indexExisteEmEstoque ), dados.getString( indexStatusProduto ));
					
					
					model.setId( dados.getInt(indexCodigo) );
					model.setNomeArquivo( dados.getString(indexNomeArquivo) );
					model.setCor( dados.getString( indexCor ) );
					model.setProduto( new ProdutoVO(dados.getString( indexCodProduto )));
					model.setExisteEmEstoque( dados.getString( indexExisteEmEstoque ) );
					model.setStatusProduto( dados.getString( indexStatusProduto ) );
					
					imagensProduto.add(model);
				}while(dados.moveToNext());
			}

			return imagensProduto;
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			if(dados != null){
				dados.close();
			}
		}
		return new ArrayList<ImagemProdutoVO>();
	}

	public ImagemProdutoVO consultarImagemProdutoPorNomeArquivo(String nomeArquivo){
		Cursor dados = null;
		try{

			dados = db.query(NOME_TABELA, new String[]{ID, NOME_ARQUIVO, COR, COD_PRODUTO, EXISTE_EM_ESTOQUE, STATUS_PRODUTO},ImagemProdutoDAO.NOME_ARQUIVO+ " LIKE ? ", new String[]{nomeArquivo}, null, null, null);

			ImagemProdutoVO model = new ImagemProdutoVO();
			
			if(dados.moveToFirst()){
				// Pegando os Indices das Colunas
				int indexCodigo =  dados.getColumnIndex(ID);				
				int indexNomeArquivo =  dados.getColumnIndex(NOME_ARQUIVO);
				int indexCor =  dados.getColumnIndex(COR);
				int indexCodProduto = dados.getColumnIndex( COD_PRODUTO );
				int indexExisteEmEstoque = dados.getColumnIndex( EXISTE_EM_ESTOQUE );
				int indexStatusProduto = dados.getColumnIndex( STATUS_PRODUTO );
				

				do{		

					model = new ImagemProdutoVO(dados.getInt(indexCodigo),dados.getString(indexNomeArquivo),dados.getString( indexCor ), new ProdutoVO( dados.getString( indexCodProduto ) ),dados.getString( indexExisteEmEstoque ), dados.getString( indexStatusProduto ));
					

					model.setId( dados.getInt(indexCodigo) );
					model.setNomeArquivo( dados.getString(indexNomeArquivo) );
					model.setCor( dados.getString( indexCor ) );
					model.setProduto( new ProdutoVO(dados.getString( indexCodProduto )));
					model.setExisteEmEstoque( dados.getString( indexExisteEmEstoque ) );
					model.setStatusProduto( dados.getString( indexStatusProduto ) );
									

					
				}while(dados.moveToNext());
			}

			return model;
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			if(dados != null){
				dados.close();
			}
		}
		return new ImagemProdutoVO();
	}
	
	public ImagemProdutoVO consultarImagemProdutoPorCorEProd(String cor, String idProduto){
		Cursor dados = null;
		try{

			dados = db.query(NOME_TABELA, new String[]{ID, NOME_ARQUIVO, COR, COD_PRODUTO, EXISTE_EM_ESTOQUE, STATUS_PRODUTO},ImagemProdutoDAO.COR+ " LIKE ? AND "+ImagemProdutoDAO.COD_PRODUTO+ " LIKE ? ", new String[]{cor, idProduto}, null, null, null);

			ImagemProdutoVO model = new ImagemProdutoVO();
			
			if(dados.moveToFirst()){
				// Pegando os Indices das Colunas
				int indexCodigo =  dados.getColumnIndex(ID);				
				int indexNomeArquivo =  dados.getColumnIndex(NOME_ARQUIVO);
				int indexCor =  dados.getColumnIndex(COR);
				int indexCodProduto = dados.getColumnIndex( COD_PRODUTO );
				int indexExisteEmEstoque = dados.getColumnIndex( EXISTE_EM_ESTOQUE );
				int indexStatusProduto = dados.getColumnIndex( STATUS_PRODUTO );
				

				do{		

					model = new ImagemProdutoVO(dados.getInt(indexCodigo),dados.getString(indexNomeArquivo),dados.getString( indexCor ), new ProdutoVO( dados.getString( indexCodProduto ) ),dados.getString( indexExisteEmEstoque ), dados.getString( indexStatusProduto ));
					

					model.setId( dados.getInt(indexCodigo) );
					model.setNomeArquivo( dados.getString(indexNomeArquivo) );
					model.setCor( dados.getString( indexCor ) );
					model.setProduto( new ProdutoVO(dados.getString( indexCodProduto )));
					model.setExisteEmEstoque( dados.getString( indexExisteEmEstoque ) );
					model.setStatusProduto( dados.getString( indexStatusProduto ) );
									

					
				}while(dados.moveToNext());
			}

			return model;
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			if(dados != null){
				dados.close();
			}
		}
		return new ImagemProdutoVO();
	}
	
	public ArrayList<ImagemProdutoVO> consultarTudoImagemProdutoPorIdProduto(String idProduto){
		Cursor dados = null;
		try{
			dados = db.query(NOME_TABELA, new String[]{ID, NOME_ARQUIVO, COR, COD_PRODUTO, EXISTE_EM_ESTOQUE, STATUS_PRODUTO}, ImagemProdutoDAO.COD_PRODUTO+" LIKE ? ", new String[]{idProduto}, null, null, null);

			ArrayList<ImagemProdutoVO> imagensProduto = new ArrayList<ImagemProdutoVO>();

			if(dados.moveToFirst()){
				// Pegando os Indices das Colunas
				int indexCodigo =  dados.getColumnIndex(ID);				
				int indexNomeArquivo =  dados.getColumnIndex(NOME_ARQUIVO); 
				int indexCor = dados.getColumnIndex( COR );
				int indexCodProduto = dados.getColumnIndex( COD_PRODUTO );
				int indexExisteEmEstoque = dados.getColumnIndex( EXISTE_EM_ESTOQUE );
				int indexStatusProduto = dados.getColumnIndex( STATUS_PRODUTO );
				
				
				
				do{		

					ImagemProdutoVO model = new ImagemProdutoVO(dados.getInt(indexCodigo),dados.getString(indexNomeArquivo),dados.getString( indexCor ), new ProdutoVO( dados.getString( indexCodProduto ) ),dados.getString( indexExisteEmEstoque ), dados.getString( indexStatusProduto ));
					
					
					model.setId( dados.getInt(indexCodigo) );
					model.setNomeArquivo( dados.getString(indexNomeArquivo) );
					model.setCor( dados.getString( indexCor ) );
					model.setProduto( new ProdutoVO(dados.getString( indexCodProduto )));
					model.setExisteEmEstoque( dados.getString( indexExisteEmEstoque ) );
					model.setStatusProduto( dados.getString( indexStatusProduto ) );
					
					imagensProduto.add(model);
				}while(dados.moveToNext());
			}

			return imagensProduto;
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			if(dados != null){
				dados.close();
			}
		}
		return new ArrayList<ImagemProdutoVO>();
	}
	
}
