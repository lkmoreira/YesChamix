/**
 * @author Bruno Henrique Dannas Pelogia <bruno.pelogia@gmail.com>
 * @since 23/05/2013 07:49:58
 * @version 1.0 
 */
package br.com.gv8.yeschamix.relacaousuariopreco.dao;

import java.util.ArrayList;
import java.util.Vector;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.util.Log;
import br.com.gv8.yeschamix.abstracts.AbstractDAO;
import br.com.gv8.yeschamix.preco.model.persistence.PrecoVO;
import br.com.gv8.yeschamix.relacaousuariopreco.model.persistence.RelacaoUsuarioPrecoVO;
import br.com.gv8.yeschamix.usuario.dao.UsuarioDAO;
import br.com.gv8.yeschamix.usuario.model.persistence.UsuarioVO;
import br.com.gv8.yeschamix.util.Utilidades;

/**
 * @author Bruno Pelogia
 * @since 23/05/2013 - 07:49:58
 */
public class RelacaoUsuarioPrecoDAO extends AbstractDAO {
	
	public static final String CONTEXTO_LOGICO = "RelacaoUsuarioPrecoDAO";

	// Nome da tabela
	public static final String NOME_TABELA = "usuario_preco";

	// Nomes das colunas da tabela.
	public static final String COD_PRECO = "cod_preco";
	public static final String COD_USUARIO = "cod_usuario";
	
	
	
	public RelacaoUsuarioPrecoDAO(Context context){
		super(context);
	}
	
	public long inserir(RelacaoUsuarioPrecoVO model){
		Log.i(CONTEXTO_LOGICO, "Inserindo...");
		ContentValues values = new ContentValues();

		
		if(!Utilidades.isNullOrBlank( model.getPreco() )){
			values.put(COD_PRECO, model.getPreco().getId());
		}else{
			values.putNull(COD_PRECO);
		}
		
		if(!Utilidades.isNullOrBlank( model.getUsuario() )){
			values.put(COD_USUARIO, model.getUsuario().getId());
		}else{
			values.putNull(COD_USUARIO);
		}
		
		return db.insert(NOME_TABELA, null, values);
	}
	
	public long alterar(RelacaoUsuarioPrecoVO model){
		Log.i(CONTEXTO_LOGICO, "Alterando...");
		ContentValues values = new ContentValues();
		
		if(!Utilidades.isNullOrBlank( model.getPreco() )){
			values.put(COD_PRECO, model.getPreco().getId());
		}else{
			values.putNull(COD_PRECO);
		}
		
		if(!Utilidades.isNullOrBlank( model.getUsuario() )){
			values.put(COD_USUARIO, model.getUsuario().getId());
		}else{
			values.putNull(COD_USUARIO);
		}
		
		return db.update(NOME_TABELA, values, RelacaoUsuarioPrecoDAO.COD_PRECO+"=?"+" AND "+RelacaoUsuarioPrecoDAO.COD_USUARIO+"=?",
				new String[]{model.getPreco().getId().toString(),model.getUsuario().getId().toString()});
	}
	
