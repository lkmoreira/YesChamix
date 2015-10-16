/**
 * @author Bruno Henrique Dannas Pelogia <bruno.pelogia@gmail.com>
 * @since 22/05/2013 20:09:30
 * @version 1.0 
 */
package br.com.gv8.yeschamix.preco.dao;

import java.util.ArrayList;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.util.Log;
import br.com.gv8.yeschamix.abstracts.AbstractDAO;
import br.com.gv8.yeschamix.preco.model.persistence.PrecoVO;
import br.com.gv8.yeschamix.produto.model.persistence.ProdutoVO;
import br.com.gv8.yeschamix.usuario.dao.UsuarioDAO;
import br.com.gv8.yeschamix.util.Utilidades;

/**
 * @author Bruno Pelogia
 * @since 22/05/2013 - 20:09:30
 */
public class PrecoDAO extends AbstractDAO {

	public static final String CONTEXTO_LOGICO = "PrecoDAO";

	// Nome da tabela
	public static final String NOME_TABELA = "preco";

	// Nomes das colunas da tabela.
	public static final String CODIGO = "codigo";
	public static final String ID = "id";
	public static final String PRECO = "preco";
	public static final String COD_PRODUTO = "cod_produto";

	public PrecoDAO( Context context ) {
		super( context );

	}

	public long inserir( PrecoVO model ) {
		Log.i( CONTEXTO_LOGICO , "Inserindo..." );
		ContentValues values = new ContentValues();

		//if(!Utilidades.isNullOrBlank( model.getCodigo() )){
		values.put( CODIGO , model.getCodigo() );
		/*}else{
			values.putNull(CODIGO);
		}*/

		if(!Utilidades.isNullOrBlank( model.getId() )){
			values.put( ID , model.getId() );
		}else{
			values.putNull(ID);
		}
		
		if ( !Utilidades.isNullOrBlank( model.getPreco() ) ) {
			values.put( PRECO , model.getPreco() );
		} else {
			values.putNull( PRECO );
		}

		if ( !Utilidades.isNullOrBlank( model.getProduto() ) ) {
			values.put( COD_PRODUTO , model.getProduto().getId() );
		} else {
			values.putNull( COD_PRODUTO );
		}

		return db.insert( NOME_TABELA , null , values );
	}
	
	public long alterar(PrecoVO model){
		Log.i(CONTEXTO_LOGICO, "Alterando...");
		ContentValues values = new ContentValues();
		
		if(!Utilidades.isNullOrBlank( model.getId() )){
			values.put( ID , model.getId() );
		}else{
			values.putNull(ID);
		}
		
		if(!Utilidades.isNullOrBlank( model.getPreco() )){
			values.put(PRECO, model.getPreco());
		}else{
			values.putNull(PRECO);
		}
		
		if(!Utilidades.isNullOrBlank( model.getProduto() )){
			values.put(COD_PRODUTO, model.getProduto().getId());
		}else{
			values.putNull(COD_PRODUTO);
		}

		return db.update(NOME_TABELA, values, ID+" = ? AND "+COD_PRODUTO+" = ? ", new String[]{model.getId().toString(), model.getProduto().getId()});
	}

	public ArrayList<PrecoVO> consultarTodos(){
		Cursor dados = null;
		try{
			dados = db.query(NOME_TABELA, new String[]{CODIGO,ID, PRECO, COD_PRODUTO}, null, null, null, null, null);

			ArrayList<PrecoVO> precos = new ArrayList<PrecoVO>();

			if(dados.moveToFirst()){
				// Pegando os Indices das Colunas
				int indexCodigo =  dados.getColumnIndex(CODIGO);
				int indexId = dados.getColumnIndex( ID );
				int indexPreco =  dados.getColumnIndex(PRECO);
				int indexCodigoProduto = dados.getColumnIndex( COD_PRODUTO );
			
				
				do{		

					PrecoVO model = new PrecoVO(dados.getInt( indexCodigo ),dados.getString(indexId),new ProdutoVO(dados.getString( indexCodigoProduto )), dados.getDouble(indexPreco));
					
					model.setCodigo( dados.getInt( indexCodigo ) );
					model.setId( dados.getString(indexId) );
					model.setPreco( dados.getDouble( indexPreco) );
					model.getProduto().setId( dados.getString( indexCodigoProduto ));
					
					
					precos.add(model);
				}while(dados.moveToNext());
			}

			return precos;
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			if(dados != null){
				dados.close();
			}
		}
		return new ArrayList<PrecoVO>();
	}
	
	public PrecoVO consultarPorCodPrecoCodProduto(String idPreco, String idProduto){
		Cursor dados = null;
		try{

			dados = db.query(NOME_TABELA, new String[]{CODIGO, ID, PRECO, COD_PRODUTO},PrecoDAO.ID+ " = ? AND "+PrecoDAO.COD_PRODUTO+" = ? ", new String[]{idPreco, idProduto}, null, null, null);

			PrecoVO model = new PrecoVO();

			if(dados.moveToFirst()){
				// Pegando os Indices das Colunas
				int indexCodigo = dados.getColumnIndex( CODIGO );
				int indexId =  dados.getColumnIndex(ID);				
				int indexPreco =  dados.getColumnIndex(PRECO);
				int indexCodigoProduto = dados.getColumnIndex( COD_PRODUTO );

				do{		
					model =  new PrecoVO(dados.getInt( indexCodigo ),dados.getString(indexCodigo),new ProdutoVO(dados.getString( indexCodigoProduto )), dados.getDouble(indexPreco));
					
					model.setCodigo( dados.getInt( indexCodigo ) );
					model.setId( dados.getString(indexId) );
					model.getProduto().setId( dados.getString(indexCodigoProduto) );
					model.setPreco( dados.getDouble( indexPreco ) );
					

					
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
		return new PrecoVO();
	}
	
	public ArrayList< PrecoVO> consultarPorCodigoProduto(String idProduto, String login){
		Cursor dados = null;
		try{

			dados = db.query(true, NOME_TABELA+" INNER JOIN "+UsuarioDAO.NOME_TABELA, new String[]{CODIGO, PrecoDAO.NOME_TABELA+"."+ID, PRECO, PrecoDAO.NOME_TABELA+"."+COD_PRODUTO},PrecoDAO.NOME_TABELA+"."+PrecoDAO.COD_PRODUTO+" = '"+idProduto+"' AND "+UsuarioDAO.LOGIN+" = '"+login+"' ", null, null, null, null, null);

			ArrayList< PrecoVO> lista = new ArrayList< PrecoVO>();

			if(dados.moveToFirst()){
				// Pegando os Indices das Colunas
				int indexCodigo = dados.getColumnIndex( CODIGO );
				int indexId =  dados.getColumnIndex(ID);				
				int indexPreco =  dados.getColumnIndex(PRECO);
				int indexCodigoProduto = dados.getColumnIndex( COD_PRODUTO );

				do{		
					PrecoVO model =  new PrecoVO(dados.getInt( indexCodigo ),dados.getString(indexCodigo),new ProdutoVO(dados.getString( indexCodigoProduto )), dados.getDouble(indexPreco));
					
					model.setCodigo( dados.getInt( indexCodigo ) );
					model.setId( dados.getString(indexId) );
					model.getProduto().setId( dados.getString(indexCodigoProduto) );
					model.setPreco( dados.getDouble( indexPreco ) );
					lista.add( model );

					
				}while(dados.moveToNext());
			}

			return lista;
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			if(dados != null){
				dados.close();
			}
		}
		return new ArrayList< PrecoVO>();
	}
	
}
