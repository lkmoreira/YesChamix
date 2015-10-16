package br.com.gv8.yeschamix.activity;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnKeyListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import br.com.gv8.yeschamix.activity.adapter.FolderAdapter;
import br.com.gv8.yeschamix.facade.YesChamixFacade;
import br.com.gv8.yeschamix.familia.model.persistence.FamiliaVO;
import br.com.gv8.yeschamix.produto.model.persistence.ProdutoVO;
import br.com.gv8.yeschamix.util.GerarTxtLog;
import br.com.gv8.yeschamix.util.Utilidades;

public class FamiliasActivity extends PrincipalAtividade{

	private YesChamixFacade facade = null;
	private Spinner folders= null;
	private List< FamiliaVO > familias=null;
	private GridView gridView=null;
	private AlertDialog.Builder adb=null;
	ProgressDialog progressDialog=null;
	

	@Override
	public void onCreate( Bundle savedInstanceState ) {
		super.onCreate( savedInstanceState );
		facade = new YesChamixFacade( getApplicationContext() );
		setContentView( R.layout.familias );
		folders = (Spinner) findViewById( R.id.folders );
		Principal.continuar = 0;
		System.gc();
		Runtime.getRuntime().gc();

	}

	@Override
	public void onResume() {
		if ( Utilidades.isNullOrBlank( getMyPrefs().getUsuarioLogado() ) ) {
			startActivity( new Intent( this, LoginActivity.class ) );
		}

		try {
			Principal.classificacao = null;
			Principal.grupoProduto = null;
			this.familias = retornaFamilias();
			this.adb = new AlertDialog.Builder( this );
			gridView = (GridView) findViewById( R.id.gridview );
			gridView.setAdapter( new ImageAdapter( this ) );

			gridView.setOnItemClickListener( new OnItemClickListener(){
				@SuppressWarnings( "rawtypes" )
				public void onItemClick( AdapterView parent, View v, int position, long id ) {
					Principal.familia = familias.get( position );
					getMyPrefs().setValue( "produtoBusca", null );
					getMyPrefs().save();
					startActivity( new Intent( getApplication(), Principal.class ) );
					System.gc();
					Runtime.getRuntime().gc();
				}
			} );
			
			if ( temInternet() ) {
				if ( getMyPrefs().getPrimeiraVez() ) {
	
					Boolean primeiraVez = false;
					getMyPrefs().setPrimeiraVez( primeiraVez );
					getMyPrefs().save();
					dialogoSincronizar( "Deseja sincronizar os dados ?" );
					
				}
			}
			
			Button button = (Button) findViewById( R.id.buscarProdutoFamilia );
			final EditText editText = (EditText) findViewById( R.id.produtoBuscaFamilia );
			editText.setOnKeyListener( new OnKeyListener(){
				public boolean onKey( View v, int keyCode, KeyEvent event ) {
					if ( event.getAction() == KeyEvent.ACTION_DOWN ) {
						switch ( keyCode ) {
							case KeyEvent.KEYCODE_DPAD_CENTER:
							case KeyEvent.KEYCODE_ENTER:
								buscarProdutos( editText.getText().toString().toUpperCase() );
								return true;
							default:
								break;
						}
					}
					return false;
				}
			} );
			button.setOnClickListener( new OnClickListener(){
				public void onClick( View v ) {
					buscarProdutos( editText.getText().toString().toUpperCase() );
				}
			} );
			Principal.listaProdutos = new ArrayList< ProdutoVO >();
			getMyPrefs().setValue( "produtoBusca", null );
			getMyPrefs().save();
			Button button2 = (Button) findViewById( R.id.imageBotaoFamilia );
			button2.setOnClickListener( new OnClickListener(){
				public void onClick( View v ) {
					System.gc();
					Runtime.getRuntime().gc();
				}
			} );

			ImageView retornar = (ImageView) findViewById( R.id.imageLogo );
			retornar.setOnClickListener( new OnClickListener(){
				public void onClick( View v ) {
					System.gc();
					Runtime.getRuntime().gc();
					
				}
			} );

			Button button3 = (Button) findViewById( R.id.imageBotaoProduto );
			button3.setOnClickListener( new OnClickListener(){
				public void onClick( View v ) {
					Principal.listaProdutos = new ArrayList< ProdutoVO >();
					getMyPrefs().setValue( "produtoBusca", null );
					getMyPrefs().save();
					Principal.familia = null;
					Intent intent = new Intent( getApplicationContext(), Principal.class );
					startActivity( intent );
					System.gc();
					Runtime.getRuntime().gc();
				}
			} );
		} catch ( Exception e ) {
			Intent mIntent = new Intent( this, FamiliasActivity.class );
			startActivity( mIntent );
			System.gc();
			Runtime.getRuntime().gc();

			try {
				StringWriter sw = new StringWriter();
				PrintWriter pw = new PrintWriter( sw );
				e.printStackTrace( pw );
				pw.close();
				sw.close();
				GerarTxtLog.gerarArquivoTexto( Utilidades.getDataHoraFormatada( new Date() ) + ":\t 3 EXCEPTION: " + sw.getBuffer().toString(), caminhoArquivoLog, nomeArquivoLog );
			} catch ( Exception ex ) {
			}
			e.printStackTrace();
		}

		preencherFolder();
		folders.setOnItemSelectedListener( new OnItemSelectedListener(){
			@Override
			public void onItemSelected( AdapterView< ? > parent, View view, int pos, long id ) {
				String tipoFolder;
				if ( pos != 0 ) {
					tipoFolder = (String) parent.getItemAtPosition( pos );
					getMyPrefs().setValue( "tipoFolder", tipoFolder );
					getMyPrefs().save();
					startActivity( new Intent( FamiliasActivity.this, InicialActivity.class ) );

				}
			}

			@Override
			public void onNothingSelected( AdapterView< ? > arg0 ) {}
		} );

		super.onResume();
	}

