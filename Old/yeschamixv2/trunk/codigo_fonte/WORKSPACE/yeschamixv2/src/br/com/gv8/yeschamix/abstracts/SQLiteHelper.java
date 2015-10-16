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
	 * Aqui é chamado o construtor da super Classe SQLiteOpenHelper passando os dados
	 * do banco para que ele gerencie a versão
	 * 
	 * @param Context context - Contexto da aplicação
	 * @param String nomeBanco - Nome do banco de dados a ser gerado.
	 * @param int versaoBanco = Versão em que o banco se encontra. (Se for diferente significa que a estrutura foi alterada
	 * e por isso deve-se apagar tudo e criar novamente.)
	 * @param String[] scriptsSQLCreate - Todos os scripts SQL de criaï¿½ï¿½o(CREATE) de todas as tabelas do sistema.
	 * @param String[] scriptsSQLDrop - Todos os scripts SQL de deleï¿½ï¿½o(DROP) de todas as tabelas do sistema.
	 * 
	 * Colocar:
	 * "/mnt/sdcard/"+
	 * 
	 * antes de nomeBanco para criar o bd no cartÃ£o de memÃ³ria
	 */
	SQLiteHelper(Context context, String nomeBanco, int versaoBanco, String[] scriptsSQLCreate, String[] scriptsSQLDrop)  {
		super(context, /*"/mnt/sdcard/"+*/ nomeBanco, null, versaoBanco);
		this.scriptsSQLCreate = scriptsSQLCreate;
		this.scriptsSQLDrop = scriptsSQLDrop;
	}

	/**
	 * Cramado quando se deseja criar o banco de dados.
	 * @param SQLiteDatabase db - Referencia do local onde o banco de dados serï¿½ criado.
	 */
	@Override
	public void onCreate(SQLiteDatabase db) {
		Log.i(CONTEXTO_LOGICO, "Criando todo o Banco de Dados.");

		// Executando cada script de criaÃ§Ã£o.
		for(String sqlCreate : scriptsSQLCreate){
			Log.i(CONTEXTO_LOGICO, "Executando... -------> " + sqlCreate);
			
			// Criando as tabelas executando a sql referente.
			db.execSQL("PRAGMA encoding = UTF16;");
			db.execSQL(sqlCreate);
		}
	}

	/**
	 * Cramado quando se deseja criar o banco de dados.
	 * @param SQLiteDatabase db - Referencia do local onde o banco de dados serï¿½ criado.
	 * @param int oldVersion - Numero da versï¿½o que esta em produï¿½ï¿½o.
	 * @param int newVersion - Numero da nova versï¿½o. Esta tem que ser alterada sempre que alteramos a estrutuda do BD.
	 */
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		Log.w(CONTEXTO_LOGICO, "Atualizando a versão do BD de " + oldVersion + " para " + newVersion +". <<TODOS OS REGISTROS SERÃƒO APAGADOS>>");

		// Executando cada script de criaï¿½ï¿½o.
		for(String sqlDrop : scriptsSQLDrop){
			Log.i(CONTEXTO_LOGICO, "Executando... -------> " + sqlDrop);

			// Removendo as tabelas executando a sql referente.
			db.execSQL(sqlDrop);			
		}

		// Criando todo o banco novamente com base na nova versï¿½o dos Scripts de criaï¿½ï¿½o.
		onCreate(db);
	}	
}