	public ArrayList<RelacaoUsuarioPrecoVO> consultarTodos(){
		Cursor dados = null;
		try{
			dados = db.query(NOME_TABELA, new String[]{COD_PRECO, COD_USUARIO}, null, null, null, null, null);

			ArrayList<RelacaoUsuarioPrecoVO> relacoes = new ArrayList<RelacaoUsuarioPrecoVO>();

			if(dados.moveToFirst()){
				// Pegando os Indices das Colunas
							
				int indexCodigoPreco =  dados.getColumnIndex(COD_PRECO);
				int indexCodigoUsuario = dados.getColumnIndex( COD_USUARIO );
				
				do{		

					RelacaoUsuarioPrecoVO model = new RelacaoUsuarioPrecoVO(new UsuarioVO( dados.getInt( indexCodigoUsuario ) ), new PrecoVO( dados.getString( indexCodigoPreco ) ));
					
					
					
					model.getPreco().setId( dados.getString( indexCodigoPreco) );
					model.getUsuario().setId( dados.getInt( indexCodigoUsuario ) );
					

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
		return new ArrayList<RelacaoUsuarioPrecoVO>();
	}

	public ArrayList<RelacaoUsuarioPrecoVO> consultarTodosPorCodigoRelacaoUsuarioPreco(String idPreco, String idUsuario){
		Cursor dados = null;
		try{

			dados = db.query(NOME_TABELA, new String[]{COD_PRECO, COD_USUARIO},RelacaoUsuarioPrecoDAO.COD_PRECO+ " = ?  AND "+RelacaoUsuarioPrecoDAO.COD_USUARIO+ " = ? ", new String[]{idPreco, idUsuario}, null, null, null);

			ArrayList<RelacaoUsuarioPrecoVO> relacoes = new ArrayList<RelacaoUsuarioPrecoVO>();

			if(dados.moveToFirst()){
				// Pegando os Indices das Colunas				
				int indexCodigoPreco =  dados.getColumnIndex(COD_PRECO);
				int indexCodigoUsuario = dados.getColumnIndex( COD_USUARIO );
				
				do{		

					RelacaoUsuarioPrecoVO model = new RelacaoUsuarioPrecoVO( new UsuarioVO( dados.getInt( indexCodigoUsuario ) ), new PrecoVO( dados.getString( indexCodigoPreco ) ));
					
					
				
					model.getPreco().setId( dados.getString(indexCodigoPreco) );
					model.getUsuario().setId( dados.getInt( indexCodigoUsuario ) );
					

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
		return new ArrayList<RelacaoUsuarioPrecoVO>();
	}
	
	public ArrayList<RelacaoUsuarioPrecoVO> consultarRelacaoUsuarioPrecoPorLogin(String login){
		Cursor dados = null;
		try{
			
			
			//Fazendo as junções com as tabelas necessárias
			StringBuilder tabelas = new StringBuilder();
			tabelas.append( RelacaoUsuarioPrecoDAO.NOME_TABELA );
			tabelas.append( " INNER JOIN " );
			tabelas.append( UsuarioDAO.NOME_TABELA );
			
			//Projetando as colunas desejadas
			String[] colunas;
			Vector<String> vetor = new Vector<String>();
			vetor.add( RelacaoUsuarioPrecoDAO.NOME_TABELA+"."+COD_PRECO );
			vetor.add( RelacaoUsuarioPrecoDAO.NOME_TABELA+"."+COD_USUARIO );
			colunas = vetor.toArray(new String[vetor.size()]);
			
			//Preparando as condições (amarração logica)
			StringBuilder condicoes = new StringBuilder();
			condicoes.append( RelacaoUsuarioPrecoDAO.NOME_TABELA+"."+RelacaoUsuarioPrecoDAO.COD_USUARIO);
			condicoes.append( " = ");
			condicoes.append( UsuarioDAO.NOME_TABELA+"."+UsuarioDAO.ID);
			condicoes.append( " AND ");
			condicoes.append(UsuarioDAO.NOME_TABELA+"."+UsuarioDAO.LOGIN);
			condicoes.append(" = ? ");
			
			dados = db.query(true, tabelas.toString(), colunas , condicoes.toString() , new String[]{login}, null, null, RelacaoUsuarioPrecoDAO.COD_PRECO ,null);

			ArrayList<RelacaoUsuarioPrecoVO> relacoes = new ArrayList<RelacaoUsuarioPrecoVO>();

			if(dados.moveToFirst()){
				// Pegando os Indices das Colunas				
				int indexCodigoPreco =  dados.getColumnIndex(COD_PRECO);
				int indexCodigoUsuario = dados.getColumnIndex( COD_USUARIO );
				
				do{		

					RelacaoUsuarioPrecoVO model = new RelacaoUsuarioPrecoVO( new UsuarioVO( dados.getInt( indexCodigoUsuario ) ), new PrecoVO( dados.getString( indexCodigoPreco ) ));
					
					
				
					model.getPreco().setId( dados.getString(indexCodigoPreco) );
					model.getUsuario().setId( dados.getInt( indexCodigoUsuario ) );
					

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
		return new ArrayList<RelacaoUsuarioPrecoVO>();
	}
	
	
	
}