	public void preencherFolder() {

		FolderAdapter adapterSub = new FolderAdapter( FamiliasActivity.this, Arrays.asList( "Selecione", "Lançamentos", "Promoções", "Institucionais", "Todos" ) );
		folders.setAdapter( adapterSub );
		adapterSub.notifyDataSetChanged();
	}

	public class ImageAdapter extends BaseAdapter{
		private Context context;

		public ImageAdapter( Context c ){
			context = c;
		}

		// ---returns the number of images---
		public int getCount() {
			return familias.size();
		}

		// ---returns the ID of an item---
		public Object getItem( int position ) {
			return position;
		}

		public long getItemId( int position ) {
			return position;
		}

		// ---returns an ImageView view---
		public View getView( int position, View convertView, ViewGroup parent ) {
			if ( convertView == null ) {
				LayoutInflater li = getLayoutInflater();
				convertView = li.inflate( R.layout.item_grid, null );
			}
			TextView tv = (TextView) convertView.findViewById( R.id.grid_item_text );
			tv.setText( familias.get( position ).getDescricao().trim() );
			ImageView imageView = (ImageView) convertView.findViewById( R.id.grid_item_image );
			try {
				Bitmap bitmap = Utilidades.getBitmap( context, familias.get( position ).getNomeArquivo().trim(), true, 3 );
				imageView.setImageBitmap( bitmap );
			} catch ( Exception e ) {
				System.gc();
				Runtime.getRuntime().gc();
				imageView.setImageResource( R.drawable.indisponivel );

				try {
					StringWriter sw = new StringWriter();
					PrintWriter pw = new PrintWriter( sw );
					e.printStackTrace( pw );
					pw.close();
					sw.close();
					GerarTxtLog.gerarArquivoTexto( Utilidades.getDataHoraFormatada( new Date() ) + ":\t 3 EXCEPTION: " + sw.getBuffer().toString(), caminhoArquivoLog, nomeArquivoLog );
				} catch ( Exception ex ) {
				}
				e.printStackTrace();
			}
			return convertView;
		}
	}

	private List< FamiliaVO > retornaFamilias() {
		List< FamiliaVO > lista = new ArrayList< FamiliaVO >();
		try {
			lista = facade.consultarTudoFamilia();
		} catch ( Exception e ) {
			lista = new ArrayList< FamiliaVO >();
		}
		return lista;
	}

	@Override
	public void onBackPressed() {

		AlertDialog.Builder adb = new AlertDialog.Builder( FamiliasActivity.this );
		adb.setTitle( "Deseja realmente sair?" );
		adb.setMessage( "Confirma saída" );
		adb.setNegativeButton( "Não", null );
		adb.setPositiveButton( "Sim", new AlertDialog.OnClickListener(){
			public void onClick( DialogInterface dialog, int which ) {
				Intent intent = new Intent( Intent.ACTION_MAIN );
				intent.addCategory( Intent.CATEGORY_HOME );
				intent.setFlags( Intent.FLAG_ACTIVITY_NEW_TASK );
				startActivity( intent );
				getMyPrefs().setUsuarioLogado( "" );
				getMyPrefs().isPrecoSelecionado( false );
				getMyPrefs().save();
				System.gc();
				Runtime.getRuntime().gc();
				

			}
		} );
		adb.show();
	}

	private void buscarProdutos( String produtoBusca ) {
		if ( Utilidades.isNullOrBlank( produtoBusca ) ) {
			AlertDialog ad = FamiliasActivity.this.adb.create();
			ad.setMessage( "Digite código ou nome do produto." );
			ad.show();
			return;
		}
		Principal.familia = null;
		getMyPrefs().setValue( "produtoBusca", produtoBusca );
		getMyPrefs().save();
		startActivity( new Intent( getApplicationContext(), Principal.class ) );
		System.gc();
		Runtime.getRuntime().gc();
	}

	@Override
	protected void onPause() {
		
		progressDialog = ProgressDialog.show( this, "", "Aguarde..." );
		Utilidades.nullViewDrawablesRecursive( gridView );
		gridView = null;
		System.gc();
		Runtime.getRuntime().gc();
		progressDialog.cancel();
		super.onPause();
		this.onDestroy();
	}

	@Override
	protected void onDestroy() {
		System.gc();
		Runtime.getRuntime().gc();

		super.onDestroy();
	}

}
