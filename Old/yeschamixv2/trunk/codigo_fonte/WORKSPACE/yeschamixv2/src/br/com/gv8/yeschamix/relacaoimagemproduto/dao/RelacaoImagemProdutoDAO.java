/**
 * @author Bruno Henrique Dannas Pelogia <bruno.pelogia@gmail.com>
 * @since 23/05/2013 14:01:10
 * @version 1.0 
 */
package br.com.gv8.yeschamix.relacaoimagemproduto.dao;

import java.util.ArrayList;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.util.Log;
import br.com.gv8.yeschamix.abstracts.AbstractDAO;
import br.com.gv8.yeschamix.imagem.model.persistence.ImagemProdutoVO;
import br.com.gv8.yeschamix.produto.model.persistence.ProdutoVO;
import br.com.gv8.yeschamix.relacaoimagemproduto.model.persistence.RelacaoImagemProdutoVO;
import br.com.gv8.yeschamix.util.Utilidades;

/**
 * @author Bruno Pelogia
 * @since 23/05/2013 - 14:01:10
 */
public class RelacaoImagemProdutoDAO extends AbstractDAO {
	
	public static final String CONTEXTO_LOGICO = "RelacaoImagemProdutoDAO";

	// Nome da tabela
	public static final String NOME_TABELA = "relacao_imagem_produto";

	// Nomes das colunas da tabela.
	public static final String COD_IMAGEM = "cod_imagem";
	public static final String COD_PRODUTO = "cod_produto";
	
	
	
	public RelacaoImagemProdutoDAO(Context context){
		super(context);
	}
	
	public long inserir(RelacaoImagemProdutoVO model){
		Log.i(CONTEXTO_LOGICO, "Inserindo...");
		ContentValues values = new ContentValues();

		
		if(!Utilidades.isNullOrBlank( model.getImagem() )){
			values.put(COD_IMAGEM, model.getImagem().getId());
		}else{
			values.putNull(COD_IMAGEM);
		}
		
		if(!Utilidades.isNullOrBlank( model.getProduto() )){
			values.put(COD_PRODUTO, model.getProduto().getId());
		}else{
			values.putNull(COD_PRODUTO);
		}
		
		return db.insert(NOME_TABELA, null, values);
	}
	
	public long alterar(RelacaoImagemProdutoVO model){
		Log.i(CONTEXTO_LOGICO, "Alterando...");
		ContentValues values = new ContentValues();
		

		if(!Utilidades.isNullOrBlank( model.getImagem() )){
			values.put(COD_IMAGEM, model.getImagem().getId());
		}else{
			values.putNull(COD_IMAGEM);
		}
		
		if(!Utilidades.isNullOrBlank( model.getProduto() )){
			values.put(COD_PRODUTO, model.getProduto().getId());
		}else{
			values.putNull(COD_PRODUTO);
		}
		
		return db.update(NOME_TABELA, values, RelacaoImagemProdutoDAO.COD_IMAGEM+"=?"+" AND "+RelacaoImagemProdutoDAO.COD_PRODUTO+"=?",
				new String[]{model.getImagem().getId().toString(),model.getProduto().getId().toString()});
	}
	
	public ArrayList<RelacaoImagemProdutoVO> consultarTodos(){
		Cursor dados = null;
		try{
			dados = db.query(NOME_TABELA, new String[]{COD_IMAGEM, COD_PRODUTO}, null, null, null, null, null);

			ArrayList<RelacaoImagemProdutoVO> relacoes = new ArrayList<RelacaoImagemProdutoVO>();

			if(dados.moveToFirst()){
				// Pegando os Indices das Colunas
							
				int indexCodigoImagem =  dados.getColumnIndex(COD_IMAGEM);
				int indexCodigoProduto = dados.getColumnIndex( COD_PRODUTO );
				
				do{		

					RelacaoImagemProdutoVO model = new RelacaoImagemProdutoVO(new ImagemProdutoVO( dados.getInt( indexCodigoImagem) ), new ProdutoVO( dados.getString( indexCodigoProduto ) ));
					
					
					
					model.getImagem().setId( dados.getInt( indexCodigoImagem) );
					model.getProduto().setId( dados.getString( indexCodigoProduto) );
					

					relacoes.add(model);
				}while(dados.moveToNext());
			}

			return relacoes;
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			if(dados != null){
				dados.close();
			}
		}
		return new ArrayList<RelacaoImagemProdutoVO>();
	}

	public ArrayList<RelacaoImagemProdutoVO> consultarTodosPorCodigoRelacaoImagemProduto(String idImagem, String idProduto){
		Cursor dados = null;
		try{

			dados = db.query(NOME_TABELA, new String[]{COD_IMAGEM, COD_PRODUTO},RelacaoImagemProdutoDAO.COD_IMAGEM+ " = '"+idImagem+"' AND "+RelacaoImagemProdutoDAO.COD_PRODUTO+ " = '"+idProduto+"'", null, null, null, null);

			ArrayList<RelacaoImagemProdutoVO> relacoes = new ArrayList<RelacaoImagemProdutoVO>();

			if(dados.moveToFirst()){
				// Pegando os Indices das Colunas				
				int indexCodigoImagem =  dados.getColumnIndex(COD_IMAGEM);
				int indexCodigoProduto = dados.getColumnIndex( COD_PRODUTO );
				
				do{		

					RelacaoImagemProdutoVO model = new RelacaoImagemProdutoVO( new ImagemProdutoVO( dados.getInt( indexCodigoImagem ) ), new ProdutoVO( dados.getString( indexCodigoProduto ) ));
					
					
				
					model.getImagem().setId( dados.getInt(indexCodigoImagem) );
					model.getProduto().setId( dados.getString( indexCodigoProduto ) );
					

					relacoes.add(model);
				}while(dados.moveToNext());
			}

			return relacoes;
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			if(dados != null){
				dados.close();
			}
		}
		return new ArrayList<RelacaoImagemProdutoVO>();
	}
	
}
