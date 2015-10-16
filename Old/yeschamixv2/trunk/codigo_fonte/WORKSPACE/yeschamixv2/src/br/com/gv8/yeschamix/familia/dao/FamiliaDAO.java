/**
 * @author Bruno Henrique Dannas Pelogia <bruno.pelogia@gmail.com>
 * @since 22/05/2013 17:08:48
 * @version 1.0 
 */
package br.com.gv8.yeschamix.familia.dao;

import java.util.ArrayList;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.util.Log;
import br.com.gv8.yeschamix.abstracts.AbstractDAO;
import br.com.gv8.yeschamix.familia.model.persistence.FamiliaVO;
import br.com.gv8.yeschamix.preco.dao.PrecoDAO;
import br.com.gv8.yeschamix.produto.dao.ProdutoDAO;
import br.com.gv8.yeschamix.relacaousuariopreco.dao.RelacaoUsuarioPrecoDAO;
import br.com.gv8.yeschamix.usuario.dao.UsuarioDAO;
import br.com.gv8.yeschamix.util.Utilidades;

/**
 * @author Bruno Pelogia
 * @since 22/05/2013 - 17:08:48
 */
public class FamiliaDAO extends AbstractDAO {
	
	public static final String CONTEXTO_LOGICO = "FamiliaDAO";

	// Nome da tabela
	public static final String NOME_TABELA = "familia";

	// Nomes das colunas da tabela.
	public static final String ID = "id";	
	public static final String DESCRICAO = "descricao";
	public static final String NOME_ARQUIVO = "nome_arquivo";
	public static final String ORDEM = "ordem";
	
	
	
	public FamiliaDAO( Context context ) {
		super( context );
		
	}
	
	public long inserir(FamiliaVO model){
		Log.i(CONTEXTO_LOGICO, "Inserindo...");
		ContentValues values = new ContentValues();


		//if(!Utilidades.isNullOrBlank( model.getCodigo() )){
		values.put(ID, model.getId());
		/*}else{
			values.putNull(ID);
		}*/

		if(!Utilidades.isNullOrBlank( model.getDescricao() )){
			values.put(DESCRICAO, model.getDescricao());
		}else{
			values.putNull(DESCRICAO);
		}
		
		if(!Utilidades.isNullOrBlank( model.getNomeArquivo() )){
			values.put(NOME_ARQUIVO, model.getNomeArquivo());
		}else{
			values.putNull(NOME_ARQUIVO);
		}
		
		if(!Utilidades.isNullOrBlank( model.getOrdem() )){
			values.put(ORDEM, model.getOrdem());
		}else{
			values.putNull(ORDEM);
		}
		return db.insert(NOME_TABELA, null, values);
	}
	
	
	public long alterar(FamiliaVO model){
		Log.i(CONTEXTO_LOGICO, "Alterando...");
		ContentValues values = new ContentValues();
		
		if(!Utilidades.isNullOrBlank( model.getDescricao() )){
			values.put(DESCRICAO, model.getDescricao());
		}else{
			values.putNull(DESCRICAO);
		}
		
		if(!Utilidades.isNullOrBlank( model.getOrdem() )){
			values.put(ORDEM, model.getOrdem());
		}else{
			values.putNull(ORDEM);
		}
		
		if(!Utilidades.isNullOrBlank( model.getNomeArquivo() )){
			values.put(NOME_ARQUIVO, model.getNomeArquivo());
		}else{
			values.putNull(NOME_ARQUIVO);
		}

		return db.update(NOME_TABELA, values, ID+"=?", new String[]{model.getId().toString()});
	}

	public ArrayList<FamiliaVO> consultarTodos(){
		Cursor dados = null;
		try{
			dados = db.query(NOME_TABELA, new String[]{ID, DESCRICAO, NOME_ARQUIVO, ORDEM}, null, null, null, null, null);

			ArrayList<FamiliaVO> familias = new ArrayList<FamiliaVO>();

			if(dados.moveToFirst()){
				// Pegando os Indices das Colunas
				int indexCodigo =  dados.getColumnIndex(ID);				
				int indexDescricao =  dados.getColumnIndex(DESCRICAO);
				int indexNomeArquivo = dados.getColumnIndex( NOME_ARQUIVO );
				int indexOrdem = dados.getColumnIndex( ORDEM );
				
				do{		

					FamiliaVO model = new FamiliaVO(dados.getString(indexCodigo),dados.getString(indexDescricao), dados.getInt( indexOrdem ), dados.getString( indexNomeArquivo ) );
					
					
					model.setId( dados.getString(indexCodigo) );
					model.setDescricao( dados.getString(indexDescricao) );
					model.setOrdem( dados.getInt( indexOrdem ) );
					model.setNomeArquivo( dados.getString( indexNomeArquivo ) );
					

					familias.add(model);
				}while(dados.moveToNext());
			}

			return familias;
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			if(dados != null){
				dados.close();
			}
		}
		return new ArrayList<FamiliaVO>();
	}
	
