package br.com.gv8.yeschamix.activity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnKeyListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Spinner;
import br.com.gv8.yeschamix.activity.adapter.ClassificacaoAdapter;
import br.com.gv8.yeschamix.activity.adapter.GrupoProdutoAdapter;
import br.com.gv8.yeschamix.activity.adapter.LazyAdapter;
import br.com.gv8.yeschamix.classificacao.model.persistence.ClassificacaoVO;
import br.com.gv8.yeschamix.facade.YesChamixFacade;
import br.com.gv8.yeschamix.familia.model.persistence.FamiliaVO;
import br.com.gv8.yeschamix.grupoproduto.model.persistence.GrupoProdutoVO;
import br.com.gv8.yeschamix.produto.model.persistence.ProdutoVO;
import br.com.gv8.yeschamix.util.Utilidades;

public class Principal extends PrincipalAtividade {

	private YesChamixFacade facade;
	private Spinner listaClassificacao;
	private Spinner listaGrupoProduto;
	private CheckBox oportunidade;
	private CheckBox destaque;
	public static GrupoProdutoVO grupoProduto;
	public static ClassificacaoVO classificacao;
	private GridView list;
	private LazyAdapter adapter;
	public static List< ProdutoVO > listaProdutos;
	public static String produtoBusca;
	public static FamiliaVO familia;
	private AlertDialog.Builder adb;
	public static int continuar = 0;
	public static int position =0;
	private boolean initializedView = false;
	public static List< ProdutoVO > produtosRecarregados;
	private static ProgressDialog dialog;

	@Override
	public void onCreate( Bundle savedInstanceState ) {
		super.onCreate( savedInstanceState );
		setContentView( R.layout.principal );
		list = (GridView) findViewById( R.id.lista3 );
		listaGrupoProduto = (Spinner) findViewById( R.id.grupoProdutos );
		listaClassificacao = (Spinner) findViewById( R.id.classificacao );
		oportunidade = (CheckBox) findViewById( R.id.checkboxOportunidade );
		destaque = (CheckBox) findViewById( R.id.checkboxDestaque );
	}

