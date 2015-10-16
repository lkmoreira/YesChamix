package br.com.gv8.yeschamix.abstracts;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public abstract class AbstractDAO {

	private static final String CONTEXTO_LOGICO = "AbstractDAO";

	// Nome do Banco de Dados
	private static final String NOME_BANCO = "yesturbo.db";

	// Versão do Banco de Dados
	private static final int VERSAO_BANCO = 1;

	// Banco de dados propriamente dito.
	protected SQLiteDatabase db;

	private SQLiteHelper dbHelper;

	/**
	 * Cria o Banco de Dados utilizando o Helper.
	 * @param context
	 */
	protected AbstractDAO(Context context){
		Log.i(CONTEXTO_LOGICO, "Criando superclasse");
		// Criando o Banco utilizando o Helper
		dbHelper = new SQLiteHelper(context, NOME_BANCO, VERSAO_BANCO, ScriptsCreateDropSQL.SQL_CREATES, ScriptsCreateDropSQL.SQL_DROPS);
		// Abre o Banco de dados no modo escrita para poder alterar tambem.
		db = dbHelper.getWritableDatabase();

	}
	
	public void limparBase(Context context){
		dbHelper = new SQLiteHelper(context, NOME_BANCO, VERSAO_BANCO, ScriptsCreateDropSQL.SQL_CREATES, ScriptsCreateDropSQL.SQL_DROPS);
		// Abre o Banco de dados no modo escrita para poder alterar tambem.
		dbHelper.onUpgrade( db, db.getVersion(), db.getVersion()+1 );
	}
	
	
	public void fechar(){
		if(db != null){
			db.close();
		}
		
		if(dbHelper != null){
			dbHelper.close();
		}
	}
	
}
