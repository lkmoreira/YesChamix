package br.com.gv8.yeschamix.abstracts;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class SQLiteHelper extends SQLiteOpenHelper{

	private static final String CONTEXTO_LOGICO = "SQLiteHelper";
	private String[] scriptsSQLCreate;
	private String[] scriptsSQLDrop;
	@Override
	public void onOpen(SQLiteDatabase db) {
		super.onOpen(db);
		if (!db.isReadOnly()) {
			//db.setLocale( Locale.getDefault() );
			db.execSQL("PRAGMA foreign_keys=ON;");
			
			//db.execSQL("PRAGMA encoding = UTF16;");
		}
	}

	/**
	 * Cria uma instancia de AdapterAbstractDAO.
	 * 
	 * Aqui � chamado o construtor da super Classe SQLiteOpenHelper passando os dados
	 * do banco para que ele gerencie a vers�o
	 * 
	 * @param Context context - Contexto da aplica��o
	 * @param String nomeBanco - Nome do banco de dados a ser gerado.
	 * @param int versaoBanco = Vers�o em que o banco se encontra. (Se for diferente significa que a estrutura foi alterada
	 * e por isso deve-se apagar tudo e criar novamente.)
	 * @param String[] scriptsSQLCreate - Todos os scripts SQL de cria��o(CREATE) de todas as tabelas do sistema.
	 * @param String[] scriptsSQLDrop - Todos os scripts SQL de dele��o(DROP) de todas as tabelas do sistema.
	 * 
	 * Colocar:
	 * "/mnt/sdcard/"+
	 * 
	 * antes de nomeBanco para criar o bd no cartão de memória
	 */
	SQLiteHelper(Context context, String nomeBanco, int versaoBanco, String[] scriptsSQLCreate, String[] scriptsSQLDrop)  {
		super(context, /*"/mnt/sdcard/"+*/ nomeBanco, null, versaoBanco);
		this.scriptsSQLCreate = scriptsSQLCreate;
		this.scriptsSQLDrop = scriptsSQLDrop;
	}

	/**
	 * Cramado quando se deseja criar o banco de dados.
	 * @param SQLiteDatabase db - Referencia do local onde o banco de dados ser� criado.
	 */
	@Override
	public void onCreate(SQLiteDatabase db) {
		Log.i(CONTEXTO_LOGICO, "Criando todo o Banco de Dados.");

		// Executando cada script de criação.
		for(String sqlCreate : scriptsSQLCreate){
			Log.i(CONTEXTO_LOGICO, "Executando... -------> " + sqlCreate);
			
			// Criando as tabelas executando a sql referente.
			db.execSQL("PRAGMA encoding = UTF16;");
			db.execSQL(sqlCreate);
		}
	}

	/**
	 * Cramado quando se deseja criar o banco de dados.
	 * @param SQLiteDatabase db - Referencia do local onde o banco de dados ser� criado.
	 * @param int oldVersion - Numero da vers�o que esta em produ��o.
	 * @param int newVersion - Numero da nova vers�o. Esta tem que ser alterada sempre que alteramos a estrutuda do BD.
	 */
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		Log.w(CONTEXTO_LOGICO, "Atualizando a vers�o do BD de " + oldVersion + " para " + newVersion +". <<TODOS OS REGISTROS SERÃO APAGADOS>>");

		// Executando cada script de cria��o.
		for(String sqlDrop : scriptsSQLDrop){
			Log.i(CONTEXTO_LOGICO, "Executando... -------> " + sqlDrop);

			// Removendo as tabelas executando a sql referente.
			db.execSQL(sqlDrop);			
		}

		// Criando todo o banco novamente com base na nova vers�o dos Scripts de cria��o.
		onCreate(db);
	}	
}