	@Override
	public void onResume() {
		super.onResume();
		facade = new YesChamixFacade( getApplicationContext() );
		this.adb = new AlertDialog.Builder( this );
		try {
			this.acertarGrid();
			oportunidade.setChecked( false );
			destaque.setChecked( false );
			preencherClassificacao();
			listaClassificacao.setOnItemSelectedListener( new OnItemSelectedListener() {
				@Override
				public void onItemSelected( AdapterView< ? > parent , View view , int pos , long id ) {
					if ( pos != 0 ) {
						classificacao = (ClassificacaoVO) parent.getItemAtPosition( pos );
						dialog = ProgressDialog.show( Principal.this , "Yes Turbo" , "carregando..." );
						new LoadItemsTask().execute( null , null , null );
					} else {
						classificacao = new ClassificacaoVO();
						classificacao.setId( "0" );
						classificacao.setDescricao( "Classificação" );
					}
				}

				@Override
				public void onNothingSelected( AdapterView< ? > arg0 ) {
				}
			} );

			listaGrupoProduto.setOnItemSelectedListener( new OnItemSelectedListener() {
				@Override
				public void onItemSelected( AdapterView< ? > parent , View view , int pos , long id ) {
					if ( pos != 0 ) {
						grupoProduto = (GrupoProdutoVO) parent.getItemAtPosition( pos );
						preencherClassificacao();
						dialog = ProgressDialog.show( Principal.this , "Yes Turbo" , "carregando..." );
						new LoadItemsTask().execute( null , null , null );
					} else if ( initializedView == false ) {
						initializedView = true;
						grupoProduto = new GrupoProdutoVO();
						grupoProduto.setId( "0" );
						grupoProduto.setDescricao( "Grupo de Produtos" );
					} else {
						grupoProduto = new GrupoProdutoVO();
						grupoProduto.setId( "0" );
						grupoProduto.setDescricao( "Grupo de Produtos" );
						preencherGrupoProduto();
						preencherClassificacao();
						acertarGrid();
					}
				}

				@Override
				public void onNothingSelected( AdapterView< ? > arg0 ) {
				}
			} );
			final EditText editText = (EditText) findViewById( R.id.produtoBusca );
			editText.setOnKeyListener( new OnKeyListener() {
				public boolean onKey( View v , int keyCode , KeyEvent event ) {
					if ( event.getAction() == KeyEvent.ACTION_DOWN ) {
						switch ( keyCode ) {
							case KeyEvent.KEYCODE_DPAD_CENTER:
							case KeyEvent.KEYCODE_ENTER:
								produtoBusca = editText.getText().toString();
								getMyPrefs().setValue( "produtoBusca", produtoBusca );
								getMyPrefs().save();
								familia = null;
								if ( Utilidades.isNullOrBlank( produtoBusca ) ) {
									AlertDialog ad = Principal.this.adb.create();
									ad.setMessage( "Digite código ou nome do produto." );
									ad.show();
									return true;
								}
								editText.clearFocus();
								editText.setText( "" );
								InputMethodManager imm = (InputMethodManager) getSystemService( INPUT_METHOD_SERVICE );
								imm.hideSoftInputFromWindow( editText.getWindowToken() , 0 );
								gridFamiliaBuscaNomeCodigo();
								return true;
							default:
								break;
						}
					}
					return false;
				}
			} );
			final Button button = (Button) findViewById( R.id.buscarProduto );
			button.setOnClickListener( new OnClickListener() {
				public void onClick( View v ) {
					produtoBusca = editText.getText().toString();
					getMyPrefs().setValue( "produtoBusca", produtoBusca );
					getMyPrefs().save();
					familia = null;
					if ( Utilidades.isNullOrBlank( produtoBusca ) ) {
						AlertDialog ad = Principal.this.adb.create();
						ad.setMessage( "Digite código ou nome do produto." );
						ad.show();
						return;
					}
					editText.clearFocus();
					editText.setText( "" );
					InputMethodManager imm = (InputMethodManager) getSystemService( INPUT_METHOD_SERVICE );
					imm.hideSoftInputFromWindow( button.getApplicationWindowToken() , 0 );
					gridFamiliaBuscaNomeCodigo();
				}
			} );
			ImageView retornar = (ImageView) findViewById( R.id.imageLogo );
			retornar.setOnClickListener( new OnClickListener() {
				public void onClick( View v ) {
					System.gc();
					Runtime.getRuntime().gc();
					finish();
				}
			} );
			Button button2 = (Button) findViewById( R.id.imageBotaoFamilia );
			button2.setOnClickListener( new OnClickListener() {
				public void onClick( View v ) {
					System.gc();
					Runtime.getRuntime().gc();
					finish();
				}
			} );

			Button button3 = (Button) findViewById( R.id.imageBotaoProduto );
			button3.setOnClickListener( new OnClickListener() {
				public void onClick( View v ) {
					System.gc();
					Runtime.getRuntime().gc();
				}
			} );
			
		} catch( Exception e ) {
			grupoProduto = null;
			classificacao = null;
			familia = null;
			initializedView = false;
			Intent mIntent = new Intent( this , FamiliasActivity.class );
			startActivity( mIntent );
			System.gc();
			Runtime.getRuntime().gc();
		}
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		System.gc();
		Runtime.getRuntime().gc();
		
	}

	private void preencherClassificacao() {
		ClassificacaoAdapter adapterSub = new ClassificacaoAdapter( this , retornaClassificacao() );
		adapterSub.notifyDataSetChanged();
		listaClassificacao.setAdapter( adapterSub );
	}

	private void preencherGrupoProduto() {
		GrupoProdutoAdapter adapter = new GrupoProdutoAdapter( this , retornaGrupoProduto() );
		listaGrupoProduto.setAdapter( adapter );
		adapter.notifyDataSetChanged();
		preencherClassificacao();
	}

	public void acertarGrid() {
		String produtoBusca = getMyPrefs().getValue( "produtoBusca", null );
		if ( continuar == 0 ) {
			if ( !Utilidades.isNullOrBlank( familia ) ) {
				gridFamilia();
			} else if ( !Utilidades.isNullOrBlank( produtoBusca ) ) {
				gridFamiliaBuscaNomeCodigo();
			} else {
				gridProdutoRandom();
			}
			preencherGrupoProduto();
		} else {
			adapter = new LazyAdapter( this , this , produtosRecarregados );
			produtosRecarregados = new ArrayList< ProdutoVO >();
			adapter.notifyDataSetChanged();
			continuar = 0;
			preencherGrupoProduto();
			preencherClassificacao();
			list.setAdapter( adapter );
			list.setSelection(position);
		}

	}