	public ArrayList<FamiliaVO> consultarTodosPorLogin(String login){
		Cursor dados = null;
		try{
			dados = db.query(true,NOME_TABELA+" INNER JOIN "+ProdutoDAO.NOME_TABELA+
					 " INNER JOIN "+PrecoDAO.NOME_TABELA+
					 " INNER JOIN "+RelacaoUsuarioPrecoDAO.NOME_TABELA+
					 " INNER JOIN "+UsuarioDAO.NOME_TABELA, new String[]{FamiliaDAO.NOME_TABELA+"."+ID, FamiliaDAO.NOME_TABELA+"."+DESCRICAO, NOME_ARQUIVO, ORDEM}, 
					 													FamiliaDAO.NOME_TABELA+"."+FamiliaDAO.ID+" = "+ProdutoDAO.NOME_TABELA+"."+ProdutoDAO.COD_FAMILIA+" AND "+
					 													PrecoDAO.NOME_TABELA+"."+PrecoDAO.COD_PRODUTO+" = "+ProdutoDAO.NOME_TABELA+"."+ProdutoDAO.ID+" AND "+
					 													RelacaoUsuarioPrecoDAO.NOME_TABELA+"."+RelacaoUsuarioPrecoDAO.COD_PRECO+" = "+PrecoDAO.NOME_TABELA+"."+PrecoDAO.ID+" AND "+
																		UsuarioDAO.NOME_TABELA+"."+UsuarioDAO.ID+" = "+RelacaoUsuarioPrecoDAO.NOME_TABELA+"."+RelacaoUsuarioPrecoDAO.COD_USUARIO+" AND "+
																		UsuarioDAO.NOME_TABELA+"."+UsuarioDAO.LOGIN+" = ? ", new String[]{login}, null, null, null,null);

			ArrayList<FamiliaVO> familias = new ArrayList<FamiliaVO>();

			if(dados.moveToFirst()){
				// Pegando os Indices das Colunas
				int indexCodigo =  dados.getColumnIndex(ID);				
				int indexDescricao =  dados.getColumnIndex(DESCRICAO);
				int indexNomeArquivo = dados.getColumnIndex( NOME_ARQUIVO );
				int indexOrdem = dados.getColumnIndex( ORDEM );
				
				do{		

					FamiliaVO model = new FamiliaVO(dados.getString(indexCodigo),dados.getString(indexDescricao), dados.getInt( indexOrdem ), dados.getString( indexNomeArquivo ) );
					
					
					model.setId( dados.getString(indexCodigo) );
					model.setDescricao( dados.getString(indexDescricao) );
					model.setOrdem( dados.getInt( indexOrdem ) );
					model.setNomeArquivo( dados.getString( indexNomeArquivo ) );
					

					familias.add(model);
				}while(dados.moveToNext());
			}

			return familias;
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			if(dados != null){
				dados.close();
			}
		}
		return new ArrayList<FamiliaVO>();
	}
	
	public FamiliaVO consultarPorCodigoFamilia(String idFamilia){
		Cursor dados = null;
		try{

			dados = db.query(NOME_TABELA, new String[]{ID, DESCRICAO,NOME_ARQUIVO, ORDEM},FamiliaDAO.ID+ " LIKE ? ", new String[]{idFamilia}, null, null, FamiliaDAO.ID);

			FamiliaVO model = new FamiliaVO();
			if(dados.moveToFirst()){
				// Pegando os Indices das Colunas
				int indexCodigo =  dados.getColumnIndex(ID);				
				int indexDescricao =  dados.getColumnIndex(DESCRICAO); 
				int indexNomeArquivo = dados.getColumnIndex( NOME_ARQUIVO );
				int indexOrdem = dados.getColumnIndex( ORDEM );

				do{		
					model =  new FamiliaVO(dados.getString(indexCodigo),dados.getString(indexDescricao), dados.getInt( indexOrdem ), dados.getString( indexNomeArquivo ) );
					
					
					model.setId( dados.getString(indexCodigo) );
					model.setDescricao( dados.getString(indexDescricao) );
					model.setOrdem( dados.getInt( indexOrdem ) );
					model.setNomeArquivo( dados.getString( indexNomeArquivo ) );
					

					
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
		return new FamiliaVO();
	}
	
	
}
