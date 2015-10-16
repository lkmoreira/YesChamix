/**
 * @author Bruno Henrique Dannas Pelogia <bruno.pelogia@gmail.com>
 * @since 22/05/2013 22:57:31
 * @version 1.0 
 */
package br.com.gv8.yeschamix.usuario.dao;

import java.util.ArrayList;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.util.Log;
import br.com.gv8.yeschamix.abstracts.AbstractDAO;
import br.com.gv8.yeschamix.usuario.model.persistence.UsuarioVO;
import br.com.gv8.yeschamix.util.Utilidades;

/**
 * @author Bruno Pelogia
 * @since 22/05/2013 - 22:57:31
 */
public class UsuarioDAO extends AbstractDAO{
	
	
	
	public static final String CONTEXTO_LOGICO = "UsuarioDAO";

	// Nome da tabela
	public static final String NOME_TABELA = "usuario";

	// Nomes das colunas da tabela.
	public static final String ID = "id";
	public static final String LOGIN = "login";
	public static final String NOME = "nome";
	public static final String SENHA = "senha";
	public static final String BLOQUEADO = "bloqueado";
	
	public UsuarioDAO( Context context ) {
		super( context );
	}
	
	public long inserir(UsuarioVO model){
		Log.i(CONTEXTO_LOGICO, "Inserindo...");
		ContentValues values = new ContentValues();


		//if(!Utilidades.isNullOrBlank( model.getCodigo() )){
		values.put(ID, model.getId());
		/*}else{
			values.putNull(ID);
		}*/

		if(!Utilidades.isNullOrBlank( model.getLogin() )){
			values.put(LOGIN, model.getLogin());
		}else{
			values.putNull(LOGIN);
		}
		
		if(!Utilidades.isNullOrBlank( model.getNome() )){
			values.put(NOME, model.getNome());
		}else{
			values.putNull(NOME);
		}
		
		if(!Utilidades.isNullOrBlank( model.getSenha() )){
			values.put(SENHA, model.getSenha());
		}else{
			values.putNull(SENHA);
		}
		
		if(!Utilidades.isNullOrBlank( model.getBloqueado() )){
			values.put(BLOQUEADO, model.getBloqueado());
		}else{
			values.putNull(BLOQUEADO);
		}
		
		return db.insert(NOME_TABELA, null, values);
	}
	
	
	public long alterar(UsuarioVO model){
		Log.i(CONTEXTO_LOGICO, "Alterando...");
		ContentValues values = new ContentValues();
		
		if(!Utilidades.isNullOrBlank( model.getLogin() )){
			values.put(LOGIN, model.getLogin());
		}else{
			values.putNull(LOGIN);
		}
		
		if(!Utilidades.isNullOrBlank( model.getNome() )){
			values.put(NOME, model.getNome());
		}else{
			values.putNull(NOME);
		}
		
		if(!Utilidades.isNullOrBlank( model.getSenha() )){
			values.put(SENHA, model.getSenha());
		}else{
			values.putNull(SENHA);
		}
		
		if(!Utilidades.isNullOrBlank( model.getBloqueado() )){
			values.put(BLOQUEADO, model.getBloqueado());
		}else{
			values.putNull(BLOQUEADO);
		}

		return db.update(NOME_TABELA, values, ID+"=?", new String[]{model.getId().toString()});
	}
	
	public ArrayList<UsuarioVO> consultarTodos(){
		Cursor dados = null;
		try{
			dados = db.query(NOME_TABELA, new String[]{ID, LOGIN, NOME, SENHA, BLOQUEADO}, null, null, null, null, null);

			ArrayList<UsuarioVO> usuarios = new ArrayList<UsuarioVO>();

			if(dados.moveToFirst()){
				// Pegando os Indices das Colunas
				int indexCodigo =  dados.getColumnIndex(ID);				
				int indexLogin =  dados.getColumnIndex(LOGIN);
				int indexNome = dados.getColumnIndex( NOME );
				int indexSenha = dados.getColumnIndex( SENHA );
				int indexBloqueado = dados.getColumnIndex( BLOQUEADO );
				
				do{		

					UsuarioVO model = new UsuarioVO(dados.getInt(indexCodigo),dados.getString(indexLogin), dados.getString( indexNome ), dados.getString( indexSenha ), dados.getInt( indexBloqueado ) );
					
					
					model.setId( dados.getInt( indexCodigo) );
					model.setLogin( dados.getString(indexLogin) );
					model.setNome( dados.getString( indexNome ) );
					model.setSenha( dados.getString( indexSenha) );
					model.setBloqueado( dados.getInt( indexBloqueado ) );
					

					usuarios.add(model);
				}while(dados.moveToNext());
			}

			return usuarios;
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			if(dados != null){
				dados.close();
			}
		}
		return new ArrayList<UsuarioVO>();
	}
	
	public UsuarioVO consultarPorCodigoUsuario(String idUsuario){
		Cursor dados = null;
		try{

			dados = db.query(NOME_TABELA, new String[]{ID, LOGIN, NOME, SENHA, BLOQUEADO},UsuarioDAO.ID+ " = ? ", new String[]{idUsuario}, null, null, null);

			UsuarioVO model = new UsuarioVO();

			if(dados.moveToFirst()){
				// Pegando os Indices das Colunas
				int indexCodigo =  dados.getColumnIndex(ID);				
				int indexLogin =  dados.getColumnIndex(LOGIN);
				int indexNome = dados.getColumnIndex( NOME );
				int indexSenha = dados.getColumnIndex( SENHA );
				int indexBloqueado = dados.getColumnIndex( BLOQUEADO );

				do{		
					model = new UsuarioVO(dados.getInt(indexCodigo),dados.getString(indexLogin), dados.getString( indexNome ), dados.getString( indexSenha ), dados.getInt( indexBloqueado ) );
					
					
					model.setId( dados.getInt( indexCodigo) );
					model.setLogin( dados.getString(indexLogin) );
					model.setNome( dados.getString( indexNome ) );
					model.setSenha( dados.getString( indexSenha) );
					model.setBloqueado( dados.getInt( indexBloqueado ) );
					

					
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
		return new UsuarioVO();
	}
	
	public UsuarioVO consultarPorLogin(String login){
		Cursor dados = null;
		try{

			dados = db.query(NOME_TABELA, new String[]{ID, LOGIN, NOME, SENHA, BLOQUEADO},UsuarioDAO.LOGIN+ " = ? ", new String[]{login}, null, null, null);

			UsuarioVO model = new UsuarioVO();

			if(dados.moveToFirst()){
				// Pegando os Indices das Colunas
				int indexCodigo =  dados.getColumnIndex(ID);				
				int indexLogin =  dados.getColumnIndex(LOGIN);
				int indexNome = dados.getColumnIndex( NOME );
				int indexSenha = dados.getColumnIndex( SENHA );
				int indexBloqueado = dados.getColumnIndex( BLOQUEADO );

				do{		
					model = new UsuarioVO(dados.getInt(indexCodigo),dados.getString(indexLogin), dados.getString( indexNome ), dados.getString( indexSenha ), dados.getInt( indexBloqueado ) );
					
					
					model.setId( dados.getInt( indexCodigo) );
					model.setLogin( dados.getString(indexLogin) );
					model.setNome( dados.getString( indexNome ) );
					model.setSenha( dados.getString( indexSenha) );
					model.setBloqueado( dados.getInt( indexBloqueado ) );
					

					
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
		return new UsuarioVO();
	}
	
}