	private void gridFamilia() {
//		listaProdutos = retornaProdutoFamilia();	
//		adapter = new LazyAdapter( this , this , listaProdutos );
//		adapter.notifyDataSetChanged();
//		list.setAdapter( adapter );
		Processo processo = new Processo( this );
		processo.execute( 0 );
	}

	private void gridFamiliaBuscaNomeCodigo() {
//		listaProdutos = retornaProdutoFamiliaBuscaNomeCodigo();
//		adapter = new LazyAdapter( this , this , listaProdutos );
//		adapter.notifyDataSetChanged();
//		list.setAdapter( adapter );
		Processo processo = new Processo( this );
		processo.execute( 1 );
	}

	private void gridProdutoRandom() {
//		listaProdutos = retornarProdutoRandom();
//		adapter = new LazyAdapter( this , this , listaProdutos );
//		adapter.notifyDataSetChanged();
//		list.setAdapter( adapter );
		Processo processo = new Processo( this );
		processo.execute( 2 );
	}

	private List< ProdutoVO > retornaProdutoFamilia() {

		List< ProdutoVO > lista = new ArrayList< ProdutoVO >();
		lista = facade.consultarProdutoPorFamilia( familia );

		return lista;
	}

	private List< ProdutoVO > retornaProdutoFamiliaBuscaNomeCodigo() {

		List< ProdutoVO > lista = new ArrayList< ProdutoVO >();
		String produtoBusca = getMyPrefs().getValue( "produtoBusca", null );
		if ( produtoBusca != null ) {
			lista = facade.consultarProdutoPorNomeOuCodigo( produtoBusca );
		}

		return lista;
	}

	private List< ProdutoVO > retornarProdutoRandom() {

		List< ProdutoVO > lista = new ArrayList< ProdutoVO >();
		lista = facade.consultarProdutosRandom();

		return lista;
	}

	private List< GrupoProdutoVO > retornaGrupoProduto() {
		List< GrupoProdutoVO > lista = new ArrayList< GrupoProdutoVO >();
		try {
			String produtoBusca = getMyPrefs().getValue( "produtoBusca", null );
			
			if ( !Utilidades.isNullOrBlank( familia ) ) {
				lista = facade.consultarGrupoProdutoFamilia( familia );
			} else if ( !Utilidades.isNullOrBlank( produtoBusca ) ) {
				HashMap< String , GrupoProdutoVO > map = new HashMap< String , GrupoProdutoVO >();
				for( ProdutoVO produto : listaProdutos ) {
					if ( !map.containsKey( produto.getGrupoProduto().getId() ) ) {
						if ( produto.getGrupoProduto().getFamilia().getId().equals( grupoProduto.getFamilia().getId() ) ) {
							map.put( produto.getGrupoProduto().getId() , produto.getGrupoProduto() );
						}
					} else {
						if ( !produto.getGrupoProduto().getFamilia().getId().equals( grupoProduto.getFamilia().getId() ) ) {
							map.put( produto.getGrupoProduto().getId() , produto.getGrupoProduto() );
						}
					}
				}
				produtoBusca = null;
				
				lista.addAll( facade.inicializaGrupoProduto() );
				lista.addAll( map.values() );
			} else {
				lista = facade.consultarTudoGrupoProduto();
			}
		} catch( Exception e ) {
			lista = new ArrayList< GrupoProdutoVO >();
		}
		return lista;
	}

	@Override
	protected void onPause() {
		Utilidades.nullViewDrawablesRecursive( list );
		list = null;
		System.gc();
		Runtime.getRuntime().gc();
		super.onPause();
	}

	private List< ClassificacaoVO > retornaClassificacao() {
		List< ClassificacaoVO > lista = new ArrayList< ClassificacaoVO >();
		if ( Utilidades.isNullOrBlank( grupoProduto ) || grupoProduto.getDescricao().equals( "Grupo de Produtos" ) || grupoProduto.getId().equals( "0" ) ) {
			lista = facade.inicializaClassificacao();
		} else {
			if ( Utilidades.isNullOrBlank( familia ) ) {
				try {

					lista.addAll( facade.consultarClassificacaoPorGrupoProdutoFamilia( grupoProduto , grupoProduto.getFamilia() ) );
				} catch( Exception e ) {
					lista = new ArrayList< ClassificacaoVO >();
				}
			} else {
				try {

					lista.addAll( facade.consultarClassificacaoPorGrupoProdutoFamilia( grupoProduto , familia ) );
				} catch( Exception e ) {
					lista = new ArrayList< ClassificacaoVO >();
				}
			}
		}
		return lista;
	}

	public void onCheckboxOportunidadeClicked( View v ) {
		dialog = ProgressDialog.show( Principal.this , "Yes Turbo" , "carregando..." );
		new LoadItemsTask().execute( null , null , null );
	}

	public void onCheckboxDestaqueClicked( View v ) {
		dialog = ProgressDialog.show( Principal.this , "Yes Turbo" , "carregando..." );
		new LoadItemsTask().execute( null , null , null );
	}
	

	private class LoadItemsTask extends AsyncTask< Void , Void , Void > {
		List< ProdutoVO > listaProd = new ArrayList< ProdutoVO >();
		String oport = oportunidade.isChecked() ? "1" : "0";
		String lancamento = destaque.isChecked() ? "1" : "0";
		String idGrupoProduto = null;
		String idClassificacao = null;
		String idFamilia = null;

		@Override
		protected Void doInBackground( Void... params ) {

			if ( !Utilidades.isNullOrBlank( familia ) ) {
				idFamilia = familia.getId();
			} else {
				if ( !Utilidades.isNullOrBlank( idGrupoProduto ) ) {
					idFamilia = grupoProduto.getFamilia().getId();
				}
			}
			if ( !Utilidades.isNullOrBlank( grupoProduto ) ) {
				if ( !grupoProduto.getDescricao().equals( "Grupo de Produtos" ) && !grupoProduto.getId().equals( "0" ) ) {
					idGrupoProduto = grupoProduto.getId();
				}
			}
			if ( !Utilidades.isNullOrBlank( classificacao ) ) {
				if ( !classificacao.getDescricao().equals( "Classificação" ) && !classificacao.getId().equals( "0" ) ) {
					idClassificacao = classificacao.getId();
				}
			}

			try {
				
				if(Utilidades.isNullOrBlank(idFamilia ) && Utilidades.isNullOrBlank(idGrupoProduto ) && Utilidades.isNullOrBlank(idClassificacao ) && "0".equals(lancamento ) && "0".equals( oport)){
					listaProd = facade.consultarProdutosRandom();
				}else{
					listaProd = facade.consultarProduto( idFamilia , idGrupoProduto , idClassificacao , lancamento , oport);
				}
			} catch( Exception e ) {
				listaProd = new ArrayList< ProdutoVO >();
			}
			return null;
		}

		@Override
		protected void onPostExecute( Void result ) {
			adapter = new LazyAdapter( Principal.this , Principal.this , listaProd );
			adapter.notifyDataSetChanged();
			list.setAdapter( adapter );
			dialog.dismiss();
		}
	}

	public class Processo extends AsyncTask< Integer , String , Integer > {

		private ProgressDialog progress;
		private Context context;
		Intent intent;

		public Processo( Context context ) {
			this.context = context;
		}

		@Override
		protected void onPreExecute() {
			//Cria novo um ProgressDialogo e exibe
			progress = new ProgressDialog( context );
			progress.setMessage( "Aguarde..." );
			progress.show();
		}

		@Override
		protected Integer doInBackground( Integer... paramss ) {
			for( int i = 0; i < paramss.length; i++ ) {
				try {
					//Simula processo...
					switch ( paramss[i] ) {
						case 0:
							listaProdutos = retornaProdutoFamilia();
							break;

						case 1:
							listaProdutos = retornaProdutoFamiliaBuscaNomeCodigo();
							break;
							
						case 2:
							listaProdutos = retornarProdutoRandom();
							break;
							
					}

					//Atualiza a interface através do onProgressUpdate
					publishProgress( "..." );
				} catch( Exception e ) {
					e.printStackTrace();
				}
			}
			return 1;
		}

		@Override
		protected void onPostExecute( Integer result ) {
			//Cancela progressDialogo
			progress.dismiss();	
			
			adapter = new LazyAdapter( context , Principal.this , listaProdutos );
			adapter.notifyDataSetChanged();
			list = (GridView) findViewById( R.id.lista3 );
			list.setAdapter( adapter );
		}

		@Override
		protected void onProgressUpdate( String... values ) {
			//Atualiza mensagem
			progress.setMessage( values[0] );
		}
	}

	@Override
	public void onBackPressed() {
			startActivity( new Intent(this,FamiliasActivity.class) );
	}
}